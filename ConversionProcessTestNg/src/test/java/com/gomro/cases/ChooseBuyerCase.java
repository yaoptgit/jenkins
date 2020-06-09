package com.gomro.cases;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.gomro.factory.BaseTester;
import com.gomro.pojo.ChooseBuyerData;
import com.gomro.pojo.LoginData;
import com.gomro.utlis.CaseUtils;
import com.gomro.utlis.ExcelUtils;

/**
 * 转采选择采购员
 * 
 * @author yaopengtao
 * @data 2020年4月24日 下午2:50:07
 */
public class ChooseBuyerCase extends BaseTester {
	// IsNegative(是否为正向用例) Desc(用例描述) ErrorMsg(错误提示)
	@Test(dataProvider = "datas")
	public void test(ITestContext testContext,String IsNegative,String ErrorMsg) throws Exception {
		testContext.setAttribute("caseId", i++);
		String pageName = "转采选择采购员";
		log.info("=============转采选择采购员开始============");
		// 1、进入到转采管理页面
		open(CONVERSIONMANAGEMENT_PAGE);
		// 2、点击转采客户订单商品
		click(pageName, "点击转采客户订单商品");
		// 3、选择转采商品
		click(pageName, "选择转采商品");
		// 4、点击分配采购员
		click(pageName, "分配采购员");
		// 断言
		if ("0".equals(IsNegative)) {
			//点击立即提交
			click(pageName, "立即提交");
			//获取错误提示
			Thread.sleep(1000);
			String textExpected = getText(pageName, "错误提示");
			System.out.println("错误提示：" + textExpected);
			assertActualExpected(ErrorMsg, textExpected);
			log.info(pageName + "执行完毕");
			//刷新页面
			driver.navigate().refresh();
		} else {
			try {
				click(pageName, "点击下拉框");
				//获取获取选择的采购员名称
				String textExpected = getText(pageName, "获取选择的采购员名称");
				System.out.println("采购员：" + textExpected);
				// 5、选择采购员
				click(pageName, "选择采购员");
				// 6、点击立即提交
				click(pageName, "立即提交");
				// 7、警告弹窗点击确定
				click(pageName, "警告弹窗确定");
				//8、获取采购员名称
				Thread.sleep(3000);
				String textActual = getText(pageName, "获取采购员名称");
				System.out.println("采购员名称：" + textActual);
				assertActualExpected(textActual, textExpected);
			} catch (Exception e) {
				e.printStackTrace();
				Assert.assertTrue(false);
				log.info(e.toString());
			} finally {
				log.info(pageName + "执行完毕");
				//刷新页面
				driver.navigate().refresh();
			}
		}
	}

	@DataProvider
	public static Object[][] datas() {
		List<ChooseBuyerData> list = ExcelUtils.read("src/test/resources/ChooseBuyer.xlsx", "Sheet1",
				ChooseBuyerData.class);
		// IsNegative(是否为正向用例) Desc(用例描述)  ErrorMsg(错误提示)
		String[] cellNames = { "IsNegative","ErrorMsg" };
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
