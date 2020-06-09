package com.gomro.pojo;
/**
 * 转采选择采购员实体类
 * @author yaopengtao
 * @data 2020年4月24日 下午3:56:45
 */
public class ChooseBuyerData {
	// IsNegative(是否为正向用例)  Desc(用例描述) ErrorMsg(错误提示)
	private String isNegative;
	private String desc;
	private String errorMsg;
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
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	@Override
	public String toString() {
		return "ChooseBuyerData [isNegative=" + isNegative + ", desc=" + desc + ", errorMsg=" + errorMsg + "]";
	}
	
}
