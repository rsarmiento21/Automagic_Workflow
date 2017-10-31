package com.revature.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.revature.domain.BoardUser;

@Controller
public class HomeCtrl {
	
	@RequestMapping(value= {"/", "/portal"})
	public ModelAndView navigator(HttpServletRequest req) {
		return new ModelAndView("/resources/portal.html");
	}
	
}
