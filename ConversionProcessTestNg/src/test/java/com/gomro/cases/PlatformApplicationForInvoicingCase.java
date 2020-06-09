package com.gomro.cases;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.gomro.factory.BaseTester;
import com.gomro.pojo.PlatformApplicationForInvoicingData;
import com.gomro.utlis.CaseUtils;
import com.gomro.utlis.ExcelUtils;
/**
 * 平台申请开票
 * @author yaopengtao
 * @data 2020年3月27日 下午2:41:39
 */

public class PlatformApplicationForInvoicingCase extends BaseTester {
	// IsNegative(是否为正向用例) Desc(用例描述) Error(错误提示) InvoiceNote(发票信息备注) TipPopup(弹窗提示)
	@Test(dataProvider = "datas")
	public void test(ITestContext testContext, String IsNegative, String Desc, String Error, String InvoiceNote,
			String TipPopup) throws Throwable {
		log.info("=================================平台申请开票================================");
		testContext.setAttribute("caseId", i++);
		String pageName = "平台申请开票";
		// 1、进入到平台发票页面
		open(PlatformInvoice_PAGE);
		// 2、点击平台供应商发票
		click(pageName, "平台供应商发票");
		// 3、选择开票的数据
		click(pageName, "选择数据");
		// 4、点击申请收票
		click(pageName, "申请开票");
		// 5、清空备注0
		clearText(pageName, "发票信息备注");
		// 6、输入备注
		sendKeys(pageName, "发票信息备注", InvoiceNote);
		// 7、点击提交
		click(pageName, "提交");
		Thread.sleep(6000);
		// 8、断言
		if ("0".equals(IsNegative)) {
			// 错误提示
			String actual = getText(pageName, "错误提示");
			// 断言
			assertActualExpected(actual, Error);
		} else {
			Thread.sleep(2000);
			try {
				// 获取提交弹窗内容
				String TipPopupActual = getText(pageName, "弹窗内容");
				System.out.println("弹窗内容 = " + TipPopupActual);
				assertActualExpected(TipPopupActual, TipPopup);
			} catch (Exception e) {
				e.printStackTrace();
				Assert.assertTrue(false);
				log.info(e.toString());
			}
		}
	}

	@DataProvider
	public static Object[][] datas() {
		List<PlatformApplicationForInvoicingData> list = ExcelUtils.read(
				"src/test/resources/PlatformApplicationForInvoicing.xlsx", "Sheet1",
				PlatformApplicationForInvoicingData.class);
		// IsNegative(是否为正向用例) Desc(用例描述) Error(错误提示) InvoiceNote(发票信息备注) TipPopup(弹窗提示)
		String[] cellNames = { "IsNegative", "Desc", "Error", "InvoiceNote", "TipPopup" };
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
