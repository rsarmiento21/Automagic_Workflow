package com.revature.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.domain.Board;
import com.revature.domain.BoardUser;
import com.revature.domain.SwimLane;
import com.revature.service.BoardService;

@RestController
public class AjaxSwimLaneCtrl {
	@Autowired
	private BoardService boardService;
	
	//self explanatory, creates a swimlane with the name and boardId
	//Only use alphanumeric, using characters such as ? won't work
	//not really sure what to return, if anything? Maybe just make it a void
	@RequestMapping(value="/ajax/swimlane/new")
	@ResponseBody
	public ResponseEntity<Object> createSwimLane(@RequestBody SwimLane sl, HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if(session != null) {
			BoardUser bu = (BoardUser) session.getAttribute("user");
			if(bu != null) {
				sl = boardService.createSwimLane(sl);
				bu = boardService.getBoardUserById(bu);
				session.setAttribute("user", bu);
				return new ResponseEntity<>(sl, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@RequestMapping(value="ajax/swimlane/delete")
	@ResponseBody

	public ResponseEntity<Object> deleteSwimLane(@RequestBody SwimLane sl, HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if(session != null) {
			BoardUser bu = (BoardUser) session.getAttribute("user");
			if(bu != null) {
				boardService.deleteSwimLane(sl);
				bu = boardService.getBoardUserById(bu);
				session.setAttribute("user", bu);
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@RequestMapping(value="ajax/swimlane/edit")
	@ResponseBody
	public ResponseEntity<Object> editSwimLane(@RequestBody SwimLane sl, HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if(session != null) {
			BoardUser bu = (BoardUser) session.getAttribute("user");
			if(bu != null) {
				boardService.updateSwimLane(sl);
				bu = boardService.getBoardUserById(bu);
				session.setAttribute("user", bu);
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
}
