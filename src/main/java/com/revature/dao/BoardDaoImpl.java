package com.revature.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.domain.Board;
import com.revature.domain.BoardUser;
import com.revature.domain.Story;
import com.revature.domain.SwimLane;
import com.revature.domain.Task;

@Component
@Repository
@Transactional
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
	
	@Override
	public void createSwimLane(SwimLane sl) {
		Session session = sessionFactory.getCurrentSession();
		session.save(sl);
	}
	
	@Override
	public void createStory(Story s) {
		Session session = sessionFactory.getCurrentSession();
		session.save(s);
		
	}

	@Override
	public void createTask(Task t) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(t);
	}
	
	
	
	//READ
	@Override
	public BoardUser getBoardUserById(BoardUser bu) {
		Session session = sessionFactory.getCurrentSession();
		return (BoardUser) session.get(BoardUser.class, bu.getId());
	}
	@Override
	public BoardUser getBoardUserByUsername(BoardUser bu) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(BoardUser.class);
		
		return (BoardUser) criteria.add(Restrictions.eq("username", bu.getUsername())).uniqueResult();
	}
	
	@Override
	public Board getBoardById(Board b) {
		Session session = sessionFactory.getCurrentSession();
		return (Board) session.get(Board.class, b.getId());
	}
	
	@Override
	public SwimLane getSwimLaneById(SwimLane sl) {
		Session session = sessionFactory.getCurrentSession();
		return (SwimLane) session.get(SwimLane.class, sl.getId());
	}
	
	@Override
	public Story getStoryById(Story s) {
		Session session = sessionFactory.getCurrentSession();
		return (Story) session.get(Story.class, s.getId());
	}
	
	@Override
	public Task getTaskById(Task t) {
		Session session = sessionFactory.getCurrentSession();
		return (Task) session.get(Task.class, t.getId());
	}
	
	
	//UPDATE
	@Override
	public void updateBoard(Board b) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(b);
	}
	
	public void updateBoardUser(BoardUser bu) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(bu);
	}
	
	@Override
	public void updateSwimLane(SwimLane sl) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(sl);
	}
	
	@Override
	public void updateStory(Story s) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(s);
	}

	@Override
	public void updateTask(Task t) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(t);
	}
	
	
	
	//DELETE
	@Override
	public void deleteBoard(Board b) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(b);
	}
	
	@Override
	public void deleteSwimLane(SwimLane sl) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(sl);
	}

	@Override
	public void deleteStory(Story s) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(s);
	}

	@Override
	public void deleteTask(Task t) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(t);
	}
	
}
