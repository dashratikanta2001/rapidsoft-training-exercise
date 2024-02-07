package com.test.util;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.test.Dto.CountryDto;

@Service
public class ExcelOperation<T> {
	private List<T> countryList = new ArrayList<>();
	private List<Method> setterMethods = new ArrayList<>();

	private final String excelFilePath = "/home/rapidsoft/Desktop/countries.xlsx";

	public Object readData(Object dtoType) throws Exception {
		
		
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
		
		findAllMembers(dtoType);
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
				
				
				
				switch (cell.getColumnIndex()) {
				case 0:
					System.out.print(cell.getStringCellValue());
					setterMethods.get(0).invoke(instance, cell.getStringCellValue());
//					country.setCountry(cell.getStringCellValue());
					break;
				case 1:
					System.out.print(cell.getStringCellValue());
					setterMethods.get(1).invoke(instance, cell.getStringCellValue());

//					country.setCapital(cell.getStringCellValue());
					break;
				case 2:
					System.out.print(cell.getNumericCellValue());
					setterMethods.get(2).invoke(instance, (long)cell.getNumericCellValue());

//					country.setPopulation((long) cell.getNumericCellValue());
					break;
				}
				System.out.print(" | ");
////				cell.getv
				
				///////// 3rd Approach
				
//				System.out.println("C = "+instance.getCountry());
				
			}
			
			countryList.add((T) instance);
			
			System.out.println();
		}

		System.out.println(setterMethods);
		return countryList;
	}

	
	
	private void findAllMembers(Object obj)
	{
//		System.out.println(obj);
//		System.out.println();
//		Field[] fields = obj.getClass().getDeclaredFields();
//
//        // Create a map to store variable names and values
//        Map<String, Object> variableMap = new HashMap<>();
//
//        // Loop through each field and store its name and value in the map
//        for (Field field : fields) {
//            field.setAccessible(true); // Ensure private fields can be accessed
//            try {
//                // Get the value of the field from the object
//                Object value = field.get(obj);
//                // Store the variable name and value in the map
//                variableMap.put(field.getName(), value);
//            } catch (IllegalAccessException e) {
//                // Handle if unable to access the field
//                System.out.println("Error accessing field: " + e.getMessage());
//            }
//        }
//
//        // Print the variable name from the index
//        
//        for (Map.Entry<String, Object> var : variableMap.entrySet()) {
//			String key = var.getKey();
//			Object val = var.getValue();
//			System.out.println(key);
//			
//		}
		
		Class<?> clazz = obj.getClass();
        Method[] methods = clazz.getMethods();

        System.out.println("Setter methods for class " + clazz.getSimpleName() + ":");

        for (Method method : methods) {
            // Check if method starts with "set" and has one parameter
            if (method.getName().startsWith("set") && method.getParameterCount() == 1) {
                System.out.println(method.getName());
                setterMethods.add(method);
            }
        }
        
	}

}
