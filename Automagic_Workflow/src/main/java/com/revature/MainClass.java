package com.revature;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.domain.Board;
import com.revature.domain.BoardUser;
import com.revature.service.BoardService;

public class MainClass {
	
	public static void main(String[] args) {
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		BoardService app = (BoardService) ac.getBean("boardService");
		
		BoardUser bu = new BoardUser();
		bu.setId(21);
		bu = app.getBoardUserById(bu);
		bu.setPassword("sark");
		app.updateBoardUserService(bu);
		for(Board bd : bu.getBoards())
		{
			bd.setName("sticks");
		}
		app.updateBoardUserService(bu);
		
	}	
}
