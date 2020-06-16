package com.api.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;

public class AuthenticationTokenUtils {
	public static Map<String, String> tokens = new HashMap<String, String>();
	public static final String RESPONSE_HEADER = "Set-Cookie";
	public static final String REQUEST_HEADER = "Cookie";
	public static final String TOKEN_NAME = "JSESSIONID";
	
	// 抓取cookies存到 cookies缓存中
	public static void getCookieByName(HttpResponse response) {
		// 从响应头里面获取指定的头字段
		Header header = response.getFirstHeader(RESPONSE_HEADER);
		//如果header不为空
		if (header != null) {
			// 获取头字段值
			String cookie = header.getValue();
			// 如果头字段的值不为空
			if (StringUtils.isNotBlank(cookie)) {
				String[] values = cookie.split(";");
				for (String value : values) {
					// 如果包含JSESSIONID那么放入缓存cookies中。
					if (value.contains(TOKEN_NAME)) {
						tokens.put(TOKEN_NAME, value);
					}
				}
			}
		}
	}

	// 从cookies缓存中取出cookie添加到请求头中
	public static void addCookiesInRequest(HttpRequest request) {
		String value = tokens.get(TOKEN_NAME);
		// 如果cookie不为空添加到请求头里边
		if (StringUtils.isNotBlank(value)) {
			request.setHeader(REQUEST_HEADER, value);
		}

	}
}
