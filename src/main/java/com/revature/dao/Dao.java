package com.revature.dao;

import com.revature.domain.Board;
import com.revature.domain.BoardUser;
import com.revature.domain.Story;
import com.revature.domain.SwimLane;
import com.revature.domain.Task;

public interface Dao {
	//CREATE
	public void createBoard(Board b);
	public void createNewBoardUser(BoardUser bu);
	public void createSwimLane(SwimLane sl);
	public void createStory(Story s);
	public Task createTask(Task t);
	
	//READ
	public BoardUser getBoardUserById(BoardUser bu);
	public BoardUser getBoardUserByUsername(BoardUser bu);
	public Board getBoardById(Board b);
	public SwimLane getSwimLaneById(SwimLane sl);
	public Story getStoryById(Story s);
	public Task getTaskById(Task t);
	public void updateBoardUser(BoardUser bu);
	
	
	//UPDATE
	public void updateBoard(Board b);
	public void updateSwimLane(SwimLane sl);
	public void updateStory(Story s);
	public void updateTask(Task t);
	
	//DELETE
	public void deleteBoard(Board b);
	public void deleteSwimLane(SwimLane sl);
	public void deleteStory(Story s);
	public void deleteTask(Task t);
	
	/*
	 * CREATE
	 * createNewBoardUser(BoardUser) - not really necessary
	 * createNewRole(BoardUser, Board, Role)? - e.g. giving another user a different role, to a certain table?
	 * createBoard(Board)
	 * createSwimLane(SwimLane)
	 * createStory(Story)
	 * createTask(Task)
	 * 
	 * 
	 * READ (Mostly done by Hibernate anyway? So unnecessary?)
	 * getUserByUsername(BoardUser)
	 * getBoardById(Board)
	 * getSwimLanesByBoardId(Board)
	 * getStoriesBySwimLaneId(SwimLane)
	 * getTasksByStoryId(Story)
	 * 
	 * UPDATE
	 * updateBoards(Board)
	 * updateSwimLane(SwimLane)
	 * updateStory(Story)
	 * updateTask(Task)
	 * 
	 * 
	 * DELETE
	 * deleteBoardUserRole(Role)? - for example, removing a role from a user, so they're no longer part of a board
	 * deleteBoard(Board)
	 * deleteSwimLane(SwimLane)
	 * deleteStory(Story)
	 * deleteTask(Task)
	 */
}
