package com.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("myController")
@RequestMapping("")
public class MyController {
	@RequestMapping("/index")
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		return mv;
	}
}
