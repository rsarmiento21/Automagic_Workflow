package com.revature.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.domain.BoardUser;

@RestController
public class AjaxBoardUserCtrl {
	@RequestMapping(value="/ajaxIsLoggedIn")
	@ResponseBody
	public ResponseEntity<Object> isLoggedIn(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if (session != null) {
			BoardUser bu = (BoardUser) session.getAttribute("user");
			if (bu != null) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
	}
}
