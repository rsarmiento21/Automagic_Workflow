package com.revature.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.dao.Dao;
import com.revature.domain.BoardUser;

@Component("authService")
public class AuthService {

	@Autowired
	private Dao dao;

	@Transactional
	public BoardUser login(BoardUser usr) {
		BoardUser out = dao.getBoardUserByUsername(usr);

		if (out != null && out.getUsername().equals(usr.getUsername()) &&
				out.getPassword().equals(usr.getPassword())) {
			return out;
		} else {
			return null;
		}
	}
	
	@Transactional
	public void registerBoardUser(BoardUser usr) {
		if(usr != null)
		{
			BoardUser usr2 = new BoardUser();
			usr2 = dao.createNewBoardUser(usr);
		}
	}
}
