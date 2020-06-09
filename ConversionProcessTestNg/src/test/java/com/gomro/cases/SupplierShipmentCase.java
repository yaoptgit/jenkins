package com.gomro.cases;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.gomro.factory.BaseTester;
import com.gomro.pojo.SupplierShipmentData;
import com.gomro.utlis.CaseUtils;
import com.gomro.utlis.ExcelUtils;

/**
 * 供应商发货
 * 
 * @author admin
 *
 */
public class SupplierShipmentCase extends BaseTester {
	// IsNegative(是否为正向用例) Desc(用例描述) UserName(用户名) Pwd(密码) LogisticsName(物流名称)
	// ShipmentNumber(物流单号) Freight(运费) Error(错误提示)
	@Test(dataProvider = "datas")
	public void test(ITestContext testContext, String IsNegative,String Desc ,String UserName, String Pwd, String LogisticsName,
			String ShipmentNumber, String Freight, String Error) throws Throwable {
		log.info("========================供应商发货======================");
		testContext.setAttribute("caseId", i++);
		String pageName = "供应商发货";
		//退出客户账号并登录供应商账号
		HoverMouse(pageName, "账号名称");
		click(pageName, "退出");
		open("https://test-pm.gomro.cn/login");
		sendKeys(pageName, "用户名", UserName);
		sendKeys(pageName, "密码", Pwd);
		click(pageName, "登录");
		// 1、进入到待接单单页面
		open(SALESORDER_PAGE);
		click(pageName, "待接单");
		//2、选择订单付款框
		click(pageName, "订单复选框");
		//3、点击确认接单
		click(pageName, "确认接单");
		//4、信息框点击确定
		click(pageName, "信息框点击确定");
		//5、点击进入待发货页面
		click(pageName, "待发货");
		// 6、供应商订单序号
		click(pageName, "供应商订单序号");
		// 7、点击全选复选框
		Thread.sleep(2000);
		click(pageName, "全选复选框");
		// 8、点击发货
		click(pageName, "发货");
		// 9、输入物流名称
		sendKeys(pageName, "物流名称", LogisticsName);
		// 10、输入物流单号
		sendKeys(pageName, "物流单号", ShipmentNumber);
		// 11、输入运费
		//sendKeys(pageName, "运费", Freight);
		// 12、点击立即提交
		click(pageName, "立即提交");
		Thread.sleep(4000);
		// 13、断言
		if ("0".equals(IsNegative)) {
			// 错误提示
			String actual = getText(pageName, "错误提示");
			// 断言
			assertActualExpected(actual, Error);
		} else {
			try {
				open(SALESORDER_PAGE);
				Thread.sleep(2000);
				//点击全部
				click(pageName, "全部");
				Thread.sleep(2000);
				String text = getText(pageName, "获取订单状态");
				System.out.println("订单状态 = " + text);
				assertActualExpected(text, "已发货");
			} catch (Exception e) {
				e.printStackTrace();
				Assert.assertTrue(false);
				log.info(e.toString());
			}
		}

	}

	@DataProvider
	public static Object[][] datas() {
		List<SupplierShipmentData> list = ExcelUtils.read("src/test/resources/SupplierShipment.xlsx", "Sheet1",
				SupplierShipmentData.class);
		// IsNegative(是否为正向用例) Desc(用例描述) UserName(用户名) Pwd(密码) LogisticsName(物流名称)
		// ShipmentNumber(物流单号) Freight(运费) Error(错误提示)
		String[] cellNames = { "IsNegative", "Desc","UserName", "Pwd", "LogisticsName", "ShipmentNumber", "Freight", "Error" };
		Object[][] datas = CaseUtils.getDataProviders(list, cellNames);
		return datas;
	}

	public static void main(String[] args) {
		Object[][] datas = datas();
		for (Object[] objects : datas) {
			System.out.println(Arrays.toString(objects));
		}
	}
}
