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
	
	@Before("execution(* com.revature.service.AuthService.login(..))")
	public void login(JoinPoint jp) {
		String message = "Attempting login";
		LOGGER.info(message);
	}
	
	//After Login
	
	@Before("execution(* com.revature.service.BoardService.create* (..))")
	public void beforeCreate(JoinPoint jp) {
		String message = "ATTEMPTING CREATE AT: " + jp.getSignature();
		LOGGER.info(message);
	}
	
	@AfterReturning("execution(* com.revature.service.BoardService.create* (..))")
	public void afterCreate(JoinPoint jp) {
		String message = "AFTER GOOD CREATE AT: " + jp.getSignature();
		LOGGER.info(message);
	}
	
	@AfterThrowing("execution(* com.revature.service.BoardService.create* (..))")
	public void badCreate(JoinPoint jp) {
		String message = "AFTER BAD CREATE AT: " + jp.getSignature();
		LOGGER.warn(message);
	}
//	
//	//CREATES
//	//BEFORE CREATE Logs
//	@Before("execution(* createBoard(..))")
//	public void beforeCreateBoard(JoinPoint jp) {
//		String message = "ATTEMPTING CREATE BOARD: " + jp.getSignature();
//		LOGGER.info(message);
//	}
//	
//	@Before("execution(* createSwimLane(..))")
//	public void beforeCreateSwimlane(JoinPoint jp) {
//		String message = "ATTEMPTING CREATE SWIMLANE: " + jp.getSignature();
//		LOGGER.info(message);
//		
//	}
//	
//	@Before("execution(* createStory(..))")
//	public void beforeCreateStory(JoinPoint jp) {
//		String message = "ATTEMPTING CREATE STORY: " + jp.getSignature();
//		LOGGER.info(message);
//		
//	}
//	
//	@Before("execution(* createTask(..))")
//	public void beforeCreateTask(JoinPoint jp) {
//		String message = "ATTEMPTING CREATE TASK: " + jp.getSignature();
//		LOGGER.info(message);
//	}
//	
//	
//	//AFTER RETURN CREATE LOGS
//	@AfterReturning("execution(* createBoard(..))")
//	public void afterCreateBoard(JoinPoint jp) {
//		String message = "AFTER GOOD CREATE BOARD: " + jp.getSignature();
//		LOGGER.info(message);
//	}
//	
//	@AfterReturning("execution(* createSwimLane(..))")
//	public void afterCreateSwimlane(JoinPoint jp) {
//		String message = "AFTER GOOD CREATE SWIMLANE: " + jp.getSignature();
//		LOGGER.info(message);
//		
//	}
//	
//	@AfterReturning("execution(* createStory(..))")
//	public void afterCreateStory(JoinPoint jp) {
//		String message = "AFTER GOOD CREATE STORY: " + jp.getSignature();
//		LOGGER.info(message);
//		
//	}
//	
//	@AfterReturning("execution(* createTask(..))")
//	public void afterCreateTask(JoinPoint jp) {
//		String message = "AFTER GOOD CREATE TASK: " + jp.getSignature();
//		LOGGER.info(message);
//	}
//	
//	//AFTER EXCEPTION CREATE LOGS
//	@AfterThrowing("execution(* createBoard(..))")
//	public void badCreateBoard(JoinPoint jp) {
//		String message = "AFTER BAD CREATE BOARD: " + jp.getSignature();
//		LOGGER.warn(message);
//	}
//	
//	@AfterThrowing("execution(* createSwimLane(..))")
//	public void badCreateSwimlane(JoinPoint jp) {
//		String message = "AFTER BAD CREATE SWIMLANE: " + jp.getSignature();
//		LOGGER.warn(message);
//	}
//	
//	@AfterThrowing("execution(* createStory(..))")
//	public void badCreateStory(JoinPoint jp) {
//		String message = "AFTER BAD CREATE STORY: " + jp.getSignature();
//		LOGGER.warn(message);
//	}
//	
//	@AfterThrowing("execution(* createTask(..))")
//	public void badCreateTask(JoinPoint jp) {
//		String message = "AFTER BAD CREATE TASK: " + jp.getSignature();
//		LOGGER.warn(message);
//	}
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	//EDITS
//	//BEFORE EDIT Logs
//	@Before("execution(* editBoard(..)) || execution(* updateBoard(..))")
//	public void beforeEditBoard(JoinPoint jp) {
//		String message = "ATTEMPTING TO EDIT BOARD: " + jp.getSignature();
//		LOGGER.info(message);
//	}
//	
//	@Before("execution(* editSwimLane(..)) || execution(* updateSwimLane(..))")
//	public void beforeEditSwimlane(JoinPoint jp) {
//		String message = "ATTEMPTING TO EDIT SWIMLANE: " + jp.getSignature();
//		LOGGER.info(message);
//	}
//	
//	@Before("execution(* editStory(..)) || execution(* updateStory(..))")
//	public void beforeEditStory(JoinPoint jp) {
//		String message = "ATTEMPTING TO EDIT STORY: " + jp.getSignature();
//		LOGGER.info(message);
//	}
//	
//	@Before("execution(* editTask(..)) || execution(* updateTask(..))")
//	public void beforeEditTask(JoinPoint jp) {
//		String message = "ATTEMPTING TO EDIT TASK: " + jp.getSignature();
//		LOGGER.info(message);
//	}
//	
//	
//	//AFTER RETURN EDIT LOGS
//	@AfterReturning("execution(* editBoard(..)) || execution(* updateBoard(..))")
//	public void afterEditBoard(JoinPoint jp) {
//		String message = "AFTER GOOD EDIT BOARD: " + jp.getSignature();
//		LOGGER.info(message);
//	}
//	
//	@AfterReturning("execution(* editSwimLane(..)) || execution(* updateSwimLane(..))")
//	public void afterEditSwimlane(JoinPoint jp) {
//		String message = "AFTER GOOD EDIT SWIMLANE: " + jp.getSignature();
//		LOGGER.info(message);
//	}
//	
//	@AfterReturning("execution(* editStory(..)) || execution(* updateStory(..))")
//	public void afterEditStory(JoinPoint jp) {
//		String message = "AFTER GOOD EDIT STORY: " + jp.getSignature();
//		LOGGER.info(message);
//	}
//	
//	@AfterReturning("execution(* editTask(..)) || execution(* updateTask(..))")
//	public void afterEditTask(JoinPoint jp) {
//		String message = "AFTER GOOD EDIT TASK: " + jp.getSignature();
//		LOGGER.info(message);
//	}
//	
//	//AFTER EXCEPTION CREATE LOGS
//	@AfterThrowing("execution(* editBoard(..)) || execution(* updateBoard(..))")
//	public void badEditBoard(JoinPoint jp) {
//		String message = "AFTER BAD EDIT BOARD: " + jp.getSignature();
//		LOGGER.warn(message);
//	}
//	
//	@AfterThrowing("execution(* editSwimLane(..)) || execution(* updateSwimLane(..))")
//	public void badEditSwimlane(JoinPoint jp) {
//		String message = "AFTER BAD EDIT SWIMLANE: " + jp.getSignature();
//		LOGGER.warn(message);
//	}
//	
//	@AfterThrowing("execution(* editStory(..)) || execution(* updateStory(..))")
//	public void badEditStory(JoinPoint jp) {
//		String message = "AFTER BAD EDIT STORY: " + jp.getSignature();
//		LOGGER.warn(message);
//	}
//	
//	@AfterThrowing("execution(* editTask(..)) || execution(* updateTask(..))")
//	public void badEditTask(JoinPoint jp) {
//		String message = "AFTER BAD EDIT TASK: " + jp.getSignature();
//		LOGGER.warn(message);
//	}

	
}
