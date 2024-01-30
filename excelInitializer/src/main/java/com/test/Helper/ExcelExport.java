package com.test.Helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

@Component
public class ExcelExport {

	public String[] getHeader(Object object) {
		Class<?> class1 = object.getClass();

		Field[] declaredFields = class1.getDeclaredFields();

		String setFlides[] = new String[declaredFields.length];
		for (int i = 0; i < setFlides.length; i++) {
			setFlides[i] = declaredFields[i].getName();
		}

		return setFlides;
	}

	public ByteArrayInputStream datatoexcel(List<?> objlist) throws IOException {

		String[] HEADERS = getHeader(objlist.get(0));

		Workbook workbook = new XSSFWorkbook();

		CellStyle greenStyle = createCellStyle(workbook, IndexedColors.GREEN.getIndex());
		CellStyle orangeStyle = createCellStyle(workbook, IndexedColors.ORANGE.getIndex());
		CellStyle yellowStyle = createCellStyle(workbook, IndexedColors.YELLOW.getIndex());
		CellStyle RedStyle = createCellStyle(workbook, IndexedColors.RED.getIndex());
		Font boldFont = workbook.createFont();
		boldFont.setBold(true);

		// Set the bold Font to the yellowStyle

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {

			Sheet createSheet = workbook.createSheet("data");

			Row row = createSheet.createRow(0);

			for (int i = 0; i < HEADERS.length; i++) {
				Cell cell = row.createCell(i);
				cell.setCellValue(HEADERS[i].toUpperCase());
				yellowStyle.setFont(boldFont);
				cell.setCellStyle(yellowStyle);
			}

			int rowIndex = 1;
			DecimalFormat decimalFormat = new DecimalFormat("#.##"); // Define a format without trailing zeros

			for (Object obj : objlist) {
				Field[] declaredFields = obj.getClass().getDeclaredFields();
				Row createRow = createSheet.createRow(rowIndex);

				for (int i = 0; i < declaredFields.length; i++) {
					Field field = declaredFields[i];
					Object object = field.get(obj);
					Cell cell = createRow.createCell(i);
					if (object != null) {
						if (object instanceof Double) {
							double doubleValue = (Double) object;
							if (doubleValue == (int) doubleValue) {

								cell.setCellValue(Integer.toString((int) doubleValue));
							} else {

								cell.setCellValue(decimalFormat.format(object));
							}
						} else {

							cell.setCellValue(object.toString());
						}

					} else {
						cell.setCellValue("NULL");
						cell.setCellStyle(orangeStyle);
					}
				}
				rowIndex++;

			}

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());

		} catch (Exception e) {

			e.printStackTrace();

			System.out.println("falid to export datas");

		} finally {
			workbook.close();
		}

		return null;
	}

	private static CellStyle createCellStyle(Workbook workbook, short colorIndex) {
		CellStyle style = workbook.createCellStyle();
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setFillForegroundColor(colorIndex);
		return style;
	}

}
