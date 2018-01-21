package com.jerryguowei.springdemo.aspect;

import java.util.logging.Logger;

import javax.persistence.criteria.Join;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {
 private Logger logger=Logger.getLogger(CRMLoggingAspect.class.getName());
 
 @Pointcut("execution(* com.jerryguowei.springdemo.controller.*.*(..))")
 private void forControllerPackage(){}
 @Pointcut("execution(* com.jerryguowei.springdemo.service.*.*(..))")
 private void forServicePackage(){}
 @Pointcut("execution(* com.jerryguowei.springdemo.dao.*.*(..))")
 private void forDaoPackage(){}
 
 @Pointcut("forControllerPackage()||forServicePackage()||forDaoPackage()")
 private void forAppFlow(){}
 
 @Before("forAppFlow()")
 public void before(JoinPoint theJointPoint){
	String theMethod=theJointPoint.getSignature().toShortString();
	 logger.info("===>in @Before: calling method:"+theMethod);
	 Object args[]=theJointPoint.getArgs();
	 for(Object arg:args){
		 logger.info("=====the argument:"+arg);
	 }
 }
// @AfterReturning(pointcut="forAppFlow()",returning="theResult")
// public Object afterReturning(JoinPoint joinpoint, Object theResult){
//	
//	 logger.info(theResult.toString());
//	 return theResult;
// }
 
}
