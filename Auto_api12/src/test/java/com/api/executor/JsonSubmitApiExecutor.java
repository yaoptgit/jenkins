package com.api.executor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

public class JsonSubmitApiExecutor {
	
	public static String testByGet(String url,String parameters,String charset) {
		//1、创建request链接
		//2、填写url
		//String parametersString = URLEncodedUtils.format(parameters, charset);
		
		//把json字符串转成key1 = vaule1&key2 = vaule2 ...,json转成map，然后遍历
		String parametersString = "";
		Map<String,String> map = JSONObject.parseObject(parameters,Map.class);
		//获取所有的key
		Set<String> keySet = map.keySet();
		//遍历
		String result = "";
		for (String key : keySet) {
			String value = map.get(key);
			if (!"".equals(parametersString)) {
				parametersString += "&" ;
			//除了第一对剩余的key和value
			}
			parametersString += key + "=" + value;
		}
		url += "?" + parametersString;
		HttpGet get =new HttpGet(url);
		//4、发送请求
		//HttpClients是一个工具类，用来服务HTTPClient类的。
		//以后看见某个类名子是另一个类的名称+s，说明他就是一个工具类。
		HttpClient client = HttpClients.createDefault();
		//编译时异常的两种处理方式：1、抛出去    2、try catch捕获
		try {
			//在请求头上加上json格式
			get.setHeader("Content-Type","application/json");
			//4.1、发出请求获得响应对象
			HttpResponse response = client.execute(get);
			//5、获取状态码
			int code = response.getStatusLine().getStatusCode(); //返回状态码
			//6、格式化
			HttpEntity entity = response.getEntity();//返回报文内容
			result = EntityUtils.toString(entity);
			//6.1、获取头信息
			String allHeaders = Arrays.toString(response.getAllHeaders()); //获取请求头
			
			System.out.println("响应报文头信息：" + allHeaders);
			System.out.println("状态码：" + code);
			System.out.println("响应报文头报文：" + result);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String testByPost(String url,String parameters,String charset) {
		//1、创建request链接
		//2、填写url
		HttpPost post =new HttpPost(url);
		//2.1、创建client对象
		HttpClient client = HttpClients.createDefault();
		//编译时异常的两种处理方式：1、抛出去    2、try catch捕获
		String result = "";
		try {
			//3、绑定参数
			//post.setEntity(new UrlEncodedFormEntity(parameters,charset));
			//设置头
			post.setHeader("Content-Type","application/json");
			post.setEntity(new StringEntity(parameters,charset));
			//4、发送请求
			//4.1、发出请求获得响应对象
//			HttpHost proxy = new HttpHost("localhost",8888);  	//代理
//			HttpResponse response = client.execute(proxy,post);	//代理
			HttpResponse response = client.execute(post);
			//5、获取状态码
			int code = response.getStatusLine().getStatusCode(); //返回状态码
			//6、格式化
			HttpEntity entity = response.getEntity();//返回报文内容
			result = EntityUtils.toString(entity);
			//6.1、获取头信息
			String allHeaders = Arrays.toString(response.getAllHeaders()); //获取请求头
			
			System.out.println("响应报文头信息：" + allHeaders);
			System.out.println("状态码：" + code);
			System.out.println("响应报文头报文：" + result);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	 
}
