package com.gomro.listener;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;

import com.gomro.utlis.ScreemshotUitls;
import com.google.common.io.Files;

import io.qameta.allure.Attachment;
import sun.util.logging.resources.logging;

/**
 * @author yaopengtao
 * @data 2019年12月3日 上午11:27:16
 * 启动allure：mvn io.qameta.allure:allure-maven:serve
 */
public class AllureReportListener implements IHookable {
	public static Logger log = Logger.getLogger(AllureReportListener.class);

	@Override
	public void run(IHookCallBack cllBack, ITestResult testResult) {
		// 1、执行用例的test方法
		cllBack.runTestMethod(testResult);
		Throwable throwable = testResult.getThrowable();
		String caseId = testResult.getTestContext().getAttribute("caseId").toString();
		// 如果throwable 对象不为空说明test方法抛出了异常
		if (throwable != null) {
			try {
				// 3、截图，同时把报错的地方传递给allure
				screenshot(testResult.getMethod().getMethodName(), caseId);
			} catch (IOException e) {
				e.printStackTrace();
				log.info("错误截图传递到allure失败！！！");
			}
		}
	}

	@Attachment(value = "Failure in method {0}", type = "image/png")
	public byte[] screenshot(String methodName, String caseId) throws IOException {
		// 1、在src/test/resource/img/ 生成了一张图片
		String pngName = "src/test/resource/img/" + caseId + "_" + methodName + "_" + System.currentTimeMillis()
				+ ".png";
		File file = ScreemshotUitls.screenshot(pngName);
		// 2、把这个图片以附件的方式传送到allure
		return Files.toByteArray(file);
	}
}
