package com.revature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.revature.dao.BoardDaoImpl;
import com.revature.dao.Dao;
import com.revature.domain.Board;
import com.revature.domain.BoardUser;
import com.revature.domain.SwimLane;
import com.revature.service.BoardService;

@Controller
public class MainClass {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/main")
	public String main() {
		return "/resources/dummy.txt";
	}
	
	//TESTING PURPOSES ONLY
	public static void main(String[] args) {
		
		
		
		Board b = new Board();
		b.setId(26);
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		
		BoardService bs = (BoardService) ac.getBean("boardService");
		
		SwimLane sl = new SwimLane();
		sl.setBoard(b);
		sl.setName("Another Test Lane");
		
		bs.createSwimLane(sl);
		
//		b = dao.getBoardById(b);
//		
//		
//		SwimLane sl = new SwimLane();
//		sl.setBoard(b);
//		sl.setName("test swim lane");
//		
//		dao.createSwimLane(sl);
	}
}
