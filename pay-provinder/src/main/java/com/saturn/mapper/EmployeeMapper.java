package com.saturn.mapper;

import java.util.List;

import com.saturn.model.Employee;
import com.saturn.mybatis.BaseMapper;
import com.saturn.mybatis.Page;

public interface EmployeeMapper extends BaseMapper<Employee>{

	List<Employee> findPage(Page<Employee> page);
	
}
