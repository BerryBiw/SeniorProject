
package org.springframework.samples.ltas.service;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.ltas.entity.*;
import org.springframework.samples.ltas.entity.Activity;



public interface SystemService {

    Collection<ActType> findActTypes() throws DataAccessException;
    Collection<Faculty> findFaculties() throws DataAccessException;

    Activity findActivityById(int id) throws DataAccessException;

    Student findStudentById(int id) throws DataAccessException;

    void saveStudent(Student student) throws DataAccessException;

    void saveVisit(Visit visit) throws DataAccessException;

    Collection<Instructor> findInstructors() throws DataAccessException;

    void saveInstructor(Instructor instructor) throws DataAccessException;
    Instructor findInstructorById(int id) throws DataAccessException;
    Collection<Instructor> findInstructorByLastName(String lastName) throws DataAccessException;



    void saveActivity(Activity activity) throws DataAccessException;

    Collection<Activity> findActivityByName(String name) throws DataAccessException;

}
