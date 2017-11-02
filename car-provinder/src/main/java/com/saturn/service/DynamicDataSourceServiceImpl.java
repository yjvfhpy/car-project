package com.saturn.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.saturn.api.IDynamicDataSourceService;
import com.saturn.entity.TSStudent;
import com.saturn.hibernate.datasource.DataSourceContextHolder;
import com.saturn.hibernate.datasource.DataSourceType;


@Service("dynamicDataSourceService")
public class DynamicDataSourceServiceImpl extends CommonServiceImpl implements IDynamicDataSourceService {
	
	private static final Logger logger = Logger.getLogger(DynamicDataSourceServiceImpl.class);

	public Map<String, Object> multipleDataSources() {
		
		//默认是:dataSource_read
		List<TSStudent> datas = commonDao.loadAll(TSStudent.class);
		logger.info(datas);
		
		//切换数据源:dataSource_write
		DataSourceContextHolder.setDataSourceType(DataSourceType.dataSource_write);
		Map<String, Object> maps = commonDao.findOneForJdbc("SELECT * FROM `momotest`.`uinfo` LIMIT 1");
		logger.info(maps);
		return maps;
	}

	@Override
	public Map<String, Object> readDataSources() {
		Map<String, Object> maps = commonDao.findOneForJdbc("SELECT * FROM `t_s_student` LIMIT 1");
		logger.info(maps);
		return maps;
	}

	@Override
	public Map<String, Object> writeDataSources() {
		Map<String, Object> maps = commonDao.findOneForJdbc("SELECT * FROM `momotest`.`uinfo` LIMIT 1");
		logger.info(maps);
		return maps;
	}
}
