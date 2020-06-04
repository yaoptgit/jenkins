package com.gomro.cases;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.gomro.factory.BaseTester;
import com.gomro.pojo.PriceCalibrationData;
import com.gomro.utlis.CaseUtils;
import com.gomro.utlis.ExcelUtils;

/**
 * 核价定标
 * 
 * @author admin
 *
 */
public class PriceCalibrationCase extends BaseTester {
	// IsNegative(是否为正向用例) Desc(用例描述) UserName(用户名) Pwd(密码)Error(错误提示)
	@Test(dataProvider = "datas")
	public void test(ITestContext testContext, String IsNegative, String Desc, String UserName, String Pwd,
			String Error) throws Throwable {
		testContext.setAttribute("caseId", i++);
		log.info("====================核价定标===================");
		String pageName = "核价定标";
		// 1、退出供应商账号
		HoverMouse(pageName, "账号名称");
		click(pageName, "退出");
		// 2、登录客户账号
		sendKeys(pageName, "用户名", UserName);
		sendKeys(pageName, "密码", Pwd);
		click(pageName, "登录");
		System.out.println("Username = " + UserName + "\nPwd = " + Pwd);
		// 3、进入到我的核价页面
		open(PriceCalibration_PAGE);
		// 4、点击询价编号
		Thread.sleep(3000);
		click(pageName, "点击询价编号");
		// 获取订单商品号用于断言
		Thread.sleep(3000);
		String name = getText(pageName, "商品名称");
		System.out.println("定标商品名称 = " + name);
		// 5、点击一键最低价定标
		click(pageName, "一键最低价定标");
		// 7、断言
		if ("0".equals(IsNegative)) {
			// 错误提示
			String actual = getText(pageName, "错误提示");
			// 断言
			assertActualExpected(actual, Error);
			log.info(pageName + "执行完毕");
		} else {
			// 6、提示弹窗确定
			click(pageName, "提示弹窗确定");
			try {
				Thread.sleep(2000);
				// 切换到我的定标单页面
				open(MyCalibrationOrder_PAGE);
				Thread.sleep(2000);
				String text = getText(pageName, "我的定标单中的商品名称");
				System.out.println("我的定标单商品名称 = " + text);
				assertActualExpected(text, name);
			} catch (Exception e) {
				e.printStackTrace();
				Assert.assertTrue(false);
				log.info(e.toString());
			}finally {
				log.info(pageName + "执行完毕");
			}
		}
	}

	@DataProvider
	public static Object[][] datas() {
		List<PriceCalibrationData> list = ExcelUtils.read("src/test/resources/PriceCalibration.xlsx", "Sheet1",
				PriceCalibrationData.class);
		// IsNegative(是否为正向用例) Desc(用例描述) UserName(用户名) Pwd(密码)Error(错误提示)
		String[] cellNames = { "IsNegative", "Desc", "UserName", "Pwd", "Error" };
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
