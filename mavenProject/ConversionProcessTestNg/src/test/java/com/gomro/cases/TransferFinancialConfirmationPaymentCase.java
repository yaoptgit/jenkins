package com.gomro.cases;

import static org.testng.Assert.assertTrue;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.gomro.factory.BaseTester;
/**
 * 转采财务确认付款
 * @author yaopengtao
 * @data 2020年5月8日 上午11:14:02
 */
public class TransferFinancialConfirmationPaymentCase extends BaseTester{
	@Test
	public void test(ITestContext testContext) {
		testContext.setAttribute("caseId", i++);
		String pageName = "转采财务确认付款";
		log.info("=======================转采财务确认付款开始=======================");
		//1、进入到转采财务管理页面
		open(CONVERSIONFINANCE_PAGE);
		//2、点击采购付款列表
		click(pageName, "财务付款列表");
		//3、选择付款单
		click(pageName, "付款单");
		//4、点击确认付款
		click(pageName, "确认付款");
		//5、点击立即提交
		click(pageName, "立即提交");
		//6、二次确认提示框点击确认
		click(pageName, "确定");
		//7、断言
		String textActual = getText(pageName, "付款确认提示");
		System.out.println("付款提示：" + textActual);
		try {
			assertConatins(pageName, "付款确认提示", "付款成功");
			//assertActualExpected(textActual, "付款成功");
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
			log.info(e.toString());
		}finally {
			log.info(pageName + ">>>执行完毕");
		}
		
	}
}
