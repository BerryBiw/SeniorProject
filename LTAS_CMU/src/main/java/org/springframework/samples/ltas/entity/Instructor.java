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
package org.springframework.samples.ltas.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.core.style.ToStringCreator;


@Entity
@Table(name = "instructors")
public class Instructor extends Person {

    @Column(name = "user_name")
    @NotEmpty
    protected String userName;

    @Column(name = "password")
    @NotEmpty
    protected String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "instructor_faculties", joinColumns = @JoinColumn(name = "instructor_id"),
            inverseJoinColumns = @JoinColumn(name = "faculty_id"))
    private Set<Faculty> faculties;


    protected void setFacultiesInternal(Set<Faculty> faculties) {
        this.faculties = faculties;
    }

    protected Set<Faculty> getFacultiesInternal() {
        if (this.faculties == null) {
            this.faculties = new HashSet<Faculty>();
        }
        return this.faculties;
    }

    @XmlElement
    public List<Faculty> getFaculties() {
        List<Faculty> sortedSpecs = new ArrayList<Faculty>(getFacultiesInternal());
        PropertyComparator.sort(sortedSpecs, new MutableSortDefinition("name", true, true));
        return Collections.unmodifiableList(sortedSpecs);
    }

    public int getNrOfFaculties() {
        return getFacultiesInternal().size();
    }

    public void addFaculty(Faculty faculty) {
        getFacultiesInternal().add(faculty);
    }

    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName){this.userName = userName;}

    public String getPassword(){return this.password;}

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return new ToStringCreator(this)

                .append("id", this.getId())
                .append("new", this.isNew())
                .append("first_name", this.getFirstName())
                .append("last_name", this.getLastName())
                .append("email", this.getEmail())
                .append("telephone", this.getTelephone())
                .append("user_name", this.userName)
                .append("password", this.password)




                .toString();
    }
}
