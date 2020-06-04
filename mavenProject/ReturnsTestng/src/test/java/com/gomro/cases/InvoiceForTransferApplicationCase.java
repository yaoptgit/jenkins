package com.gomro.cases;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.Keys;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.gomro.factory.BaseTester;
import com.gomro.pojo.InvoiceForTransferApplicationData;
import com.gomro.utlis.CaseUtils;
import com.gomro.utlis.ExcelUtils;

/**
 * 转采申请收票
 * 
 * @author yaopengtao
 * @data 2020年5月9日 上午8:53:40
 */
public class InvoiceForTransferApplicationCase extends BaseTester {
	// IsNegative(是否为正向用例) Desc(用例描述) InvoiceAmount(发票金额) Msg(提示内容)
	@Test(dataProvider = "datas")
	public void test(ITestContext testContext, String IsNegative, String Desc, String InvoiceAmount, String Msg) throws Throwable {
		testContext.setAttribute("caseId", i++);
		Thread.sleep(2000);
		log.info("=====================转采申请收票======================");
		String pageName = "转采申请收票";
		// 1、进入到转采管理页面
		open(CONVERSIONMANAGEMENT_PAGE);
		// 2、点击采购订单
		click(pageName, "采购订单");
		// 3、选择转采单
		try {
			Thread.sleep(1000);
			elementsClick("//div[2]/div[2]/div/div/div/div[4]/div[3]/div[1]/div[3]/div[2]/table/tbody/tr/td/div/div/i",
					0);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("转采单定位失败！！！");
		}
		// 4、点击采购收票
		click(pageName, "采购收票");
		// 5、输入发票号
		clearText(pageName, "发票号");
		String receiptNumber = getReceiptNumber(10); // 生成一个10位的随机数
		System.out.println("发票号码：" + receiptNumber);
		sendKeys(pageName, "发票号", receiptNumber);
		// 6、输入发票金额
		clearText(pageName, "发票金额");
		sendKeys(pageName, "发票金额", InvoiceAmount);
		keyboardEvents(pageName, "发票金额", Keys.ENTER);
		// 7、开票时间
		String jsString = "var x = document.getElementsByName('detail[0].invoiceTime');x[0].value = '2021-10-18 10:23:53';";
		RunJs(jsString);
		// 8、点击提交收票单
		click(pageName, "提交收票单");
		// 9、二次确认弹窗点击确定
		click(pageName, "二次确认弹窗确定");
		// 10、断言
		if ("0".equals(IsNegative)) {
			// 反向流程
			// 获取错误提示
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			String text = getText(pageName, "错误提示");
			System.out.println("错误提示：" + text);
			// 验证是否通过
			assertConatins(pageName, "错误提示", Msg);
			log.info(pageName + Desc + "执行完毕");
			driver.navigate().refresh();
		} else {
			// 正向流程
			// 获取成功提示
			String text = getText(pageName, "成功提示");
			System.out.println("成功提示：" + text);
			// 验证是否通过
			assertConatins(pageName, "成功提示", Msg);
			log.info(pageName + Desc + "执行完毕");
			driver.navigate().refresh();
		}
	}

	@DataProvider
	public static Object[][] datas() {
		List<InvoiceForTransferApplicationData> list = ExcelUtils.read(
				"src/test/resources/InvoiceForTransferApplication.xlsx", "Sheet1",
				InvoiceForTransferApplicationData.class);
		// IsNegative(是否为正向用例) Desc(用例描述) InvoiceAmount(发票金额) Msg(提示内容)
		String[] cellNames = { "IsNegative", "Desc", "InvoiceAmount", "Msg" };
		Object[][] datas = CaseUtils.getDataProviders(list, cellNames);
		return datas;
	}

	public static void main(String[] args) {
//		Object[][] datas = datas();
//		for (Object[] objects : datas) {
//			System.out.println(Arrays.toString(objects));
//		}
		String receiptNumber = getReceiptNumber(10);
		System.out.println(receiptNumber);
	}

}
