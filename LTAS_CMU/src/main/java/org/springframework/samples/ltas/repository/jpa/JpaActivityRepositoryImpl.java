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

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.orm.hibernate3.support.OpenSessionInViewFilter;
import org.springframework.samples.ltas.entity.ActType;
import org.springframework.samples.ltas.entity.Activity;
import org.springframework.samples.ltas.repository.ActivityRepository;
import org.springframework.stereotype.Repository;


@Repository
public class JpaActivityRepositoryImpl implements ActivityRepository {

    @PersistenceContext
    private EntityManager em;



    @SuppressWarnings("unchecked")
    public Collection<Activity> findByName(String name) {

        Query query = this.em.createQuery("SELECT DISTINCT activity FROM Activity activity left join fetch activity.students WHERE activity.name LIKE :name");
        query.setParameter("name", name + "%");
        return query.getResultList();
    }

    @Override
    public Activity findById(int id) {

        Query query = this.em.createQuery("SELECT activity FROM Activity activity left join fetch activity.students WHERE activity.id =:id");
        query.setParameter("id", id);
        return (Activity) query.getSingleResult();
    }


    @Override
    public void save(Activity activity) {
    	if (activity.getId() == null) {
    		this.em.persist(activity);
    	}
    	else {
    		this.em.merge(activity);
    	}

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ActType> findActTypes() {
        return this.em.createQuery("SELECT atype FROM ActType atype ORDER BY atype.name").getResultList();
    }


}
