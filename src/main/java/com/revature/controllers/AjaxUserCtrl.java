package com.revature.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.domain.Board;
import com.revature.domain.BoardUser;

@RestController
public class AjaxUserCtrl {
	@RequestMapping(value="/ajax/isLoggedIn")
	@ResponseBody
	public ResponseEntity<Object> isLoggedIn(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if (session != null) {
			BoardUser bu = (BoardUser) session.getAttribute("user");
			if (bu != null) {
				return new ResponseEntity<>(new Boolean(true), HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(new Boolean(false), HttpStatus.OK);
	}
	
}
