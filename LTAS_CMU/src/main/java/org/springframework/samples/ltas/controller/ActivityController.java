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

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.ltas.entity.ActType;
import org.springframework.samples.ltas.entity.Activity;
import org.springframework.samples.ltas.service.SystemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 */
@Controller
@SessionAttributes(types = Activity.class)
public class ActivityController {

    private final SystemService systemService;


    @Autowired
    public ActivityController(SystemService systemService) {
        this.systemService = systemService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("types")
    public Collection<ActType> populateActTypes() {
        return this.systemService.findActTypes();
    }

    @RequestMapping(value = "/activities/new", method = RequestMethod.GET)
    public String initCreationForm(Map<String, Object> model) {
        Activity activity = new Activity();
        model.put("activity", activity);
        return "activities/createOrUpdateActivityForm";
    }

    @RequestMapping(value = "/activities/new", method = RequestMethod.POST)
    public String processCreationForm(@Valid Activity activity, BindingResult result, SessionStatus status) {
        if (result.hasErrors()) {
            return "activities/createOrUpdateActivityForm";
        } else {
            this.systemService.saveActivity(activity);
            status.setComplete();
            return "redirect:/activities/" + activity.getId();
        }
    }

    @RequestMapping(value = "/activities/find", method = RequestMethod.GET)
    public String initFindForm(Map<String, Object> model) {
        model.put("activity", new Activity());
//        model.put("formUrl")
        return "activities/findActivities";
    }

    @RequestMapping(value = "/activities" +
            "", method = RequestMethod.GET)
    public String processFindForm(Activity activity, BindingResult result, Map<String, Object> model) {

        if (activity.getName() == null) {
            activity.setName(""); // empty string signifies broadest possible search
        }

        Collection<Activity> results = this.systemService.findActivityByName(activity.getName());
        if (results.size() < 1) {
            result.rejectValue("name", "notFound", "not found");
            return "activities/findActivities";
        }
        if (results.size() > 1) {
            model.put("selections", results);
            return "activities/activitiesList";
        } else {
            // 1 activity found
            activity = results.iterator().next();
            return "redirect:/activities/" + activity.getId();
        }
    }

    @RequestMapping(value = "/activities/{activityId}/edit", method = RequestMethod.GET)
    public String initUpdateActivityForm(@PathVariable("activityId") int activityId, Model model) {
        Activity activity = this.systemService.findActivityById(activityId);
        model.addAttribute(activity);
        return "activities/createOrUpdateActivityForm";
    }

    @RequestMapping(value = "/activities/{activityId}/edit", method = RequestMethod.PUT)
    public String processUpdateActivityForm(@Valid Activity activity, BindingResult result, SessionStatus status) {
        if (result.hasErrors()) {
            return "activities/createOrUpdateActivityForm";
        } else {
            this.systemService.saveActivity(activity);
            status.setComplete();
            return "redirect:/activities/{activityId}";
        }
    }


    @RequestMapping("/activities/{activityId}")
    public ModelAndView showActivity(@PathVariable("activityId") int activityId) {
        ModelAndView mav = new ModelAndView("activities/activityDetails");
        mav.addObject(this.systemService.findActivityById(activityId));
        return mav;
    }

}
