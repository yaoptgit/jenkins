package com.gomro.cases;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.Keys;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.gomro.factory.BaseTester;
import com.gomro.pojo.TransferPaymentData;
import com.gomro.utlis.CaseUtils;
import com.gomro.utlis.ExcelUtils;

/**
 * 转采申请付款
 * 
 * @author yaopengtao
 * @data 2020年5月7日 上午10:33:34
 */
public class TransferPaymentCase extends BaseTester {
	// IsNegative(是否为正向用例) Desc(用例描述) PaymentRatio(付款比例) Errors(错误提示)
	@Test(dataProvider = "datas")
	public void test(ITestContext testContext, String IsNegative, String Desc, String PaymentRatio, String Errors) {
		testContext.setAttribute("caseId", i++);
		String pageName = "转采申请付款";
		log.info("======================转采申请付款开始====================");
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 1、打开转采管理页面
		open(CONVERSIONMANAGEMENT_PAGE);
		// 2、点击采购订单
		click(pageName, "采购订单");
		// 3、选择转采单
		click(pageName, "选择转采订单");
		// 4、点击申请付款
		click(pageName, "申请付款");
		// 5、输入付款比例
		clearText(pageName, "付款比例");
		sendKeys(pageName, "付款比例", PaymentRatio);
		System.out.println(PaymentRatio);
		keyboardEvents(pageName, "付款比例", Keys.ENTER);
		if ("0".equals(IsNegative)) {
			if ("110".equals(PaymentRatio)) {
				String text = getText(pageName, "错误比例提示");
				log.info("错误比例提示：" + text);
				assertConatins(pageName, "错误比例提示", Errors);
				log.info(pageName + Desc + "执行完毕");
			} else {
				// 6、点击提交付款单
				click(pageName, "提交付款单");
				// 7、二次确认弹窗确认
				click(pageName, "二次确认");
				// 8、获取提示信息弹窗内容并断言
				String textActual = getText(pageName, "提示弹窗");
				System.out.println("确认后提示弹窗内容：" + textActual);
				assertActualExpected(textActual, Errors);
				click(pageName, "确定");
				log.info(pageName + Desc + "执行完毕");
			}
		} else {
			// 6、点击提交付款单
			click(pageName, "提交付款单");
			// 7、二次确认弹窗确认
			click(pageName, "二次确认");
			// 8、获取提示信息弹窗内容并断言
			String text = getText(pageName, "成功提示弹窗");
			log.info("确认后提示弹窗内容：" + text);
			assertConatins(pageName, "成功提示弹窗", Errors);
			click(pageName, "确定");
			log.info(pageName + Desc + "执行完毕");
		}
		log.info(pageName + ">>>" + Desc + ">>>执行完毕");
		driver.navigate().refresh();

	}

	@DataProvider
	public static Object[][] datas() {
		List<TransferPaymentData> list = ExcelUtils.read("src/test/resources/TransferPayment.xlsx", "Sheet1",
				TransferPaymentData.class);
		// IsNegative(是否为正向用例) Desc(用例描述) PaymentRatio(付款比例) Errors(错误提示)
		String[] cellNames = { "IsNegative", "Desc", "PaymentRatio", "Errors" };
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
