package com.gomro.pojo;

/**
 * 对账并申请开票实体类
 * 
 * @author yaopengtao
 * @data 2020年3月24日 下午3:45:02
 */
public class ReconcileAndRequestBillingData {
	// IsNegative(是否为正向用例) Desc(用例描述) Note(备注) Prompt(提交申请开票提示) Error(报错提示)
	private String isNegative;
	private String desc;
	private String note;
	private String error;
	private String prompt;
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
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getPrompt() {
		return prompt;
	}
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}
	@Override
	public String toString() {
		return "ReconcileAndRequestBillingData [isNegative=" + isNegative + ", desc=" + desc + ", note=" + note
				+ ", error=" + error + ", prompt=" + prompt + "]";
	}
	
}
