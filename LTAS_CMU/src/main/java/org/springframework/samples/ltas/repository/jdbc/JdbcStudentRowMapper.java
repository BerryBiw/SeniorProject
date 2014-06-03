
package org.springframework.samples.ltas.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.joda.time.DateTime;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

class JdbcStudentRowMapper implements ParameterizedRowMapper<JdbcStudent> {

    @Override
    public JdbcStudent mapRow(ResultSet rs, int rownum) throws SQLException {
        JdbcStudent student = new JdbcStudent();
        student.setId(rs.getInt("id"));
        student.setName(rs.getString("name"));
        student.setStudentId(rs.getString("student_id"));
        student.setFacultyId(rs.getInt("faculty_id"));
        student.setActivityId(rs.getInt("activity_id"));
        return student;
    }
}
