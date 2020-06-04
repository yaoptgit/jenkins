package com.gomro.pojo;

/**
 * 创建需求单实体类
 * 
 * @author admin
 *
 */
public class CreatedemandData {
	// IsNegative(是否为正向用例) Desc(用例描述) RequirementTitle(需求标题) ContactPerson(联系人)
	// ContactNumber(联系电话) CostCenter(成本中心) Note(备注) Description(描述)
	// DemandProductName(需求商品名称) Brand(品牌) Model(型号) Num(数量) Unit(单位)
	// Specification(规格) ProductDescription(商品描述) ProductNotes(商品备注) Error(错误提示)
	private String isNegative;
	private String desc;
	private String requirementTitle;
	private String contactPerson;
	private String contactNumber;
	private String costCenter;
	private String note;
	private String description;
	private String demandProductName;
	private String brand;
	private String model;
	private String num;
	private String unit;
	private String specification;
	private String productDescription;
	private String productNotes;
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
	public String getRequirementTitle() {
		return requirementTitle;
	}
	public void setRequirementTitle(String requirementTitle) {
		this.requirementTitle = requirementTitle;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDemandProductName() {
		return demandProductName;
	}
	public void setDemandProductName(String demandProductName) {
		this.demandProductName = demandProductName;
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
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public String getProductNotes() {
		return productNotes;
	}
	public void setProductNotes(String productNotes) {
		this.productNotes = productNotes;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	@Override
	public String toString() {
		return "CreatedemandData [isNegative=" + isNegative + ", desc=" + desc + ", requirementTitle="
				+ requirementTitle + ", contactPerson=" + contactPerson + ", contactNumber=" + contactNumber
				+ ", costCenter=" + costCenter + ", note=" + note + ", description=" + description
				+ ", demandProductName=" + demandProductName + ", brand=" + brand + ", model=" + model + ", num=" + num
				+ ", unit=" + unit + ", specification=" + specification + ", productDescription=" + productDescription
				+ ", productNotes=" + productNotes + ", error=" + error + "]";
	}
	
}
