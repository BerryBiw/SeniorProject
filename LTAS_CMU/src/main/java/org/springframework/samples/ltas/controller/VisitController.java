
package org.springframework.samples.ltas.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.ltas.entity.Student;
import org.springframework.samples.ltas.entity.Visit;
import org.springframework.samples.ltas.service.SystemService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;


@Controller
@SessionAttributes("visit")
public class VisitController {

    private final SystemService systemService;


    @Autowired
    public VisitController(SystemService systemService) {
        this.systemService = systemService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping(value = "/activities/*/students/{studentId}/visits/new", method = RequestMethod.GET)
    public String initNewVisitForm(@PathVariable("studentId") int studentId, Map<String, Object> model) {
        Student student = this.systemService.findStudentById(studentId);
        Visit visit = new Visit();
        student.addVisit(visit);
        model.put("visit", visit);
        return "students/createOrUpdateVisitForm";
    }

    @RequestMapping(value = "/activities/{activityId}/students/{studentId}/visits/new", method = RequestMethod.POST)
    public String processNewVisitForm(@Valid Visit visit, BindingResult result, SessionStatus status) {
        if (result.hasErrors()) {
            return "students/createOrUpdateVisitForm";
        } else {
            this.systemService.saveVisit(visit);
            status.setComplete();
            return "redirect:/activities/{activityId}";
        }
    }

    @RequestMapping(value = "/activities/*/students/{studentId}/visits", method = RequestMethod.GET)
    public ModelAndView showVisits(@PathVariable int studentId) {
        ModelAndView mav = new ModelAndView("visitList");
        mav.addObject("visits", this.systemService.findStudentById(studentId).getVisits());
        return mav;
    }

}
