package com.test.Helper;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

public class ExcelGenerateV2<T> {

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;

	private Class<?> entityName;
	private Field[] fields;
	private List<Method> methodList = new ArrayList();
	private List<Object> entitylist;
	private Method[] methods;

	public ExcelGenerateV2(List<Object> entitylist) {
		// TODO Auto-generated constructor stub
		this.entitylist = entitylist;
		printFieldName(entitylist.get(0));
		workbook = new XSSFWorkbook();
	}

	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);

		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Double) {
			cell.setCellValue((Double) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else if (value instanceof Long) {
			cell.setCellValue((Long) value);
		} else {
			cell.setCellValue((String) value);
		}

		cell.setCellStyle(style);

	}

	private void createHeaderRow() {
		sheet = workbook.createSheet("Customer Information");

		Row row = sheet.createRow(0);
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.CENTER);

		int index = 0;
		for (Field header : fields) {
			createCell(row, index++, header.getName().toUpperCase(), style);
		}

	}

	private void writeCustomerData() {
		int rowCount = 1;
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(false);
		font.setFontHeight(12);
		style.setFont(font);

		for (Object entity : entitylist) {
			Row row = sheet.createRow(rowCount++);
			Object[] values = printEntityValues(entity);
			if (values != null) {
				int columnCount = 0;
				for (Object value : values) {
					createCell(row, columnCount++, value, style);
				}

			}

		}

	}

	public void exportDataToExcel(HttpServletResponse response) throws IOException {
		createHeaderRow();
		writeCustomerData();
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}

	private void printFieldName(Object object) {
		entityName = object.getClass();
		fields = entityName.getDeclaredFields();
		methods = entityName.getMethods();

		for (Field field : fields) {
			findGetter(field.getName());

		}
	}

	public Object[] printEntityValues(Object object) {

		Object[] values = new Object[methodList.size()];
		int index = 0;

		for (Method method : methodList) {
			try {
				values[index] = method.invoke(object);
			} catch (Exception e) {
				e.printStackTrace();
			}
			index++;
		}

		return values;
	}

	private void findGetter(String fieldName) {

		for (Method method : methods) {
			if (method.getName().startsWith("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1))
					&& method.getParameterCount() == 0 && !method.getName().equalsIgnoreCase("getClass")) {
				methodList.add(method);
			}
		}

	}

}
