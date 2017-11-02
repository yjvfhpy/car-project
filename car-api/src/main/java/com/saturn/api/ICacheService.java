package com.saturn.api;

import java.util.List;

import com.saturn.entity.TSStudent;

public interface ICacheService extends CommonService{
	String findCache(String id);
	
	List<TSStudent> byCache(String hql);
}
