package com.api.utils;

import java.util.ArrayList;
import java.util.List;

import com.api.pojo.ApiInfo;


public class ApiInfoUtlis {
	public static List<ApiInfo> list = new ArrayList<ApiInfo>();
	static {
		list = ExcelUtls.read("接口信息", ApiInfo.class);
	}
	public static String getUrlByApiId(String apiId) {
		if (apiId == null) {
			return null;
		}
		for (ApiInfo apiData : list) {
			if (apiId.equals(apiData.getApiId())) {
				return apiData.getUrl();
			}
		}
		return null;
	}
	public static String getTypeByApiId(String apiId) {for (ApiInfo apiData : list) {
		if (apiId == null) {
			return null;
		}
		if (apiId.equals(apiData.getApiId())) {
			return apiData.getType();
		}
	}
		return null;
	}
}
