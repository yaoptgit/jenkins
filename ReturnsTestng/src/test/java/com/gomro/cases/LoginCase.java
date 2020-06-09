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
 * @author yaopengtao
 * @data 2019年12月3日 下午4:39:14
 */
public class LoginCase extends BaseTester {
	@Test(dataProvider = "datas")
	public void test(ITestContext testContext, String isNegative, String userName, String password, String expected)
			throws Exception {
		testContext.setAttribute("caseId", i++);
		log.info("====================登录===================");
		String pageName = "登录";
		open(LOGIN_PAGE);
		sendKeys(pageName, "用户名", userName);
		sendKeys(pageName, "密码", password);
		click(pageName, "登录按钮");
		if ("0".equals(isNegative)) {
			String actual = getText("登录", "错误提示");
			// System.out.println(actual);
			// System.out.println(expected);
			assertActualExpected(actual, expected);
			log.info(pageName + "执行完毕");
		} else {
			try {
				assertUrl("portal");
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
		List<LoginData> list = ExcelUtils.read("src/test/resources/login.xlsx", "Sheet1", LoginData.class);
		// IsNegative(是否为正向用例) Desc(用例描述) UserName(用户名) Password(密码) ErrorMsg(错误提示期望值)
		String[] cellNames = { "IsNegative", "UserName", "Password", "ErrorMsg" };
		Object[][] datas = CaseUtils.getDataProviders(list, cellNames);
		return datas;
	}

//	public static void main(String[] args) {
//		Object[][] datas = datas();
//		for (Object[] objects : datas) {
//			System.out.println(Arrays.toString(objects));
//		}
//	}

}
