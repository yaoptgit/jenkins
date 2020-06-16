package com.api.reflect;

import java.lang.reflect.Constructor;
import java.util.Arrays;

public class Demo2 {
	public static void main(String[] args) throws Exception {
		//通过反射创建对象
//		Case c = new Case();
		//1、获取字节码对象
		Class clazz1 = Class.forName("com.api.reflect.Case");
		//2、调用空参构造
		Object obj = clazz1.newInstance();
		System.out.println(obj);
		//3、通过其他构造方法创建对象
//		Constructor[] constructors = clazz1.getConstructors();
//		System.out.println(Arrays.toString(constructors));
		//3.1、获得空参参数
		Constructor constructor = clazz1.getConstructor();
		Object obj2 = constructor.newInstance();
		System.out.println(obj2);
		//3.2、获取有参构造 创建对象
		Constructor constructor2 = clazz1.getConstructor(String.class,String.class,String.class,String.class);
		Object obj3 = constructor2.newInstance("1","传手机号，不穿密码","{\"mobilephone\":\"19000000000\",\"pwd\":\"\"}","1");
		System.out.println(obj3);
	}
	
	public void method(int ... arr) {//可变参数，可以传0-n个参数。
		for (int i : arr) {
			
		}
	}
	
}
