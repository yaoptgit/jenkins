package com.api.pojo;

public class Case {
	private String caseId;
	private String Desc;
	private String apiId;
	private String params;
	//ExpectedResponseData(期望响应数据)	ActualResponseData(实际响应数据)
	private String expectedResponseData;
	private String actualResponseData;
	//ValidateSql(接口执行前的脚本验证)	BeforeResult(接口执行前数据库验证结果)	AfterResult(接口执行后数据库验证结果)
	private String validateSql;
	private String beforeResult;
	private String afterResult;
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String getDesc() {
		return Desc;
	}
	public void setDesc(String desc) {
		Desc = desc;
	}
	public String getApiId() {
		return apiId;
	}
	public void setApiId(String apiId) {
		this.apiId = apiId;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public String getExpectedResponseData() {
		return expectedResponseData;
	}
	public void setExpectedResponseData(String expectedResponseData) {
		this.expectedResponseData = expectedResponseData;
	}
	public String getActualResponseData() {
		return actualResponseData;
	}
	public void setActualResponseData(String actualResponseData) {
		this.actualResponseData = actualResponseData;
	}
	public String getValidateSql() {
		return validateSql;
	}
	public void setValidateSql(String validateSql) {
		this.validateSql = validateSql;
	}
	public String getBeforeResult() {
		return beforeResult;
	}
	public void setBeforeResult(String beforeResult) {
		this.beforeResult = beforeResult;
	}
	public String getAfterResult() {
		return afterResult;
	}
	public void setAfterResult(String afterResult) {
		this.afterResult = afterResult;
	}
	@Override
	public String toString() {
		return "Case [caseId=" + caseId + ", Desc=" + Desc + ", apiId=" + apiId + ", params=" + params
				+ ", expectedResponseData=" + expectedResponseData + ", actualResponseData=" + actualResponseData
				+ ", validateSql=" + validateSql + ", beforeResult=" + beforeResult + ", afterResult=" + afterResult
				+ "]";
	}
	public Case() {
		super();
	}
	public Case(String caseId, String desc, String apiId, String params, String expectedResponseData,
			String actualResponseData, String validateSql, String beforeResult, String afterResult) {
		super();
		this.caseId = caseId;
		Desc = desc;
		this.apiId = apiId;
		this.params = params;
		this.expectedResponseData = expectedResponseData;
		this.actualResponseData = actualResponseData;
		this.validateSql = validateSql;
		this.beforeResult = beforeResult;
		this.afterResult = afterResult;
	}
	 
}
