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
import javax.persistence.Query;

import org.springframework.samples.ltas.entity.Visit;
import org.springframework.samples.ltas.repository.VisitRepository;
import org.springframework.stereotype.Repository;


@Repository
public class JpaVisitRepositoryImpl implements VisitRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    public void save(Visit visit) {
    	if (visit.getId() == null) {
    		this.em.persist(visit);     		
    	}
    	else {
    		this.em.merge(visit);    
    	}
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<Visit> findByStudentId(Integer studentId) {
        Query query = this.em.createQuery("SELECT visit FROM Visit v where v.students.id= :id");
        query.setParameter("id", studentId);
        return query.getResultList();
    }

}
