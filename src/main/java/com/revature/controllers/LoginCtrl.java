package com.revature.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.domain.BoardUser;

@Controller
public class LoginCtrl 
{
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public @ResponseBody String loginUser(BoardUser usr)
	{
		String username = "";
		return username;
	}
	
}
