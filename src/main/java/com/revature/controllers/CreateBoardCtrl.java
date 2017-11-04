package com.revature.controllers;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.revature.domain.Board;
import com.revature.domain.BoardUser;
import com.revature.service.BoardService;

@RestController
public class CreateBoardCtrl {
	@Autowired
	private BoardService bs;
	
	@RequestMapping("/createBoard")
	public ModelAndView createBoard() {
		return new ModelAndView("/resources/portal.html");
	}
	
	@RequestMapping(value="/createBoard", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> createBoard(@RequestBody Board b, HttpServletRequest req) {
		System.out.println("creating board " + b);
		HttpSession session = req.getSession();
		BoardUser bu = (BoardUser) session.getAttribute("user");
		b.setOwner(bu);
		bs.createBoard(b);
		return new ResponseEntity<>(HttpStatus.OK);	//send back the board user to go back to board page with ID			
	}
	
}
