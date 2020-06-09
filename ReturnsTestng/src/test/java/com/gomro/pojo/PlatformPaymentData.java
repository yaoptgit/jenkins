package com.gomro.pojo;

/**
 * 平台付款实体类
 * 
 * @author yaopengtao
 * @data 2020年3月30日 下午2:53:13
 */
public class PlatformPaymentData {
	// IsNegative(是否为正向用例) Desc(用例描述)
	// PaymentStatusCode() PaymentStatus(付款状态)
	// SubmitApprovalStatusCode() SubmitApprovalStatus(提交审批后状态)
	// BusinessApprovalStatusCode() BusinessApprovalStatus(业务审批后状态)
	// FinancialApprovalStatusCode() FinancialApprovalStatus(财务审批后状态)
	// PaymentPopupCode() PaymentPopup(付款弹窗)
	// Error(错误提示)
	private String isNegative;
	private String desc;
	private String paymentStatusCode;
	private String PaymentStatus;
	private String submitApprovalStatusCode;
	private String submitApprovalStatus;
	private String businessApprovalStatusCode;
	private String businessApprovalStatus;
	private String financialApprovalStatusCode;
	private String financialApprovalStatus;
	private String paymentPopupCode;
	private String paymentPopup;
	private String error;

	public String getIsNegative() {
		return isNegative;
	}

	public void setIsNegative(String isNegative) {
		this.isNegative = isNegative;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getPaymentStatusCode() {
		return paymentStatusCode;
	}

	public void setPaymentStatusCode(String paymentStatusCode) {
		this.paymentStatusCode = paymentStatusCode;
	}

	public String getPaymentStatus() {
		return PaymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		PaymentStatus = paymentStatus;
	}

	public String getSubmitApprovalStatusCode() {
		return submitApprovalStatusCode;
	}

	public void setSubmitApprovalStatusCode(String submitApprovalStatusCode) {
		this.submitApprovalStatusCode = submitApprovalStatusCode;
	}

	public String getSubmitApprovalStatus() {
		return submitApprovalStatus;
	}

	public void setSubmitApprovalStatus(String submitApprovalStatus) {
		this.submitApprovalStatus = submitApprovalStatus;
	}

	public String getBusinessApprovalStatusCode() {
		return businessApprovalStatusCode;
	}

	public void setBusinessApprovalStatusCode(String businessApprovalStatusCode) {
		this.businessApprovalStatusCode = businessApprovalStatusCode;
	}

	public String getBusinessApprovalStatus() {
		return businessApprovalStatus;
	}

	public void setBusinessApprovalStatus(String businessApprovalStatus) {
		this.businessApprovalStatus = businessApprovalStatus;
	}

	public String getFinancialApprovalStatusCode() {
		return financialApprovalStatusCode;
	}

	public void setFinancialApprovalStatusCode(String financialApprovalStatusCode) {
		this.financialApprovalStatusCode = financialApprovalStatusCode;
	}

	public String getFinancialApprovalStatus() {
		return financialApprovalStatus;
	}

	public void setFinancialApprovalStatus(String financialApprovalStatus) {
		this.financialApprovalStatus = financialApprovalStatus;
	}

	public String getPaymentPopupCode() {
		return paymentPopupCode;
	}

	public void setPaymentPopupCode(String paymentPopupCode) {
		this.paymentPopupCode = paymentPopupCode;
	}

	public String getPaymentPopup() {
		return paymentPopup;
	}

	public void setPaymentPopup(String paymentPopup) {
		this.paymentPopup = paymentPopup;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "PlatformPaymentData [isNegative=" + isNegative + ", desc=" + desc + ", paymentStatusCode="
				+ paymentStatusCode + ", PaymentStatus=" + PaymentStatus + ", submitApprovalStatusCode="
				+ submitApprovalStatusCode + ", submitApprovalStatus=" + submitApprovalStatus
				+ ", businessApprovalStatusCode=" + businessApprovalStatusCode + ", businessApprovalStatus="
				+ businessApprovalStatus + ", financialApprovalStatusCode=" + financialApprovalStatusCode
				+ ", financialApprovalStatus=" + financialApprovalStatus + ", paymentPopupCode=" + paymentPopupCode
				+ ", paymentPopup=" + paymentPopup + ", error=" + error + "]";
	}

}
