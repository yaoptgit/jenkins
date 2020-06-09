package com.gomro.cases;

import java.util.Arrays;
import java.util.List;


import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.gomro.factory.BaseTester;
import com.gomro.pojo.CreateTransferOrderData;
import com.gomro.utlis.CaseUtils;
import com.gomro.utlis.ExcelUtils;

/**
 * 创建转采订单
 * 
 * @author yaopengtao
 * @data 2020年4月24日 上午9:46:22
 */
public class CreateTransferOrderCase extends BaseTester {
	// IsNegative(是否为正向用例) Desc(用例描述) Remarks(备注) PurchasePrices(采购单价)
	// PurchaseQuantity(采购数量) Errors(错误提示)
	@Test(dataProvider = "datas")
	public void test(ITestContext testContext, String IsNegative,String Desc, String Remarks, String PurchasePrices,
			String PurchaseQuantity, String Errors) throws Exception {
		testContext.setAttribute("caseId", i++);
		String pageName = "创建转采订单";
		log.info("===============创建转采订单开始===============");
		// 1、进入到转采管理页面
		open(CONVERSIONMANAGEMENT_PAGE);
		// 2、点击转采客户订单商品
		click(pageName, "转采客户订单商品");
		// 3、选择转采商品
		click(pageName, "转采商品");
		// 4、点击生成订单
		click(pageName, "生成订单");
		// 5、选择付款条款
		click(pageName, "付款条约");
		elementsClick("//div[@class='layui-unselect layui-form-select layui-form-selected']//li//a", 7);
		// 6、选择供应商
		click(pageName, "供应商");
		doubleClick(pageName, "选择供应商");
		// 7、选择发票类型
		click(pageName, "发票类型");
		Thread.sleep(500);
		elementsClick("//*[@id='submitPurchaseOrder']/div[2]/div[4]/div[1]/div/div/dl/dd", 2);
		// 8、选择发票税率
		click(pageName, "发票税率");
		Thread.sleep(500);
		elementsClick("//*[@id='submitPurchaseOrder']/div[2]/div[2]/div[2]/div/div/dl/dd", 5);
		// 9、选择淘宝账户
		click(pageName, "淘宝账户");
		Thread.sleep(500);
		elementsClick("//div[@class='layui-unselect layui-form-select layui-form-selected']//li", 2);
		// 10、清空备注框并输入备注
		clearText(pageName, "备注");
		sendKeys(pageName, "备注", Remarks);
		// 11、输入需求日期
		sendKeys(pageName, "需求日期", "2021-04-26 00:00:00");
		// 12、输入采购单价（含税）
		clearText(pageName, "采购单价");
		sendKeys(pageName, "采购单价", PurchasePrices);
		// 13、输入采购数量
		clearText(pageName, "采购数量");
		sendKeys(pageName, "采购数量", PurchaseQuantity);
		// 14、点击提交采购订单
		click(pageName, "提交采购订单");
		//断言
		if ("0".equals(IsNegative)) {
			String textActual = getText(pageName, "必填项提示");
			System.out.println("错误提示：" + textActual);
			assertActualExpected(textActual, Errors);
			log.info(pageName + Desc + "执行完毕");
		} else {
			try {
				// 15、弹窗确认
				click(pageName, "弹窗确认");
				String textActual = getText(pageName, "提交采购订单提示");
				System.out.println("提交采购订单提示：" +  textActual);
				assertActualExpected(textActual, Errors);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				log.info(pageName + "执行完毕");
			}
		}
		

	}

	@DataProvider
	public static Object[][] datas() {
		List<CreateTransferOrderData> list = ExcelUtils.read("src/test/resources/CreateTransferOrder.xlsx", "Sheet1",
				CreateTransferOrderData.class);
		// IsNegative(是否为正向用例) Desc(用例描述) Remarks(备注) PurchasePrices(采购单价)
		// PurchaseQuantity(采购数量) Errors(错误提示)
		String[] cellNames = { "IsNegative","Desc", "Remarks", "PurchasePrices", "PurchaseQuantity", "Errors" };
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
