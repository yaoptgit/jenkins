package com.api.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

import com.api.pojo.Case;
import com.api.pojo.Variable;

public class CaseUtils {
	
	
	public static List<Case> list = new ArrayList<Case>();
	public static List<Variable> variables = new ArrayList<Variable>();
	static {
		list = ExcelUtls.read("用例", Case.class);
		variables = ExcelUtls.read("变量", Variable.class);
	}

	// 把list转成Object[][] 
	public static Object[][] datas(String apiId, String[] cellNames) {
		List<Case> fileterList;
		if (StringUtils.isEmpty(apiId)) {
			fileterList = list;
		}else {
			fileterList = filterCaseByApiId(apiId);
		}
		fileterList  = filterCaseByApiId(apiId);		
		Object[][] datas = new Object[fileterList.size()][cellNames.length];
		for (int i = 0; i < fileterList.size(); i++) { // 循环行
			Case case1 = fileterList.get(i);
			for (int j = 0; j < cellNames.length; j++) { // 循环列
				// 反射调用get方法
				String methodName = "get" + cellNames[j];
				try {
					Method method = Case.class.getMethod(methodName);
					Object value = method.invoke(case1);
					datas[i][j] = value;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return datas;
	}

	public static List<Case> filterCaseByApiId(String apiId) {
		List<Case> fileterList = new ArrayList<Case>();
		//遍历所有的Case
		for (Case case1 : list) {
			//如果ApiId相等添加到集合中
			if (apiId.equals(case1.getApiId())) {
				fileterList.add(case1);
			}
		}
		return fileterList;
	}

	/**
	 * 替换参数
	 * @param str	未替换参数
	 * @return		替换后的参数
	 */
	public static String replaceVariable(String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		//{"mobilephone":"${toBeRegisterMobilephone}","pwd":"${toBeRegisterPassword}"}
		for (Variable var : variables) {
			if (str.contains(var.getName())) {
				str = str.replace(var.getName(), var.getValue());
			}
		}
		return str;
	}
}
