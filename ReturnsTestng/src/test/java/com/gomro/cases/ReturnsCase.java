package com.gomro.cases;

import java.util.Arrays;
import java.util.List;

import org.springframework.util.StringUtils;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.gomro.factory.BaseTester;
import com.gomro.pojo.ReturnsData;
import com.gomro.utlis.CaseUtils;
import com.gomro.utlis.ExcelUtils;

/**
 * 客户退货
 * 
 * @author yaopengtao
 * @data 2020年4月3日 下午1:41:40
 */

public class ReturnsCase extends BaseTester {
	// IsNegative(是否为正向用例) Desc(用例描述) Num(退货数量) RefundInstructions(退款说明)
	// PromptPopup(提示弹窗)
	@Test(dataProvider = "datas")
	public void test(ITestContext testContext, String IsNegative, String Desc, String Num, String RefundInstructions,
			String PromptPopup) throws Throwable {
		testContext.setAttribute("caseId", i++);
		log.info("---------------------------客户退货--------------------------");
		String pageName = "客户退货";
		// 1、进入到采购订单页面
		open(PurchaseOrder_PAGE);
		// 2、点击全部
		click(pageName, "全部");
		// 3、点击客户订单序号
		click(pageName, "订单序号");
		// 4、选择退货商品
		click(pageName, "退货商品");
		// 5、点击申请退款
		click(pageName, "申请退款");
		// 6、清空数量
		clearText(pageName, "数量");
		// 7、重新输入数量
		sendKeys(pageName, "数量", Num);
		// 8、输入退款说明
		sendKeys(pageName, "退款说明", RefundInstructions);
		// 9、点击立即提交
		click(pageName, "提交");
		// 10、断言
		if ("0".equals(IsNegative)) {
			if (StringUtils.isEmpty(RefundInstructions)) {
				Thread.sleep(500);
				// 错误提示
				String actual = getText(pageName, "退款说明为空错误提示");
				System.out.println("退款说明为空错误提示内容：" + actual);
				// 断言
				assertActualExpected(actual, PromptPopup);
			}else {
				click(pageName, "警告弹窗");
				Thread.sleep(1000);
				String actual = getText(pageName, "退货数量大于总数量弹窗提示");
				System.out.println("退货数量大于总数量弹窗提示内容：" + actual);
				assertActualExpected(actual, PromptPopup);
			}
		} else {
			try {
				//警告弹窗确定
				click(pageName, "警告弹窗");
				Thread.sleep(1000);
				String actual = getText(pageName, "退款申请成功弹窗");
				System.out.println("退款申请成功弹窗内容：" + actual);
				assertActualExpected(actual, PromptPopup);
			} catch (Exception e) {
				e.printStackTrace();
				Assert.assertTrue(false);
				log.info(e.toString());
			}
		}
	}

	@DataProvider
	public static Object[][] datas() {
		List<ReturnsData> list = ExcelUtils.read("src/test/resources/Returns.xlsx", "Sheet1", ReturnsData.class);
		// IsNegative(是否为正向用例) Desc(用例描述) Num(退货数量) RefundInstructions(退款说明)
		// PromptPopup(提示弹窗)
		String[] cellNames = { "IsNegative", "Desc", "Num", "RefundInstructions", "PromptPopup" };
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
