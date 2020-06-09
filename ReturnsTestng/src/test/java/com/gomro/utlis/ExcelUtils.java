package com.gomro.utlis;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yaopengtao
 * @data 2019年10月25日 上午10:58:35
 */
public class ExcelUtils {
//	public static String filePath = "src/test/resources/register.xlsx";
	/**
	 * 读取exce中指定Sheet页数据
	 * 
	 * @param filePath  excel文件路径
	 * @param sheetName sheet名称
	 * @return
	 */
	public static <T> List<T> read(String filePath, String sheetName, Class<T> clazz) {
		List<T> list = new ArrayList<T>();
		FileInputStream fis = null;
		try {
			// 找到excel文件的位置
			fis = new FileInputStream(filePath);
			// 双击打开
			Workbook workbook = WorkbookFactory.create(fis);
			// 获取指定Sheet页内容
			Sheet sheet = workbook.getSheet(sheetName);
			// 先获取表头 也就是第一行
			Row titleRow = sheet.getRow(0);
			short titleCellNum = titleRow.getLastCellNum();
			Object[] titleArray = new Object[titleCellNum];
			for (int i = 0; i < titleCellNum; i++) {
				Cell cell = titleRow.getCell(i, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cell.setCellType(CellType.STRING);
				String value = cell.getStringCellValue();
				titleArray[i] = value.substring(0, value.indexOf("("));
			}
			// 再读取内容
			int lastRowNum = sheet.getLastRowNum();
			for (int i = 1; i <= lastRowNum; i++) {
				Row dataRow = sheet.getRow(i);
				// 反射创建对象
				T obj = clazz.newInstance();
				if (dataRow == null || isEmptyRow(dataRow)) {
					continue;
				}
				short dataCelNum = dataRow.getLastCellNum();
				for (int j = 0; j < dataCelNum; j++) {
					Cell cell = dataRow.getCell(j, MissingCellPolicy.CREATE_NULL_AS_BLANK);
					cell.setCellType(CellType.STRING);
					String value = cell.getStringCellValue();
					// 通过反射获取setXXX方法，并且调用。set + Desc
					String methodName = "set" + titleArray[j];
					Method method = clazz.getMethod(methodName, String.class);
					method.invoke(obj, value);
				}
				list.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	/**
	 * 判断excel某一行是否为空
	 * 
	 * @param dataRow
	 * @return
	 */
	private static boolean isEmptyRow(Row row) {
		short lastCellNum = row.getLastCellNum();
		for (int i = 0; i < lastCellNum; i++) {
			Cell cell = row.getCell(i, MissingCellPolicy.CREATE_NULL_AS_BLANK);
			cell.setCellType(CellType.STRING);
			String value = cell.getStringCellValue();
			// 一次循环不能判断一行是否都为空，但可以判断一列是否有值。
			if (StringUtils.isNotBlank(value)) {
				return false;
			}
		}
		return false;
	}
}
