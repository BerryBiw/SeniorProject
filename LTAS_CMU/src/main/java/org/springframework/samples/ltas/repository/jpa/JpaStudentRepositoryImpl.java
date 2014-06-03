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
package org.springframework.samples.ltas.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.samples.ltas.entity.Faculty;
import org.springframework.samples.ltas.entity.Student;
import org.springframework.samples.ltas.repository.StudentRepository;
import org.springframework.stereotype.Repository;


@Repository
public class JpaStudentRepositoryImpl implements StudentRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    public List<Faculty> findFaculties() {
        return this.em.createQuery("SELECT fac FROM Faculty fac ORDER BY fac.name").getResultList();
    }

    @Override
    public Student findById(int id) {
        return this.em.find(Student.class, id);
    }

    @Override
    public void save(Student student) {
    	if (student.getId() == null) {
    		this.em.persist(student);
    	}
    	else {
    		this.em.merge(student);
    	}
    }

}
