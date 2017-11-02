package com.saturn.aspect;

import org.apache.commons.lang.time.StopWatch;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 日志log
 */
@Component
@Aspect
public class LogAspect {
	

	@Pointcut("execution(* com.saturn.controller..*.*(..))")
	public void mypoint() {

	}

	@Before("mypoint()")
	public void before(JoinPoint joinPoint) throws Throwable {
		System.out.println("@Before");
	}

	@After("mypoint()")
	public void after() {
		System.out.println("@after");
	}

	@AfterReturning(pointcut = "mypoint()")
	public void simpleAdvice() {
		System.out.println("@AfterReturning");
	}

	/*
	 * 环绕通知就类似Filter,在一个方法中包含开始,执行,结束,抛出异常 , 甚至可以不调用joinPoint的proceed方法执行真实逻辑
	 * 亦可以多次调用,全由开发者业务逻辑决定
	 */
	@Around("mypoint()")
	public Object aroundLogCalls(ProceedingJoinPoint joinPoint) throws Throwable {
		StopWatch clock = new StopWatch();
		clock.start(); // 计时开始
		Object result = joinPoint.proceed();
		clock.stop(); // 计时结束

		String methodName = joinPoint.getSignature().getName();
		// 显示出方法原型及耗时
		System.out.println( methodName + "方法执行：" + clock.getTime() + " ms");
		return result;
	}

}
