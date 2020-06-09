package com.gomro.cases;

import com.gomro.factory.BaseTester;
import com.gomro.pojo.CreateInquiryData;
import com.gomro.utlis.CaseUtils;
import com.gomro.utlis.ExcelUtils;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
/**
 * 创建询价单
 * @author admin
 *
 */
public class CreateInquiryCase extends BaseTester {

	@Test(dataProvider = "datas")
	public void test(ITestContext testContext, String IsNegative, String InquiryTitle, String InquiryProductName,
			String Brand, String Model, String Num, String Unit, String ErrorMsg) throws Exception {
		testContext.setAttribute("caseId", i++);
		String pageName = "创建询价单";  
		open(CREATEINQUIRY_PAGE);
		// 点击创建
		click(pageName, "创建");
		// 输入询价单标题
		sendKeys(pageName, "询价单标题", InquiryTitle);
		// 询价截止日期
		sendKeys(pageName, "询价截止日期", "2021-01-22 00:00:00");
		// 需求部门
		click(pageName, "需求部门");
		// 选择部门
//		List<WebElement> list = driver.findElements(By.xpath("//*[@id=\"selecttree_departmentId\"]/li"));
//		list.get(0).click();
		elementsClick("//*[@id=\"selecttree_departmentId\"]/li", 0);

		// 点击联系信息
		click(pageName, "联系信息");
		// 选择个人信息
		doubleClick(pageName, "选择个人信息");
		// 点击新建
		click(pageName, "新建");
		// 点击平台类目
		click(pageName, "平台类目");
		// 选择类目
		doubleClick(pageName, "选择类目");
		// 输入商品名称
		sendKeys(pageName, "询价商品名称", InquiryProductName);
		// 输入品牌
		sendKeys(pageName, "品牌", Brand);
		// 输入型号
		sendKeys(pageName, "型号", Model);
		// 输入数量
		sendKeys(pageName, "数量", Num);
		// 输入单位
		sendKeys(pageName, "单位", Unit);
		// 点击保存并提交
		click(pageName, "保存并提交");
		if ("0".equals(IsNegative)) {
			// 错误提示
			String actual = getText(pageName, "错误提示");
			// 断言
			assertActualExpected(actual, ErrorMsg);
			log.info(pageName + "执行完毕");
		} else {
			try {
				
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
		List<CreateInquiryData> list = ExcelUtils.read("src/test/resources/createInquiry.xlsx", "Sheet1",
				CreateInquiryData.class);
		// IsNegative(是否为正向用例) Desc(用例描述) InquiryTitle(询价标题) InquiryDeadline(询价截止日期)
		// InquiryDeadlineCollection(询价截止日期集合) InquiryProductName(询价商品名称) Brand(品牌)
		// Model(型号) Num(数量) Unit(单位) ErrorMsg(错误提示)
		String[] cellNames = { "IsNegative", "InquiryTitle", "InquiryProductName", "Brand", "Model", "Num", "Unit",
				"ErrorMsg" };
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
