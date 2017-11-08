package com.revature.controllers;

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
public class DeleteBoardCtrl {
	
	@Autowired
	private BoardService bs;
	

	
	@RequestMapping(value="/deleteBoard", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> deleteBoard(@RequestBody Board b, HttpServletRequest req) {
		
		Board bb = bs.getBoardById(b);

		bs.deleteBoard(bb);
		return new ResponseEntity<>(HttpStatus.OK);	//send back the board user to go back to board page with ID			
	}
	

}
