package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

import com.revature.domain.Story;

import com.revature.domain.SwimLane;
import com.revature.service.BoardService;

@RestController
public class AjaxBoardCtrl {

	@Autowired

	private BoardService boardService;

	private BoardService serve;

	
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

	
	@RequestMapping(value="/ajax/board/stories/{id}")
	@ResponseBody
	public ResponseEntity<Object> getStoriesFromBoardId(@PathVariable("id") String s, HttpServletRequest req) {
		int id = Integer.parseInt(s);
		Board b = new Board();
		b.setId(id);
		Board board = boardService.getBoardById(b);
		
		Set<SwimLane> setOfSwimLane = board.getSwimLanes();
		
		List<Story> listOfStories = new ArrayList<Story>();
		
		for(SwimLane sl : setOfSwimLane) {
			listOfStories.addAll(sl.getStories());
		}		
		
		return new ResponseEntity<>(listOfStories, HttpStatus.OK);
	}
	

	@RequestMapping(value="ajax/board/edit")
	@ResponseBody
	public void editBoard(@RequestBody Board bd, HttpServletRequest req) {

		HttpSession session = req.getSession(false);
		if(session != null) {
			BoardUser bu = (BoardUser) session.getAttribute("user");
			if(bu != null) {
				String name = bd.getName();
				Board bd2 = new Board();
				bd2.setId(bd.getId());
				bd2 = serve.getBoardById(bd);
				System.out.println(bd2);
				if(bd2 != null)
				{
					bd2.setName(name);
					try 
					{
						System.out.println(bd2);
						serve.updateBoard(bd2);
						bu = serve.getBoardUserById(bu);
						session.setAttribute("user", bu);
					}
					catch(NullPointerException e)
					{
						e.printStackTrace();
					}
					catch (Exception e) 
					{
						e.printStackTrace();
					}
				}
			}
		}
	}

}
	

