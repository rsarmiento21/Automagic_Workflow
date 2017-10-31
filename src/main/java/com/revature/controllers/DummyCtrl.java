package com.revature.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DummyCtrl {
	
	@RequestMapping("/dummy")
	public ModelAndView dummy() {
		return new ModelAndView("/resources/dummy.txt");
	}
}
