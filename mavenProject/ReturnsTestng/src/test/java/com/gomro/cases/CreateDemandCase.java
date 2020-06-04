package com.gomro.cases;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.gomro.factory.BaseTester;
import com.gomro.pojo.CreatedemandData;
import com.gomro.pojo.ParticipateInAQuoteData;
import com.gomro.utlis.CaseUtils;
import com.gomro.utlis.ExcelUtils;
/**
 * 创建需求单
 * 
 * @author admin
 *
 */
public class CreateDemandCase extends BaseTester {
	// IsNegative(是否为正向用例) Desc(用例描述) RequirementTitle(需求标题) ContactPerson(联系人)
	// ContactNumber(联系电话) CostCenter(成本中心) Note(备注) Description(描述)
	// DemandProductName(需求商品名称) Brand(品牌) Model(型号) Num(数量) Unit(单位)
	// Specification(规格) ProductDescription(商品描述) ProductNotes(商品备注) Error(错误提示)
	@Test(dataProvider = "datas")
	public void test(ITestContext testContext, String IsNegative, String RequirementTitle, String ContactPerson,
			String ContactNumber, String CostCenter, String Note, String Description, String DemandProductName,
			String Brand, String Model, String Num, String Unit, String Specification, String ProductDescription,
			String ProductNotes, String Error) throws Exception {
		testContext.setAttribute("caseId", i++);
		String pageName = "创建需求";
		log.info("=================创建需求=================");
		// 1、打开 需求列表页面
		open(CreateDemand_PAGE);
		// 1.1、点击 需求列表
		click(pageName, "需求列表");
		// 2、点击创建
		click(pageName, "创建");
		// 3、输入需求标题
		sendKeys(pageName, "需求标题", RequirementTitle);
		// 4、选择部门
		click(pageName, "需求部门");
		elementsClick("//li[@data-spread='true']", 0);
		// 5、输入联系人
		sendKeys(pageName, "联系人", ContactPerson);
		// 6、输入电话
		sendKeys(pageName, "联系电话", ContactNumber);
		// 7、输入成本中心
		sendKeys(pageName, "成本中心", CostCenter);
		// 8、输入需求备注
		sendKeys(pageName, "备注", Note);
		// 9、输入需求描述
		sendKeys(pageName, "描述", Description);
		// 10、选择平台类目
		click(pageName, "平台类目");
		doubleClick(pageName, "选择类目");
		// 11、输入需求商品名称
		sendKeys(pageName, "需求商品名称", DemandProductName);
		// 12、输入品牌
		sendKeys(pageName, "品牌", Brand);
		// 13、输入型号
		sendKeys(pageName, "型号", Model);
		// 14、输入数量
		sendKeys(pageName, "数量", Num);
		// 15、输入单位
		sendKeys(pageName, "单位", Unit);
		// 16、输入规格
		sendKeys(pageName, "规格", Specification);
		// 17、添加附件
		//sendKeys(pageName, "附件", "src/test/resources/Create_demand.xlsx");
		// 18、输入描述
		sendKeys(pageName, "商品描述", ProductDescription);
		// 19、输入备注
		sendKeys(pageName, "商品备注", ProductNotes);
		// 20、点击保存并提交
		click(pageName, "保存并提交");
		// 21、断言
		if ("0".equals(IsNegative)) {
			// 错误提示
			String actual = getText(pageName, "错误提示");
			// 断言
			assertActualExpected(actual, Error);
			log.info(pageName + "执行完毕");
		} else {
			// 点击确定
			click(pageName, "提示弹窗确定");
			try {
				Thread.sleep(2000);
				// 进入到已分配的申请页面
				click(pageName, "已分配的申请");
				Thread.sleep(2000);
				String text = getText(pageName, "获取需求名称");
				//System.out.println(text);
				assertActualExpected(text, RequirementTitle);
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
		List<CreatedemandData> list = ExcelUtils.read("src/test/resources/Createdemand.xlsx", "Sheet1",
				CreatedemandData.class);
		// IsNegative(是否为正向用例) Desc(用例描述) RequirementTitle(需求标题) ContactPerson(联系人)
		// ContactNumber(联系电话) CostCenter(成本中心) Note(备注) Description(描述)
		// DemandProductName(需求商品名称) Brand(品牌) Model(型号) Num(数量) Unit(单位)
		// Specification(规格) ProductDescription(商品描述) ProductNotes(商品备注) Error(错误提示)
		String[] cellNames = { "IsNegative", "RequirementTitle", "ContactPerson", "ContactNumber", "CostCenter", "Note",
				"Description", "DemandProductName", "Brand", "Model", "Num", "Unit", "Specification",
				"ProductDescription", "ProductNotes", "Error" };
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
