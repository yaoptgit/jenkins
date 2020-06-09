package com.gomro.cases;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.gomro.factory.BaseTester;
import com.gomro.pojo.CreateOrderData;
import com.gomro.utlis.CaseUtils;
import com.gomro.utlis.ExcelUtils;
/**
 * 创建订单
 * @author admin
 *
 */
public class CreateOrderCase extends BaseTester{
	// IsNegative(是否为正向用例) Desc(用例描述) ErpPo(用户名) Error(错误提示)
	@Test(dataProvider = "datas")
	public void test(ITestContext testContext, String IsNegative, String ErpPo,String Error) throws Throwable {
		log.info("======================创建订单====================");
		testContext.setAttribute("caseId", i++);
		String pageName = "创建订单";
		// 1、进入到企业采购目录
		open(CreateOrderCase_PAGE);
		// 2、点击询价定标商品
		click(pageName, "询价定标商品");
		Thread.sleep(3000);
		// 3、点击加入
		click(pageName, "加入");
		// 4、点击采购清单
		Thread.sleep(3000);
		click(pageName, "采购清单");
		// 5、选择采购清单 商品复选框
		click(pageName, "商品复选框");
		// 6、点击采购订单
		click(pageName, "生成采购订单");
		// 7、输入ERO PO
		sendKeys(pageName, "输入ErpPO", ErpPo);
		// 8、点击提交订单
		click(pageName, "提交订单");
		// 10、断言
		if ("0".equals(IsNegative)) {
			// 错误提示
			String actual = getText(pageName, "错误提示");
			// 断言
			assertActualExpected(actual, Error);
		} else {
			// 9、警告框点击确定
			click(pageName, "警告弹窗确定");
			log.info(pageName + "执行完毕");
			try {
				Thread.sleep(2000);
				//进入到采购订单页面
				open(PurchaseOrder_PAGE);
				//点击全部
				click(pageName, "全部");
				Thread.sleep(2000);
				String text = getText(pageName, "获取ErpPo");
				System.out.println("ErpPo = " + text);
				assertActualExpected(text, ErpPo);
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
		List<CreateOrderData> list = ExcelUtils.read("src/test/resources/CreateOrder.xlsx", "Sheet1",
				CreateOrderData.class);
		// IsNegative(是否为正向用例) Desc(用例描述) ErpPo(用户名) Error(错误提示)
		String[] cellNames = { "IsNegative", "ErpPo","Error" };
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
