package com.gomro.pojo;
/**
 * 转采申请收票实体类
 * @author yaopengtao
 * @data 2020年5月9日 上午9:08:54
 */
public class InvoiceForTransferApplicationData {
	//IsNegative(是否为正向用例)	Desc(用例描述)	InvoiceAmount(发票金额)	Msg(提示内容)
	private String isNegative;
	private String desc;
	private String invoiceAmount;
	private String msg;
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
	public String getInvoiceAmount() {
		return invoiceAmount;
	}
	public void setInvoiceAmount(String invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "InvoiceForTransferApplicationData [isNegative=" + isNegative + ", desc=" + desc + ", invoiceAmount="
				+ invoiceAmount + ", msg=" + msg + "]";
	}
	
}
