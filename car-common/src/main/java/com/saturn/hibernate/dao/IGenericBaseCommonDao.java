package com.saturn.hibernate.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 
 * 类描述：DAO层泛型基类接口
 */
public interface IGenericBaseCommonDao {

	public Integer getAllDbTableSize();
	
	/**
	 * 通过JDBC查找对象集合 使用指定的检索标准检索数据返回数据
	 */
	public Map<String, Object> findOneForJdbc(String sql, Object... objs);
	
	
	/**
	 * 通过hql 查询语句查找对象
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public <T> List<T> findHql(String hql, Object... param);
	
	
	
	/**
	 * 加载全部实体
	 * 
	 * @param <T>
	 * @param entityClass
	 * @return
	 */
	public <T> List<T> loadAll(final Class<T> entityClass);
	
	
	public <T> void saveOrUpdate(T entity);

	/**
	 * 删除实体
	 * 
	 * @param <T>
	 * 
	 * @param <T>
	 * 
	 * @param <T>
	 * @param entitie
	 */
	public <T> void delete(T entitie);
	
	
	
	/**
	 * 根据实体名称和主键获取实体
	 * 
	 * @param <T>
	 * @param entityName
	 * @param id
	 * @return
	 */
	public <T> T get(Class<T> entityName, Serializable id);
	


}
