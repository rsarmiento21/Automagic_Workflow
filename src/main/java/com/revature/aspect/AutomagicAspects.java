package com.revature.aspect;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component("aspect")
public class AutomagicAspects {
	
	private final Logger LOGGER = Logger.getLogger(AutomagicAspects.class);
	
	static {
		//sets a system property to make a new log each time, per day
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd z");
		System.setProperty("current.date", dateFormat.format(new Date()));
	}
	
	@Before("execution(* login(..))")
	public void login(JoinPoint jp) {
		String message = "Attempting login";
		LOGGER.info(message);
		
	}
	
	
//	@Before("execution(* create*(..))")
//	public void beforeAl(JoinPoint jp) {
//		
//	}
	//CREATE Logs
	@Before("execution(* createBoard(..))")
	public void beforeCreateBoard(JoinPoint jp) {
		String message = "ATTEMPTING CREATE BOARD: " + jp.getSignature();
		LOGGER.info(message);
	}
	
	@Before("execution(* createSwimLane(..))")
	public void beforeCreateSwimlane(JoinPoint jp) {
		String message = "ATTEMPTING CREATE SWIMLANE: " + jp.getSignature();
		LOGGER.info(message);
		
	}
	
	@Before("execution(* createStory(..))")
	public void beforeCreateStory(JoinPoint jp) {
		String message = "ATTEMPTING CREATE STORY: " + jp.getSignature();
		LOGGER.info(message);
		
	}
	
	@Before("execution(* createTask(..))")
	public void beforeCreateTask(JoinPoint jp) {
		String message = "ATTEMPTING CREATE TASK: " + jp.getSignature();
		LOGGER.info(message);
	}
	
}
