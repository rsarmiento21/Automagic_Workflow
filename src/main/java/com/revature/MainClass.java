package com.revature;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.revature.domain.BoardUser;
import com.revature.service.BoardService;

@Controller
public class MainClass 
{
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/main")
	public String main() 
	{
		
		BoardUser bu = new BoardUser();
		bu.setId(21);
		bu = boardService.getBoardUserById(bu);
		
		System.out.println(bu);
		
		return "/resources/dummy.txt";
	}	
}
