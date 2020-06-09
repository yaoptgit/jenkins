package com.gomro.cases;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.gomro.factory.BaseTester;
import com.gomro.pojo.PlatformAndVendorReconciliationData;
import com.gomro.utlis.CaseUtils;
import com.gomro.utlis.ExcelUtils;

/**
 * 平台供应商对账
 * 
 * @author yaopengtao
 * @data 2020年3月26日 上午9:34:34
 */
public class PlatformAndVendorReconciliationCase extends BaseTester {
	// IsNegative(是否为正向用例) Desc(用例描述) Error(错误提示) TipPopupt(对账成功提示)
	@Test(dataProvider = "datas")
	public void test(ITestContext testContext, String IsNegative, String Desc, String Error, String TipPopupt)
			throws Throwable {
		log.info("===========================平台供应商对账============================");
		testContext.setAttribute("caseId", i++);
		String pageName = "平台供应商对账";
		// 1、进入到平台对账页面
		open(PlatformReconciliation_PAGE);
		// 2、点击平台与供应商对账
		click(pageName, "平台与供应商对账");
		// 4、选择对账商品
		click(pageName, "对账商品");
		// 5、点击与供应商进行对账
		click(pageName, "与供应商进行对账");
		// 6、点击提交
		click(pageName, "提交");
		// 7、断言
		if ("0".equals(IsNegative)) {
			// 错误提示
			String actual = getText(pageName, "错误提示");
			// 断言
			assertActualExpected(actual, Error);
		} else {
			Thread.sleep(2000);
			try {
				// 获取对账状态
				String InvoiceStatus = getText(pageName, "供应商对账状态");
				System.out.println("供应商对账状态 = " + InvoiceStatus);
				assertActualExpected(InvoiceStatus, TipPopupt);
			} catch (Exception e) {
				e.printStackTrace();
				Assert.assertTrue(false);
				log.info(e.toString());
			}
		}

	}

	@DataProvider
	public static Object[][] datas() {
		List<PlatformAndVendorReconciliationData> list = ExcelUtils.read(
				"src/test/resources/PlatformAndVendorReconciliation.xlsx", "Sheet1",
				PlatformAndVendorReconciliationData.class);
		// IsNegative(是否为正向用例) Desc(用例描述) Error(错误提示) TipPopupt(对账成功提示)
		String[] cellNames = { "IsNegative", "Desc", "Error", "TipPopupt" };
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
