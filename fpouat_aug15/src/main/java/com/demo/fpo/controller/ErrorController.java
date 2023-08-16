package com.demo.fpo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {

	@RequestMapping("/**/{path:[^.]*}")
    public ModelAndView handleUnmatchedURL() {
    	
    	ModelAndView modelAndView = new ModelAndView("redirect:/error");
			return modelAndView;
    }
}
