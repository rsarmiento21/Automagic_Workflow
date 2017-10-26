package com.revature;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.revature.domain.Board;
import com.revature.domain.BoardUser;
import com.revature.service.BoardService;

@Controller
public class MainClass {
	
	@RequestMapping(value="/main")
	public void main(BoardService boardService) {
		

		BoardUser bu = new BoardUser();
		bu.setId(1);
		System.out.println(boardService);
		System.out.println(bu);
		bu = boardService.getBoardUserById(bu);
		
		Board b = new Board();
		b.setId(25);
		
//		System.out.println(bu);
	}	
}
