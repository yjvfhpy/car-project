package com.saturn.api;

import java.util.Map;

import com.saturn.annotation.DataSource;

public interface IDynamicDataSourceService extends CommonService {

	Map<String, Object> multipleDataSources();

	@DataSource("read")
	Map<String, Object> readDataSources();

	@DataSource("write")
	Map<String, Object> writeDataSources();
}
