package com.gomro.cases;

import static org.testng.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.springframework.util.StringUtils;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.gomro.factory.BaseTester;
import com.gomro.pojo.VendorApprovalRefundData;
import com.gomro.utlis.CaseUtils;
import com.gomro.utlis.ExcelUtils;

/**
 * 供应审批退款
 * 
 * @author yaopengtao
 * @data 2020年4月8日 上午9:25:36
 */
public class VendorApprovalRefundCase extends BaseTester {
	// AgreeAndDeny(同意或拒绝) Desc(用例描述) UserName(用户名) Pwd(密码) Error(错误提示)
	// TipPopup(弹窗提示) ApprovalComments(审批意见)
	@Test(dataProvider = "datas")
	public void test(ITestContext testContext, String AgreeAndDeny, String Desc, String UserName, String Pwd,
			String Error, String TipPopup, String ApprovalComments) throws Throwable {
		testContext.setAttribute("caseId", i++);
		log.info("======================供应审批退款======================");
		String pageName = "供应审批退款";
		// 1、退出平台账号并登录供应商账号
		Thread.sleep(3000);
		HoverMouse(pageName, "账号名称");
		isDisplayElement(pageName, "//div[1]/div[1]/div/div/ul[2]/li/dl/dd/a", "退出");
		click(pageName, "退出");
		open("https://test-pm.gomro.cn/login");
		sendKeys(pageName, "用户名", UserName);
		sendKeys(pageName, "密码", Pwd);
		click(pageName, "登录");
		// 2、进入到销售订单页面
		open(SALESORDER_PAGE);
		// 点击全部
		click(pageName, "全部");
		// 3、点击供应商订单编号
		click(pageName, "供应商订单编号");
		// 4、选择商品
		click(pageName, "选择商品");
		// 5、点击退款审批
		click(pageName, "退款审批");
		// 6、判断同意或拒绝
		if (AgreeAndDeny.equalsIgnoreCase("TRUE")) {
			// 同意流程
			// 点击同意退款
			isDisplayElement(pageName + "-同意退款", "//*[@id='confirmAgreeForm']/div[1]/div/div[1]/i", "");
			click(pageName, "同意退款");
			// 点击确定
			click(pageName, "确认");
			Thread.sleep(500);
			// 获取弹窗内容
			String text = getText(pageName, "审批通过弹窗内容");
			System.out.println("审批通过弹窗内容：" + text);
			// 断言
			try {
				assertConatins(pageName, "审批通过弹窗内容", TipPopup);
			} catch (Exception e) {
				e.printStackTrace();
				log.info(e.toString());
				assertTrue(false);
			}
		} else if (AgreeAndDeny.equalsIgnoreCase("FALSE")) {
			// 拒绝流程
			// 点击拒绝退款
			click(pageName, "拒绝退款");
			// 清空审批意见
			clearText(pageName, "审批意见");
			// 输入审批意见
			sendKeys(pageName, "审批意见", ApprovalComments);
			if (StringUtils.isEmpty(ApprovalComments)) {
				Thread.sleep(500);
				// 错误提示
				String actual = getText(pageName, "审批意见为空提示内容");
				System.out.println("审批意见为空提示内容：" + actual);
				// 断言
				assertActualExpected(actual, TipPopup);
			} else {
				// 断言
				try {
					// 点击确认
					click(pageName, "确认");
					String text = getText(pageName, "审批通过弹窗内容");
					System.out.println("审批通过弹窗内容：" + text);
					assertConatins(pageName, "审批通过弹窗内容", TipPopup);
				} catch (Exception e) {
					e.printStackTrace();
					log.info(e.toString());
					assertTrue(false);
				}
			}
		} else {
			log.info("请在用例的AgreeAndDeny列输入TRUE(同意)或FALSE(拒绝)");
		}

	}

	@DataProvider
	public static Object[][] datas() {
		//供应商拒绝退款测试用例路径
//		List<VendorApprovalRefundData> list = ExcelUtils.read("src/test/resources/VendorApprovalRefund_false.xlsx", "Sheet1",
//		VendorApprovalRefundData.class);
		//供应同意退款测试用例路径
		List<VendorApprovalRefundData> list = ExcelUtils.read("src/test/resources/VendorApprovalRefund_true.xlsx", "Sheet1",
				VendorApprovalRefundData.class);

		// AgreeAndDeny(同意或拒绝) Desc(用例描述) UserName(用户名) Pwd(密码) Error(错误提示)
		// TipPopup(弹窗提示) ApprovalComments(审批意见)
		String[] cellNames = { "AgreeAndDeny", "Desc", "UserName", "Pwd", "Error", "TipPopup", "ApprovalComments" };
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
