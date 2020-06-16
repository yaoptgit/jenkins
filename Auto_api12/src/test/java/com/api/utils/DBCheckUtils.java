package com.api.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.api.pojo.Case;
import com.api.pojo.SQLData;
import com.api.pojo.SQLResult;
import com.api.pojo.WriteBackData;

public class DBCheckUtils {
	public static void query(String caseId, String cellName, String ValidateSql) {
		// 判断是否为空
		if (StringUtils.isBlank(ValidateSql)) {
			return;
		}
		// 1、解析Json字符串转换成List<SQLData>对象
		List<SQLData> list = JSONObject.parseArray(ValidateSql, SQLData.class);
		// 定义会写结果集List集合
		List<SQLResult> results = new ArrayList<SQLResult>();
		// 2、遍历List，取出sql和no属性
		for (SQLData sqlData : list) {
			// 查询数据库dbutils,创建QueryRunner对象
			QueryRunner qr = new QueryRunner();
			Connection conn = JDBCUtils.getConnection();
			String sql = sqlData.getSql();
			String no = sqlData.getNo();
			try {
				// 执行数据库查询操作（数据连接、sql脚本、返回结果集对象）
				// 3、执行sql语句，并获得返回结果map
				Map<String, Object> map = qr.query(conn, sql, new MapHandler());
				// 4、封装SQLResult no 和 ColumnLabelsAndValues属性
				SQLResult sr = new SQLResult();
				sr.setNo(no);
				sr.setColumnLabelsAndValues(map);
				// 把SQLResult添加到集合中
				results.add(sr);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 5、结果集LIst转成Json字符串
		String result = JSONObject.toJSONString(results);
		// 6、回写Json字符串
		WriteBackData wbd = new WriteBackData(caseId, cellName, result);
		ExcelUtls.wbdList.add(wbd);
	}
}
