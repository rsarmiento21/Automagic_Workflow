package com.revature.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.domain.BoardUser;
import com.revature.service.AuthService;
import com.revature.service.BoardService;

@RestController
public class AjaxUserCtrl {
	
	@Autowired
	private AuthService as;
	
	@Autowired
	private BoardService bs;
	
	@RequestMapping(value="/ajax/isLoggedIn")
	@ResponseBody
	public ResponseEntity<Object> isLoggedIn(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if (session != null) {
			BoardUser bu = (BoardUser) session.getAttribute("user");
			if (bu != null) {
				bu = bs.getBoardUserById(bu);
				session.setAttribute("user", bu);
				return new ResponseEntity<>(new Boolean(true), HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(new Boolean(false), HttpStatus.OK);
	}
	
	@RequestMapping(value="/ajax/register")
	@ResponseBody
	public void registerNewUser(@RequestBody BoardUser bu,HttpServletRequest req)
	{
			as.registerBoardUser(bu);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Exception> handleException(Exception e){
		return new ResponseEntity<Exception>(e,HttpStatus.CONFLICT);
	}
	
}
