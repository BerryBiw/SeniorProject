
package org.springframework.samples.ltas.repository.jpa;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.cache.annotation.Cacheable;

import org.springframework.samples.ltas.entity.Instructor;
import org.springframework.samples.ltas.repository.InstructorRepository;
import org.springframework.stereotype.Repository;

@Repository
public class JpaInstructorRepositoryImpl implements InstructorRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    @Cacheable(value = "instructors")
    public Collection<Instructor> findAll() {
        return this.em.createQuery("SELECT distinct instructor FROM Instructor instructor left join fetch instructor.faculties ORDER BY instructor.lastName, instructor.firstName,instructor.email,instructor.telephone,instructor.userName,instructor.password").getResultList();
    }

    @SuppressWarnings("unchecked")
    public Collection<Instructor> findByLastName(String lastName) {

        Query query = this.em.createQuery("SELECT DISTINCT instructor FROM Instructor instructor  WHERE instructor.lastName LIKE :lastName");
        query.setParameter("lastName", lastName + "%");
        return query.getResultList();
    }


    @Override
    public Instructor findById(int id) {

        Query query = this.em.createQuery("SELECT instructor FROM Instructor instructor  WHERE instructor.id =:id");
        query.setParameter("id", id);
        return (Instructor) query.getSingleResult();
    }

    @Override
    public void save(Instructor instructor) {
        if (instructor.getId() == null) {
            this.em.persist(instructor);
        }
        else {
            this.em.merge(instructor);
        }

    }


}
