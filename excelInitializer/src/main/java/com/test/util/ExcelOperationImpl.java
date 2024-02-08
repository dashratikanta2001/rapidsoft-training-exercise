package com.test.util;

import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;
import com.test.Dto.CountryDto;

@Service
public class ExcelOperationImpl<T> implements ExcelOperation{

//	private final String excelFilePath = "/home/rapidsoft/Desktop/countries.xlsx";
	ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public Object readData(Object dtoType,String excelFilePath) throws Exception {
		List<T> countryList = new ArrayList<>();
		 List<Method> setterMethods = new ArrayList<>();
		
//		setterMethods.clear();

		FileInputStream inputStream = new FileInputStream(excelFilePath);

		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

//		XSSFSheet sheet = workbook.getSheet("Sheet1");
		// This will start at index 0
		XSSFSheet sheet = workbook.getSheetAt(0);

		// Using for loop
		/*
		 * int rows = sheet.getLastRowNum(); int cols =
		 * sheet.getRow(1).getLastCellNum();
		 * 
		 * for(int r=0; r<=rows; r++) { XSSFRow row = sheet.getRow(r);
		 * 
		 * for(int c=0; c<cols;c++) { XSSFCell cell = row.getCell(c);
		 * switch(cell.getCellType()) { case STRING
		 * :System.out.print(cell.getStringCellValue());break; case NUMERIC
		 * :System.out.print(cell.getNumericCellValue());break; case BOOLEAN
		 * :System.out.print(cell.getBooleanCellValue());break; }
		 * System.out.print(" | "); } System.out.println(); }
		 */

		// Iterator//////////////////////

		findAllMethods(dtoType,setterMethods);
		Iterator<Row> iterator = sheet.iterator();
		iterator.next();
		while (iterator.hasNext()) {
			Iterator<Cell> cellIterator = iterator.next().cellIterator();

//			CountryDto country = new CountryDto();

			Object instance = dtoType.getClass().getConstructor().newInstance();

			while (cellIterator.hasNext()) {
				XSSFCell cell = (XSSFCell) cellIterator.next();

				//////// 1st aproach

//				switch (cell.getCellType()) {
//				case STRING:
//					System.out.print(cell.getStringCellValue());
//					break;
//				case NUMERIC:
//					System.out.print(cell.getNumericCellValue());
//					break;
//				case BOOLEAN:
//					System.out.print(cell.getBooleanCellValue());
//					break;
//				default:
//					break;
//				}
//				System.out.print(" | ");
//				System.out.println("\n"+cell.getColumnIndex());
//				if (iterator.) {
//					
//				}

				//////////// 2nd approach

//				switch (cell.getColumnIndex()) {
//				case 0:
//					System.out.print(cell.getStringCellValue());
//					setterMethods.get(0).invoke(instance, cell.getStringCellValue());
////					country.setCountry(cell.getStringCellValue());
//					break;
//				case 1:
//					System.out.print(cell.getStringCellValue());
//					setterMethods.get(1).invoke(instance, cell.getStringCellValue());
//
////					country.setCapital(cell.getStringCellValue());
//					break;
//				case 2:
//					System.out.print(cell.getNumericCellValue());
//					setterMethods.get(2).invoke(instance, (long)cell.getNumericCellValue());
//
////					country.setPopulation((long) cell.getNumericCellValue());
//					break;
//				}
//				System.out.print(" | ");
//				cell.getv

				///////// 2nd Approach ended

				///////// 3rd Approach

				storeDataToObject(cell, instance,setterMethods);

				//////// 3rd Approach ended

			}
//			Thread.sleep(1000);

			countryList.add((T) instance);

//			System.out.println();
		}

//		System.out.println(setterMethods);
		return countryList;
	}

	private void storeDataToObject(XSSFCell cell, Object instance, List<Method> setterMethods) {

		int columnIndex = cell.getColumnIndex();
		try {

			Class<?>[] parameterTypes = setterMethods.get(columnIndex).getParameterTypes();
			Class<?> parameterType = parameterTypes[0];
//            	System.out.println("Parameter = "+parameterType);
			Object cellValue = getCellValue(cell);

			Object convertValue = objectMapper.convertValue(cellValue, parameterType);

			setterMethods.get(columnIndex).invoke(instance, convertValue);
//			System.out.println("Methods = = = === "+setterMethods.get(columnIndex));
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException("Please give a proper formated excel file.");
		}

	}

	private Object getCellValue(XSSFCell cell) {
		switch (cell.getCellType()) {
		case STRING:
			return cell.getStringCellValue();
		case NUMERIC:
			return cell.getNumericCellValue();
		case BOOLEAN:
			return cell.getBooleanCellValue();
		case FORMULA:
			return cell.getNumericCellValue();
		default:
			return null;
		}
	}

	private void findAllMethods(Object obj, List<Method> setterMethods) {

//		Class<?> clazz = obj.getClass();
//		Method[] methods = clazz.getMethods();
//
//		for (Method method : methods) {
//			if (method.getName().startsWith("set") && method.getParameterCount() == 1) {
//				System.out.println(method.getName());
//				setterMethods.add(method);
//			}
//		}
		
		Class<?> entityName = obj.getClass();
		Field[] fields = entityName.getDeclaredFields();
		Method[] methods = entityName.getMethods();

		for (Field field : fields) {
			String fieldName = field.getName();
			for (Method method : methods) {
				if (method.getName().startsWith("set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1))
						&& method.getParameterCount() == 1 && !method.getName().equalsIgnoreCase("setClass")) {
//					System.out.println(method.getName());
					setterMethods.add(method);
				}
			}

		}

	}

}
