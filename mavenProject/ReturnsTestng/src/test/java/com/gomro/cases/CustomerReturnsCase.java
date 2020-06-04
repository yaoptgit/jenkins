package com.gomro.cases;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.gomro.factory.BaseTester;
import com.gomro.pojo.CustomerReturnsData;
import com.gomro.utlis.CaseUtils;
import com.gomro.utlis.ExcelUtils;

/**
 * 客户退货
 * 
 * @author yaopengtao
 * @data 2020年4月9日 上午8:26:04
 */
public class CustomerReturnsCase extends BaseTester {
	// IsNegative(是否为正向用例) Desc(用例描述) UserName(用户名) Pwd(密码) LogisticsName(物流名称)
	// ShipmentNumber(物流单号)Error(错误提示) TipPopup(弹窗提示)
	@Test(dataProvider = "datas")
	public void test(ITestContext testContext, String IsNegative, String Desc, String UserName, String Pwd,
			String LogisticsName, String ShipmentNumber, String Error, String TipPopup) throws Throwable {
		testContext.setAttribute("caseId", i++);
		log.info("=====================客户退货====================");
		String pageName = "客户退货";
		Thread.sleep(2000);
		// 1、退出供应商账号并登录客户账号
		HoverMouse(pageName, "账号名称");
		click(pageName, "退出");
		sendKeys(pageName, "用户名", UserName);
		sendKeys(pageName, "密码", Pwd);
		click(pageName, "登录");
		// 2、进入到采购订单页面并点击全部tab标签
		open(PurchaseOrder_PAGE);
		click(pageName, "全部");
		// 3、点击退货的客户订单编号
		click(pageName, "客户订单编号");
		// 4、选择退款商品
		click(pageName, "退款商品");
		// 5、点击填写退货物流
		click(pageName, "填写退货物流");
		// 6、输入物流名称
		isDisplayElement(pageName + "物流名称", "//*[@id=\"logistics\"]", "");
		sendKeys(pageName, "物流名称", LogisticsName);
		// 7、输入物流单号
		sendKeys(pageName, "物流单号", ShipmentNumber);
		// 8、点击立即提交
		click(pageName, "立即提交");
		// 9、断言
		if ("0".equals(IsNegative)) {
			// 获取报错信息
			Thread.sleep(500);
			String text = getText(pageName, "错误提示");
			System.out.println("错误提示：" + text);
			assertConatins(pageName, "错误提示", Error);
			Thread.sleep(1000);
			// 刷新页面
			driver.navigate().refresh();
		} else if ("1".equals(IsNegative)) {
			try {
				// 8、警告弹窗确认
				click(pageName, "警告弹窗确定");
				String text = getText(pageName, "发货弹窗提示");
				System.out.println("发货弹窗提示：" + text);
				assertConatins(pageName, "发货弹窗提示", TipPopup);
			} catch (Exception e) {
				e.printStackTrace();
				Assert.assertTrue(false);
				log.info(e.toString());
			}
		} else {
			log.info("请修改测试用例IsNegative列里边存1(正向)或0(反向)");
			Assert.assertTrue(false);
		}

	}

	@DataProvider
	public static Object[][] datas() {
		List<CustomerReturnsData> list = ExcelUtils.read("src/test/resources/CustomerReturns.xlsx", "Sheet1",
				CustomerReturnsData.class);
		// IsNegative(是否为正向用例) Desc(用例描述) UserName(用户名) Pwd(密码) LogisticsName(物流名称)
		// ShipmentNumber(物流单号)Error(错误提示) TipPopup(弹窗提示)
		String[] cellNames = { "IsNegative", "Desc", "UserName", "Pwd", "LogisticsName", "ShipmentNumber", "Error",
				"TipPopup" };
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
