
package org.springframework.samples.ltas.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.samples.ltas.entity.*;
import org.springframework.samples.ltas.repository.InstructorRepository;
import org.springframework.samples.ltas.repository.VisitRepository;
import org.springframework.samples.ltas.util.EntityUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;


@Repository
public class JdbcInstructorRepositoryImpl implements InstructorRepository {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertInstructor;


    @Autowired
    public JdbcInstructorRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Collection<Instructor> findAll() throws DataAccessException {
        List<Instructor> instructors = new ArrayList<Instructor>();
        // Retrieve the list of all instructors.
        instructors.addAll(this.jdbcTemplate.query(
                "SELECT id, first_name, last_name,email,telephone,user_name,password FROM instructors ORDER BY last_name,first_name",
                ParameterizedBeanPropertyRowMapper.newInstance(Instructor.class)));

        final List<Faculty> faculties = this.jdbcTemplate.query(
                "SELECT id, name FROM faculties",
                ParameterizedBeanPropertyRowMapper.newInstance(Faculty.class));

        for (Instructor instructor : instructors) {
            final List<Integer> instructorFacultiesIds = this.jdbcTemplate.query(
                    "SELECT faculty_id FROM instructor_faculties WHERE instructor_id=?",
                    new ParameterizedRowMapper<Integer>() {
                        @Override
                        public Integer mapRow(ResultSet rs, int row) throws SQLException {
                            return Integer.valueOf(rs.getInt(1));
                        }
                    },
                    instructor.getId().intValue());
            for (int facultyId : instructorFacultiesIds) {
                Faculty faculty = EntityUtils.getById(faculties, Faculty.class, facultyId);
                instructor.addFaculty(faculty);
            }
        }
        return instructors;
    }




    @Override
    public Instructor findById(int id) throws DataAccessException {
        Instructor instructor;
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("id", id);
            instructor = this.namedParameterJdbcTemplate.queryForObject(
                    "SELECT id, first_name,last_name,email,telephone,user_name,password FROM instructors WHERE id= :id",
                    params,
                    ParameterizedBeanPropertyRowMapper.newInstance(Instructor.class)
            );
        } catch (EmptyResultDataAccessException ex) {
            throw new ObjectRetrievalFailureException(Instructor.class, id);
        }

        return instructor;
    }








    @Override
    public void save(Instructor instructor) throws DataAccessException {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(instructor);
        if (instructor.isNew()) {
            Number newKey = this.insertInstructor.executeAndReturnKey(parameterSource);
            instructor.setId(newKey.intValue());
        } else {
            this.namedParameterJdbcTemplate.update(
                    "UPDATE instructors SET first_name=:first_name, last_name=:last_name, " +
                            "email=:email, telephone=:telephone,user_name=:user_name,password=:password WHERE id=:id",
                    parameterSource);
        }
    }


    @Override
    public Collection<Instructor> findByLastName(String lastName) throws DataAccessException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("lastName", lastName + "%");
        List<Instructor> instructors = this.namedParameterJdbcTemplate.query(
                "SELECT id, first_name,last_name,email, telephone,user_name,password FROM instructors WHERE lastName like :lastName",
                params,
                ParameterizedBeanPropertyRowMapper.newInstance(Instructor.class)
        );

        return instructors;
    }


}
