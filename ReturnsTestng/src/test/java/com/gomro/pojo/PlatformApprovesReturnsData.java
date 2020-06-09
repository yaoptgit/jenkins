package com.gomro.pojo;

/**
 * 平台审批退货实体类
 * 
 * @author yaopengtao
 * @data 2020年4月7日 上午10:25:17
 */
public class PlatformApprovesReturnsData {
	// IsNegative(是否为正向用例) Desc(用例描述) UserName(用户名) Pwd(密码) PromptPopup(提示弹窗)
	private String isNegative;
	private String desc;
	private String userName;
	private String pwd;
	private String promptPopup;
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
	public String getPromptPopup() {
		return promptPopup;
	}
	public void setPromptPopup(String promptPopup) {
		this.promptPopup = promptPopup;
	}
	@Override
	public String toString() {
		return "PlatformApprovesReturnsData [isNegative=" + isNegative + ", desc=" + desc + ", userName=" + userName
				+ ", pwd=" + pwd + ", promptPopup=" + promptPopup + "]";
	}
	
}
