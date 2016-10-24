package org.dtmhapcs.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@org.springframework.stereotype.Controller
public class Controller {
    public String message = "Welcom to you!";

    @RequestMapping("/hello")
    public ModelAndView showMessage() {
        System.out.println("from controller");
        return new ModelAndView("hello", "message", message);
    }
}
