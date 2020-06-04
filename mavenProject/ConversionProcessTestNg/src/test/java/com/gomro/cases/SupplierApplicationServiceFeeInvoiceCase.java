package com.gomro.cases;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.gomro.factory.BaseTester;
import com.gomro.pojo.SupplierApplicationServiceFeeInvoiceData;
import com.gomro.utlis.CaseUtils;
import com.gomro.utlis.ExcelUtils;

/**
 * 供应商申请开服务费发票
 * 
 * @author yaopengtao
 * @data 2020年3月31日 下午4:04:50
 */
public class SupplierApplicationServiceFeeInvoiceCase extends BaseTester {
	// IsNegative(是否为正向用例) Desc(用例描述) UserName(用户名) Pwd(密码) Error(错误提示)
	// TipPopup(弹窗提示)
	@Test(dataProvider = "datas")
	public void test(ITestContext testContext, String IsNegative, String Desc, String UserName, String Pwd,
			String Error, String TipPopup) throws Throwable {
		log.info("============================供应商申请服务费发票==========================");
		log.info("执行用例描述：" + Desc);
		testContext.setAttribute("caseId", i++);
		String pageName = "供应商申请开服务费发票";
		// 退出平台账号并登录供应商账号
		HoverMouse(pageName, "账号名称");
		click(pageName, "退出");
		open("https://test-pm.gomro.cn/login");
		sendKeys(pageName, "用户名", UserName);
		sendKeys(pageName, "密码", Pwd);
		click(pageName, "登录");
		//1、进入到服务费发票管理页面
		open(SERVICEFEEINVOICEMANAGEMENT_PAGE);
		//2、点击供应商服务费发票
		click(pageName, "供应商服务费发票");
		//3、选择发票数据
		click(pageName, "发票数据");
		//4、点击申请开票
		click(pageName, "申请开票");
		//5、点击下一步
		click(pageName, "下一步");
		//6、点击提交
		click(pageName, "提交");
		//8、断言
		if ("0".equals(IsNegative)) {
			// 错误提示
			String actual = getText(pageName, "错误提示");
			// 断言
			assertActualExpected(actual, Error);
		} else {
			Thread.sleep(2000);
			try {
				//7、获取发票申请信息
				String actualText = getText(pageName, "发票申请提示");
				// 获取提交弹窗内容
				assertActualExpected(actualText, TipPopup);
			} catch (Exception e) {
				e.printStackTrace();
				Assert.assertTrue(false);
				log.info(e.toString());
			}
		}
		//9、点击(X)关掉页面
		click(pageName, "关闭");
	}

	@DataProvider
	public static Object[][] datas() {
		List<SupplierApplicationServiceFeeInvoiceData> list = ExcelUtils.read(
				"src/test/resources/SupplierApplicationServiceFeeInvoice.xlsx", "Sheet1",
				SupplierApplicationServiceFeeInvoiceData.class);
		// IsNegative(是否为正向用例) Desc(用例描述) UserName(用户名) Pwd(密码) Error(错误提示)
		// TipPopup(弹窗提示)
		String[] cellNames = { "IsNegative", "Desc", "UserName", "Pwd", "Error", "TipPopup" };
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
