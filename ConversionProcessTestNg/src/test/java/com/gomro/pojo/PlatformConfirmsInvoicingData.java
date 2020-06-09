package com.gomro.pojo;
/**
 * 平台确认开票
 * @author yaopengtao
 * @data 2020年3月26日 上午10:51:09
 */
public class PlatformConfirmsInvoicingData {
	// IsNegative(是否是正向用例) Desc(用例描述) UserName(用户名) Pwd(密码) InvoiceCode(发票代码)
	// InvoiceNumber(发票号码)
	// Issuer(开票人) Error(错误提示)
	private String isNegative;
	private String desc;
	private String userName;
	private String pwd;
	private String invoiceCode;
	private String invoiceNumber;
	private String issuer;
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

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "PlatformConfirmsInvoicingData [isNegative=" + isNegative + ", desc=" + desc + ", userName=" + userName
				+ ", pwd=" + pwd + ", invoiceCode=" + invoiceCode + ", invoiceNumber=" + invoiceNumber + ", issuer="
				+ issuer + ", error=" + error + "]";
	}

}
