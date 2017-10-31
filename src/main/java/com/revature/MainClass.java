package com.revature;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.revature.domain.Board;
import com.revature.domain.BoardUser;
import com.revature.domain.Story;
import com.revature.domain.SwimLane;
import com.revature.domain.Task;
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
		Set<Board> b = bu.getBoards();
	
		System.out.println(b);
	
//		Set<SwimLane> sl = b.get(0).getSwimLanes();
//		
//		System.out.println(sl);
//		
//		Set<Story> st = sl.get(0).getStories();
//		
//		System.out.println(st);
//		
//		Set<Task> tsk = st.get(0).getTasks();
//		
//		System.out.println(tsk);
		return "/resources/dummy.txt";
		
		
	}	

	
}