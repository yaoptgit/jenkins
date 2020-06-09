package com.gomro.cases;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.gomro.factory.BaseTester;
import com.gomro.pojo.ReconcileAndRequestBillingData;
import com.gomro.utlis.CaseUtils;
import com.gomro.utlis.ExcelUtils;

/**
 * 对账并申请开票
 * 
 * @author yaopengtao
 * @data 2020年3月24日 下午3:24:30
 */
public class ReconcileAndRequestBillingCase extends BaseTester {
	// IsNegative(是否为正向用例) Desc(用例描述) Note(备注) Prompt(提交申请开票提示) Error(报错提示)
	@Test(dataProvider = "datas")
	public void test(ITestContext testContext, String IsNegative, String Desc, String Note, String Prompt,
			String Error) throws Throwable {
		log.info("==========================对账并申请开票=========================");
		testContext.setAttribute("caseId", i++);
		String pageName = "对账并申请开票";
		// 1、进入对账管理页面
		open(ReconciliationManagement_PAGE);
		// 2、选择对账的商品
		click(pageName, "选择商品");
		// 获取订单商品号
		String productId = getText(pageName, "对账页面商品id");
		System.out.println("对账页面商品id = " + productId);
		// 3、点击对账并申请开票
		click(pageName, "对账并申请开票");
		// 4、点击提交
		click(pageName, "对账信息提交");
		// 5、信息提示框确定
		click(pageName, "信息弹窗确定");
		Thread.sleep(3000);
		// 6、输入发票备注信息
		// 6.1清空备注
		clearText(pageName, "申请开票备注");
		// 6.2输入备注
		sendKeys(pageName, "申请开票备注", Note);
		// 7、点击提交
		click(pageName, "申请发票提交");
		Thread.sleep(6000);
		// 8、断言
		if ("0".equals(IsNegative)) {
			// 错误提示
			String actual = getText(pageName, "错误提示");
			// 断言
			assertActualExpected(actual, Error);
		} else {
			String contentString = getText(pageName, "提交申请发票提示");
			System.out.println("提交申请发票提示 = " + contentString);
			// 获取发票管理页面商品id
			String productIds = getText(pageName, "客户发票商品Id");
			System.out.println("客户发票商品Id = " + productIds);
			if (productId.equals(productIds)) {
				try {
					assertActualExpected(contentString, Prompt);
				} catch (Exception e) {
					e.printStackTrace();
					Assert.assertTrue(false);
					log.info(e.toString());
				}
			} else {
				Assert.assertTrue(false);
				log.info("申请开票失败");
			}

		}
	}

	@DataProvider
	public static Object[][] datas() {
		List<ReconcileAndRequestBillingData> list = ExcelUtils.read(
				"src/test/resources/ReconcileAndRequestBilling.xlsx", "Sheet1", ReconcileAndRequestBillingData.class);
		// IsNegative(是否为正向用例) Desc(用例描述) Note(备注) Prompt(提交申请开票提示) Error(报错提示)
		String[] cellNames = { "IsNegative", "Desc", "Note", "Prompt", "Error" };
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
