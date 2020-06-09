package com.gomro.pojo;
/**
 * 需求转询价实体类
 * @author admin
 *
 */
public class DemandInquiryData {
	// IsNegative(是否为正向用例) Desc(用例描述) RequirementTitle(询价标题) Note(备注)
	// Description(描述) Error(错误提示)
	private String isNegative;
	private String desc;
	private String requirementTitle;
	private String note;
	private String description;
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

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "DemandInquiryData [isNegative=" + isNegative + ", desc=" + desc + ", requirementTitle="
				+ requirementTitle + ", note=" + note + ", description=" + description + ", error=" + error + "]";
	}

}
