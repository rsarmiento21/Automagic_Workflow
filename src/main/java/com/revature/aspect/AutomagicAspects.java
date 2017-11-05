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
	
	@Around("execution(* login(..))")
	public void login(ProceedingJoinPoint pjp) throws Throwable{
		String message = "Attempting login";
		LOGGER.info(message);
		
		pjp.proceed();
		
		message = "After login";
		LOGGER.info(message);
	}
	
	
//	@Before("execution(* create*(..))")
//	public void beforeAl(JoinPoint jp) {
//		
//	}
	//CREATE Logs
	@Around("execution(* createBoard(..))")
	public void beforeCreates(ProceedingJoinPoint pjp) throws Throwable{
		String message = "ATTEMPTING CREATE BOARD: " + pjp.getSignature();
		LOGGER.info(message);
		
		pjp.proceed();
		
		message = "AFTER CREATE BOARD: " + pjp.getSignature();
	}
	
	@Around("execution(* createSwimLane(..))")
	public void beforeSwimlane(ProceedingJoinPoint pjp) throws Throwable{
		String message = "ATTEMPTING CREATE SWIMLANE: " + pjp.getSignature();
		LOGGER.info(message);
		
		pjp.proceed();
		
		message = "AFTER CREATE SWIMLANE: " + pjp.getSignature();
	}
	
	@Around("execution(* createStory(..))")
	public void beforeStory(ProceedingJoinPoint pjp) throws Throwable{
		String message = "ATTEMPTING CREATE STORY: " + pjp.getSignature();
		LOGGER.info(message);
		
		pjp.proceed();
		
		message = "AFTER CREATE STORY: " + pjp.getSignature();
	}
	
	@Around("execution(* createTask(..))")
	public void beforeBoard(ProceedingJoinPoint pjp) throws Throwable{
		String message = "ATTEMPTING CREATE TASK: " + pjp.getSignature();
		LOGGER.info(message);
		
		pjp.proceed();
		
		message = "AFTER CREATE TASK: " + pjp.getSignature();
	}
	
	
	@Before("execution(* deleteSwimLane(..))")
	public void beforeDelete(JoinPoint pjp) {
		
	}

}
