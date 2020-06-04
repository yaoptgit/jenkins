package com.gomro.cases;

import static org.testng.Assert.assertTrue;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.gomro.factory.BaseTester;
/**
 * 转采付款审核
 * @author yaopengtao
 * @data 2020年5月8日 上午9:53:05
 */
public class ConversionPaymentReviewCase extends BaseTester{
	@Test
	public void test(ITestContext testContext) {
		testContext.setAttribute("caseId", i++);
		String pageName = "转采付款审核";
		log.info("=========================转采付款审核开始=========================");
		//1、进入都转采管理
		open(CONVERSIONMANAGEMENT_PAGE);
		//2、点击采购付款
		click(pageName, "转采付款");
		//3、选择付款单
		click(pageName, "选择付款单");
		//4、点击审核
		click(pageName, "审核");
		//5、提示弹窗点击审核通过
		click(pageName, "通过");
		//6、断言
		String text = getText(pageName, "审核提示");
		System.out.println("付款审核提示：" + text);
		try {
			assertConatins(pageName, "审核提示", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
			log.info(e.toString());
		}
		log.info(pageName + ">>>执行完毕");
	}
}
