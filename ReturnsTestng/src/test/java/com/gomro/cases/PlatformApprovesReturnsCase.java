package com.gomro.cases;

import static org.testng.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.gomro.factory.BaseTester;
import com.gomro.pojo.PlatformApprovesReturnsData;
import com.gomro.utlis.CaseUtils;
import com.gomro.utlis.ExcelUtils;

/**
 * 平台审批退货
 * 
 * @author yaopengtao
 * @data 2020年4月7日 上午10:00:33
 */
public class PlatformApprovesReturnsCase extends BaseTester {
	// IsNegative(是否为正向用例) Desc(用例描述) UserName(用户名) Pwd(密码) PromptPopup(提示弹窗)
	@Test(dataProvider = "datas")
	public void test(ITestContext testContext, String IsNegative, String Desc, String UserName, String Pwd,
			String PromptPopup) throws Throwable {
		testContext.setAttribute("caseId", i++);
		log.info("------------------------------平台审批退货-----------------------");
		Thread.sleep(5000);
		String pageName = "平台审批退货";
		// 1、退出客户账号并登陆平台账号
		HoverMouse(pageName, "账号名称");
		isDisplayElement(pageName, "//div[1]/div[1]/div/div/ul[2]/li[2]/dl/dd/a", "退出");
		click(pageName, "退出");
		sendKeys(pageName, "用户名", UserName);
		sendKeys(pageName, "密码", Pwd);
		click(pageName, "登录");
		// 2、进入到售后退款页面
		open(After_SalesRefund_Page);
		// 3、点击待审批
		click(pageName, "待审批");
		// 4、选择退货订单
		click(pageName, "退货订单");
		// 5、点击退款审批
		click(pageName, "退款审批");
		// 6、弹窗确认
		click(pageName, "弹窗确定");
		// 7、断言
		if ("0".equals(IsNegative)) {
			assertTrue(false);
		} else {
			try {
				// 获取审批弹窗内容
				Thread.sleep(500);
				String text = getText(pageName, "审批成功弹窗");
				System.out.println("审批成功弹窗内容：" + text);
				assertConatins(pageName, "审批成功弹窗", PromptPopup);
			} catch (Exception e) {
				e.printStackTrace();
				Assert.assertTrue(false);
				log.info(e.toString());
			}
		}
	}

	@DataProvider
	public static Object[][] datas() {
		List<PlatformApprovesReturnsData> list = ExcelUtils.read("src/test/resources/PlatformApprovesReturns.xlsx", "Sheet1", PlatformApprovesReturnsData.class);
		// IsNegative(是否为正向用例) Desc(用例描述) UserName(用户名) Pwd(密码) PromptPopup(提示弹窗)
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
