package com.gomro.cases;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.gomro.factory.BaseTester;
import com.gomro.pojo.LoginData;
import com.gomro.utlis.CaseUtils;
import com.gomro.utlis.ExcelUtils;

/**
 * 从虚拟供应商切换为平台账号
 * @author yaopengtao
 * @data 2020年4月24日 上午9:55:24
 */
public class SwitchPlatformAccountCase extends BaseTester{
	// IsNegative(是否为正向用例) Desc(用例描述) UserName(用户名) Password(密码) ErrorMsg(错误提示期望值)
	@Test(dataProvider = "datas")
	public void test(ITestContext testContext,String IsNegative,String UserName,String Password ,String ErrorMsg) {
		testContext.setAttribute("caseId", i++);
		String pageName = "从虚拟供应商切换为平台账号";
		log.info("===================从虚拟供应商切换为平台账号开始===================");
		//1、退出并登录平台账号
		HoverMouse(pageName, "账号名称");
		click(pageName, "退出");
		sendKeys(pageName, "用户名", UserName);
		sendKeys(pageName, "密码", Password);
		click(pageName, "登录");
		//2、断言
		if ("0".equals(IsNegative)) {
			String actual = getText("登录", "错误提示");
			System.out.println("错误提示：" + actual);
			assertActualExpected(actual, ErrorMsg);
			log.info(pageName + "执行完毕");
		} else {
			try {
				//如果登录后的url包含portal则通过
				assertUrl("main");
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
		List<LoginData> list = ExcelUtils.read("src/test/resources/SwitchPlatformAccount.xlsx", "Sheet1", LoginData.class);
		// IsNegative(是否为正向用例) Desc(用例描述) UserName(用户名) Password(密码) ErrorMsg(错误提示期望值)
		String[] cellNames = { "IsNegative", "UserName", "Password", "ErrorMsg" };
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
