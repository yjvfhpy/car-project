package com.saturn.service;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.rpc.service.GenericException;
import com.alibaba.dubbo.rpc.service.GenericService;

/**
 * 泛化实现 
 * 
 * 泛接口实现方式主要用于服务器端没有API接口及模型类元的情况，参数及返回值中的所有POJO均用Map表示，
 * 通常用于框架集成，比如：实现一个通用的远程服务Mock框架，可通过实现GenericService接口处理所有服务请求。
 **/

@Service("genericService")
public class GenericServiceImpl implements GenericService {

	@Override
	public Object $invoke(String methodName, String[] parameterTypes, Object[] args) throws GenericException {
		if ("export".equals(methodName)) {
			return "Server export on--> " + args[0] + ":" + args[1];
		} else {
			return "no method invoke";
		}
	}
}
