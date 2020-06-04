package com.gomro.cases;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.gomro.factory.BaseTester;
import com.gomro.pojo.PlatformConfirmsReceiptData;
import com.gomro.utlis.CaseUtils;
import com.gomro.utlis.ExcelUtils;

/**
 * 平台确认收票
 * 
 * @author yaopengtao
 * @data 2020年3月30日 上午10:43:31
 */
public class PlatformConfirmsReceiptCase extends BaseTester {
	// IsNegative(是否为正向用例) Desc(用例描述) UserName(用户名) Pwd(密码) Error(错误提示) TipPopup(弹窗提示)
	@Test(dataProvider = "datas")
	public void test(ITestContext testContext, String IsNegative, String Desc, String UserName, String Pwd,
			String Error,String TipPopup) throws Throwable {
		testContext.setAttribute("caseId", i++);
		log.info("=========================平台确认收票======================");
		String pageName = "平台确认收票";
		// 1、退出供应商账号
		HoverMouse(pageName, "账号名称");
		isDisplayElement("退出按钮", "//a[@href='/logout']", "退出");
		click(pageName, "退出");
		// 2、登录平台账号
		sendKeys(pageName, "用户名", UserName);
		sendKeys(pageName, "密码", Pwd);
		click(pageName, "登录");
		System.out.println("Username = " + UserName + "\nPwd = " + Pwd);
		// 3、进入到平台发票页面
		open(PlatformInvoice_PAGE);
		// 4、点击平台供应商发票申请单管理
		click(pageName, "平台供应商发票申请单管理");
		// 5、选择一条发票数据
		click(pageName, "选择发票");
		// 6、点击确认收票
		click(pageName, "确认收票");
		// 7、点击确定
		click(pageName, "确定");
		// 获取提示弹窗内容
		Thread.sleep(2000);
		String actualText = getText(pageName, "弹窗内容");
		System.out.println("弹窗内容 = " + actualText);
		// 8、提示弹窗点击确定
		click(pageName, "弹窗确定");
		// 9、断言
		if ("0".equals(IsNegative)) {
			// 错误提示
			String actual = getText(pageName, "错误提示");
			// 断言
			assertActualExpected(actual, Error);
		} else {
			try {
				assertActualExpected(actualText, TipPopup);
			} catch (Exception e) {
				e.printStackTrace();
				Assert.assertTrue(false);
				log.info(e.toString());
			}
		}

	}

	@DataProvider
	public static Object[][] datas() {
		List<PlatformConfirmsReceiptData> list = ExcelUtils.read("src/test/resources/PlatformConfirmsReceipt.xlsx", "Sheet1",
				PlatformConfirmsReceiptData.class);
		// IsNegative(是否为正向用例) Desc(用例描述) UserName(用户名) Pwd(密码) Error(错误提示) TipPopup(弹窗提示)
		String[] cellNames = { "IsNegative", "Desc", "UserName", "Pwd", "Error", "TipPopup"};
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
