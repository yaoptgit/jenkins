package com.gomro.pojo;

/**
 * 平台收票
 * 
 * @author yaopengtao
 * @data 2020年3月30日 上午10:48:11
 */
public class PlatformConfirmsReceiptData {
	// IsNegative(是否为正向用例) Desc(用例描述) UserName(用户名) Pwd(密码) Error(错误提示) TipPopup(弹窗提示)
	private String isNegative;
	private String desc;
	private String userName;
	private String pwd;
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
		return "PlatformConfirmsReceiptData [isNegative=" + isNegative + ", desc=" + desc + ", userName=" + userName
				+ ", pwd=" + pwd + ", error=" + error + ", tipPopup=" + tipPopup + "]";
	}

}
