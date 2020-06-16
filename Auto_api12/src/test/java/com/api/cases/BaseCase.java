package com.api.cases;

import java.util.List;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.api.executor.ApiExecutor;
import com.api.pojo.SQLData;
import com.api.pojo.WriteBackData;
import com.api.utils.ApiInfoUtlis;
import com.api.utils.CaseUtils;
import com.api.utils.DBCheckUtils;
import com.api.utils.ExcelUtls;

public abstract class BaseCase {
	public Logger log = Logger.getLogger(BaseCase.class);

	public String[] cellNames = { "CaseId", "ApiId", "Params", "ValidateSql" };

	@BeforeSuite
	public void init() {
		log.info("===================套件开始执行====================");
	}

	@Test(dataProvider = "datas")
	public void test(String caseId, String apiId, String params, String validateSql) {
		log.info("===================开始" + caseId + "测试用例====================");
		String url = ApiInfoUtlis.getUrlByApiId(apiId);
		String type = ApiInfoUtlis.getTypeByApiId(apiId);
		// 执行替换参数
		params = CaseUtils.replaceVariable(params);
		validateSql = CaseUtils.replaceVariable(validateSql);

		// 执行接口调用之前的数据库操作
		DBCheckUtils.query(caseId, "BeforeResult", validateSql);

		// 执行接口
		String result = ApiExecutor.doService(url, type, params); // 代码的整理，提高复用性

		// 执行接口调用之后的数据库操作
		DBCheckUtils.query(caseId, "AfterResult", validateSql);

		// 批量回写
		List<SQLData> list = JSONObject.parseArray(validateSql, SQLData.class);
		// System.out.println(list);
		WriteBackData wbd = new WriteBackData(caseId, "ActualResponseData", result);
		ExcelUtls.wbdList.add(wbd);
		log.info("===================结束" + caseId + "测试用例====================");
	}

	@AfterSuite
	public void batchWrite() {
		log.info("===================批量回写====================");
		// 执行批量回写
		ExcelUtls.batchWrite();
		log.info("===================套件结束执行====================");
	}

	// datas获取数据的方法教给子类去实现
	public abstract Object[][] datas();
}
