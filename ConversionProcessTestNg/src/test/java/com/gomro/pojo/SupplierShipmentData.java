package com.gomro.pojo;

/**
 * 供应商发货实体类
 * 
 * @author admin
 *
 */
public class SupplierShipmentData {
	// IsNegative(是否为正向用例) Desc(用例描述) UserName(用户名) Pwd(密码) LogisticsName(物流名称)
	// ShipmentNumber(物流单号) Freight(运费) Error(错误提示)
	private String isNegative;
	private String desc;
	private String userName;
	private String pwd;
	private String logisticsName;
	private String shipmentNumber;
	private String freight;
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
	public String getFreight() {
		return freight;
	}
	public void setFreight(String freight) {
		this.freight = freight;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	@Override
	public String toString() {
		return "SupplierShipmentData [isNegative=" + isNegative + ", desc=" + desc + ", userName=" + userName + ", pwd="
				+ pwd + ", logisticsName=" + logisticsName + ", shipmentNumber=" + shipmentNumber + ", freight="
				+ freight + ", error=" + error + "]";
	}

}
