package com.test.Helper;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.test.entity.Customer;

public class ExcelGenerateV1 {

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;

	private List<Customer> customerList;

	public ExcelGenerateV1(List<Customer> customerList) {
		super();
		this.customerList = customerList;

		workbook = new XSSFWorkbook();
	}

	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);

		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
			System.out.print("int = "+value+" : ");
		} else if (value instanceof Double) {
			cell.setCellValue((Double) value);
			System.out.print("double = "+value+" : ");
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
			System.out.print("Boolean = "+value+" : ");
		} else if (value instanceof Long) {
			cell.setCellValue((Long) value);
			System.out.print("long = "+value+" : ");
		} else {
			cell.setCellValue((String) value);
			System.out.print("string = "+value+" : ");
		}
		
		cell.setCellStyle(style);
		
		
	}

	
	private void createHeaderRow()
	{
		sheet = workbook.createSheet("Customer Information");
		
		Row row = sheet.createRow(0);
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
//		font.setFontHeightInPoints((short) 20);
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.CENTER);
		
//		createCell(row, 0, "Customer Information", style);
//		sheet.addMergedRegion(new CellRangeAddress(0,0,0,7));
//		
//		row = sheet.createRow(0);
//		font.setBold(true);
//		font.setFontHeight(20);
//		style.setFont(font);
		createCell(row, 0, "ID", style);
		createCell(row, 1, "NAME", style);
		createCell(row, 2, "USERNAME", style);
		createCell(row, 3, "EMAIL", style);
//		createCell(row, 3, "PASSWORD", style);
		createCell(row, 4, "ROLE", style);
		createCell(row, 5, "ADDRESS", style);
		createCell(row, 6, "ENABLED", style);
		
		
	}
	
	
	private void writeCustomerData()
	{
		int rowCount=1;
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(false);
		font.setFontHeight(12);
		style.setFont(font);
		
		for (Customer customer : customerList) {
			Row row = sheet.createRow(rowCount++);
			int columnCount=0;
			System.out.println("------------------------------------\n");
			createCell(row, columnCount++, customer.getId(), style);
			System.out.println();
			createCell(row, columnCount++, customer.getName(), style);
			System.out.println();
			createCell(row, columnCount++, customer.getUsername(), style);
			System.out.println();
			createCell(row, columnCount++, customer.getEmail(), style);
			System.out.println();
			createCell(row, columnCount++, customer.getRoles(), style);
			System.out.println();
			createCell(row, columnCount++, customer.getAddress(), style);
			System.out.println();
			createCell(row, columnCount++, customer.getEnabled(), style);
		}
		
	}
	
	public void exportDataToExcel(HttpServletResponse response) throws IOException
	{
		createHeaderRow();
		writeCustomerData();
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}
	
}
























