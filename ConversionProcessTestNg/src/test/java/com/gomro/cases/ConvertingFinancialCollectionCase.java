package com.gomro.cases;

import java.util.Arrays;
import java.util.List;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.gomro.factory.BaseTester;
import com.gomro.pojo.ConvertingFinancialCollectionData;
import com.gomro.utlis.CaseUtils;
import com.gomro.utlis.ExcelUtils;

/**
 * 转采财务收票
 * 
 * @author yaopengtao
 * @data 2020年5月9日 上午8:53:04
 */
public class ConvertingFinancialCollectionCase extends BaseTester {
	// IsNegative(是否为正向用例) Desc(用例描述) Msg(提示)
	@Test(dataProvider = "datas")
	public void test(ITestContext testContext, String IsNegative, String Desc, String Msg) {
		testContext.setAttribute("caseId", i++);
		String pageName = "转采财务收票";
		// 1、进入到转采财务管理页面
		open(CONVERSIONFINANCE_PAGE);
		// 2、点击采购收票列表
		click(pageName, "采购收票列表");
		// 3、选择收票单
		try {
			Thread.sleep(1000);
			elementsClick("//div[@class='layui-tab-item layui-show']//tr//td[1]//div[1]//div[1]//i[1]", 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 4、点击确认收票
		click(pageName, "确认收票");
		// 5、断言
		if ("0".equals(IsNegative)) {
			// 点击重置
			click(pageName, "重置");
			// 点击立即提交
			click(pageName, "立即提交");
			// 获取提示内容
			String text = getText(pageName, "必填项提示");
			System.out.println("必填项内容：" + text);
			assertConatins(pageName, "必填项提示", Msg);
		} else {
			// 点击立即提交
			click(pageName, "立即提交");
			// 二次确认框确定
			click(pageName, "确定");
			// 获取提示内容
			String text = getText(pageName, "成功提示");
			System.out.println("成功提示内容：" + text);
			assertConatins(pageName, "成功提示", Msg);
		}

	}

	@DataProvider
	public static Object[][] datas() {
		List<ConvertingFinancialCollectionData> list = ExcelUtils.read(
				"src/test/resources/ConvertingFinancialCollection.xlsx", "Sheet1",
				ConvertingFinancialCollectionData.class);
		// IsNegative(是否为正向用例) Desc(用例描述) Msg(提示)
		String[] cellNames = { "IsNegative", "Desc", "Msg" };
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
