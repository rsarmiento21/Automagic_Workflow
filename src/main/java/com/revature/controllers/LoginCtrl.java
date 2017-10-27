package com.revature.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.domain.BoardUser;

@Controller
public class LoginCtrl 
{
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public @ResponseBody String loginUser(BoardUser usr,HttpServletRequest req)
	{
		String username = "";
		return username;
	}
	
}
