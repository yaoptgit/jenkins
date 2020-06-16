package com.api.utils;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * 加载properties文件
 * 
 */
public class PropertiesUtils {
	// 当别人调用PropertiesUtils的属性是，就加载config.properties文件
	public static Properties prop = new Properties();
	static {
		// 静态代码块，当这个类第一次被别人调用的时候，这段代码就会执行
		//System.out.println("我是静态代码块");
		try {
			FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
			prop.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
