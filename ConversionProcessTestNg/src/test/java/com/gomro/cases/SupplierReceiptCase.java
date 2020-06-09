package com.gomro.cases;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.gomro.factory.BaseTester;
import com.gomro.pojo.SupplierReceiptData;
import com.gomro.utlis.CaseUtils;
import com.gomro.utlis.ExcelUtils;

/**
 * 供应商收货
 * @author yaopengtao
 * @data 2020年4月10日 上午8:53:07
 */
public class SupplierReceiptCase extends BaseTester{
	// IsNegative(是否为正向用例) Desc(用例描述) UserName(用户名) Pwd(密码) Error(错误提示)
	// TipPopup(弹窗提示)
	@Test(dataProvider = "datas")
	public void test(ITestContext testContext, String IsNegative, String Desc, String UserName, String Pwd,
			String Error, String TipPopup) throws Throwable {
		testContext.setAttribute("caseId", i++);
		log.info("=========================供应商收货=======================");
		String pageName = "供应商收货";
		Thread.sleep(5000);
		// 1、退出客户账号并登录供应商账号
		HoverMouse(pageName, "账号名称");
		click(pageName, "退出");
		sendKeys(pageName, "用户名", UserName);
		sendKeys(pageName, "密码", Pwd);
		click(pageName, "登录");
		// 2、进入到销售订单页面
		open(SALESORDER_PAGE);
		// 3、点击全部tab标签
		click(pageName, "全部");
		// 4、点击需要收货的供应商订单序号
		click(pageName, "供应商订单序号");
		// 5、选择收货商品
		click(pageName, "订单商品");
		// 6、点击确定收货
		click(pageName, "确定收货");
		// 8、断言
		if ("0".equals(IsNegative)) {
			Assert.assertTrue(false);
			log.info(pageName + "模块没有反向流程");
		} else if ("1".equals(IsNegative)) {
			try {
				// 7、警告弹窗确认
				click(pageName, "警告弹窗确定");
				String text = getText(pageName, "收货弹窗提示");
				System.out.println("收货弹窗提示：" + text);
				assertConatins(pageName, "收货弹窗提示", TipPopup);
			} catch (Exception e) {
				e.printStackTrace();
				Assert.assertTrue(false);
				log.info(e.toString());
			}
		} else {
			log.info("请修改测试用例IsNegative列里边存1(正向)或0(反向)");
			Assert.assertTrue(false);
		}
		
	}

	@DataProvider
	public static Object[][] datas() {
		List<SupplierReceiptData> list = ExcelUtils.read("src/test/resources/SupplierReceipt.xlsx", "Sheet1",
				SupplierReceiptData.class);
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
