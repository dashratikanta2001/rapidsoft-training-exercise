package com.test.Helper;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

public class ExcelGenerateV3<T> {

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;

	private Class<?> entityName;
	private List<Field> fields = new ArrayList<>();
	private List<Method> methodList = new ArrayList<>();
	private List<Object> entitylist;
	private List<Method> methods = new ArrayList<>();
	private int x = 1;

	public ExcelGenerateV3(List<Object> entitylist) {
		// TODO Auto-generated constructor stub
		this.entitylist = entitylist;
		printFieldName(entitylist.get(0));

		int count=1;
		for (Field field : fields) {
			System.out.println((count++) +" : "+ field.getName());
//			findGetter(field.getName());
		}
//		for (Field field : entityName.getDeclaredFields()) {
//			findGetter(field.getName());
//
//		}
		sortedMethod();
		
		for (Method method : methods) {
			System.out.println(method.getName());
		}
		
		workbook = new XSSFWorkbook();
	}

	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		System.out.println(value);
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
			createCell(row, index++, header.getName(), style);
		}

	}

	private void writeCustomerData() {
		int rowCount = 1;
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(false);
		font.setFontHeight(12);
		style.setFont(font);
System.out.println("____________________________________________________________________________");
		for (Object entity : entitylist) {
			Row row = sheet.createRow(rowCount++);
			Object[] values = printEntityValues(entity);
//			System.out.println(values);
//			if (values != null) {
//				int columnCount = 0;
//				for (Object value : values) {
//					createCell(row, columnCount++, value, style);
//				}
//
//			}

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
		Method[] allMethods = entityName.getMethods();

//		for (Method mm : methods) {
//			System.out.println(mm.getName());
//		}
		for (Method method : allMethods) {
			if (method.getName().startsWith("get") && !method.getName().equalsIgnoreCase("getClass")) {
				
//				System.out.println(method.getName());
				methodList.add(method);
			}
		}
		for (Field field : entityName.getDeclaredFields()) {

			boolean find=false;
			if (field.getAnnotation(Embedded.class) != null) {
				try {
					printFieldName(field.getType().getDeclaredConstructor().newInstance());
				} catch (Exception e) {
					e.printStackTrace();
				}
				find=true;
			}
			
//			System.out.println(field.getName());
			
			if (!find) {
				find=false;
				fields.add(field);
//				Method method = findGetter(field.getName());
//				if (method !=null) {
//					methodList.add(method);
//				}
			}
		}
		
	}
	
	
//	public Object invokeMethod(Object object)
//	{
//		for (Field field : entityName.getDeclaredFields()) {
//
//			boolean find=false;
//			if (field.getAnnotation(Embedded.class) != null) {
//				try {
//					printFieldName(field.getType().getDeclaredConstructor().newInstance());
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				find=true;
//			}
//			
////			System.out.println(field.getName());
//			
//			if (!find) {
//				find=false;
//				fields.add(field);
////				Method method = findGetter(field.getName());
////				if (method !=null) {
////					methodList.add(method);
////				}
//			}
//		}
//	}

	public Object[] printEntityValues(Object object) {

		Object[] values = new Object[methods.size()];
		int index = 0;

		for (Method method : methods) {
			try {
				boolean flag=false;
				 Object invoke = method.invoke(object);
				if (invoke.getClass().isAnnotationPresent(Embeddable.class)) {
					System.out.println("1");
					printEntityValues(invoke);
					flag=true;
				}
				if (!flag) {
					values[index]=invoke;
					System.out.println("value = "+ method.getName()+" : "+values[index]);
					flag=false;
				}
//				values[index] = 
			} catch (Exception e) {
				e.printStackTrace();
//				continue;
			}
			index++;
		}

		return values;
	}

	private void sortedMethod()
	{
		for (Field field : fields) {
			int index=0;
			for (Method method : methodList) {
				if (method.getName().toLowerCase().endsWith(field.getName().toLowerCase())) {
					System.out.println("-> "+method.getName());
//					methods[index++]=method;
					methods.add(method);
				}
			}
		}
		
	}
	
	
//	private Method findGetter(String fieldName) {
//		
//		
//		
//		for (Method method : methods) {
//			if (method.getName().startsWith("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1))
//					&& method.getParameterCount() == 0 && !method.getName().equalsIgnoreCase("getClass")) {
////				methodList.add(method);
////				System.out.println(method.getName());
//				return method;
//			}
//		}
//		return null;
//
//	}

}
