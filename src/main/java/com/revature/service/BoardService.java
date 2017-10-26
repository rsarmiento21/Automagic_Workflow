package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.revature.dao.Dao;
import com.revature.domain.BoardUser;

@Component("boardService")
@Transactional
public class BoardService {
	
	@Autowired
	private Dao dao;
	
	public BoardUser getBoardUserById(BoardUser bu){
		return dao.getBoardUserById(bu);
	}
	public void updateBoardUserService(BoardUser bu)
	{
		dao.updateBoardUser(bu);
	}
}
