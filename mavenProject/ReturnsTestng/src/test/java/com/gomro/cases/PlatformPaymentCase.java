package com.gomro.cases;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.gomro.factory.BaseTester;
import com.gomro.pojo.PlatformPaymentData;
import com.gomro.utlis.CaseUtils;
import com.gomro.utlis.ExcelUtils;

/**
 * 平台付款
 * 
 * @author yaopengtao
 * @data 2020年3月30日 下午2:49:32
 */
public class PlatformPaymentCase extends BaseTester {
	// IsNegative(是否为正向用例) Desc(用例描述)
	// PaymentStatusCode() PaymentStatus(付款状态)
	// SubmitApprovalStatusCode() SubmitApprovalStatus(提交审批后状态)
	// BusinessApprovalStatusCode() BusinessApprovalStatus(业务审批后状态)
	// FinancialApprovalStatusCode() FinancialApprovalStatus(财务审批后状态)
	// PaymentPopupCode() PaymentPopup(付款弹窗)
	// Error(错误提示)
	@Test(dataProvider = "datas")
	public void test(ITestContext testContext, String IsNegative, String Desc, String PaymentStatusCode,
			String PaymentStatus, String SubmitApprovalStatusCode, String SubmitApprovalStatus,
			String BusinessApprovalStatusCode, String BusinessApprovalStatus, String FinancialApprovalStatusCode,
			String FinancialApprovalStatus, String PaymentPopupCode, String PaymentPopup, String Error)
			throws Throwable {
		log.info("========================平台付款========================");
		testContext.setAttribute("caseId", i++);
		String pageName = "平台付款";
		// 1、进入到平台结算页面
		open(PLATFORMSETTLEMENT_PAGE);
		// 2、点击平台供应商付款
		isDisplayElement("平台供应商付款", "//div[2]/div[2]/div/div/ul/li[4]", "平台供应商付款");
		click(pageName, "平台供应商付款");
		// 3、选择付款的发票单
		click(pageName, "付款的发票单");
		// 4.1、点击立即结算
		click(pageName, "立即结算");
		// 4.2、确认结算信息确定
		click(pageName, "确认结算信息确定");
		// 4.3、断言立即结算
		if ("0".equals(PaymentStatusCode)) {
			// 错误提示
			String actual = getText(pageName, "错误提示");
			// 断言
			assertActualExpected(actual, Error);
		} else {
			try {
				Thread.sleep(3000);
				// 付款状态
				String  paymentStatusString = getText(pageName, "付款状态");
				System.out.println(pageName + "：付款状态 = " + paymentStatusString);
				assertActualExpected(paymentStatusString, PaymentStatus);
			} catch (Exception e) {
				e.printStackTrace();
				Assert.assertTrue(false);
				log.info(e.toString());
			}
		}
		Thread.sleep(2000);
		// 5、点击平台供应商付款单管理
		click(pageName, "平台供应商付款单管理");
		// 6、选择付款单
		click(pageName, "付款单");
		// 7.1、点击提交审批
		click(pageName, "提交审批");
		// 7.2、提示弹窗确定
		click(pageName, "弹窗确定");
		// 7.3、断言提交审批
		if ("0".equals(SubmitApprovalStatusCode)) {
			// 错误提示
			final String actual = getText(pageName, "错误提示");
			// 断言
			assertActualExpected(actual, Error);
		} else {
			try {
				Thread.sleep(5000);
				// 获取提示弹窗内容
				String submitApprovalStatusString = getText(pageName, "状态");
				System.out.println("提交审批后状态 = " + submitApprovalStatusString);
				assertActualExpected(submitApprovalStatusString, SubmitApprovalStatus);
			} catch (Exception e) {
				e.printStackTrace();
				Assert.assertTrue(false);
				log.info(e.toString());
			}
		}
		// 8、选择付款单
		click(pageName, "付款单");
		// 9.1、点击业务审批通过
		click(pageName, "业务审批通过");
		// 9.2、业务审批弹窗确定
		click(pageName, "弹窗确定");
		// 9.3、业务审批断言
		if ("0".equals(BusinessApprovalStatusCode)) {
			// 错误提示
			String actual = getText(pageName, "错误提示");
			// 断言
			assertActualExpected(actual, Error);
		} else {
			try {
				Thread.sleep(5000);
				// 获取提示弹窗内容
				String businessApprovalStatusString = getText(pageName, "状态");
				System.out.println("业务审批后状态 = " + businessApprovalStatusString);
				assertActualExpected(businessApprovalStatusString, BusinessApprovalStatus);
			} catch (Exception e) {
				e.printStackTrace();
				Assert.assertTrue(false);
				log.info(e.toString());
			}
		}
		// 11、选择付款单
		click(pageName, "付款单");
		// 12.1、点击财务审批通过
		click(pageName, "财务审批通过");
		// 12.2、财务审批弹窗确定
		click(pageName, "弹窗确定");
		// 12.3、财务审批断言
		if ("0".equals(FinancialApprovalStatusCode)) {
			// 错误提示
			String actual = getText(pageName, "错误提示");
			// 断言
			assertActualExpected(actual, Error);
		} else {
			try {
				Thread.sleep(5000);
				// 获取提示弹窗内容
				String financialApprovalStatusString = getText(pageName, "状态");
				System.out.println("财务审批后状态 = " + financialApprovalStatusString);
				assertActualExpected(financialApprovalStatusString, FinancialApprovalStatus);
			} catch (Exception e) {
				e.printStackTrace();
				Assert.assertTrue(false);
				log.info(e.toString());
			}
		}
		// 13、选择付款单
		click(pageName, "付款单");
		// 14.1、点击付款
		click(pageName, "付款");
		// 14.2、付款弹窗立即提交
		click(pageName, "立即提交");
		Thread.sleep(5000);
		
		// 14.3、付款断言
		if ("0".equals(PaymentPopupCode)) {
			// 错误提示
			String actual = getText(pageName, "错误提示");
			// 断言
			assertActualExpected(actual, Error);
		} else {
			try {
				// 获取提示弹窗内容
				String paymentPopupString = getText(pageName, "付款弹窗提示内容");
				System.out.println("付款弹窗提示内容  = " + paymentPopupString);
				//15、付款后弹窗确定
				click(pageName, "弹窗确定"); 
				assertActualExpected(paymentPopupString, PaymentPopup);
			} catch (Exception e) {
				e.printStackTrace();
				Assert.assertTrue(false);
				log.info(e.toString());
			}
		}
		
	}

	@DataProvider
	public static Object[][] datas() {
		List<PlatformPaymentData> list = ExcelUtils.read("src/test/resources/PlatformPayment.xlsx", "Sheet1",
				PlatformPaymentData.class);
		// IsNegative(是否为正向用例) Desc(用例描述)
		// PaymentStatusCode() PaymentStatus(付款状态)
		// SubmitApprovalStatusCode() SubmitApprovalStatus(提交审批后状态)
		// BusinessApprovalStatusCode() BusinessApprovalStatus(业务审批后状态)
		// FinancialApprovalStatusCode() FinancialApprovalStatus(财务审批后状态)
		// PaymentPopupCode() PaymentPopup(付款弹窗)
		// Error(错误提示)
		String[] cellNames = { "IsNegative", "Desc", "PaymentStatusCode", "PaymentStatus", "SubmitApprovalStatusCode",
				"SubmitApprovalStatus", "BusinessApprovalStatusCode", "BusinessApprovalStatus",
				"FinancialApprovalStatusCode", "FinancialApprovalStatus", "PaymentPopupCode", "PaymentPopup", "Error" };
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
