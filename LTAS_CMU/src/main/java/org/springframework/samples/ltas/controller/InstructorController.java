
package org.springframework.samples.ltas.controller;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.ltas.entity.ActType;
import org.springframework.samples.ltas.entity.Activity;
import org.springframework.samples.ltas.entity.Instructor;
import org.springframework.samples.ltas.entity.Instructors;
import org.springframework.samples.ltas.service.SystemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
@SessionAttributes(types = Instructor.class)

public class InstructorController {

    private final SystemService systemService;


    @Autowired
    public InstructorController(SystemService systemService) {
        this.systemService = systemService;
    }

    @RequestMapping("/instructors")
    public String showInstructorList(Map<String, Object> model) {

        Instructors instructors = new Instructors();
        instructors.getInstructorList().addAll(this.systemService.findInstructors());
        model.put("instructors", instructors);
        return "instructors/instructorList";
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }




    @RequestMapping(value = "/instructors/new", method = RequestMethod.GET)
    public String initCreationForm(Map<String, Object> model) {
        Instructor instructor = new Instructor();
        model.put("instructor", instructor);
        return "instructors/createOrUpdateInstructorForm";
    }

    @RequestMapping(value = "/instructors/new", method = RequestMethod.POST)
    public String processCreationForm(@Valid Instructor instructor, BindingResult result, SessionStatus status) {
        if (result.hasErrors()) {
            return "instructors/createOrUpdateInstructorForm";
        } else {
            this.systemService.saveInstructor(instructor);
            status.setComplete();
            return "redirect:/instructors/" + instructor.getId();
        }
    }

    @RequestMapping(value = "/instructors/find", method = RequestMethod.GET)
    public String initFindForm(Map<String, Object> model) {
        model.put("instructor", new Instructor());
//        model.put("formUrl")
        return "instructors/findInstructors";
    }



    @RequestMapping(value = "/instructors/{instructorId}/edit", method = RequestMethod.GET)
    public String initUpdateInstructorForm(@PathVariable("instructorId") int instructorId, Model model) {
        Instructor instructor = this.systemService.findInstructorById(instructorId);
        model.addAttribute(instructor);
        return "instructors/createOrUpdateInstructorForm";
    }

    @RequestMapping(value = "/instructors/{instructorId}/edit", method = RequestMethod.PUT)
    public String processUpdateInstructorForm(@Valid Instructor instructor, BindingResult result, SessionStatus status) {
        if (result.hasErrors()) {
            return "instructors/createOrUpdateInstructorForm";
        } else {
            this.systemService.saveInstructor(instructor);
            status.setComplete();
            return "redirect:/instructors/{instructorId}";
        }
    }




    @RequestMapping(value = "/instructors" +
            "", method = RequestMethod.GET)
    public String processFindForm(Instructor instructor, BindingResult result, Map<String, Object> model) {

        if (instructor.getLastName() == null) {
            instructor.setLastName(""); // empty string signifies broadest possible search
        }

        Collection<Instructor> results = this.systemService.findInstructorByLastName(instructor.getLastName());
        if (results.size() < 1) {
            result.rejectValue("name", "notFound", "not found");
            return "instructors/findInstructors";
        }

         else {
            // 1 activity found
            instructor = results.iterator().next();
            return "redirect:/instructors/" + instructor.getId();
        }
    }




}
