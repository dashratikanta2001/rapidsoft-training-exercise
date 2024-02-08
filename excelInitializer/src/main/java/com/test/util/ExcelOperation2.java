package com.test.util;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.test.Dto.TestDto;

@Service
public class ExcelOperation2 {

	public Object writeFormulaData(TestDto testDto, String filePath) {
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		XSSFSheet sheet = workbook.createSheet("Numbers");
		XSSFRow row = sheet.createRow(0);
		
		row.createCell(0).setCellValue(10);
		row.createCell(1).setCellValue(20);
		
//		row.createCell(2).setCellFormula("A1*B1");
//		row.createCell(2).setCellValue("=CONCAT(A1,B1)");
		row.createCell(3).setCellFormula("SUM(A1,B1)");
		
		try {
			FileOutputStream outputStream = new FileOutputStream(filePath);
			workbook.write(outputStream);
			outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("File generated");
		return "File generated";
	}

}
