package com.test.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.usermodel.HeaderFooter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Footer;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
public class FormatingCellColor {

	public Object formatExcelCellColor() throws IOException {

		XSSFWorkbook workbook = new XSSFWorkbook();

		XSSFSheet sheet = workbook.createSheet("sheet1");
		XSSFRow row = sheet.createRow(0);
		// Setting background color to cell

		XSSFCellStyle style = workbook.createCellStyle();

		style.setFillBackgroundColor(IndexedColors.BRIGHT_GREEN.index);
		style.setFillPattern(FillPatternType.BIG_SPOTS);

		XSSFCell cell = row.createCell(1);
		cell.setCellValue("Welcome");
		cell.setCellStyle(style);

		// setting foreground color
		style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.YELLOW.index);
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell = row.createCell(2);
		cell.setCellValue("Back");
		cell.setCellStyle(style);

		FileOutputStream fos = new FileOutputStream("/home/rapidsoft/Desktop/excel/test1.xlsx");

		workbook.write(fos);
		System.out.println("File generated successfully.");
		return "File generated successfully.";
	}

	public Object hashMapToExcel() throws IOException {

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Student data");
		Map<String, String> data = new LinkedHashMap<>();
		for (int i = 1; i < 20; i++) {
			data.put("ID" + i, "Student" + i);
		}

		int rowno = 0;
		XSSFRow header = sheet.createRow(rowno++);
		XSSFCell hcell0 = header.createCell(0);
		hcell0.setCellValue("Student Id");
		XSSFCell hcell1 = header.createCell(1);
		hcell1.setCellValue("Student Name");

		for (Map.Entry entry : data.entrySet()) {
			XSSFRow row = sheet.createRow(rowno++);
			row.createCell(0).setCellValue((String) entry.getKey());
			row.createCell(1).setCellValue((String) entry.getValue());

		}
		
		FileOutputStream fos = new FileOutputStream("/home/rapidsoft/Desktop/excel/test1.xlsx");

		workbook.write(fos);
		System.out.println("File generated successfully.");
		return "File generated successfully.";
	}
	
	public Object readDataToHashMap() throws IOException
	{
		FileInputStream fos = new FileInputStream("/home/rapidsoft/Desktop/excel/test1.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fos);
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		int rows = sheet.getLastRowNum();
		HashMap<String,String> data = new LinkedHashMap<>();
		
		//Reading data from the excel to HashMap
		
		for(int r=1;r<=rows;r++)
		{
			String key = sheet.getRow(r).getCell(0).getStringCellValue();
			String value = sheet.getRow(r).getCell(1).getStringCellValue();
			data.put(key, value);
		}
		
		
		return data;
	}
	
	public Object workingWithDateCells() throws IOException
	{
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Date Formats");
		sheet.createFreezePane( 0, 1, 0, 1 );
		sheet.autoSizeColumn(0);
		XSSFRow row = sheet.createRow(0);
		XSSFCell cell0 = row.createCell(0);
		cell0.setCellValue("DATE");
//		XSSFCellStyle cell0Style = workbook.createCellStyle();
//		cell0Style.setLocked(true);
//		cell0.setCellStyle(cell0Style);
		
		Footer footer = sheet.getFooter();
		footer.setCenter("Page " + HeaderFooter.page() + " of " + HeaderFooter.numPages() );
		
		//Date in number format
		XSSFCell cell = sheet.createRow(1).createCell(0);
		cell.setCellValue(new Date());
		
		//Date in any format
		XSSFCreationHelper creationHelper = workbook.getCreationHelper();
		
		//FORMAT1 : dd-MM-yyyy
		XSSFCellStyle style1 = workbook.createCellStyle();
		style1.setDataFormat(creationHelper.createDataFormat().getFormat("dd-MM-yyyy"));
		XSSFCell cell1 = sheet.createRow(2).createCell(0);
		cell1.setCellValue(new Date());
		cell1.setCellStyle(style1);
		
		
		//FORMAT2 : MM-dd-yyyy
		XSSFCellStyle style2 = workbook.createCellStyle();
		style2.setDataFormat(creationHelper.createDataFormat().getFormat("MM-dd-yyyy"));
		XSSFCell cell2 = sheet.createRow(3).createCell(0);
		cell2.setCellValue(new Date());
		cell2.setCellStyle(style2);
		
		
		//FORMAT3 : dd-MM-yyyy
		XSSFCellStyle style3 = workbook.createCellStyle();
		style3.setDataFormat(creationHelper.createDataFormat().getFormat("MM-dd-yyyy hh:mm:ss"));
//		style3.setAlignment(HorizontalAlignment.);
		
		XSSFCell cell3 = sheet.createRow(4).createCell(0);
		cell3.setCellValue(new Date());
		cell3.setCellStyle(style3);
		
		XSSFCreationHelper helper = workbook.getCreationHelper();
		Hyperlink link = helper.createHyperlink(HyperlinkType.URL);
		
		
		XSSFCell cell4 = sheet.createRow(5).createCell(1);
		cell4.setCellValue("https://poi.apache.org/components/spreadsheet/quick-guide.html");
		link.setAddress("https://poi.apache.org/components/spreadsheet/quick-guide.html");
		cell4.setHyperlink(link);

		FileOutputStream fos = new FileOutputStream("/home/rapidsoft/Desktop/excel/test1.xlsx");

		workbook.write(fos);
		System.out.println("File generated successfully.");
		return "File generated successfully.";
	}
	

}
