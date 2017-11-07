package com.revature.controllers;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.revature.domain.BoardUser;
import com.revature.service.AuthService;

@RestController
public class LoginCtrl 
{
	@Autowired
	private AuthService authService;
	
	@RequestMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("/resources/portal.html");
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> loginBoardUser(@RequestBody BoardUser bu, HttpServletRequest req) {
		bu = authService.login(bu);
		if (bu != null) {
			HttpSession session = req.getSession(true);
			session.setAttribute("user", bu);
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(Collections.singletonList("Username/password incorrect."), HttpStatus.CONFLICT);
		}
		
	}
	
	@RequestMapping(value="/logout")
	@ResponseBody
	public ResponseEntity<Object> logoutBoardUser(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if (session != null) {
			session.removeAttribute("user");
			session.invalidate();
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(Collections.singletonList("Non-existant session."), HttpStatus.CONFLICT);
		}
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Exception> handleException(Exception e){
		return new ResponseEntity<Exception>(e,HttpStatus.CONFLICT);
	}
	
}
