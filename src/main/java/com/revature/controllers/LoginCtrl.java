package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.revature.domain.BoardUser;
import com.revature.service.AuthService;

@Controller
public class LoginCtrl 
{
	@Autowired
	private AuthService authService;
	
	@RequestMapping(value = "login")
	public String loginVisit() {
		return "html/login.html";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE/*,
			produces=MediaType.APPLICATION_JSON_VALUE*/)
	public String loginUser(@RequestBody BoardUser bu) {
		System.out.println(bu);
		System.out.println(authService);
		System.out.println(authService.login(bu));
		return "redirect:resources/dummy.txt";
	}
	
}
