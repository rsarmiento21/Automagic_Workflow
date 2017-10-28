package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.revature.dao.Dao;
import com.revature.domain.Board;
import com.revature.domain.BoardUser;
import com.revature.domain.Story;
import com.revature.domain.SwimLane;
import com.revature.domain.Task;

@Component("boardService")
@Transactional
public class BoardService {
	
	@Autowired
	private Dao dao;
	
	//CREATE
	public void createBoard(Board b) {
		dao.createBoard(b);
	}
	public void createSwimLane(SwimLane sl) {
		dao.createSwimLane(sl);
	}
	public void createStory(Story s) {
		dao.createStory(s);
	}
	public void createTask(Task t) {
		dao.createTask(t);
	}
	
	//READ
	public BoardUser getBoardUserById(BoardUser bu) {
		return dao.getBoardUserById(bu);
	}
	public BoardUser getBoardUserByUsername(BoardUser bu) {
		return dao.getBoardUserByUsername(bu);
	}
	public Board getBoardById(Board b) {
		return dao.getBoardById(b);
	}
	public SwimLane getSwimLaneById(SwimLane sl) {
		return dao.getSwimLaneById(sl);
	}
	public Story getStoryById(Story s) {
		return dao.getStoryById(s);
	}
	public Task getTaskById(Task t) {
		return dao.getTaskById(t);
	}
	
	//UPDATE
	
	public void updateBoard(Board b) {
		dao.updateBoard(b);
	}
	
	public void updateBoardUser(BoardUser bu) {
		dao.updateBoardUser(bu);
	}
	
	public void updateSwimLane(SwimLane sl) {
		dao.updateSwimLane(sl);
	}
	public void updateStory(Story s) {
		dao.updateStory(s);
	}
	public void updateTask(Task t) {
		dao.updateTask(t);
	}
	
	//DELETE
	public void deleteBoard(Board b) {
		dao.deleteBoard(b);
	}
	public void deleteSwimLane(SwimLane sl) {
		dao.deleteSwimLane(sl);
	}
	public void deleteStory(Story s) {
		dao.deleteStory(s);
	}
	public void deleteTask(Task t) {
		dao.deleteTask(t);
	}
	
	
	
	
	
  
}
