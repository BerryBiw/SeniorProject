
package org.springframework.samples.ltas.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.ltas.entity.BaseEntity;
import org.springframework.samples.ltas.entity.Faculty;
import org.springframework.samples.ltas.entity.Student;
import org.springframework.samples.ltas.entity.ActType;


public interface StudentRepository {


    List<Faculty> findFaculties() throws DataAccessException;


    Student findById(int id) throws DataAccessException;


    void save(Student student) throws DataAccessException;

}
