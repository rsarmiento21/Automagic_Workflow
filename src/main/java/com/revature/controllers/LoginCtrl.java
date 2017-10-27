package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.revature.domain.BoardUser;
import com.revature.service.AuthService;

@Controller("/login")
public class LoginCtrl 
{
	@Autowired
	private AuthService authService;
	
	@RequestMapping(value = "/login")
	public String loginVisit() {
		BoardUser bu = new BoardUser();
		bu.setUsername("steve");
		bu.setPassword("123");
//		AuthService app = (AuthService) ac.getBean("authService");
		System.out.println(authService);
		System.out.println(authService.login(bu));
		return "/resources/dummy.txt";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginUser(BoardUser usr, ApplicationContext ac) {
		return "/resources/dummy.txt";
	}
	
}
