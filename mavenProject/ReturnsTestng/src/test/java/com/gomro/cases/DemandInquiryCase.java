package com.gomro.cases;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.gomro.factory.BaseTester;
import com.gomro.pojo.CreateInquiryData;
import com.gomro.pojo.DemandInquiryData;
import com.gomro.utlis.CaseUtils;
import com.gomro.utlis.ExcelUtils;

/**
 * 需求转询价
 * 
 * @author admin
 *
 */

public class DemandInquiryCase extends BaseTester {
	// IsNegative(是否为正向用例) Desc(用例描述) RequirementTitle(询价标题) Note(备注)
	// Description(描述) Error(错误提示)
	@Test(dataProvider = "datas")
	public void test(ITestContext testContext, String IsNegative, String RequirementTitle, String Note,
			String Description, String Error) throws Throwable {
		log.info("================需求转询价===============");
		testContext.setAttribute("caseId", i++);
		String pageName = "需求转询价";
		// 1、进入到寻求转询价页面
		open(DemandInquiry_PAGE);
		click(pageName, "需求转询价页面");
		// 2、选择一条需求
		click(pageName, "选择需求");
		// 3、点击申请转询价
		click(pageName, "申请询价");
		// 4、输入询价标题
		sendKeys(pageName, "询价标题", RequirementTitle);
		// 5、输入询价截至日期
		sendKeys(pageName, "询价截止日期", "2021-01-22 00:00:00");
		Thread.sleep(2000);
		// 6、选择需求部门
		click(pageName, "需求部门");
		driver.findElement(By.xpath("//body//div//div//div//div//div//div//div//div//div//div//li")).click();
		// 7.1、点击联系信息
		click(pageName, "联系信息按钮");
		// 7.2、选择联系信息
		doubleClick(pageName, "选择联系信息");
		// 8、输入询价备注
		sendKeys(pageName, "询价备注", Note);
		// 9、输入询价描述
		sendKeys(pageName, "询价描述", Description);
		sendKeys(pageName, "包装数量", "10");
		//获取商品名称用于断言
		String attributeText = getAttributeText(pageName, "获取询价商品名称", "value");
		// 10、点击保存并提交
		click(pageName, "保存并提交");
		// 12、断言
		if ("0".equals(IsNegative)) {
			// 错误提示
			String actual = getText(pageName, "错误提示");
			// 断言
			assertActualExpected(actual, Error);
			log.info(pageName + "执行完毕");
		} else {
			//11、提示窗口 点击确定
			click(pageName, "提示窗口确定");
			try {
				Thread.sleep(2000);
				//切换到报价中页面
				click(pageName, "报价中页面");
				Thread.sleep(2000);
				String text = getText(pageName, "报价中页面获取询价商品名称");
				System.out.println(text);
				assertActualExpected(text, attributeText);
			} catch (Exception e) {
				e.printStackTrace();
				Assert.assertTrue(false);
				log.info(e.toString());
			}finally {
				log.info(pageName + "执行完毕");
			}
		}
	}

	@DataProvider
	public static Object[][] datas() {
		List<DemandInquiryData> list = ExcelUtils.read("src/test/resources/DemandInquiry.xlsx", "Sheet1",
				DemandInquiryData.class);
		// IsNegative(是否为正向用例) Desc(用例描述) RequirementTitle(询价标题) Note(备注)
		// Description(描述) Error(错误提示)
		String[] cellNames = { "IsNegative", "RequirementTitle", "Note", "Description", "Error" };
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
