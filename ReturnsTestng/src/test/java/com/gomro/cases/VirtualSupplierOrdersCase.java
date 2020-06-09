package com.gomro.cases;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.gomro.factory.BaseTester;
import com.gomro.pojo.VirtualSupplierOrdersData;
import com.gomro.utlis.CaseUtils;
import com.gomro.utlis.ExcelUtils;

/**
 * 虚拟供应商接单
 * 
 * @author yaopengtao
 * @data 2020年4月23日 上午9:25:10
 */
public class VirtualSupplierOrdersCase extends BaseTester {
	// IsNegative(是否为正向用例) Desc(用例描述) UserName(用户名) Pwd(密码) PromptPopup(弹窗提示)
	@Test(dataProvider = "datas")
	public void test(ITestContext testContext, String IsNegative, String Desc, String UserName, String Pwd,
			String PromptPopup) {
		log.info("========================虚拟供应商接单======================");
		testContext.setAttribute("caseId", i++);
		String pageName = "虚拟供应商接单";
		// 退出客户账号并登录供应商账号
		HoverMouse(pageName, "账号名称");
		click(pageName, "退出");
		open("https://test-pm.gomro.cn/login");
		sendKeys(pageName, "用户名", UserName);
		sendKeys(pageName, "密码", Pwd);
		click(pageName, "登录");
		// 1、进入到待接单单页面
		open(SALESORDER_PAGE);
		click(pageName, "待接单");
		// 2、选择订单付款框
		click(pageName, "订单复选框");
		// 3、点击确认接单
		click(pageName, "确认接单");
		if ("0".equals(IsNegative)) {
			Assert.assertTrue(false);
			log.info(pageName + "执行完毕");
		} else {
			try {
				click(pageName, "弹窗确认");
				String textExpected = getText(pageName, "接单提示");
				System.out.println("接单提示：" + textExpected);
				assertActualExpected(PromptPopup, textExpected);
				Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
				Assert.assertTrue(false);
				log.info(e.toString());
			} finally {
				log.info(pageName + "----执行完毕");
			}
		}
	}

	@DataProvider
	public static Object[][] datas() {
		List<VirtualSupplierOrdersData> list = ExcelUtils.read("src/test/resources/VirtualSupplierOrders.xlsx",
				"Sheet1", VirtualSupplierOrdersData.class);
		// IsNegative(是否为正向用例) Desc(用例描述) UserName(用户名) Pwd(密码) PromptPopup(弹窗提示)
		String[] cellNames = { "IsNegative", "Desc", "UserName", "Pwd", "PromptPopup" };
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
