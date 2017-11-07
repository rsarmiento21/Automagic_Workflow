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
	//Login
	@Before("execution(* com.revature.service.AuthService.login(..))")
	public void login(JoinPoint jp) {
		String message = "Attempting login";
		LOGGER.info(message);
	}
	
	@AfterReturning("execution(* com.revature.service.AuthService.login(..))")
	public void goodLogin(JoinPoint jp) {
		String message = "Good login";
		LOGGER.info(message);
	}
	
	@AfterThrowing("execution(* com.revature.service.AuthService.login(..))")
	public void badLogin(JoinPoint jp) {
		String message = "Bad login";
		LOGGER.info(message);
	}
	
	//logout
	@Before("execution(* com.revature.controllers.LoginCtrl.logout*(..))")
	public void logout(JoinPoint jp) {
		String message = "Attempting logout";
		LOGGER.info(message);
	}
	
	@AfterReturning("execution(* com.revature.controllers.LoginCtrl.logout*(..))")
	public void goodLogout(JoinPoint jp) {
		String message = "Good logout";
		LOGGER.info(message);
	}
	
	@AfterThrowing("execution(* com.revature.controllers.LoginCtrl.logout*(..))")
	public void badLogout(JoinPoint jp) {
		String message = "Bad logout";
		LOGGER.info(message);
	}
	
	//CREATES
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
	
	
	//EDITS/UPDATES
	@Before("execution(* com.revature.service.BoardService.update* (..))")
	public void beforeUpdate(JoinPoint jp) {
		String message = "ATTEMPTING UPDATE AT: " + jp.getSignature();
		LOGGER.info(message);
	}
	
	@AfterReturning("execution(* com.revature.service.BoardService.update* (..))")
	public void afterUpdate(JoinPoint jp) {
		String message = "AFTER GOOD UPDATE AT: " + jp.getSignature();
		LOGGER.info(message);
	}
	
	@AfterThrowing("execution(* com.revature.service.BoardService.update* (..))")
	public void badUpdate(JoinPoint jp) {
		String message = "AFTER BAD UPDATE AT: " + jp.getSignature();
		LOGGER.warn(message);
	}
	
	//DELETES
	@Before("execution(* com.revature.service.BoardService.delete* (..))")
	public void beforeDelete(JoinPoint jp) {
		String message = "ATTEMPTING DELETE AT: " + jp.getSignature();
		LOGGER.info(message);
	}
	
	@AfterReturning("execution(* com.revature.service.BoardService.delete* (..))")
	public void afterDelete(JoinPoint jp) {
		String message = "AFTER GOOD DELETE AT: " + jp.getSignature();
		LOGGER.info(message);
	}
	
	@AfterThrowing("execution(* com.revature.service.BoardService.delete* (..))")
	public void badDelete(JoinPoint jp) {
		String message = "AFTER BAD DELETE AT: " + jp.getSignature();
		LOGGER.warn(message);
	}
}
