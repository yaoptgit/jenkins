package com.gomro.pojo;

public class UpdateInquiryData {
	// IsNegative(是否为正向用例) Desc(用例描述) InquiryTitle(询价标题) InquiryDeadline(询价截止日期)
	// InquiryDeadlineCollection(询价截止日期集合) InquiryProductName(询价商品名称) Brand(品牌)
	// Model(型号) Num(数量) Unit(单位) ErrorMsg(错误提示)
	private String isNegative;
	private String desc;
	private String inquiryTitle;
	private String inquiryDeadline;
	private String inquiryDeadlineCollection;
	private String inquiryProductName;
	private String brand;
	private String model;
	private String num;
	private String unit;
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
	public String getInquiryTitle() {
		return inquiryTitle;
	}
	public void setInquiryTitle(String inquiryTitle) {
		this.inquiryTitle = inquiryTitle;
	}
	public String getInquiryDeadline() {
		return inquiryDeadline;
	}
	public void setInquiryDeadline(String inquiryDeadline) {
		this.inquiryDeadline = inquiryDeadline;
	}
	public String getInquiryDeadlineCollection() {
		return inquiryDeadlineCollection;
	}
	public void setInquiryDeadlineCollection(String inquiryDeadlineCollection) {
		this.inquiryDeadlineCollection = inquiryDeadlineCollection;
	}
	public String getInquiryProductName() {
		return inquiryProductName;
	}
	public void setInquiryProductName(String inquiryProductName) {
		this.inquiryProductName = inquiryProductName;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	@Override
	public String toString() {
		return "CreateInquiryData [isNegative=" + isNegative + ", desc=" + desc + ", inquiryTitle=" + inquiryTitle
				+ ", inquiryDeadline=" + inquiryDeadline + ", inquiryDeadlineCollection=" + inquiryDeadlineCollection
				+ ", inquiryProductName=" + inquiryProductName + ", brand=" + brand + ", model=" + model + ", num="
				+ num + ", unit=" + unit + ", errorMsg=" + errorMsg + "]";
	}

}
