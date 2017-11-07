package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.domain.Board;
import com.revature.domain.BoardUser;

import com.revature.domain.Story;

import com.revature.domain.SwimLane;
import com.revature.service.BoardService;

@RestController
public class AjaxBoardCtrl {

	@Autowired
	private BoardService boardService;

	
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

	//Get the board ID to get the Board object with list of Swimlanes, which will then give list of stories
	@RequestMapping(value="/ajax/board/getStoriesFromBoard/{id}", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> getStoriesFromBoard(@PathVariable("id") String s, HttpServletRequest req) {
	
		int bId = Integer.parseInt(s);
		System.out.println("grabbing stories with " + bId);
		
		Board b = new Board();
		b.setId(bId);
		
		Board board = boardService.getBoardById(b);
		
		Set<SwimLane> setOfSwimLane = board.getSwimLanes();
		
		List<Story> listOfStories = new ArrayList<Story>();
		
		for(SwimLane sl : setOfSwimLane) {
			listOfStories.addAll(sl.getStories());
		}		
		
		for(Story testStory : listOfStories) {
			System.out.println("SL ID: " + testStory.getId());
		}
		
		//Story[] arrayOfStories = listOfStories.toArray(new Story[listOfStories.size()]);
		
		return new ResponseEntity<>(listOfStories, HttpStatus.OK);
	}
	
	@RequestMapping(value="ajax/board/edit")
	@ResponseBody
	public ResponseEntity<Object> editBoard(@RequestBody Board bd, HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if(session != null) {
			BoardUser bu = (BoardUser) session.getAttribute("user");
			if(bu != null) {
				bd.setOwner(bu);
				boardService.updateBoard(bd);
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Exception> handleException(Exception e){
		System.out.println(e);
		return new ResponseEntity<Exception>(e,HttpStatus.CONFLICT);
	}
}
	

