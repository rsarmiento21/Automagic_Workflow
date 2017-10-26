package com.revature.dao;

import com.revature.domain.Board;
import com.revature.domain.BoardUser;

public interface Dao {
	//CREATE
	public void createBoard(Board b);
	public void createNewBoardUser(BoardUser bu);
	
	//READ
	public BoardUser getBoardUserById(BoardUser bu);
	public void updateBoardUser(BoardUser bu);
	
	
	//UPDATE
	public void updateBoard(Board b);
	
	
	//DELETE
	
	public void deleteBoard(Board b);
	
	/*
	 * CREATE
	 * createNewBoardUser(BoardUser)
	 * createNewRole(BoardUser, Board, Role)? - e.g. giving another user a different role, to a certain table?
	 * createBoard(Board)
	 * createSwimLane(SwimLane)
	 * createStory(Story)
	 * createTask(Task)
	 * 
	 * 
	 * READ (Mostly done by Hibernate anyway? So unnecessary)
	 * getBoardsById(BoardUser)
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
	 * 
	 * 
	 */
}
