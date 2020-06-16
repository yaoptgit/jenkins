package com.api.executor;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import org.apache.http.message.BasicNameValuePair;

import com.alibaba.fastjson.JSONObject;
import com.api.utils.PropertiesUtils;

public class ApiExecutor {
	/**
	 * 执行服务
	 * @param url			接口地址
	 * @param type			接口请求类型
	 * @param Params		接口json格式参数
	 */
	public static String doService(String url, String type, String Params) {
		// 读取配置文件中的content.type信息
		String contentType = PropertiesUtils.prop.getProperty("api.content.type");
		// 读取配置文件中的charset信息
		String charset = PropertiesUtils.prop.getProperty("api.charset");
		// 如果是form格式提交
		if ("form".equalsIgnoreCase(contentType)) {
			// json字符串转为List，可以先把json转为map，然后遍历map，把数据添加到List中
			ArrayList<BasicNameValuePair> parameters = jsonParam2ListParam(Params);
			if ("get".equalsIgnoreCase(type)) {// 如果是get请求方式
				return FormSubmitApiExecutor.testByGet(url, parameters, charset);
			} else if ("post".equalsIgnoreCase(type)) {// 如果是post请求方式
				return FormSubmitApiExecutor.testByPost(url, parameters, charset);
			}
			// 如果是json请求格式
		} else if ("json".equalsIgnoreCase(contentType)) {
			if ("get".equalsIgnoreCase(type)) {// 如果是get请求方式
				return JsonSubmitApiExecutor.testByPost(url, Params, charset);
			} else if ("post".equalsIgnoreCase(type)) {// 如果是post请求方式
				JsonSubmitApiExecutor.testByPost(url, Params, charset);
			}
		}
		return null;
	}

	private static ArrayList<BasicNameValuePair> jsonParam2ListParam(String Params) {
		// 把json字符串转成Map对象
		ArrayList<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
		Map<String, String> map = JSONObject.parseObject(Params, Map.class);
		// 获取Map中的所有key
		Set<String> kyeSet = map.keySet();
		// 遍历所有的key
		for (String key : kyeSet) {
			String value = map.get(key);
			parameters.add(new BasicNameValuePair(key, value));
		}
		return parameters;
	}
}
