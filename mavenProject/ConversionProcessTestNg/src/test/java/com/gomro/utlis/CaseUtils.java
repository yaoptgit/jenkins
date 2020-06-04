package com.gomro.utlis;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import com.gomro.pojo.LoginData;


/**
 * @author yaopengtao
 * @data 2019年10月25日 下午2:41:13
 */
public class CaseUtils {
	public static <T> Object[][] getDataProviders(List<T> list, String[] cellNames) {
		Object[][] datas = new Object[list.size()][cellNames.length];
		// 1、循环list集合
		for (int i = 0; i < list.size(); i++) {
			// 2、循环cellName 数组
			for (int j = 0; j < cellNames.length; j++) {
				// 3、通过反射根据列名调用对象getXxx() 方法返回具体的值
				String cellName = cellNames[j]; // isNegative
				String getMathodName = "get" + cellName; // getIsNegative
//				T t = list.get(i);
//				Class clazz = t.getClass();
				try {
					Method method = list.get(i).getClass().getMethod(getMathodName);
					// invoke 执行对象
					Object value = method.invoke(list.get(i));
					// 4、存储二位数组并返回
					datas[i][j] = value;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return datas;
	}
	
	
	
	public static void main(String[] args) {
		List<LoginData> list = ExcelUtils.read("src/test/resources/login.xlsx", "Sheet1",LoginData.class );
		String[] cellNames = { "IsNegative", "UserName", "Password", "ErrorMsg" };
		Object[][] datas = CaseUtils.getDataProviders(list, cellNames);
		for (Object[] objects : datas) {
			System.out.println(Arrays.toString(objects));
		}
	}
}
















