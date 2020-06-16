package com.api.reflect;

public class Case {
	private String apiId;
	private String apiName;
	private String type;
	private String url;

	public Case(String apiId, String apiName, String type, String url) {
		super();
		this.apiId = apiId;
		this.apiName = apiName;
		this.type = type;
		this.url = url;
	}

	public Case() {
		super();
	}

	public String getApiId() {
		return apiId;
	}

	public void setApiId(String apiId) {
		this.apiId = apiId;
	}

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Case [apiId=" + apiId + ", apiName=" + apiName + ", type=" + type + ", url=" + url + "]";
	}

}
