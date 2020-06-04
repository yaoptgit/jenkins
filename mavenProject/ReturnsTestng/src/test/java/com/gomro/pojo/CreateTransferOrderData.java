package com.gomro.pojo;

/**
 * 创建转采订单实体类
 * 
 * @author yaopengtao
 * @data 2020年4月26日 下午2:50:30
 */
public class CreateTransferOrderData {
	// IsNegative(是否为正向用例) Desc(用例描述) Remarks(备注) PurchasePrices(采购单价)
	// PurchaseQuantity(采购数量) Errors(错误提示)
	private String isNegative;
	private String desc;
	private String remarks;
	private String purchasePrices;
	private String purchaseQuantity;
	private String errors;
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getPurchasePrices() {
		return purchasePrices;
	}
	public void setPurchasePrices(String purchasePrices) {
		this.purchasePrices = purchasePrices;
	}
	public String getPurchaseQuantity() {
		return purchaseQuantity;
	}
	public void setPurchaseQuantity(String purchaseQuantity) {
		this.purchaseQuantity = purchaseQuantity;
	}
	public String getErrors() {
		return errors;
	}
	public void setErrors(String errors) {
		this.errors = errors;
	}
	@Override
	public String toString() {
		return "CreateTransferOrderData [isNegative=" + isNegative + ", desc=" + desc + ", remarks=" + remarks
				+ ", purchasePrices=" + purchasePrices + ", purchaseQuantity=" + purchaseQuantity + ", errors=" + errors
				+ "]";
	}
	
}
