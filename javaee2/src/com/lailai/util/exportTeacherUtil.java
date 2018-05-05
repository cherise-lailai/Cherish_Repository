package com.lailai.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.lailai.entity.Teacher;
import com.lailai.entity.User;

public class exportTeacherUtil {
	public static InputStream createTeacherExecl(String filename, List<Teacher> teacherList) {
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
		cell.setCellValue("姓名");

		cell = row.createCell(1);
		cell.setCellType(Cell.CELL_TYPE_STRING);
		cell.setCellValue("邮箱");
		
		cell = row.createCell(2);
		cell.setCellType(Cell.CELL_TYPE_STRING);
		cell.setCellValue("技能");

		for (int i = 0; i < teacherList.size(); i++) {
			row = sheet.createRow(i + 1);
			cell = row.createCell(0);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(teacherList.get(i).getName());

			cell = row.createCell(1);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(teacherList.get(i).getEmail());
			
			cell = row.createCell(2);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(teacherList.get(i).getGoodAt());
		}

		InputStream inputStream = null;

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			workbook.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] content = os.toByteArray();
		inputStream = new ByteArrayInputStream(content);
		return inputStream;
	}

}
