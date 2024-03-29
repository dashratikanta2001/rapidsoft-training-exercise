package com.test.Helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.test.entity.Customer;

public class ExcelUploadHelperV1 {

	public static boolean isValidExcelFile(MultipartFile file) {
		return Objects.equals(file.getContentType(),
				"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	}

	public static List<Customer> getCustomersDataFromExcel(InputStream inputStream) {
		List<Customer> customers = new ArrayList<>();

		try {
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			XSSFSheet sheet = workbook.getSheet("Customer Information");

			int rowIndex = 0;
			for (Row row : sheet) {
				if (rowIndex == 0) {
					rowIndex++;
					continue;
				}

				Iterator<Cell> cellIterator = row.iterator();
				int cellIndex = 0;
				Customer customer = new Customer();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch (cellIndex) {
					case 0:
						customer.setId((int) cell.getNumericCellValue());
						break;
					case 1:
						customer.setName(cell.getStringCellValue());
						break;
					case 2:
						customer.setUsername(cell.getStringCellValue());
						break;
					case 3:
						customer.setEmail(cell.getStringCellValue());
						break;
					case 4:
						customer.setPassword(cell.getStringCellValue());
						break;
					case 5:
						customer.setAddress(cell.getStringCellValue());
						break;
					case 6:
						customer.setRoles(cell.getStringCellValue());
						break;
					case 7:
						customer.setEnabled(cell.getBooleanCellValue());
						break;
					default: {
					}
					}
					cellIndex++;
				}
				customers.add(customer);

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return customers;
	}
}
