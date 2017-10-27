package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.dao.Dao;
import com.revature.domain.BoardUser;

@Component("authService")
public class AuthService {

	@Autowired
	private Dao dao;

	public BoardUser login(BoardUser usr) {
		return usr;
//		BoardUser out = dao.getBoardUserByUsername(usr);
//
//		if (out != null && out.getUsername().equals(usr.getUsername()) &&
//				out.getPassword().equals(usr.getPassword())) {
//			return out;
//		} else {
//			return null;
//		}
	}
}
