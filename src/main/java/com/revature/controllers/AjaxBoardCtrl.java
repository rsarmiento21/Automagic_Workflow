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
public class AjaxBoardCtrl {

	@RequestMapping("/ajax/boards")
	@ResponseBody
	public ResponseEntity<Object> getBoards(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if(session != null) {
			BoardUser bu = (BoardUser) session.getAttribute("user");
			if(bu != null) {
				return new ResponseEntity<>(bu.getBoards(),HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@RequestMapping(value="/ajax/board/{id}")
	@ResponseBody
	public ResponseEntity<Object> getBoardById(@PathVariable("id") String s, HttpServletRequest req) {
		System.out.println(s);
		int id = Integer.parseInt(s);
		HttpSession session = req.getSession(false);
		if (session != null) {
			BoardUser bu = (BoardUser) session.getAttribute("user");
			if (bu != null) {
				for (Board bd : bu.getBoards()) {
					if (id == bd.getId()) {
						return new ResponseEntity<>(bd, HttpStatus.OK);
					}
				}
			}
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
}
