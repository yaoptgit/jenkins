package com.gomro.pojo;

/**
 * 平台申请开票
 * 
 * @author yaopengtao
 * @data 2020年3月27日 上午8:54:11
 */
public class PlatformApplicationForInvoicingData {
	// IsNegative(是否为正向用例) Desc(用例描述) Error(错误提示) InvoiceNote(发票信息备注) TipPopup(弹窗提示)
	private String isNegative;
	private String desc;
	private String error;
	private String invoiceNote;
	private String tipPopup;
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
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getInvoiceNote() {
		return invoiceNote;
	}
	public void setInvoiceNote(String invoiceNote) {
		this.invoiceNote = invoiceNote;
	}
	public String getTipPopup() {
		return tipPopup;
	}
	public void setTipPopup(String tipPopup) {
		this.tipPopup = tipPopup;
	}
	@Override
	public String toString() {
		return "PlatformApplicationForInvoicingData [isNegative=" + isNegative + ", desc=" + desc + ", error=" + error
				+ ", invoiceNote=" + invoiceNote + ", tipPopup=" + tipPopup + "]";
	}

}
