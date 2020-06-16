package com.api.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.api.pojo.WriteBackData;

public class ExcelUtls {

	public static String filePath = PropertiesUtils.prop.getProperty("api.execl.path");
	public static Map<String, Integer> caseIdRownumMapping =  new HashMap<String, Integer>();
	public static Map<String, Integer> cellnameCellnumMapping =  new HashMap<String, Integer>();
	public static List<WriteBackData> wbdList = new ArrayList<WriteBackData>();
	static {
		loadRowAndCellMapping();
	}
	
	public static Object[][] read(int[] rows, int[] cells) {
		Object[][] datas = new Object[rows.length][cells.length];
		FileInputStream fis = null;
		// 创建流对象，找到文件位置
		try {
			// 创建流对象，找到Excel位置
			fis = new FileInputStream(filePath);
			// 双击打开
			Workbook workbook = WorkbookFactory.create(fis);
			// 找到对应的Sheet
			Sheet sheet = workbook.getSheet("用例");
			for (int i = 0; i < rows.length; i++) {// 循环row
				// 获取row
				Row row = sheet.getRow(rows[i]);
				// 获取cell
				for (int j = 0; j < cells.length; j++) {
					Cell cell = row.getCell(cells[j], MissingCellPolicy.CREATE_NULL_AS_BLANK);
					cell.setCellType(CellType.STRING);
					String value = cell.getStringCellValue();
					datas[i][j] = value;
				}
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
		return datas;
	}


	/**
	 * 读取excel中的指定sheet
	 * 
	 * @param <T>
	 * @param filePath
	 * @param sheetName
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> read(String sheetName, Class<T> clazz) {
		List<T> list = new ArrayList<T>();
		FileInputStream fis = null;
		try {
			// 创建流对象，找到Excel位置
			fis = new FileInputStream(filePath);
			// 双击打开
			Workbook workbook = WorkbookFactory.create(fis);
			// 获取指定Sheet页内容
			Sheet sheet = workbook.getSheet(sheetName);
			// 先获取表头
			Row titleRow = sheet.getRow(0);
			short titleCellNum = titleRow.getLastCellNum();
			Object[] titleArray = new Object[titleCellNum];
			for (int i = 0; i < titleArray.length; i++) {
				Cell cell = titleRow.getCell(i, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cell.setCellType(CellType.STRING);
				String value = cell.getStringCellValue();
				titleArray[i] = value.substring(0, value.indexOf("("));
				// titleArray[i] = value;
			}
//			System.out.println(Arrays.toString(titleArray));
//			System.out.println("==================================================================");
			// 在读取内容
			int lastRowNum = sheet.getLastRowNum();
			for (int i = 1; i <= lastRowNum; i++) { // 读行
				Row dataRow = sheet.getRow(i);
				// Class<Case> clazz = Case.class;
				// 反射创建对象
				T obj = clazz.newInstance();
				if (dataRow == null || isEmptyRow(dataRow)) {
					continue;
				}
				short dataCellNum = dataRow.getLastCellNum();
				for (int j = 0; j < dataCellNum; j++) {
					Cell cell = dataRow.getCell(j, MissingCellPolicy.CREATE_NULL_AS_BLANK);
					cell.setCellType(CellType.STRING);
					String value = cell.getStringCellValue();
					// 通过反射获取setXXX 方法，并且调用
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
	 * 判断excel的某一行是否为空
	 * 
	 * @param row
	 * @return
	 */
	private static boolean isEmptyRow(Row row) {
		short lastCellNum = row.getLastCellNum();
		for (int i = 0; i < lastCellNum; i++) {
			Cell cell = row.getCell(i, MissingCellPolicy.CREATE_NULL_AS_BLANK);
			cell.setCellType(CellType.STRING);
			String value = cell.getStringCellValue();
			// 一次循环不能判断一行是否都为空，但可以判断一列是否有值
			if (StringUtils.isNotBlank(value)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 回写excel数据
	 * @param caseId		caseId
	 * @param cellName		列明
	 * @param result		回写的值
	 */
	public static void write(String caseId, String cellName, String result) {
		// caseId -> rowNum
		Integer rowNum = caseIdRownumMapping.get(caseId);
		// cellName -> cellNum
		Integer cellNum = cellnameCellnumMapping.get(cellName);
		write("用例", rowNum, cellNum, result);
	}
	
	/**
	 * 加载caseId和行号的映射以及CellName和列号的映射
	 */
	private static void loadRowAndCellMapping() {
		FileInputStream fis = null;
		// 创建流对象，找到文件位置
		try {
			fis = new FileInputStream(filePath);
			Workbook workbook = WorkbookFactory.create(fis);
			Sheet sheet = workbook.getSheet("用例");
			
			//cellnameCellnumMapping cell名称和列号映射
			Row titleRow = sheet.getRow(0);
			short lastCellNum = titleRow.getLastCellNum();
			int caseIdCellnum = -1;
			//遍历titleRow所有列
			for (int i = 0; i < lastCellNum; i++) {
				Cell cell = titleRow.getCell(i,MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cell.setCellType(CellType.STRING);
				String cellname = cell.getStringCellValue();
				cellname = cellname.substring(0, cellname.indexOf("("));
				//如果当前列的名称是CaseId，获取对应的列号
				if ("CaseId".equals(cellname)) {
					caseIdCellnum = i;
				}
				//列明和列号映射储存
				cellnameCellnumMapping.put(cellname, i);
			}
			// caseIdRownnumMapping caseId和行号映射
			int lastRowNum = sheet.getLastRowNum();
			//从第二行开始遍历所有行
			for (int i = 1; i <= lastRowNum; i++) {
				Row row = sheet.getRow(i);
				//只获取CaseId列的值
				Cell cell = row.getCell(caseIdCellnum,MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cell.setCellType(CellType.STRING);
				String caseId = cell.getStringCellValue();
				//caseId和行号映射储存
				caseIdRownumMapping.put(caseId, i);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 根据指定的行号和列号回写数据
	 * 
	 * @param sheetName sheet名称
	 * @param rowNum    行号
	 * @param cellNum   列号
	 * @param result    回写的值
	 */
	private static void write(String sheetName, int rowNum, int cellNum, String result) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			// 创建流对象，找到Excel位置
			fis = new FileInputStream(filePath);
			// 双击打开
			Workbook workbook = WorkbookFactory.create(fis);
			// 获取指定Sheet页内容
			Sheet sheet = workbook.getSheet(sheetName);
			// 获取指定的行
			Row row = sheet.getRow(rowNum);
			// 获取指定的列
			Cell cell = row.getCell(cellNum, MissingCellPolicy.CREATE_NULL_AS_BLANK);
			cell.setCellType(CellType.STRING);
			// 回写数据
			cell.setCellValue(result);
			fos = new FileOutputStream(filePath);
			workbook.write(fos);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 批量回写excel
	 */
	public static void batchWrite() {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			// 创建流对象，找到Excel位置
			fis = new FileInputStream(filePath);
			// 双击打开
			Workbook workbook = WorkbookFactory.create(fis);
			// 获取指定Sheet页内容
			Sheet sheet = workbook.getSheet("用例");
			for (WriteBackData wbd : wbdList) {
				String caseId = wbd.getCaseId();
				String cellName = wbd.getCellName();
				String result = wbd.getResult();
				Integer rowNum = caseIdRownumMapping.get(caseId);
				Integer cellNum = cellnameCellnumMapping.get(cellName);
				// 获取指定的行
				Row row = sheet.getRow(rowNum);
				// 获取指定的列
				Cell cell = row.getCell(cellNum, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cell.setCellType(CellType.STRING);
				// 回写数据
				cell.setCellValue(result);
			}
			fos = new FileOutputStream(filePath);
			workbook.write(fos);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
