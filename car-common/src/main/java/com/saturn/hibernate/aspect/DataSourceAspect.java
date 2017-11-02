package com.saturn.hibernate.aspect;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.saturn.hibernate.annotation.DataSource;
import com.saturn.hibernate.datasource.DataSourceContextHolder;
import com.saturn.hibernate.datasource.DataSourceType;

@Aspect
@Component
public class DataSourceAspect {
	private static final Logger logger = Logger.getLogger(DataSourceAspect.class);
	
	@Pointcut("execution(* com.saturn.service.*.*(..))")
	public void pointCut() {
		
	};

	@Before(value = "pointCut()")
	public void before(JoinPoint point) {
		Object target = point.getTarget();
		String method = point.getSignature().getName();
		Class<?>[] classz = target.getClass().getInterfaces();
		Class<?>[] parameterTypes = ((MethodSignature) point.getSignature()).getMethod().getParameterTypes();
		try {
			Method m = classz[0].getMethod(method, parameterTypes);
			if (m != null && m.isAnnotationPresent(DataSource.class)) {
				DataSource data = m.getAnnotation(DataSource.class);
				String value = data.value();
				if("read".equalsIgnoreCase(value)){
					DataSourceContextHolder.setDataSourceType(DataSourceType.dataSource_read);
				}
				if("write".equalsIgnoreCase(value)){
					logger.info(value);
					DataSourceContextHolder.setDataSourceType(DataSourceType.dataSource_write);
				}
			}
			else{
				DataSourceContextHolder.setDataSourceType(DataSourceType.dataSource_read);
			}
			logger.info(DataSourceContextHolder.getDataSourceType());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}