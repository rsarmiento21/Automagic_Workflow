package com.revature.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.revature.domain.BoardUser;

@Component
@Repository
public class BoardDaoImpl implements Dao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public BoardUser getBoardUserById(BoardUser bu) {
		Session session = sessionFactory.getCurrentSession();
		return (BoardUser) session.get(BoardUser.class, bu.getId());
	}
  
	public void updateBoardUser(BoardUser bu)
	{
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(bu);
	}
	
	public void test() {
		System.out.println("dao test");
	}
}
