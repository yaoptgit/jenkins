package com.gomro.pojo;

/**
 * 供应审批退款
 * 
 * @author yaopengtao
 * @data 2020年4月8日 上午10:55:55
 */
public class VendorApprovalRefundData {
	// AgreeAndDeny(同意或拒绝) Desc(用例描述) UserName(用户名) Pwd(密码) Error(错误提示)
	// TipPopup(弹窗提示) ApprovalComments(审批意见)
	private String agreeAndDeny;
	private String desc;
	private String userName;
	private String pwd;
	private String error;
	private String tipPopup;
	private String approvalComments;
	public String getAgreeAndDeny() {
		return agreeAndDeny;
	}
	public void setAgreeAndDeny(String agreeAndDeny) {
		this.agreeAndDeny = agreeAndDeny;
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
	public String getApprovalComments() {
		return approvalComments;
	}
	public void setApprovalComments(String approvalComments) {
		this.approvalComments = approvalComments;
	}
	@Override
	public String toString() {
		return "VendorApprovalRefundData [agreeAndDeny=" + agreeAndDeny + ", desc=" + desc + ", userName=" + userName
				+ ", pwd=" + pwd + ", error=" + error + ", tipPopup=" + tipPopup + ", approvalComments="
				+ approvalComments + "]";
	}
	
}
