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
		
//		BoardUser bu = new BoardUser();
//		bu.setId(1);
//		bu = app.getBoardUserById(bu);
//		
//		for(Board b : bu.getBoards()) {
//			b.setName("Board of Steven");
//			app.updateBoard(b);
//		}
//		
//		Board b = new Board();
//		b.setName("Test board");
//		b.setOwner(bu);
//		
//		app.createBoard(b);
		
		Board b = new Board();
		b.setId(25);
		
		app.deleteBoard(b);
		
//		System.out.println(bu);
	}	
}
