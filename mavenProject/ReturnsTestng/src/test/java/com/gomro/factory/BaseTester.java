package com.gomro.factory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.alibaba.fastjson.JSONObject;
import com.gomro.page.Page;
import com.gomro.page.UIElement;

/**
 * @author yaopengtao
 * @data 2019年12月3日 上午10:11:34
 */
public class BaseTester {
	// 用于caseId
	public static int i = 1;
	/**
	 * 页面路径
	 */
	// 网页域名
	public static final String domainName = "https://test-pm.gomro.cn";

	// 登录路径
	public static final String LOGIN_PAGE = domainName + "/login";
	// 需求池-需求列表页面
	public static final String CreateDemand_PAGE = domainName
			+ "/main?page=inquiry_source/demand/demand_pool&layId=applyDemand#!page=1";
	// 我的询价-需求转询价页面
	public static final String DemandInquiry_PAGE = domainName + "/main?page=inquiry_source/inquiry/my_inquiry#!page=1";
	// 我的询价-待申请询价单页面
	public static final String CREATEINQUIRY_PAGE = domainName + "/main?page=inquiry_source/inquiry/my_inquiry";
	// 我的询价-报价中页面路径
	public static final String INTHEOFFER_PAGE = domainName + "/main?page=inquiry_source/inquiry/my_inquiry#!page=1";
	// 我的报价-参与报价页面
	public static final String ParticipateInAQuote_PAGE = domainName
			+ "/main?page=inquiry_source/quotation/quotation#!page=1";
	// 寻源中心-我的核价
	public static final String PriceCalibration_PAGE = domainName + "/main?page=inquiry_source/inquiry/my_pricing";
	// 我的定标单页面
	public static final String MyCalibrationOrder_PAGE = domainName
			+ "/main?page=inquiry_source/inquiry/my_calibration";
	// 企业采购目录
	public static final String CreateOrderCase_PAGE = domainName + "/main?page=goods_center/goods_purchase";
	// 采购订单页面
	public static final String PurchaseOrder_PAGE = domainName + "/main?page=order_center/customer_order";
	// 销售订单页面
	public static final String SALESORDER_PAGE = domainName + "/main?page=order_center/vendor_order";
	// 对账管理页面
	public static final String ReconciliationManagement_PAGE = domainName + "/main?page=finance/reconciliation";
	// 平台发票页面
	public static final String PlatformInvoice_PAGE = domainName + "/main?page=finance/invoice_platform#!page=1";
	// 平台对账页面
	public static final String PlatformReconciliation_PAGE = domainName
			+ "/main?page=finance/reconciliation_platform#!page=1";
	// 供应商发票管理页面
	public static final String SUPPLIERINVOICEMANAGEMENT_PAGE = domainName + "/main?page=finance/invoice";
	// 平台结算页面
	public static final String PLATFORMSETTLEMENT_PAGE = domainName + "/main?page=finance/payment_platform#!page=1";
	// 供应商服务费发票管理页面
	public static final String SERVICEFEEINVOICEMANAGEMENT_PAGE = domainName
			+ "/main?page=finance/service_invoice#!page=1";
	// 售后退款页面
	public static final String After_SalesRefund_Page = domainName + "/main?page=service_center/return_goods#!page=1";
	// 转采管理页面
	public static final String CONVERSIONMANAGEMENT_PAGE = domainName
			+ "/main?page=purchase_management/transfer_order#!page=1";
	// 转采财务页面
	public static final String CONVERSIONFINANCE_PAGE = domainName + "/main?page=purchase_management/transfer_finance";

	public static List<Page> pages = new ArrayList<Page>();
	public static WebDriver driver = null;
	public static Logger log = Logger.getLogger(BaseTester.class);

	@BeforeSuite
	@Parameters({ "driverType", "seleniumVersion" })
	public void init(String driverType, String seleniumVersion) throws IOException {
		log.info("============================套件开始============================");
		// 加载浏览器驱动
		Browser(driverType, seleniumVersion);
		// 加载PO对象
		loadPages();
	}

	public static void Browser(String driverType, String seleniumVersion) {
		log.info("============================选择浏览器驱动============================");
		String DriverType = driverType.toLowerCase();
		if ("chrome".equals(DriverType)) {
			// 浏览器驱动位置
			System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
			// 浏览器启动位置
			System.setProperty("webdriver.chrome.bin",
					"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
			// 创建driver 对象
			driver = new ChromeDriver();
		} else if ("ie".equals(DriverType)) {
			// 浏览器驱动位置
			System.setProperty("webdriver.ie.driver", "src/test/resources/IEDriverServer.exe");
			// 创建 InternetExplorerOptions 对象
			InternetExplorerOptions capabilities = new InternetExplorerOptions();
			// 忽略缩放大小比例
			capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			// 忽略保护模式
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			// 创建driver 对象
			driver = new InternetExplorerDriver();
		} else if ("firefox".equals(DriverType)) {
			if ("3.X".equals(seleniumVersion)) {
				// 配置浏览器驱动位置
				System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
				// 配置浏览器启动位置
				System.setProperty("webdriver.firefox.bin", "E:\\浏览器\\Firefox\\firefox.exe");
				driver = new FirefoxDriver();
			}
		} else {
			log.info("浏览器选择错误！！！");
		}
		// 浏览器最大化
		driver.manage().window().maximize();
	}

	// 加载Json UI库
	public static void loadPages() throws IOException {
		log.info("============================加载UI库============================");
		String json = FileUtils.readFileToString(new File("src/test/resources/UILibrary.json"), "UTF-8");
		pages = JSONObject.parseArray(json, Page.class);
	}

	public static void main(String[] args) throws IOException {
		loadPages();
		System.out.println(pages);
//		String string = "付款成功";
//		boolean b = string.contains("付款不成功");
//		System.out.println(b);
	}

	public static WebElement getElement(String pageName, String elementName) {
		WebElement findElement = null;
		// 如果是同一个页面
		for (Page page : pages) {
			if (pageName.equals(page.getPageName())) {
				// 从page中获取所有页面元素
				List<UIElement> uiElements = page.getUiElements();
				for (UIElement element : uiElements) {
					// 找到指定的元素
					if (elementName.equals(element.getName())) {
						String methodName = element.getBy();
						String value = element.getValue();
						// 反射处理定位方法
						// 反射第一步：获取字节码对象
						try {
							Method method = By.class.getMethod(methodName, String.class);
							// 因为是静态方法，不需要对象直接使用类名即可调用
							By by = (By) method.invoke(null, value);
							// 加载显示等待
							WebDriverWait wait = new WebDriverWait(driver, 5);
							findElement = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
						} catch (Exception e) {
							log.info(e.toString());
							e.printStackTrace();
						}
						return findElement;
					}
				}
			}
		}
		return null;
	}

	// 套件执行完关闭驱动
	@AfterSuite
	public void terardwon() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
		log.info("============================套件结束============================");
	}

	// 打开页面
	public void open(String url) {
		driver.get(url);
	}

	// 发送
	public static void sendKeys(String pageName, String elementName, String content) {
		WebElement element = getElement(pageName, elementName);
		if (element != null) {
			element.sendKeys(content);
		} else {
			log.info("元素定位失败《" + pageName + "》《" + elementName + "》");
		}
	}

	// 模拟键盘事件
	public static void keyboardEvents(String pageName, String elementName, Keys keyboard) {
		WebElement element = getElement(pageName, elementName);
		if (element != null) {
			element.sendKeys(keyboard);
		} else {
			log.info("元素定位失败《" + pageName + "》《" + elementName + "》");
		}
	}

	// 点击
	public void click(String pageName, String elementName) {
		WebElement element = getElement(pageName, elementName);
		if (element != null) {
			// 把鼠标移动到元素上
			Actions actions = new Actions(driver);
			actions.moveToElement(element).perform();
			// 点击该元素
			element.click();
		} else {
			log.info("元素定位失败《" + pageName + "》《" + elementName + "》");
		}
	}

	// 双击事件
	public void doubleClick(String pageName, String elementName) {
		WebElement element = getElement(pageName, elementName);
		if (element != null) {
			// 把鼠标移动到元素上
			Actions actions = new Actions(driver);
			actions.moveToElement(element).perform();
			// 双击该元素
			actions.doubleClick(element).perform();
		} else {
			log.info("元素定位失败《" + pageName + "》《" + elementName + "》");
		}
	}

	// confirm、alert弹窗 确定
	public void alertConfiemAccept() {
		driver.switchTo().alert().accept();
	}

	// confirm 弹窗 取消
	public void confiemDismiss() {
		driver.switchTo().alert().dismiss();
	}

	// js 点击等待
	public void jsWaitClick(String pageName, String elementName) {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.jsReturnsValue("return document.readyState == 'complete'"));
		WebElement element = getElement(pageName, elementName);
		if (element != null) {
			Actions actions = new Actions(driver);
			actions.moveToElement(element).perform();
			element.click();
		} else {
			log.info("元素定位失败《" + pageName + "》《" + elementName + "》");
		}
	}

	// 获取文本值
	public String getText(String pageName, String elementName) {
		WebElement element = getElement(pageName, elementName);
		if (element != null) {
			return element.getText();
		} else {
			log.info("元素定位失败《" + pageName + "》《" + elementName + "》");
			return null;
		}
	}

	// 获取某个标题前内的属性内容
	public String getAttributeText(String pageName, String elementName, String TagName) {
		WebElement element = getElement(pageName, elementName);
		if (element != null) {
			return element.getAttribute(TagName);
		} else {
			log.info("元素定位失败《" + pageName + "》《" + elementName + "》");
			return null;
		}

	}

	// 下拉框 文本选择
	public void selectByVisibleText(String pageName, String elementName, String text) {
		WebElement element = getElement(pageName, elementName);
		if (element != null) {
			Select select = new Select(element);
			if (StringUtils.isNotBlank(text)) {
				select.selectByVisibleText(text);
			}
		} else {
			log.info("元素定位失败《" + pageName + "》《" + elementName + "》");
		}
	}

	// 获取一组数据，并点击获取到的对应下标的数据
	public void elementsClick(String pathString, int index) {
		List<WebElement> list = driver.findElements(By.xpath(pathString));
		if (index >= 0 && index <= list.size()) {
			WebElement indexs = list.get(index);
			indexs.click();
		} else {
			log.info("集合长度为：" + list.size() + "\t输入的长度为：" + index + "\tindex参数异常");
		}
		
	}

	// iframe
	public void switchToFrame(String pageName, String elementName) {
		WebElement element = getElement(pageName, elementName);
		if (element != null) {
			driver.switchTo().frame(element);
			WebDriverWait wait = new WebDriverWait(driver, 5);
			// 页面加载完毕后，再进行下边的操作
			wait.until(ExpectedConditions.jsReturnsValue("return document.readyState == 'complete'"));
		} else {
			log.info("元素定位失败《" + pageName + "》《" + elementName + "》");
		}
	}

	// 断言 实际结果与期望值
	public void assertActualExpected(String actual, String expected) {
		Assert.assertEquals(actual, expected);
	}

	// 断言 跳转是否跳转页面
	public void assertUrl(String page) {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		// 如果在5s内找到url包含XXX则视为true
		Boolean flag = wait.until(ExpectedConditions.urlContains(page));
		Assert.assertTrue(flag);
	}

	// 断言 如果实际值包含XXX则视为成功
	public void assertConatins(String pageName, String elementName, String expected) {
		WebElement element = getElement(pageName, elementName);
		String expectedText = element.getText();
		// 如果实际值包含期望值，则断言成功
		boolean flag = expectedText.contains(expected);
		System.out.println("expectedText：" + expectedText);
		System.out.println("flag：" + flag);
		Assert.assertTrue(flag);
	}

	// 清空文本框
	public void clearText(String pageName, String elementName) {
		WebElement element = getElement(pageName, elementName);
		if (element != null) {
			element.clear();
		} else {
			log.info("元素定位失败《" + pageName + "》《" + elementName + "》");
		}
	}

	// 鼠标悬浮
	public void HoverMouse(String pageName, String elementName) {
		WebElement element = getElement(pageName, elementName);
		if (element != null) {
			Actions actions = new Actions(driver);
			actions.moveToElement(element).perform();
		} else {
			log.info("元素定位失败《" + pageName + "》《" + elementName + "》");
		}
	}

	// 某个文本显示等待
	public Boolean isDisplayElement(final String pageName, final String xpath, final String text) {
		log.info("等待指定元素文本显示");
		boolean result = false;
		int attempts = 0;
		while (attempts < 5) {
			try {
				attempts++;
				log.info(pageName + " —— 扫描元素开始第" + attempts + "次");
				result = new WebDriverWait(driver, 30).until(new ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver driver) {
						return driver.findElement(By.xpath(xpath)).getText().contains(text);
					}
				});
				log.info("扫描元素结束");
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// 随机生成10位数字，作为发票号使用
	public static String getReceiptNumber(int length) {
		String val = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			val += String.valueOf(random.nextInt(10));
		}
		return val;
	}
	
	//执行js
	public void RunJs(String js) {
		JavascriptExecutor jExecutor = (JavascriptExecutor) driver;
		jExecutor.executeScript(js);
	}
}
