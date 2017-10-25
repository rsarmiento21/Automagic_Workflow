package com.revature.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.domain.BoardUser;

@Repository
public class BoardDaoImpl implements Dao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public BoardUser getBoardUserById(BoardUser bu) {
		System.out.println("pulling BoardUser from DB...");
		Session session = sessionFactory.getCurrentSession();
		return (BoardUser) session.get(BoardUser.class, bu.getId());
	}

}
