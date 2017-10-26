package com.revature.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.domain.Board;
import com.revature.domain.BoardUser;

@Repository
public class BoardDaoImpl implements Dao {

	@Autowired
	private SessionFactory sessionFactory;
	
	//CREATE
	@Override
	public void createBoard(Board b) {
		Session session = sessionFactory.getCurrentSession();
		session.save(b);
	}
	
	@Override
	public void createNewBoardUser(BoardUser bu) {
		Session session = sessionFactory.getCurrentSession();
		session.save(bu);
	}
	
	//READ
	@Override
	public BoardUser getBoardUserById(BoardUser bu) {
		Session session = sessionFactory.getCurrentSession();
		return (BoardUser) session.get(BoardUser.class, bu.getId());
	}
	
	//UPDATE
	@Override
	public void updateBoard(Board b) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(b);
	}
	
	//DELETE
	@Override
	public void deleteBoard(Board b) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(b);
	}
}
