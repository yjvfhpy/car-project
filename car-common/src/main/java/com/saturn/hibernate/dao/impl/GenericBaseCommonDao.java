package com.saturn.hibernate.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.saturn.hibernate.dao.IGenericBaseCommonDao;


/**
 *
 * 类描述： DAO层泛型基类
 */
@SuppressWarnings({"hiding","unchecked"})
public abstract class GenericBaseCommonDao<T, PK extends Serializable> implements IGenericBaseCommonDao {
	
	/**
	 * 初始化Log4j的一个实例
	 */
	private static final Logger logger = Logger.getLogger(GenericBaseCommonDao.class);

	/**
	 * 注入一个sessionFactory属性,并注入到父类(HibernateDaoSupport)
	 * **/
	@Autowired
	@Qualifier("sessionFactory") 
	private SessionFactory sessionFactory;
	
	
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Autowired
	@Qualifier("namedParameterJdbcTemplate")
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	

	public Session getSession() {
		// 事务必须是开启的(Required)，否则获取不到
		return sessionFactory.getCurrentSession();
	}
	
	
	/**
	 * 获取所有数据表
	 *
	 * @return
	 */
	public Integer getAllDbTableSize() {
		SessionFactory factory = getSession().getSessionFactory();
		Map<String, ClassMetadata> metaMap = factory.getAllClassMetadata();
		return metaMap.size();
	}
	
	
	
	public Map<String, Object> findOneForJdbc(String sql, Object... objs) {
		try {
			return this.jdbcTemplate.queryForMap(sql, objs);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	
	
	/**
	 * 通过hql 查询语句查找对象
	 *
	 * @param <T>
	 * @param query
	 * @return
	 */


	public <T> List<T> findHql(String hql, Object... param) {
		Query q = getSession().createQuery(hql);
		//缓存
		q.setCacheable(true);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.list();
	}
	
	
	public <T> List<T> loadAll(final Class<T> entityClass) {
		Criteria criteria = createCriteria(entityClass);
		//缓存
		criteria.setCacheable(true);
		return criteria.list();
	}
	
	
	/**
	 * 创建单一Criteria对象
	 *
	 * @param <T>
	 * @param entityClass
	 * @param criterions
	 * @return
	 */
	private <T> Criteria createCriteria(Class<T> entityClass) {
		Criteria criteria = getSession().createCriteria(entityClass);
		return criteria;
	}
	
	
	
	/**
	 * 根据传入的实体添加或更新对象
	 *
	 * @param <T>
	 *
	 * @param entity
	 */

	public <T> void saveOrUpdate(T entity) {
		try {
			getSession().saveOrUpdate(entity);
			getSession().flush();
			if (logger.isDebugEnabled()) {
				logger.debug("添加或更新成功," + entity.getClass().getName());
			}
		} catch (RuntimeException e) {
			logger.error("添加或更新异常", e);
			throw e;
		}
	}
	
	
	/**
	 * 根据传入的实体删除对象
	 */
	public <T> void delete(T entity) {
		try {
			getSession().delete(entity);
			getSession().flush();
			if (logger.isDebugEnabled()) {
				logger.debug("删除成功," + entity.getClass().getName());
			}
		} catch (RuntimeException e) {
			logger.error("删除异常", e);
			throw e;
		}
	}
	
	
	/**
	 * 根据Id获取对象。
	 */
	public <T> T get(Class<T> entityClass, final Serializable id) {

		return (T) getSession().get(entityClass, id);

	}
	
	
	

	
}
