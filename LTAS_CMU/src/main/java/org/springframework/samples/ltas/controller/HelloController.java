package org.springframework.samples.ltas.controller;

/**
 * Created by berrybiw on 6/2/14 AD.
 */
import org.springframework.samples.ltas.entity.Activity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.Map;


@Controller
@RequestMapping("/hello")
public class HelloController {
    @RequestMapping(method = RequestMethod.GET)
    public String printHello(ModelMap model) {
        model.addAttribute("message", "Hello Spring MVC Framework!");

        return "hello";
    }



}
