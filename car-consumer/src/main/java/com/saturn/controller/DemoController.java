package com.saturn.controller;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.service.EchoService;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.saturn.api.ICacheService;
import com.saturn.api.ICallbackListener;
import com.saturn.api.ICallbackService;
import com.saturn.api.IDynamicDataSourceService;
import com.saturn.api.IEmployeeService;
import com.saturn.api.IGenericService;
import com.saturn.api.IUinfoService;
import com.saturn.context.ApplicationContextUtil;
import com.saturn.entity.TSStudent;

@Controller
@RequestMapping(value = "/demo")
public class DemoController {
	private static final Logger logger = Logger.getLogger(DemoController.class);
	
	@Resource
	private IUinfoService uinfoService;

	@Resource
	private IEmployeeService employeeService;

	@Resource
	private ICacheService cacheService;

	@Resource
	private Map<String, Object> cache;
	
	@Resource
	private ICallbackService callbackService;
	
	@Resource
	private IGenericService genericService;
	
	@Resource
	private IDynamicDataSourceService dynamicDataSourceService;
	
	
	
	
	
	@RequestMapping(value = "/json")
	@ResponseBody
	public List<TSStudent> json(HttpServletRequest request,HttpServletResponse response) {
		List<TSStudent> datas = cacheService.byCache(" from TSStudent");
		return datas;
	}
	
	
	/**
	 *hibernate整合ehcache
	 *
	 * **/
	@RequestMapping(value = "/ehcache")
	public List<TSStudent> ehcache() {
		List<TSStudent> datas = cacheService.byCache(" from TSStudent");
		return datas;
	}
	
	
	/**
	 *多数据源
	 * **/
	@RequestMapping(value = "/dynamic")
	public String dynamic(Map<String, Object> map) {
		dynamicDataSourceService.multipleDataSources();
		
		
		dynamicDataSourceService.readDataSources();
		
		
		dynamicDataSourceService.writeDataSources();
		
		return "/uinfo/list";
	}
	
	
	/**
	 * 泛化引用
	 * 
	 * 泛接口调用方式主要用于客户端没有API接口及模型类元的情况，参数及返回值中的所有POJO均用Map表示，
	 * 通常用于框架集成，比如：实现一个通用的服务测试框架，可通过GenericService调用所有服务实现
	 * **/
	
	@RequestMapping(value = "/generic")
	public String generic(Map<String, Object> map,HttpServletRequest request,HttpServletResponse response) {
		
		String r = genericService.export("132.26.26.25", 8080);
		logger.info("泛化实现：" + r);
		
		ApplicationContext context = ApplicationContextUtil.getContext();
		GenericService userService = (GenericService) context.getBean("userService");
		Object result = userService.$invoke("sayHello", new String[] {"java.lang.String"}, new Object[] {"micalliu"}); 
		logger.info(result);
		
		String address = RpcContext.getContext().getUrl().getAddress();
		
		ApplicationConfig application = new ApplicationConfig();  
        application.setName("vector-consumer");  
		// 引用远程服务 
		ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>(); // 该实例很重量，里面封装了所有与注册中心及服务提供方连接，请缓存
		reference.setInterface("com.saturn.api.IUserService"); // 弱类型接口名 
		reference.setGeneric(true); // 声明为泛化接口 
		reference.setApplication(application);
		reference.setUrl("dubbo://" + address + "/com.saturn.api.IUserService");
		
		GenericService genericService = reference.get(); // 用com.alibaba.dubbo.rpc.service.GenericService可以替代所有接口引用 
		// 基本类型以及Date,List,Map等不需要转换，直接调用 
		Object returns = genericService.$invoke("sayHello", new String[] {"java.lang.String"}, new Object[] {"micalliu"}); 
		logger.info("基本类型以及Date,List,Map等不需要转换，直接调用:" + returns);
		
		// 用Map表示POJO参数，如果返回值为POJO也将自动转成Map 
		Object obj = genericService.$invoke("get", new String[]{"java.lang.Class","java.io.Serializable"}, new Object[]{TSStudent.class,"1"}); 
		
		if(obj instanceof Map){
			logger.info(true);
		}
		
		logger.info(obj);
		return "/uinfo/list";
	}
	
	
	

	/**
	 * 上下文  隐式传参
	 **/
	@RequestMapping(value = "/list")
	public String list(Map<String, Object> map) {
		
		// 隐式传参 setAttachment设置的KV，在完成下面一次远程调用会被清空。即多次远程调用要多次设置。
		RpcContext.getContext().setAttachment("uname", "micalliu");
		
		// 先远程调用
		List<TSStudent> list = uinfoService.loadAll(TSStudent.class);
		
		// 上下文信息
		boolean isConsumerSide = RpcContext.getContext().isConsumerSide(); // 本端是否为服务端，这里会返回true
		logger.info("本端是否为服务端:" + isConsumerSide);
		String serverIP = RpcContext.getContext().getRemoteHost(); // 获取最后一次调用的提供方IP地址
		Integer port = RpcContext.getContext().getRemotePort();// 获取最后一次调用的提供方端口地址
		logger.info("获取最后一次调用的提供方IP:端口地址:" + serverIP + ":" + port);
		// 获取当前服务配置信息，所有配置信息都将转换为URL的参数
		URL urls = RpcContext.getContext().getUrl();
		logger.info("获取当前服务配置信息:" + urls);

		map.put("list", list);
		return "/uinfo/list";
	}

	/**
	 * 回声测试用于检测服务是否可用，回声测试按照正常请求流程执行，能够测试整个调用是否通畅，可用于监控。
	 * 所有服务自动实现EchoService接口，只需将任意服务引用强制转型为EchoService，即可使用。
	 **/

	@RequestMapping(value = "/echo")
	public String echo(Map<String, Object> map) {
		logger.info(uinfoService.getClass().getName());
		printClassDefinition(uinfoService.getClass());
		EchoService echoService = (EchoService) uinfoService; // 强制转型为EchoService
		String status = (String) echoService.$echo("OK"); // 回声测试可用性
		logger.info("回声测试可用性:" + status);
		return "/uinfo/list";
	}

	/**
	 * 基于NIO的非阻塞实现并行调用，客户端不需要启动多线程即可完成并行调用多个远程服务，相对多线程开销较小。
	 * 
	 * @throws Exception
	 * @throws InterruptedException
	 **/

	@RequestMapping(value = "/async")
	public String async(Map<String, Object> map) throws Exception {
		// 此调用会立即返回null
		List<TSStudent> list = uinfoService.findHql(" from  TSStudent ");
		Future<List<TSStudent>> future = RpcContext.getContext().getFuture(); // 拿到调用的Future引用，当结果返回后，会被通知和设置到此Future。
		// 此调用会立即返回null
		Map<String, Object> maps = uinfoService.findOneForJdbc("select * from t_s_student limit 1");
		Future<Map<String, Object>> mapFuture = RpcContext.getContext().getFuture(); // 拿到调用的Future引用，当结果返回后，会被通知和设置到此Future。

		// 此时findHql和findOneForJdbc的请求同时在执行，客户端不需要启动多线程来支持并行，而是借助NIO的非阻塞完成。
		list = future.get(); // 如果list已返回，直接拿到返回值，否则线程wait住，等待list返回后，线程会被notify唤醒。
		maps = mapFuture.get(); // 同理等待maps返回。
		logger.info("maps:" + maps);
		// 如果list需要5秒返回，maps需要6秒返回，实际只需等6秒，即可获取到list和maps，进行接下来的处理。
		map.put("list", list);
		return "/uinfo/list";
	}

	/**
	 * mybaties 提供服务
	 **/
	@RequestMapping(value = "/pay")
	public String pay(Map<String, Object> map) {
		employeeService.optdb();;
		return "/uinfo/list";
	}

	/**
	 * 
	 **/
	@RequestMapping(value = "/redis")
	public String redis(Map<String, Object> map) {
		cache.remove("hello");
		Object value = cache.get("hello");
		System.out.println(value);
		if (value != null) {
			throw new IllegalStateException(value + " != null");
		}
		cache.put("hello", "world");
		value = cache.get("hello");
		System.out.println(value);
		if (!"world".equals(value)) {
			throw new IllegalStateException(value + " != world");
		}
		return "/uinfo/list";
	}

	/**
	 * 
	 **/
	@RequestMapping(value = "/cache")
	public String cache(Map<String, Object> map) throws Exception {
		// 测试缓存生效，多次调用返回同样的结果。(服务器端自增长返回值)
		String fix = null;
		for (int i = 0; i < 5; i++) {
			String result = cacheService.findCache("0");
			if (fix == null || fix.equals(result)) {
				System.out.println("i=" + i + " OK: " + result);
			} else {
				System.err.println("i=" + i + " ERROR: " + result);
			}
			fix = result;
			Thread.sleep(500);
		}

		// LRU的缺省cache.size为1000，执行1001次，应有溢出
		for (int n = 0; n < 1001; n++) {
			String pre = null;
			for (int i = 0; i < 10; i++) {
				String result = cacheService.findCache(String.valueOf(n));
				if (pre != null && !pre.equals(result)) {
					System.err.println("ERROR: " + result);
				}
				pre = result;
			}
		}

		// 测试LRU有移除最开始的一个缓存项
		String result = cacheService.findCache("0");
		if (fix != null && !fix.equals(result)) {
			System.out.println("OK: " + result);
		} else {
			System.err.println("ERROR: " + result);
		}

		return "/uinfo/list";
	}
	
	
	
	/**
	 * callback
	 **/
	@RequestMapping(value = "/callback")
	public String callback(Map<String, Object> map) {
		callbackService.addListener("foo.bar", new ICallbackListener() {
			public void changed(String msg) {
				System.out.println("callback1:" + msg);
			}
		});
		return "/uinfo/list";
	}
	
	

	public static String getModifier(int modifier) {
		String result = "";
		switch (modifier) {
		case Modifier.PRIVATE:
			result = "private";
		case Modifier.PUBLIC:
			result = "public";
		case Modifier.PROTECTED:
			result = "protected";
		case Modifier.ABSTRACT:
			result = "abstract";
		case Modifier.FINAL:
			result = "final";
		case Modifier.NATIVE:
			result = "native";
		case Modifier.STATIC:
			result = "static";
		case Modifier.SYNCHRONIZED:
			result = "synchronized";
		case Modifier.STRICT:
			result = "strict";
		case Modifier.TRANSIENT:
			result = "transient";
		case Modifier.VOLATILE:
			result = "volatile";
		case Modifier.INTERFACE:
			result = "interface";
		}
		return result;
	}

	public static void printClassDefinition(Class<?> clz) {

		String clzModifier = getModifier(clz.getModifiers());
		if (clzModifier != null && !clzModifier.equals("")) {
			clzModifier = clzModifier + " ";
		}
		String superClz = clz.getSuperclass().getName();
		if (superClz != null && !superClz.equals("")) {
			superClz = "extends " + superClz;
		}

		Class<?>[] interfaces = clz.getInterfaces();

		String inters = "";
		for (int i = 0; i < interfaces.length; i++) {
			if (i == 0) {
				inters += "implements ";
			}
			inters += interfaces[i].getName();
		}

		System.out.println(clzModifier + clz.getName() + " " + superClz + " " + inters);
		System.out.println("{");

		Field[] fields = clz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			String modifier = getModifier(fields[i].getModifiers());
			if (modifier != null && !modifier.equals("")) {
				modifier = modifier + " ";
			}
			String fieldName = fields[i].getName();
			String fieldType = fields[i].getType().getName();
			System.out.println("    " + modifier + fieldType + " " + fieldName + ";");
		}

		System.out.println();

		Method[] methods = clz.getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];

			String modifier = getModifier(method.getModifiers());
			if (modifier != null && !modifier.equals("")) {
				modifier = modifier + " ";
			}

			String methodName = method.getName();

			Class<?> returnClz = method.getReturnType();
			String retrunType = returnClz.getName();

			Class<?>[] clzs = method.getParameterTypes();
			String paraList = "(";
			for (int j = 0; j < clzs.length; j++) {
				paraList += clzs[j].getName();
				if (j != clzs.length - 1) {
					paraList += ", ";
				}
			}
			paraList += ")";

			clzs = method.getExceptionTypes();
			String exceptions = "";
			for (int j = 0; j < clzs.length; j++) {
				if (j == 0) {
					exceptions += "throws ";
				}

				exceptions += clzs[j].getName();

				if (j != clzs.length - 1) {
					exceptions += ", ";
				}
			}

			exceptions += ";";

			String methodPrototype = modifier + retrunType + " " + methodName + paraList + exceptions;

			System.out.println("    " + methodPrototype);

		}
		System.out.println("}");
	}

}
