package com.revature.service;

import java.util.List;

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
	public Board createBoard(Board b) {
		return dao.createBoard(b);
	}
	public SwimLane createSwimLane(SwimLane sl) {
		return dao.createSwimLane(sl);
	}
	public Story createStory(Story s) {
		return dao.createStory(s);
	}
	public Task createTask(Task t) {
		return dao.createTask(t);
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
	
	public void updateSwimLanes(List<SwimLane> swimlanes) {
		dao.updateSwimLanes(swimlanes);
	}
	public void updateStories(List<Story> stories) {
		dao.updateStories(stories);
	}
	public void updateTasks(List<Task> tasks) {
		dao.updateTasks(tasks);
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
