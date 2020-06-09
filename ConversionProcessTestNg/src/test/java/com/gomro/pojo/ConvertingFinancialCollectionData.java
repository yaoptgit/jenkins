package com.gomro.pojo;
/**
 * 转采财务收票实体类
 * @author yaopengtao
 * @data 2020年5月9日 下午2:12:52
 */
public class ConvertingFinancialCollectionData {
	//IsNegative(是否为正向用例)	Desc(用例描述) Msg(提示)
	private String isNegative;
	private String desc;
	private String msg;
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
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "ConvertingFinancialCollectionData [isNegative=" + isNegative + ", desc=" + desc + ", msg=" + msg + "]";
	}
	
}
