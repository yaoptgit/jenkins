package com.api.reflect;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Demo3 {
	public static void main(String[] args) throws Exception {
		// 反射执行方法
		// 1、获取字节码对象
		Class clazz1 = Class.forName("com.api.reflect.Case");
		//2、调用空参构造 创建对象
		Object obj = clazz1.newInstance();
		Object obj2 = clazz1.newInstance();
		//3、获取指定方法
		Method setMethod = clazz1.getMethod("setApiId",String.class);
		setMethod.invoke(obj, "1"); //相当于 case.setCaseId("1");
		setMethod.invoke(obj2, "2");//相当于 case2.setCaseId();
		Method getMethod = clazz1.getMethod("getApiId");
		
		//4、调用方法
		Object value1 = getMethod.invoke(obj); 	//相当于 case.getCaseId();
		Object value2 = getMethod.invoke(obj2);	//相当于 case2.getCaseId();
		System.out.println(value1);
		System.out.println(value2);
		System.out.println("====================================");
		System.out.println(setMethod.getName());
		System.out.println(getMethod.getName());
		System.out.println(Arrays.toString(setMethod.getParameterTypes()));
		System.out.println(Arrays.toString(getMethod.getParameterTypes()));
		System.out.println(setMethod.getReturnType());
		System.out.println(getMethod.getReturnType());
		System.out.println(setMethod.getParameterCount());
		System.out.println(getMethod.getParameterCount());
		
	}
}
