package com.saturn.mybatis;

import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

/**
 * Mybatis支持对Executor、StatementHandler、PameterHandler和ResultSetHandler进行拦截，
 * 也就是说会对这4种对象进行代理
 * 
 * @Intercepts用于表明当前的对象是一个Interceptor
 * @Signature则表明要拦截的接口、方法以及对应的参数类型
 * 
 **/

@Intercepts({ @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
		RowBounds.class, ResultHandler.class }) })
public class ExecutorPlugin implements Interceptor {

	private String properties;

	/**
	 * 实现拦截逻辑的地方，内部要通过invocation.proceed()显式地推进责任链前进，也就是调用下一个拦截器拦截目标方法。
	 **/
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		return invocation.proceed();
	}

	
	
	/**
	 plugin方法是拦截器用于封装目标对象的，通过该方法我们可以返回目标对象本身，也可以返回一个它的代理
	  
	 **/
	@Override
	public Object plugin(Object target) {

		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		
	}

	public String getProperties() {
		return properties;
	}

	public void setProperties(String properties) {
		this.properties = properties;
	}

}
