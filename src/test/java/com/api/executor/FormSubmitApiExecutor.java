package com.api.executor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.api.utils.AuthenticationCookieUtils;

public class FormSubmitApiExecutor {
	
	public static String testByGet(String url,List<BasicNameValuePair> parameters,String charset) {
		//1、创建request链接
		//2、填写url
		String parametersString = URLEncodedUtils.format(parameters, charset);
		url = url + "?" + parametersString;
		HttpGet get =new HttpGet(url);
		//4、发送请求
		//HttpClients是一个工具类，用来服务HTTPClient类的。
		//以后看见某个类名子是另一个类的名称+s，说明他就是一个工具类。
		HttpClient client = HttpClients.createDefault();
		//编译时异常的两种处理方式：1、抛出去    2、try catch捕获
		String result = "";
		try {
			get.setHeader("Content-Type","application/x-www-form-urlencoded;charset=" + charset);
			//String username = "客户";
			//4.1、发出请求获得响应对象
			AuthenticationCookieUtils.addCookiesInRequest(get);
			HttpResponse response = client.execute(get);
			AuthenticationCookieUtils.getCookieByName(response);
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
	
	public static String testByPost(String url,List<BasicNameValuePair> parameters,String charset) {
		//1、创建request链接
		//2、填写url
		HttpPost post =new HttpPost(url);
		//2.1、创建client对象
		HttpClient client = HttpClients.createDefault();
		//编译时异常的两种处理方式：1、抛出去    2、try catch捕获
		String result = "";
		try {
			//3、绑定参数
			post.setEntity(new UrlEncodedFormEntity(parameters,charset));
			//设置头
			//4、发送请求
			//4.1、发出请求获得响应对象
//			HttpHost proxy = new HttpHost("localhost",8888);  	//代理
//			HttpResponse response = client.execute(proxy,post);	//代理
			
			AuthenticationCookieUtils.addCookiesInRequest(post);
			
			HttpResponse response = client.execute(post);
			
			AuthenticationCookieUtils.getCookieByName(response);
			
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
	 
	public static void main(String[] args) {
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		 params.add(new BasicNameValuePair("mobilephone", "13312355555"));
		 params.add(new BasicNameValuePair("pwd", "123123"));
		 String format = URLEncodedUtils.format(params, "UTF-8");
		 System.out.println(format);
	}
}
