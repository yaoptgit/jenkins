package com.gomro.cases;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.gomro.factory.BaseTester;
import com.gomro.pojo.PlatformConfirmsInvoicingData;
import com.gomro.utlis.CaseUtils;
import com.gomro.utlis.ExcelUtils;

/**
 * 平台确认开票
 * 
 * @author yaopengtao
 * @data 2020年3月25日 上午10:51:09
 */
public class PlatformConfirmsInvoicingCase extends BaseTester {
	// IsNegative(是否是正向用例) Desc(用例描述) UserName(用户名) Pwd(密码) InvoiceCode(发票代码)
	// InvoiceNumber(发票号码)
	// Issuer(开票人) Error(错误提示)
	@Test(dataProvider = "datas")
	public void test(ITestContext testContext, String IsNegative, String Desc, String UserName, String Pwd,
			String InvoiceCode, String InvoiceNumber, String Issuer, String Error) throws Throwable {
		log.info("=========================平台确认开票=======================");
		testContext.setAttribute("caseId", i++);
		String pageName = "平台确认开票";
		// 1、退出客户账号
		HoverMouse(pageName, "账号名称");
		click(pageName, "退出");
		// 2、登录平台账号
		sendKeys(pageName, "用户名", UserName);
		sendKeys(pageName, "密码", Pwd);
		click(pageName, "登录");
		// 3、进入到平台发票页面
		open(PlatformInvoice_PAGE);
		// 4、点击平台客户发票申请单管理
		click(pageName, "平台客户发票申请单管理");
		// 等待确认开票文本显示
		System.out.println("pageName = " + pageName);
		isDisplayElement(pageName, "//button[@class='layui-btn layui-btn-sm layui-btn-normal confirmMakeInvoice']",
				"确认开票");
		// 5、选择发票申请单
		click(pageName, "选择发票申请单");
		// 6、点击确认开票
		click(pageName, "确认开票");
		// 7、输入发票代码
		sendKeys(pageName, "发票代码", InvoiceCode);
		// 8、输入发票号码
		sendKeys(pageName, "发票号码", InvoiceNumber);
		// 9、输入开票人
		sendKeys(pageName, "开票人", Issuer);
		// 10、点击立即提交
		click(pageName, "立即提交");
		// 11、信息提示弹窗确认
		click(pageName, "信息提示弹窗确定");
		// 获取信息提示弹窗的内容
		String textValue = getText(pageName, "弹窗内容");
		System.out.println("弹窗内容 = " + textValue);
		// 11、断言
		if ("0".equals(IsNegative)) {
			// 错误提示
			String actual = getText(pageName, "错误提示");
			// 断言
			assertActualExpected(actual, Error);
		} else {
			Thread.sleep(2000);
			if ("提交成功".equals(textValue)) {
				// 获取发票状态
				String InvoiceStatus = getText(pageName, "发票状态");
				System.out.println("发票状态 = " + InvoiceStatus);
				try {
					assertActualExpected(InvoiceStatus, "已开票");
				} catch (Exception e) {
					e.printStackTrace();
					Assert.assertTrue(false);
					log.info(e.toString());
				}
			} else {
				Assert.assertTrue(false);
				log.info("平台给客户开票失败");
			}
		}
	}

	@DataProvider
	public static Object[][] datas() {
		List<PlatformConfirmsInvoicingData> list = ExcelUtils.read("src/test/resources/PlatformConfirmsInvoicing.xlsx",
				"Sheet1", PlatformConfirmsInvoicingData.class);
		// IsNegative(是否是正向用例) Desc(用例描述) UserName(用户名) Pwd(密码) InvoiceCode(发票代码)
		// InvoiceNumber(发票号码)
		// Issuer(开票人) Error(错误提示)
		String[] cellNames = { "IsNegative", "Desc", "UserName", "Pwd", "InvoiceCode", "InvoiceNumber", "Issuer",
				"Error" };
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
