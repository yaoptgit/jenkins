package com.gomro.pojo;
/**
 * 创建订单实体类
 * @author admin
 *
 */
public class CreateOrderData {
	//IsNegative(是否为正向用例)	Desc(用例描述)	ErpPo(用户名) Error(错误提示)
	private String isNegative;
	private String desc;
	private String erpPo;
	private String Error;
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
	public String getErpPo() {
		return erpPo;
	}
	public void setErpPo(String erpPo) {
		this.erpPo = erpPo;
	}
	public String getError() {
		return Error;
	}
	public void setError(String error) {
		Error = error;
	}
	@Override
	public String toString() {
		return "CreateOrderData [isNegative=" + isNegative + ", desc=" + desc + ", erpPo=" + erpPo + ", Error=" + Error
				+ "]";
	}
	
}
