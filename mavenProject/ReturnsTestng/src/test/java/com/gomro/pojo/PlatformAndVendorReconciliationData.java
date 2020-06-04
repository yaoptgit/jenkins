package com.gomro.pojo;

/**
 * 平台与供应商对账实体类
 * 
 * @author yaopengtao
 * @data 2020年3月26日 上午10:02:43
 */
public class PlatformAndVendorReconciliationData {
	// IsNegative(是否为正向用例) Desc(用例描述) Error(错误提示) TipPopupt(对账成功提示)
	private String isNegative;
	private String desc;
	private String error;
	private String tipPopupt;
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
	public String getTipPopupt() {
		return tipPopupt;
	}
	public void setTipPopupt(String tipPopupt) {
		this.tipPopupt = tipPopupt;
	}
	@Override
	public String toString() {
		return "PlatformAndVendorReconciliationData [isNegative=" + isNegative + ", desc=" + desc + ", error=" + error
				+ ", tipPopupt=" + tipPopupt + "]";
	}
	
}
