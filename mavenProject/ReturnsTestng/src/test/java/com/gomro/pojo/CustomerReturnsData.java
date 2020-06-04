package com.gomro.pojo;

/**
 * 客户退货实体类
 * 
 * @author yaopengtao
 * @data 2020年4月9日 上午8:45:37
 */
public class CustomerReturnsData {
	// IsNegative(是否为正向用例) Desc(用例描述) UserName(用户名) Pwd(密码) LogisticsName(物流名称)
	// ShipmentNumber(物流单号)Error(错误提示) TipPopup(弹窗提示)
	private String isNegative;
	private String desc;
	private String userName;
	private String pwd;
	private String logisticsName;
	private String shipmentNumber;
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
	public String getLogisticsName() {
		return logisticsName;
	}
	public void setLogisticsName(String logisticsName) {
		this.logisticsName = logisticsName;
	}
	public String getShipmentNumber() {
		return shipmentNumber;
	}
	public void setShipmentNumber(String shipmentNumber) {
		this.shipmentNumber = shipmentNumber;
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
		return "CustomerReturnsData [isNegative=" + isNegative + ", desc=" + desc + ", userName=" + userName + ", pwd="
				+ pwd + ", logisticsName=" + logisticsName + ", shipmentNumber=" + shipmentNumber + ", error=" + error
				+ ", tipPopup=" + tipPopup + "]";
	}

}
