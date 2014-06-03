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
package org.springframework.samples.ltas.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.ltas.entity.Instructor;


public interface InstructorRepository {


    Collection<Instructor> findAll() throws DataAccessException;

    void save(Instructor instructor) throws DataAccessException;


    Instructor findById(int id) throws DataAccessException;

    Collection<Instructor> findByLastName(String lastName) throws DataAccessException;


}
