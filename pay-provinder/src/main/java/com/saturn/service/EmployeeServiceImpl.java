package com.saturn.service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.saturn.api.IEmployeeService;
import com.saturn.mapper.EmployeeMapper;
import com.saturn.model.Employee;
import com.saturn.mybatis.Page;

@Service("employeeService")
public class EmployeeServiceImpl  implements IEmployeeService {
	
	@Resource
	private	EmployeeMapper employeeMapper;

	@Override
	public void optdb() {
		 printClassDefinition(employeeMapper.getClass());
		
		 Page<Employee> page = new Page<Employee>();  
		 page.setPageNo(1);
		 page.setPageSize(3);
		 List<Employee> employeess = employeeMapper.findPage(page); 
		 employeess = employeeMapper.selectAll();
		 System.out.println(employeess.size());
		 for (Employee employee : employeess) {
			 System.out.println(employee.getName());
		}
	}
	
	
	public static String getModifier(int modifier) {
		String result = "";
		switch (modifier) {
		case Modifier.PRIVATE:
			result = "private";
		case Modifier.PUBLIC:
			result = "public";
		case Modifier.PROTECTED:
			result = "protected";
		case Modifier.ABSTRACT:
			result = "abstract";
		case Modifier.FINAL:
			result = "final";
		case Modifier.NATIVE:
			result = "native";
		case Modifier.STATIC:
			result = "static";
		case Modifier.SYNCHRONIZED:
			result = "synchronized";
		case Modifier.STRICT:
			result = "strict";
		case Modifier.TRANSIENT:
			result = "transient";
		case Modifier.VOLATILE:
			result = "volatile";
		case Modifier.INTERFACE:
			result = "interface";
		}
		return result;
	}

	public static void printClassDefinition(Class<?> clz) {

		String clzModifier = getModifier(clz.getModifiers());
		if (clzModifier != null && !clzModifier.equals("")) {
			clzModifier = clzModifier + " ";
		}
		String superClz = clz.getSuperclass().getName();
		if (superClz != null && !superClz.equals("")) {
			superClz = "extends " + superClz;
		}

		Class<?>[] interfaces = clz.getInterfaces();

		String inters = "";
		for (int i = 0; i < interfaces.length; i++) {
			if (i == 0) {
				inters += "implements ";
			}
			inters += interfaces[i].getName();
		}

		System.out.println(clzModifier + clz.getName() + " " + superClz + " " + inters);
		System.out.println("{");

		Field[] fields = clz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			String modifier = getModifier(fields[i].getModifiers());
			if (modifier != null && !modifier.equals("")) {
				modifier = modifier + " ";
			}
			String fieldName = fields[i].getName();
			String fieldType = fields[i].getType().getName();
			System.out.println("    " + modifier + fieldType + " " + fieldName + ";");
		}

		System.out.println();

		Method[] methods = clz.getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];

			String modifier = getModifier(method.getModifiers());
			if (modifier != null && !modifier.equals("")) {
				modifier = modifier + " ";
			}

			String methodName = method.getName();

			Class<?> returnClz = method.getReturnType();
			String retrunType = returnClz.getName();

			Class<?>[] clzs = method.getParameterTypes();
			String paraList = "(";
			for (int j = 0; j < clzs.length; j++) {
				paraList += clzs[j].getName();
				if (j != clzs.length - 1) {
					paraList += ", ";
				}
			}
			paraList += ")";

			clzs = method.getExceptionTypes();
			String exceptions = "";
			for (int j = 0; j < clzs.length; j++) {
				if (j == 0) {
					exceptions += "throws ";
				}

				exceptions += clzs[j].getName();

				if (j != clzs.length - 1) {
					exceptions += ", ";
				}
			}

			exceptions += ";";

			String methodPrototype = modifier + retrunType + " " + methodName + paraList + exceptions;

			System.out.println("    " + methodPrototype);

		}
		System.out.println("}");
	}
	
}
