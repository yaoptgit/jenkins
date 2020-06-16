package com.api.reflect;

public class Demo {
	public static void main(String[] args) throws Exception {
		//反射  反射就是根据一些特性信息，创建对象。
		Case c = new Case();
		//通过全类名获取，（包名+类名）
		Class calzz1 = Class.forName("com.api.reflect.Case");
		//通过类名.class获取
		Class clazz2 = Case.class;
		//通过类名.getClass 获取
		Class clazz3 = c.getClass();
		System.out.println(calzz1);
		System.out.println(calzz1 == clazz2);
		System.out.println(clazz2 == clazz3);
	}
}
