package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.revature.domain.BoardUser;
import com.revature.service.AuthService;

@Controller
public class LoginCtrl 
{
	@Autowired
	private AuthService authService;
	
	@RequestMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("/resources/portal.html");
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE/*,
			produces=MediaType.APPLICATION_JSON_VALUE*/)
	public String loginBoardUser(@RequestBody BoardUser bu, ModelMap modelMap) {
		bu = authService.login(bu);
		if (bu != null) {
			System.out.println(bu);
//			HttpSession session = req.getSession(true);
//			session.setAttribute("user", bu);
			modelMap.addAttribute("user", bu);
		}else {
			modelMap.addAttribute("errorMessage","Username/Password incorrect");
		}
		return "redirect:dummy";
	}
	
}
