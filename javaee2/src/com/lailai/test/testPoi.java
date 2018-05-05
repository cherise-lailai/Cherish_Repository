package com.lailai.test;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class testPoi {
	public static void main(String[] args) {
		createExecl("lailai.xls");
	}
	public static void createExecl(String filename) {
		Workbook workbook = null;
		// 产生工作簿对象
		if (filename.endsWith(".xls")) {
			workbook = new HSSFWorkbook();
		} else {
			workbook = new XSSFWorkbook();
		}
		// 产生工作表对象
		Sheet sheet = workbook.createSheet("sheet one");
		// 产生一行
		Row row = sheet.createRow(0);

		// 产生一个单元格
		Cell cell = row.createCell(0);
		// 设置单元格内容为字符串类型
		// CELL_TYPE_BLANK
		// CELL_TYPE_BOOLEAN
		// CELL_TYPE_ERROR
		// CELL_TYPE_FORMULA
		// CELL_TYPE_NUMERIC
		// CELL_TYPE_STRING

		cell.setCellType(Cell.CELL_TYPE_STRING);
		// 设置单元格内容
		cell.setCellValue("序号");

		cell = row.createCell(1);
		cell.setCellType(Cell.CELL_TYPE_STRING);
		cell.setCellValue("姓名");

		cell = row.createCell(2);
		cell.setCellType(Cell.CELL_TYPE_STRING);
		cell.setCellValue("年龄");

		cell = row.createCell(3);
		cell.setCellType(Cell.CELL_TYPE_STRING);
		cell.setCellValue("日期");

		cell = row.createCell(4);
		cell.setCellType(Cell.CELL_TYPE_STRING);
		cell.setCellValue("简介");

		for (int i = 0; i < 5; i++) {
			row = sheet.createRow(i + 1);

			cell = row.createCell(0);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("2012080300" + i);

			cell = row.createCell(1);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("080300" + i);

			cell = row.createCell(2);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("080300" + i);

			cell = row.createCell(3);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(new Date());

			cell = row.createCell(4);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue("哈哈enemy哦凹阿拉" + i);
		}

		OutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(filename);
			workbook.write(outputStream);
			outputStream.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	

	/**
	 * 读取Excel表格
	 * 
	 * @param filename
	 */
	public static void readExcel(String filename) {
		InputStream inputStream = null;
		Workbook workbook = null;
		Sheet sheet = null;
		Row row = null;
		Cell cell = null;
		StringBuffer stringBuffer = new StringBuffer();
		try {
			inputStream = new FileInputStream(filename);
			if (filename.endsWith(".xls")) {
				workbook = new HSSFWorkbook(inputStream);
			} else {
				workbook = new XSSFWorkbook(inputStream);
			}
			for (int sheetIndex = 0, sheets = workbook.getNumberOfSheets(); sheetIndex < sheets; sheetIndex++) {
				sheet = workbook.getSheetAt(sheetIndex);
				if (null != sheet) {
					stringBuffer.append(sheet.getSheetName() + "\n");
					for (int rowIndex = 0, rows = sheet.getLastRowNum(); rowIndex <= rows; rowIndex++) {
						row = sheet.getRow(rowIndex);
						if (null != row) {
							for (int cellnum = 0, cells = row.getLastCellNum(); cellnum < cells; cellnum++) {
								cell = row.getCell(cellnum);
								if (!(cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
										|| !(DateUtil.isCellDateFormatted(cell))) {
									cell.setCellType(Cell.CELL_TYPE_STRING);
									stringBuffer.append(cell.getStringCellValue() + "\t");
								} else {
									Date date = cell.getDateCellValue();
									stringBuffer.append(new SimpleDateFormat("HH:mm:ss").format(date) + "\t");
								}
							}
							stringBuffer.append("\n");
						}
					}
					stringBuffer.append("\n\n");
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(stringBuffer);
	}
}
