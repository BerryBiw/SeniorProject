
package org.springframework.samples.ltas.repository.jdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.samples.ltas.entity.*;
import org.springframework.samples.ltas.repository.ActivityRepository;
import org.springframework.samples.ltas.repository.StudentRepository;
import org.springframework.samples.ltas.repository.VisitRepository;
import org.springframework.samples.ltas.util.EntityUtils;
import org.springframework.stereotype.Repository;


@Repository
public class JdbcStudentRepositoryImpl implements StudentRepository {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertStudent;

    private ActivityRepository activityRepository;

    private VisitRepository visitRepository;


    @Autowired
    public JdbcStudentRepositoryImpl(DataSource dataSource, ActivityRepository activityRepository, VisitRepository visitRepository) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        this.insertStudent = new SimpleJdbcInsert(dataSource)
                .withTableName("students")
                .usingGeneratedKeyColumns("id");

        this.activityRepository = activityRepository;
        this.visitRepository = visitRepository;
    }

    @Override
    public List<Faculty> findFaculties() throws DataAccessException {
        Map<String, Object> params = new HashMap<String, Object>();
        return this.namedParameterJdbcTemplate.query(
                "SELECT id, name FROM faculties ORDER BY name",
                params,
                ParameterizedBeanPropertyRowMapper.newInstance(Faculty.class));
    }

    @Override
    public Student findById(int id) throws DataAccessException {
        JdbcStudent student;
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("id", id);
            student = this.namedParameterJdbcTemplate.queryForObject(
                    "SELECT id, name,student_id, faculty_id, activity_id FROM students WHERE id=:id",
                    params,
                    new JdbcStudentRowMapper());
        } catch (EmptyResultDataAccessException ex) {
            throw new ObjectRetrievalFailureException(Student.class, new Integer(id));
        }
        Activity activity = this.activityRepository.findById(student.getActivityId());
        activity.addStudent(student);
        student.setFaculty(EntityUtils.getById(findFaculties(), Faculty.class, student.getFacultyId()));

        List<Visit> visits = this.visitRepository.findByStudentId(student.getId());
        for (Visit visit : visits) {
            student.addVisit(visit);
        }
        return student;
    }

    @Override
    public void save(Student student) throws DataAccessException {
        if (student.isNew()) {
            Number newKey = this.insertStudent.executeAndReturnKey(
                    createStudentParameterSource(student));
            student.setId(newKey.intValue());
        } else {
            this.namedParameterJdbcTemplate.update(
                    "UPDATE students SET name=:name,student_id=:student_id, faculty_id=:faculty_id, " +
                            "activity_id=:activity_id WHERE id=:id",
                    createStudentParameterSource(student));
        }
    }

    /**
     * Creates a {@link MapSqlParameterSource} based on data values from the supplied {@link org.springframework.samples.ltas.entity.Student} instance.
     */
    private MapSqlParameterSource createStudentParameterSource(Student student) {
        return new MapSqlParameterSource()
                .addValue("id", student.getId())
                .addValue("name", student.getName())
                .addValue("student_id",student.getStudentId())
                .addValue("faculty_id", student.getFaculty().getId())
                .addValue("activity_id", student.getActivity().getId());
    }



}
