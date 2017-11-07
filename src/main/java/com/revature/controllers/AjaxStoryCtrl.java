package com.revature.controllers;

import java.util.List;

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
import com.revature.domain.Story;
import com.revature.service.BoardService;

@RestController
public class AjaxStoryCtrl {
	
	@Autowired
	private BoardService bs;

	@RequestMapping("/ajax/story/new")
	@ResponseBody
	public ResponseEntity<Object> createStory(@RequestBody Story story, HttpServletRequest req) {
		HttpSession session = req.getSession();
		if (session != null) {
			BoardUser bu = (BoardUser) session.getAttribute("user");
			if (bu != null) {
				story = bs.createStory(story);
				return new ResponseEntity<>(story, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@RequestMapping("/ajax/story/edit")
	@ResponseBody
	public ResponseEntity<Object> editStory(@RequestBody Story story, HttpServletRequest req) {
		HttpSession session = req.getSession();
		if (session != null) {
			BoardUser bu = (BoardUser) session.getAttribute("user");
			if (bu != null) {
				bs.updateStory(story);
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@RequestMapping("/ajax/story/editAll")
	@ResponseBody
	public ResponseEntity<Object> editStories(@RequestBody List<Story> stories, HttpServletRequest req) {
		HttpSession session = req.getSession();
		if (session != null) {
			BoardUser bu = (BoardUser) session.getAttribute("user");
			if (bu != null) {
				bs.updateStories(stories);
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@RequestMapping("/ajax/story/delete")
	@ResponseBody
	public ResponseEntity<Object> deleteStory(@RequestBody Story story, HttpServletRequest req) {
		HttpSession session = req.getSession();
		if (session != null) {
			BoardUser bu = (BoardUser) session.getAttribute("user");
			if (bu != null) {
				bs.deleteStory(story);
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Exception> handleException(Exception e){
		return new ResponseEntity<Exception>(e,HttpStatus.CONFLICT);
	}
	
}
