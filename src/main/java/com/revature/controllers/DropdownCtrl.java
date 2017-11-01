package com.revature.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.domain.BoardUser;
import com.revature.service.BoardService;

@RestController
public class DropdownCtrl
{
	@Autowired
	private BoardService bo;
	
	@RequestMapping("/dropdown")
	@ResponseBody
	public ResponseEntity<Object> populateBoardDropdown(HttpServletRequest req)
	{
		BoardUser bu = new BoardUser();
		HttpSession session = req.getSession();
		if(session != null)
		{
			if(session.getAttribute("user") != null)
			{
				bu = bo.getBoardUserById((BoardUser)session.getAttribute("user"));
				if(bu != null)
				{
					System.out.println("DropdownCtrl -GET");
					return new ResponseEntity<>(bu,HttpStatus.OK);
					
				}
				
			}
			
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
}
