package com.revature.aspect;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


//implement log4j

@Aspect
@Component("aspect")
public class AutomagicAspects {
	
	private final Logger LOGGER = Logger.getLogger(AutomagicAspects.class);
	
	static {
		//sets a system property to make a new log each time
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HHmmss z");
		System.setProperty("current.date", dateFormat.format(new Date()));
	}
	
	@Before("execution(* login(..))")
	public void highjackPlays(JoinPoint jp) {
		//log something
		String message = "hello world";
		LOGGER.warn(message);
		
		System.out.println(jp.getSignature());
	}
}
