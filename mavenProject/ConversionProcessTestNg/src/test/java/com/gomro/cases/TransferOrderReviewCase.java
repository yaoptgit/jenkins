package com.gomro.cases;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.gomro.factory.BaseTester;
/**
 * 转采订单审核
 * @author yaopengtao
 * @data 2020年5月6日 下午3:31:37
 */
public class TransferOrderReviewCase extends BaseTester{
	@Test
	public void test(ITestContext testContext) throws Exception {
		testContext.setAttribute("caseId", i++);
		String pageName = "转采订单审核";
		//1、进入到转采管理
		open(CONVERSIONMANAGEMENT_PAGE);
		//2、点击采购订单
		click(pageName, "采购订单");
		//3、选择转采订单
		click(pageName, "选择订单");
		//4、点击审核通过按钮
		click(pageName, "审核通过");
		//5、弹窗确定
		click(pageName, "确定");
		//6、获取审核结果
		Thread.sleep(1000);
		String text = getText(pageName, "审核结果");
		System.out.println("审核结果：" + text);
		//7、断言
		assertConatins(pageName, "审核结果", "审核成功!");
		log.info(pageName + "执行完毕");
	}
}
