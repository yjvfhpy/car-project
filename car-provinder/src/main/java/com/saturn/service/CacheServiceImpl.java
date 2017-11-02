package com.saturn.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.saturn.api.ICacheService;
import com.saturn.entity.TSStudent;

@Service("cacheService")
public class CacheServiceImpl extends CommonServiceImpl implements ICacheService {

	//AtomicInteger通过一种线程安全的加减操作接口
	private final AtomicInteger i = new AtomicInteger();

    public String findCache(String id) {
    	
        return "request: " + id + ", response: " + i.getAndIncrement();
    }

	@Override
	public List<TSStudent> byCache(String hql) {
		List<TSStudent> students = commonDao.findHql(hql);
		return students;
	}
    
}
