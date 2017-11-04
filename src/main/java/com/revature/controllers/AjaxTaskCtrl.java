package com.revature.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.domain.BoardUser;
import com.revature.domain.Task;
import com.revature.service.BoardService;

@RestController
public class AjaxTaskCtrl {
	
	@Autowired
	private BoardService bs;

	@RequestMapping("/ajax/task/new")
	@ResponseBody
	public ResponseEntity<Object> addNewTask(@RequestBody Task task, HttpServletRequest req) {
		System.out.println(task);
//		System.out.println(task.getStory());
		HttpSession session = req.getSession();
		if (session != null) {
			BoardUser bu = (BoardUser) session.getAttribute("user");
			if (bu != null) {
				task = bs.createTask(task);
				System.out.println(task);
				bu = bs.getBoardUserById(bu);
				session.setAttribute("user", bu);
				return new ResponseEntity<>(task, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@RequestMapping("/ajax/task/edit")
	@ResponseBody
	public ResponseEntity<Object> editTask(@RequestBody Task task, HttpServletRequest req) {
//		System.out.println(task);
//		System.out.println(task.getStory());
		HttpSession session = req.getSession();
		if (session != null) {
			BoardUser bu = (BoardUser) session.getAttribute("user");
			if (bu != null) {
				bs.updateTask(task);
				bu = bs.getBoardUserById(bu);
				session.setAttribute("user", bu);
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@RequestMapping("/ajax/task/delete")
	@ResponseBody
	public ResponseEntity<Object> deleteTask(@RequestBody Task task, HttpServletRequest req) {
//		System.out.println(task);
//		System.out.println(task.getStory());
		HttpSession session = req.getSession();
		if (session != null) {
			BoardUser bu = (BoardUser) session.getAttribute("user");
			if (bu != null) {
				bs.deleteTask(task);
				bu = bs.getBoardUserById(bu);
				session.setAttribute("user", bu);
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
