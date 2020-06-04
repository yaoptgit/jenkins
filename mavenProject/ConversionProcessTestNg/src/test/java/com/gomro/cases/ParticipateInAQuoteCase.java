package com.gomro.cases;
/**
 *	参与报价
 * @author admin
 *
 */

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.gomro.factory.BaseTester;
import com.gomro.pojo.ParticipateInAQuoteData;
import com.gomro.utlis.CaseUtils;
import com.gomro.utlis.ExcelUtils;

public class ParticipateInAQuoteCase extends BaseTester {
	

	// UserName(用户名) Password(密码) UntaxedUnitPrice(未税金额) Delivery(货期)
	// DeliveryAddress(发货地址)
	@Test(dataProvider = "datas")
	public void test(ITestContext testContext,String userName, String password, String untaxedUnitPrice, String delivery,
			String deliveryAddress) throws Throwable {
		String pageName = "参与报价";
		log.info("------------参与报价------------");
		testContext.setAttribute("caseId", i++);
		// 1、先退出客户账号
		// 1.1、鼠标悬浮
		HoverMouse(pageName, "账号名称");
		click(pageName, "退出");
		//调试登录
		open(LOGIN_PAGE);
		// 2、登录供应商账号
		sendKeys(pageName, "用户名", userName);
		sendKeys(pageName, "密码", password);
		click(pageName, "登录");
		// 3、进入到我的报价-待参与页面
		open(ParticipateInAQuote_PAGE);
		click(pageName, "待参与");
		// 4、选择参与的商品
		click(pageName, "参与商品复选框");
		// 5、点击批量参与
		click(pageName, "批量参与");
		// 6、确认参与
		click(pageName, "确定参与");
		// 7、进入到待报价页面 点击待报价
		click(pageName, "待报价");
		// 8、选择报价的商品
		click(pageName, "报价商品复选框");
		// 9、点击批批量报价
		click(pageName, "批量报价");
		// 10、提示弹窗确定
		click(pageName, "确定报价");
		// 11、输入报价有效期 2022-01-22 00:00:00
//		String jsString = "document.getElementsByName('q[0].expire').removeAttribute('readonly');";
//		JavascriptExecutor jException = (JavascriptExecutor) driver;
//		jException.executeScript(jsString);
//		sendKeys(pageName, "报价有效期", "2030-01-22 00:00:00");
		click(pageName, "报价有效期");
		click(pageName, "选择年");
		click(pageName, "确定");
		// 12、输入未税单价
		sendKeys(pageName, "未税单价", untaxedUnitPrice);
		// 13、输入货期
		sendKeys(pageName, "货期", delivery);
		// 14、输入发货地址
		sendKeys(pageName, "发货地址", deliveryAddress);
		// 15、点击保存
		Thread.sleep(4000);
		click(pageName, "保存");
		// 16、保存成功确认
		click(pageName, "确认保存");
		// 17、提交
		// 17.1、选择需要提交的商品
		Thread.sleep(2000);
		click(pageName, "提交时商品复选框");
		// 17.2、点击提交
		click(pageName, "提交");
		// 17.3、确认提交
		click(pageName, "确认提交");
		//18、断言
		//18.1、跳转到已报价页面
		click(pageName, "已报价");
		//18.2、获取货期用来断言
		try {
			String text = getText(pageName, "断言货期");
			System.out.println(text);
			assertActualExpected(text, delivery);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
			log.info(e.toString());
		}
	}
	
	@DataProvider
	public static Object[][] datas() {
		List<ParticipateInAQuoteData> list = ExcelUtils.read("src/test/resources/Participate_In_A_Quote.xlsx",
				"Sheet1", ParticipateInAQuoteData.class);
		// UserName(用户名) Password(密码) UntaxedUnitPrice(未税金额) Delivery(货期)
		// DeliveryAddress(发货地址)
		String[] cellNames = { "UserName", "Password", "UntaxedUnitPrice", "Delivery", "DeliveryAddress" };
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
