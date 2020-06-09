package com.gomro.cases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.gomro.factory.BaseTester;

/**
 * 删除询价单
 * @author admin
 *
 */
public class DeleteInquiryCase extends BaseTester{
	String PageName = "删除询价单";
	@Test
	public void test() {
		//1、打开我的询价_待申请询价单 页面
		open(CREATEINQUIRY_PAGE);
		//2、点击选择询价单
		click(PageName, "询价单复选框");
		//3、点击删除按钮
		click(PageName, "删除按钮");
		//4、点击确认删除
		click(PageName, "删除_二次确认_提示框_确认按钮");
		//5、断言 是否删除成功
		try {
			//预期值
			String expected = "删除成功";
			//实际值
			Thread.sleep(1);
			String actual = getText(PageName, "删除提示");
			System.out.println(actual);
			assertActualExpected(actual, expected);
		} catch (Exception e) {
			Assert.assertTrue(false);
			e.printStackTrace();
			log.info(e.toString());
		}
		
	}
}
