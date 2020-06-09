package com.gomro.pojo;

/**
 * 供应商确认开票实体类
 * 
 * @author yaopengtao
 * @data 2020年3月27日 下午3:02:11
 */
public class SupplierConfirmsInvoicingData {
	// IsNegative(是否为正向用例) Desc(用例描述) UserName(用户名) Pwd(密码) Issuer(开票人)
	// InvoiceCode(发票代码) InvoiceNumber(发票号码) Error(报错提示) TipPopup(提示弹窗)
	private String isNegative;
	private String desc;
	private String userName;
	private String pwd;
	private String issuer;
	private String invoiceCode;
	private String invoiceNumber;
	private String error;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getTipPopup() {
		return tipPopup;
	}

	public void setTipPopup(String tipPopup) {
		this.tipPopup = tipPopup;
	}

	@Override
	public String toString() {
		return "SupplierConfirmsInvoicingData [isNegative=" + isNegative + ", desc=" + desc + ", userName=" + userName
				+ ", pwd=" + pwd + ", issuer=" + issuer + ", invoiceCode=" + invoiceCode + ", invoiceNumber="
				+ invoiceNumber + ", error=" + error + ", tipPopup=" + tipPopup + "]";
	}

}
