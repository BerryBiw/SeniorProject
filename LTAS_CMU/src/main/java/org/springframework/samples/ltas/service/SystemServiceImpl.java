
package org.springframework.samples.ltas.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.ltas.entity.*;
import org.springframework.samples.ltas.entity.Activity;
import org.springframework.samples.ltas.repository.ActivityRepository;
import org.springframework.samples.ltas.repository.InstructorRepository;
import org.springframework.samples.ltas.repository.StudentRepository;
import org.springframework.samples.ltas.repository.VisitRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SystemServiceImpl implements SystemService {

    private StudentRepository studentRepository;
    private InstructorRepository instructorRepository;
    private ActivityRepository activityRepository;
    private VisitRepository visitRepository;

    @Autowired
    public SystemServiceImpl(StudentRepository studentRepository, InstructorRepository instructorRepository, ActivityRepository activityRepository, VisitRepository visitRepository) {
        this.studentRepository = studentRepository;
        this.instructorRepository = instructorRepository;
        this.activityRepository = activityRepository;
        this.visitRepository = visitRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Faculty> findFaculties() throws DataAccessException {
        return studentRepository.findFaculties();
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<ActType> findActTypes() throws DataAccessException {
        return activityRepository.findActTypes();
    }

    @Override
    @Transactional(readOnly = true)
    public Activity findActivityById(int id) throws DataAccessException {
        return activityRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Activity> findActivityByName(String name) throws DataAccessException {
        return activityRepository.findByName(name);
    }

    @Override
    @Transactional
    public void saveActivity(Activity activity) throws DataAccessException {
        activityRepository.save(activity);
    }


    @Override
    @Transactional
    public void saveVisit(Visit visit) throws DataAccessException {
        visitRepository.save(visit);
    }


    @Override
    @Transactional(readOnly = true)
    public Student findStudentById(int id) throws DataAccessException {
        return studentRepository.findById(id);
    }

    @Override
    @Transactional
    public void saveStudent(Student student) throws DataAccessException {
        studentRepository.save(student);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "instructors")
    public Collection<Instructor> findInstructors() throws DataAccessException {
        return instructorRepository.findAll();
    }

    @Override
    @Transactional
    public void saveInstructor(Instructor instructor) throws DataAccessException {
        instructorRepository.save(instructor);
    }

    @Override
    @Transactional(readOnly = true)
    public Instructor findInstructorById(int id) throws DataAccessException {
        return instructorRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Instructor> findInstructorByLastName(String lastName) throws DataAccessException {
        return instructorRepository.findByLastName(lastName);
    }


}
