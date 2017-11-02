package com.saturn.hibernate.dao.impl;


import org.springframework.stereotype.Repository;

import com.saturn.hibernate.dao.ICommonDao;
import com.saturn.hibernate.dao.IGenericBaseCommonDao;

/**
 * 公共扩展方法
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Repository
public class CommonDao extends GenericBaseCommonDao implements ICommonDao, IGenericBaseCommonDao {
	
}
