package com.gomro.pojo;

public class PriceCalibrationData {
	// IsNegative(是否为正向用例) Desc(用例描述) UserName(用户名) Pwd(密码)Error(错误提示)
	private String isNegative;
	private String desc;
	private String userName;
	private String pwd;
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
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	@Override
	public String toString() {
		return "PriceCalibrationData [isNegative=" + isNegative + ", desc=" + desc + ", userName=" + userName + ", pwd="
				+ pwd + ", error=" + error + "]";
	}

}
