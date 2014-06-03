/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.ltas.repository.jdbc;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.samples.ltas.entity.ActType;
import org.springframework.samples.ltas.entity.Activity;
import org.springframework.samples.ltas.entity.Faculty;
import org.springframework.samples.ltas.entity.Visit;
import org.springframework.samples.ltas.repository.ActivityRepository;
import org.springframework.samples.ltas.repository.VisitRepository;
import org.springframework.samples.ltas.util.EntityUtils;
import org.springframework.stereotype.Repository;


@Repository
public class JdbcActivityRepositoryImpl implements ActivityRepository {

    private VisitRepository visitRepository;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertActivity;

    @Autowired
    public JdbcActivityRepositoryImpl(DataSource dataSource, NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                                      VisitRepository visitRepository) {

        this.insertActivity = new SimpleJdbcInsert(dataSource)
                .withTableName("activities")
                .usingGeneratedKeyColumns("id");

        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        this.visitRepository = visitRepository;
    }

    @Override
    public List<ActType> findActTypes() throws DataAccessException {
        Map<String, Object> params = new HashMap<String, Object>();
        return this.namedParameterJdbcTemplate.query(
                "SELECT id, name FROM types ORDER BY name",
                params,
                ParameterizedBeanPropertyRowMapper.newInstance(ActType.class));
    }



    @Override
    public Collection<Activity> findByName(String name) throws DataAccessException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name + "%");
        List<Activity> activities = this.namedParameterJdbcTemplate.query(
                "SELECT id, name,description,number_student, start_date,end_date,type_id FROM activities WHERE name like :name",
                params,
                ParameterizedBeanPropertyRowMapper.newInstance(Activity.class)
        );
        loadActivitiesStudentsAndVisits(activities);
        return activities;
    }


    @Override
    public Activity findById(int id) throws DataAccessException {
        Activity activity;
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("id", id);
            activity = this.namedParameterJdbcTemplate.queryForObject(
                    "SELECT id, name,description,number_student,start_date,end_date,type_id FROM activities WHERE id= :id",
                    params,
                    ParameterizedBeanPropertyRowMapper.newInstance(Activity.class)
            );
        } catch (EmptyResultDataAccessException ex) {
            throw new ObjectRetrievalFailureException(Activity.class, id);
        }
        loadStudentsAndVisits(activity);
        return activity;
    }

    public void loadStudentsAndVisits(final Activity activity) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", activity.getId().intValue());
        final List<JdbcStudent> students = this.namedParameterJdbcTemplate.query(
                "SELECT id, name,student_id, faculty_id, activity_id FROM students WHERE activity_id=:id",
                params,
                new JdbcStudentRowMapper()
        );
        for (JdbcStudent student : students) {
            activity.addStudent(student);
            student.setFaculty(EntityUtils.getById(getFaculties(), Faculty.class, student.getFacultyId()));
            List<Visit> visits = this.visitRepository.findByStudentId(student.getId());
            for (Visit visit : visits) {
                student.addVisit(visit);
            }
        }
    }

    @Override
    public void save(Activity activity) throws DataAccessException {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(activity);
        if (activity.isNew()) {
            Number newKey = this.insertActivity.executeAndReturnKey(parameterSource);
            activity.setId(newKey.intValue());
        } else {
            this.namedParameterJdbcTemplate.update(
                    "UPDATE activities SET name=:name, description=:description, " +
                            "number_student=:number_student, start_date=:start_date,end_date=:end_date,type_id=:type_id WHERE id=:id",
                    parameterSource);
        }
    }

    public Collection<Faculty> getFaculties() throws DataAccessException {
        return this.namedParameterJdbcTemplate.query(
                "SELECT id, name FROM faculties ORDER BY name", new HashMap<String, Object>(),
                ParameterizedBeanPropertyRowMapper.newInstance(Faculty.class));
    }


    private void loadActivitiesStudentsAndVisits(List<Activity> activities) {
        for (Activity activity : activities) {
            loadStudentsAndVisits(activity);
        }
    }


}
