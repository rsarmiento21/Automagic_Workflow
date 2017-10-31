package com.revature.tests;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.revature.dao.Dao;
import com.revature.domain.Board;
import com.revature.domain.BoardUser;
import com.revature.domain.Story;
import com.revature.domain.SwimLane;
import com.revature.domain.Task;
import com.revature.service.BoardService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/beans.xml" })
@Transactional //sets it so that all tests will rollback once done, so it does not commit any changes
public class AutomagicTests {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private Dao dao;
	
	//Use id=9999 for testing id, naturally if project gets big enough(i.e. ids go all the way up to 9999, will have to use a bigger number
	//can keep reusing it since @Transactional makes it so that any commits are rollbacked to before the test started.
	private int testId = 9999;
	
	//TESTS FOR
		//CRUD
		//anything else? login?
	
	// CREATE AND READ TESTS
	// read included with create, technically with all the CRUD tests, because it NEEDS to read from the database, to make sure things actually persisted
	@Test
	@Transactional
	public void createBoardUserTest() {
		BoardUser bu = new BoardUser();
		bu.setUsername("jUnit Test");
		bu.setPassword("assert this");
		dao.createNewBoardUser(bu);
		BoardUser toTest = new BoardUser();
		toTest.setUsername("jUnit Test");
		toTest = boardService.getBoardUserByUsername(toTest);
		
		Assert.assertEquals("testing createBoardUser", bu.getPassword(), toTest.getPassword());
	}
	
	@Test
	public void createBoardTest() {
		Board b = new Board();
		b.setId(testId);
		b.setName("test create board");
		
		boardService.createBoard(b);
		
		Board toTest = new Board();
		toTest.setId(testId);
		toTest = boardService.getBoardById(toTest);
		
		Assert.assertEquals("testing createBoard", b.getName(), toTest.getName());
	}
	
	@Test
	public void createSwimLaneTest() {
		SwimLane sl = new SwimLane();
		sl.setId(testId);
		sl.setName("test create swimlane");
		
		boardService.createSwimLane(sl);
		
		SwimLane toTest = new SwimLane();
		toTest.setId(testId);
		toTest = boardService.getSwimLaneById(toTest);
		
		Assert.assertEquals("testing createswimlane", sl.getName(), toTest.getName());
	}
	
	@Test
	public void createStoryTest() {
		Story s = new Story();
		s.setId(testId);
		s.setTitle("test create story");
		
		boardService.createStory(s);
		
		Story toTest = new Story();
		toTest.setId(testId);
		toTest = boardService.getStoryById(toTest);
		
		Assert.assertEquals("testing create story", s.getTitle(), toTest.getTitle());
	}
	
	@Test
	public void createTaskTest() {
		Task t = new Task();
		t.setId(testId);
		t.setName("test create task");
		
		boardService.createTask(t);
		
		Task toTest = new Task();
		toTest.setId(testId);
		toTest = boardService.getTaskById(toTest);
		
		Assert.assertEquals("testing create task", t.getName(), toTest.getName());
	}
	
	
	//UPDATE TESTS
	//creates a board, updates it, and reads it to check if update persisted
	@Test
	public void updateBoardTest() {
		Board b = new Board();
		b.setId(testId);
		b.setName("assert this");
		boardService.createBoard(b);
		b.setName("updated board assert");
		boardService.updateBoard(b);
		
		Board toTest = new Board();
		toTest.setId(testId);
		toTest = boardService.getBoardById(toTest);
		
		Assert.assertEquals("testing updateBoard", b.getName(), toTest.getName());
	}
	
	@Test
	public void updateSwimLaneTest() {
		SwimLane sl = new SwimLane();
		sl.setId(testId);
		sl.setName("assert this");
		boardService.createSwimLane(sl);
		sl.setName("updated swimlane assert");
		boardService.updateSwimLane(sl);
		
		SwimLane toTest = new SwimLane();
		toTest.setId(testId);
		toTest = boardService.getSwimLaneById(toTest);
		
		Assert.assertEquals("testing updateSwimlane", sl.getName(), toTest.getName());
	}
	
	@Test
	public void updateStoryTest() {
		Story s = new Story();
		s.setId(testId);
		s.setTitle("assert this");
		boardService.createStory(s);
		s.setTitle("updated story assert");
		
		Story toTest = new Story();
		toTest.setId(testId);
		toTest = boardService.getStoryById(toTest);
		
		Assert.assertEquals("testing updateStory", s.getTitle(), toTest.getTitle());
	}
	
	@Test
	public void updateTaskTest() {
		Task t = new Task();
		t.setId(testId);
		t.setName("assert this");
		boardService.createTask(t);
		
		Task toTest = new Task();
		toTest.setId(testId);
		toTest = boardService.getTaskById(toTest);
		
		Assert.assertEquals("testing updateTask", t.getName(), toTest.getName());
	}
	
	//DELETE TESTS
	//creates a board, deletes it, and tries to read it. Checks for null
	@Test
	public void deleteBoardTest() {
		Board b = new Board();
		b.setId(testId);
		b.setName("delete this");
		boardService.createBoard(b);
		
		boardService.deleteBoard(b);
		
		Board toTest = new Board();
		toTest.setId(testId);
		toTest = boardService.getBoardById(toTest);
		
		Assert.assertNull(toTest);
	}
	
	@Test
	public void deleteSwimLaneTest() {
		SwimLane sl = new SwimLane();
		sl.setId(testId);
		sl.setName("delete this");
		boardService.createSwimLane(sl);
		
		boardService.deleteSwimLane(sl);
		
		SwimLane toTest = new SwimLane();
		toTest.setId(testId);
		toTest = boardService.getSwimLaneById(toTest);
		
		Assert.assertNull(toTest);
	}
	
	@Test
	public void deleteStoryTest() {
		Story s = new Story();
		s.setId(testId);
		s.setTitle("delete this");
		boardService.createStory(s);
		
		boardService.deleteStory(s);
		
		Story toTest = new Story();
		toTest.setId(testId);
		toTest = boardService.getStoryById(toTest);
		
		Assert.assertNull(toTest);
	}
	
	@Test
	public void deleteTaskTest() {
		Task t = new Task();
		t.setId(testId);
		t.setName("delete this");
		boardService.createTask(t);
		
		boardService.deleteTask(t);
		
		Task toTest = new Task();
		toTest.setId(testId);
		toTest = boardService.getTaskById(toTest);
		
		Assert.assertNull(toTest);
	}
	
	
}
