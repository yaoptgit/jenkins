package com.gomro.pojo;

/**
 * 客户退货实体类
 * 
 * @author yaopengtao
 * @data 2020年4月3日 下午1:43:11
 */
public class ReturnsData {
	// IsNegative(是否为正向用例) Desc(用例描述) Num(退货数量) RefundInstructions(退款说明)
	// PromptPopup(提示弹窗)
	private String isNegative;
	private String desc;
	private String num;
	private String refundInstructions;
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
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getRefundInstructions() {
		return refundInstructions;
	}
	public void setRefundInstructions(String refundInstructions) {
		this.refundInstructions = refundInstructions;
	}
	public String getPromptPopup() {
		return promptPopup;
	}
	public void setPromptPopup(String promptPopup) {
		this.promptPopup = promptPopup;
	}
	@Override
	public String toString() {
		return "ReturnsData [isNegative=" + isNegative + ", desc=" + desc + ", num=" + num + ", refundInstructions="
				+ refundInstructions + ", promptPopup=" + promptPopup + "]";
	}
	
}
