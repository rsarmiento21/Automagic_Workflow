package com.revature.dao;

import com.revature.domain.BoardUser;

public interface Dao {
	
	public BoardUser getBoardUserById(BoardUser bu);
	public void updateBoardUser(BoardUser bu);
	
}
