package com.gomro.pojo;

/**
 * 转采申请付款实体类
 * 
 * @author yaopengtao
 * @data 2020年5月7日 上午11:37:35
 */
public class TransferPaymentData {
	// IsNegative(是否为正向用例) Desc(用例描述) PaymentRatio(付款比例) Errors(错误提示)
	private String isNegative;
	private String desc;
	private String paymentRatio;
	private String errors;
	
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

	public String getPaymentRatio() {
		return paymentRatio;
	}

	public void setPaymentRatio(String paymentRatio) {
		this.paymentRatio = paymentRatio;
	}

	public String getErrors() {
		return errors;
	}

	public void setErrors(String errors) {
		this.errors = errors;
	}

	@Override
	public String toString() {
		return "TransferPaymentData [isNegative=" + isNegative + ", desc=" + desc + ", paymentRatio=" + paymentRatio
				+ ", errors=" + errors + "]";
	}
	
}
