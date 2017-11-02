package com.saturn.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.RpcContext;
import com.saturn.api.CommonService;
import com.saturn.hibernate.dao.ICommonDao;

@Service("commonService")
@Transactional
public class CommonServiceImpl implements CommonService {
	private static final Logger logger = Logger.getLogger(CommonServiceImpl.class);
	public ICommonDao commonDao = null;

	@Resource
	public void setCommonDao(ICommonDao commonDao) {
		this.commonDao = commonDao;
	}

	public Integer getAllDbTableSize() {
		return commonDao.getAllDbTableSize();
	}

	public <T> List<T> findHql(String hql, Object... param) {

		return commonDao.findHql(hql, param);
	}

	@Override
	public Map<String, Object> findOneForJdbc(String sql, Object... objs) {

		return commonDao.findOneForJdbc(sql, objs);
	}

	@Override
	public <T> List<T> loadAll(Class<T> entityClass) {
		
		String uname = RpcContext.getContext().getAttachment("uname"); // 获取客户端隐式传入的参数，用于框架集成，不建议常规业务使用
		logger.info("获取客户端隐式传入的参数，用于框架集成，不建议常规业务使用：" + uname);
		// 上下文信息
		boolean isConsumerSide = RpcContext.getContext().isProviderSide(); // 本端是否为服务端，这里会返回true
		logger.info("本端是否为服务端:" + isConsumerSide);
		String serverIP = RpcContext.getContext().getLocalHost(); // 获取最后一次调用的提供方IP地址
		Integer port = RpcContext.getContext().getLocalPort();// 获取最后一次调用的提供方端口地址
		logger.info("获取最后一次调用的提供方IP:端口地址:" + serverIP + ":" + port);
		// 获取当前服务配置信息，所有配置信息都将转换为URL的参数
		URL urls = RpcContext.getContext().getUrl();
		logger.info("获取当前服务配置信息:" + urls);
		
		return commonDao.loadAll(entityClass);
	}

	/**
	 * 根据实体名获取对象
	 */
	public <T> T get(Class<T> class1, Serializable id) {
		return commonDao.get(class1, id);
	}
	
	
	/**
	 * 根据传入的实体添加或更新对象
	 * @param <T>
	 * @param entity
	 */
	@Override
	public <T> void saveOrUpdate(T entity) {
		commonDao.saveOrUpdate(entity);
	}

}
