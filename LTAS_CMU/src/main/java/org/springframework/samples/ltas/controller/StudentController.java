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
package org.springframework.samples.ltas.controller;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.ltas.entity.Activity;
import org.springframework.samples.ltas.entity.Faculty;
import org.springframework.samples.ltas.entity.Student;
import org.springframework.samples.ltas.service.SystemService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
@SessionAttributes("student")
public class StudentController {

    private final SystemService systemService;


    @Autowired
    public StudentController(SystemService systemService) {
        this.systemService = systemService;
    }

    @ModelAttribute("faculties")
    public Collection<Faculty> populateFaculties() {
        return this.systemService.findFaculties();
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping(value = "/activities/{activityId}/students/new", method = RequestMethod.GET)
    public String initCreationForm(@PathVariable("activityId") int activityId, Map<String, Object> model) {
        Activity activity = this.systemService.findActivityById(activityId);
        Student student = new Student();
        activity.addStudent(student);
        model.put("student", student);
        return "students/createOrUpdateStudentForm";
    }

    @RequestMapping(value = "/activities/{activityId}/students/new", method = RequestMethod.POST)
    public String processCreationForm(@ModelAttribute("student") Student student, BindingResult result, SessionStatus status) {
        new StudentValidator().validate(student, result);
        if (result.hasErrors()) {
            return "students/createOrUpdateStudentForm";
        } else {
            this.systemService.saveStudent(student);
            status.setComplete();
            return "redirect:/activities/{activityId}";
        }
    }

    @RequestMapping(value = "/activities/*/students/{studentId}/edit", method = RequestMethod.GET)
    public String initUpdateForm(@PathVariable("studentId") int studentId, Map<String, Object> model) {
        Student student = this.systemService.findStudentById(studentId);
        model.put("student", student);
        return "students/createOrUpdateStudentForm";
    }

    @RequestMapping(value = "/activities/{activityId}/students/{studentId}/edit", method = {RequestMethod.PUT, RequestMethod.POST})
    public String processUpdateForm(@ModelAttribute("student") Student student, BindingResult result, SessionStatus status) {
        // we're not using @Valid annotation here because it is easier to define such validation rule in Java
        new StudentValidator().validate(student, result);
        if (result.hasErrors()) {
            return "students/createOrUpdateStudentForm";
        } else {
            this.systemService.saveStudent(student);
            status.setComplete();
            return "redirect:/activities/{activityId}";
        }
    }

}
