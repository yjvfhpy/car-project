package com.saturn.service;

import org.springframework.stereotype.Service;

import com.saturn.api.IUserService;


@Service("userService")
public  class UserServiceImpl extends CommonServiceImpl implements IUserService {
	@Override
	public String sayHello(String name) {
		
		return "sayHello： hello," + name;
	}

}
