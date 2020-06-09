package com.gomro.cases;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.gomro.factory.BaseTester;
import com.gomro.pojo.UserReceiptData;
import com.gomro.utlis.CaseUtils;
import com.gomro.utlis.ExcelUtils;

/**
 * 客户收货 自动化测试培训及演示
 * 
 * @author admin
 *
 */
public class UserReceiptCase extends BaseTester {
	// IsNegative(是否为正向用例) Desc(用例描述) UserName(用户名) Pwd(密码) Error(错误提示)
	@Test(dataProvider = "datas")
	public void test(ITestContext testContext, String IsNegative, String Desc, String UserName, String Pwd,
			String Error) {
		testContext.setAttribute("caseId", i++);
		String pageName = "客户收货";
		// 1、退出供应商账号并登录用户账号
		HoverMouse(pageName, "账号名称");
		click(pageName, "退出");
		sendKeys(pageName, "用户名", UserName);
		sendKeys(pageName, "密码", Pwd);
		click(pageName, "登录");
		// 2、进入到采购订单页面
		open(PurchaseOrder_PAGE);
		// 3、点击待收货
		click(pageName, "待收货");
		// 4、选择商品复选框
		click(pageName, "商品复选框");
		// 5、获取商品编号
		// 6、点击收货
		click(pageName, "收货");
		// 7、确认收货
		click(pageName, "确认收货");
		// 8、断言
		if ("0".equals(IsNegative)) {
			// 错误提示
			String actual = getText(pageName, "错误提示");
			// 断言
			assertActualExpected(actual, Error);
		} else {
			try {
				Thread.sleep(1000);
				String text = getText(pageName, "收货提示");
				System.out.println("收货提示 ： " + text);
				assertConatins(pageName, "收货提示", "收货成功");
			} catch (Exception e) {
				e.printStackTrace();
				Assert.assertTrue(false);
				log.info(e.toString());
			}
		}
	}

	@DataProvider
	public static Object[][] datas() {
		List<UserReceiptData> list = ExcelUtils.read("src/test/resources/UserReceipt.xlsx", "Sheet1",
				UserReceiptData.class);
		// IsNegative(是否为正向用例) Desc(用例描述) UserName(用户名) Pwd(密码) Error(错误提示)
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
