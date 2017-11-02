package com.saturn;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 服务容器是一个standalone的启动程序，因为后台服务不需要Tomcat或JBoss等Web容器的功能，如果硬要用Web容器去加载服务提供方，
 * 增加复杂性，也浪费资源。 服务容器只是一个简单的Main方法，并加载一个简单的Spring容器，用于暴露服务。
 */
public class App {
	private static ClassPathXmlApplicationContext context;

	public static void main(String[] args) throws IOException {
		context = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml" });
		context.start();
		System.out.println("按任意键退出");
		System.in.read();
	}
}
