package com.saturn.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 *在Spring中，实现ApplicationContextAware并覆盖setApplicationContext方法, 就可以获得ApplicationContext了。
 */
public class ApplicationContextUtil implements ApplicationContextAware {

	private static ApplicationContext context;

	
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		ApplicationContextUtil.context = context;
	}

	public static ApplicationContext getContext() {
		return context;
	}
}
