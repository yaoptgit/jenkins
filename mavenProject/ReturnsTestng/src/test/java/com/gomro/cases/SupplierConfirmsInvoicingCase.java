package com.gomro.cases;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.gomro.factory.BaseTester;
import com.gomro.pojo.SupplierConfirmsInvoicingData;
import com.gomro.utlis.CaseUtils;
import com.gomro.utlis.ExcelUtils;

/**
 * 供应商确认开票
 * 
 * @author yaopengtao
 * @data 2020年3月27日 下午2:42:26
 */
public class SupplierConfirmsInvoicingCase extends BaseTester {
	// IsNegative(是否为正向用例) Desc(用例描述) UserName(用户名) Pwd(密码) Issuer(开票人)
	// InvoiceCode(发票代码) InvoiceNumber(发票号码) Error(报错提示) TipPopup(提示弹窗)
	@Test(dataProvider = "datas")
	public void test(ITestContext testContext, String IsNegative, String Desc, String UserName, String Pwd,
			String Issuer, String InvoiceCode, String InvoiceNumber, String Error, String TipPopup) throws Throwable {
		log.info("============================供应商确认开票==========================");
		testContext.setAttribute("caseId", i++);
		String pageName = "供应商确认开票";
		// 退出客户账号并登录供应商账号
		HoverMouse(pageName, "账号名称");
		click(pageName, "退出");
		open("https://test-pm.gomro.cn/login");
		sendKeys(pageName, "用户名", UserName);
		sendKeys(pageName, "密码", Pwd);
		click(pageName, "登录");
		// 1、进入到发票管理页面
		open(SUPPLIERINVOICEMANAGEMENT_PAGE);
		// 2、点击供应商发票申请单管理
		click(pageName, "供应商发票申请单管理");
		// 3、点击发票列表编号
		click(pageName, "编号");
		// 4、点击确认开票
		click(pageName, "确认开票");
		// 5、输入开票人
		sendKeys(pageName, "开票人", Issuer);
		// 6、输入发票代码
		sendKeys(pageName, "发票代码", InvoiceCode);
		// 7、输入发票号码
		sendKeys(pageName, "发票号码", InvoiceNumber);
		// 8、点击确认
		click(pageName, "确认");
		// 获取提示弹窗内容
		isDisplayElement(pageName, "//div[@class='layui-layer-content']", "提交成功");
		String actualText = getText(pageName, "提示弹窗内容");
		System.out.println("提示弹窗内容 = " + actualText);
		// 9、提示弹窗确认
		click(pageName, "提示弹窗确认");
		// 10、断言
		if ("0".equals(IsNegative)) {
			// 错误提示
			String actual = getText(pageName, "错误提示");
			// 断言
			assertActualExpected(actual, Error);
		} else {
			Thread.sleep(2000);
			try {
				// 获取提交弹窗内容
				assertActualExpected(actualText, TipPopup);
			} catch (Exception e) {
				e.printStackTrace();
				Assert.assertTrue(false);
				log.info(e.toString());
			}
		}
	}

	@DataProvider
	public static Object[][] datas() {
		List<SupplierConfirmsInvoicingData> list = ExcelUtils.read("src/test/resources/SupplierConfirmsInvoicing.xlsx",
				"Sheet1", SupplierConfirmsInvoicingData.class);
		// IsNegative(是否为正向用例) Desc(用例描述) UserName(用户名) Pwd(密码) Issuer(开票人)
		// InvoiceCode(发票代码) InvoiceNumber(发票号码) Error(报错提示) TipPopup(提示弹窗)
		String[] cellNames = { "IsNegative", "Desc", "UserName", "Pwd", "Issuer", "InvoiceCode", "InvoiceNumber",
				"Error", "TipPopup" };
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
