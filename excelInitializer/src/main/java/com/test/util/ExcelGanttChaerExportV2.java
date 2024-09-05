package com.test.util;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.apache.poi.sl.usermodel.ShapeType;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.ShapeTypes;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.ClientAnchor.AnchorType;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.chart.LegendPosition;
import org.apache.poi.xddf.usermodel.chart.XDDFBarChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFChartLegend;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFConnector;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFSimpleShape;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSolidColorFillProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ExcelGanttChaerExportV2 {

	private static final int ROW_HEIGHT_CM = 36;

	public static ResponseEntity<?> createGanttChart() {
		try (XSSFWorkbook workbook = new XSSFWorkbook()) {
			Sheet dataSheet = workbook.createSheet("Plan Sheet");
			XSSFSheet ganttSheet = workbook.createSheet("Gantt Chart");
			XSSFDrawing ganttDrawing = ganttSheet.createDrawingPatriarch();

			CompletableFuture<Void> dataSheetThread = CompletableFuture
					.runAsync(() -> writeToTable(workbook, dataSheet));
			CompletableFuture<Void> ganttSheetThread = CompletableFuture
					.runAsync(() -> writeToGantt(workbook, ganttSheet, ganttDrawing));

			CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(dataSheetThread, ganttSheetThread);
			combinedFuture.get();

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			synchronized (workbook) {
				workbook.write(baos);
			}
			workbook.close();

			byte[] excelBytes = baos.toByteArray();

			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "attachment; filename=example.xlsx");

			return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	private static void writeToTable(XSSFWorkbook workbook, Sheet dataSheet) {
		int rowIndex = 0;

		CellStyle boldCellStyle = workbook.createCellStyle();
		Font boldFont = workbook.createFont();
		boldFont.setBold(true);
		boldFont.setFontHeightInPoints((short) 12);
		boldCellStyle.setFont(boldFont);

		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setColor(IndexedColors.WHITE.getIndex());
		headerFont.setFontHeightInPoints((short) 7);

		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFillForegroundColor(IndexedColors.DARK_TEAL.getIndex());
		headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		headerCellStyle.setFont(boldFont);
		headerCellStyle.setWrapText(true);
		headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
		headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

		CellStyle headerCellStyle1 = workbook.createCellStyle();
		headerCellStyle1.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
		headerCellStyle1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		headerCellStyle1.setFont(boldFont);
		headerCellStyle1.setWrapText(true);
		headerCellStyle1.setAlignment(HorizontalAlignment.CENTER);

		Row row = dataSheet.createRow(rowIndex++);
		row.setHeight((short) 800);
		XSSFCell c01 = (XSSFCell) row.createCell(0);
		c01.setCellValue("Haryana PKG-04 (Jind)");
		c01.setCellStyle(headerCellStyle);

		dataSheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 9));

		row = dataSheet.createRow(rowIndex++);
		if (rowIndex > 0) {
			dataSheet.setDefaultColumnWidth(20);
			dataSheet.setColumnWidth(0, 3000);
			dataSheet.setColumnWidth(1, 4500);
			dataSheet.setColumnWidth(2, 2500);
			dataSheet.setColumnWidth(3, 7500);
			dataSheet.setColumnWidth(4, 4500);
			dataSheet.setColumnWidth(5, 5500);
			dataSheet.setColumnWidth(6, 5500);
			dataSheet.setColumnWidth(7, 5500);
			dataSheet.setColumnWidth(8, 5500);
			dataSheet.setColumnWidth(9, 6500);

		}

		List<String> headerColumns = new LinkedList<String>();

		headerColumns.add("SLNO");
		headerColumns.add("SDB Code");
		headerColumns.add("Code");
		headerColumns.add("Category");
		headerColumns.add("Task / Boq");
		headerColumns.add("Dependencies");
		headerColumns.add("Start Date");
		headerColumns.add("End Date");
		headerColumns.add("Unit");
		headerColumns.add("Qty");

		for (int i = 0; i < headerColumns.size(); i++) {

			XSSFCell cell = (XSSFCell) row.createCell(i);
			cell.setCellValue(headerColumns.get(i));
			cell.setCellStyle(headerCellStyle1);

		}

		for (int k = 0; k < 20; k++) {
			row = dataSheet.createRow(rowIndex++);
			Cell cell0 = row.createCell(0);
			cell0.setCellValue(rowIndex - 2);
			row.createCell(1).setCellValue((rowIndex - 2) + "---1");
			row.createCell(2).setCellValue((rowIndex - 2) + "---2");
			row.createCell(3).setCellValue((rowIndex - 2) + "---3");
			row.createCell(4).setCellValue((rowIndex - 2) + "---4");
			row.createCell(5).setCellValue((rowIndex - 2) + "---5");
			row.createCell(6).setCellValue(new Date().toString());
			row.createCell(7).setCellValue(new Date().toString());
			row.createCell(8).setCellValue((rowIndex - 2) + "---8");
			row.createCell(9).setCellValue((rowIndex - 2) + "---9");
		}
	}

	private static void writeToGantt(XSSFWorkbook workbook, XSSFSheet ganttSheet, XSSFDrawing ganttDrawing) {
		// Set the header for Gantt chart.

		int rowIndex = 0;
		XSSFRow yearRow = ganttSheet.createRow(rowIndex++);
		yearRow.setHeight((short) 700);
		int startYear = 2023;
		int endYear = 2024;
		int yearDif = (endYear - startYear) + 1;
		CellStyle headerCellStyle1 = workbook.createCellStyle();
		Font yearBoldFont = workbook.createFont();
		yearBoldFont.setBold(true);
		yearBoldFont.setFontHeightInPoints((short) 12);

		Font monthBoldFont = workbook.createFont();
		monthBoldFont.setBold(true);
		monthBoldFont.setFontHeightInPoints((short) 8);

		headerCellStyle1.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
		headerCellStyle1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		headerCellStyle1.setFont(yearBoldFont);
		headerCellStyle1.setWrapText(true);
		headerCellStyle1.setAlignment(HorizontalAlignment.CENTER);
		headerCellStyle1.setBorderBottom(BorderStyle.THIN);
		headerCellStyle1.setBorderTop(BorderStyle.THIN);
		headerCellStyle1.setBorderRight(BorderStyle.THIN);
		headerCellStyle1.setBorderLeft(BorderStyle.THIN);

		CellStyle headerCellStyle2 = workbook.createCellStyle();
		headerCellStyle2.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
		headerCellStyle2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		headerCellStyle2.setFont(monthBoldFont);
		headerCellStyle2.setWrapText(true);
		headerCellStyle2.setAlignment(HorizontalAlignment.CENTER);
		headerCellStyle2.setAlignment(HorizontalAlignment.CENTER);
		headerCellStyle2.setBorderBottom(BorderStyle.THIN);
		headerCellStyle2.setBorderTop(BorderStyle.THIN);
		headerCellStyle2.setBorderRight(BorderStyle.THIN);
		headerCellStyle2.setBorderLeft(BorderStyle.THIN);

		int mergeCol = 0;
		List<String> ganttHeaderColumns = new LinkedList<String>();

		ganttHeaderColumns.add("JAN");
		ganttHeaderColumns.add("FEB");
		ganttHeaderColumns.add("MAR");
		ganttHeaderColumns.add("APR");
		ganttHeaderColumns.add("MAY");
		ganttHeaderColumns.add("JUN");
		ganttHeaderColumns.add("JUL");
		ganttHeaderColumns.add("AUG");
		ganttHeaderColumns.add("SEP");
		ganttHeaderColumns.add("OCT");
		ganttHeaderColumns.add("NOV");
		ganttHeaderColumns.add("DEC");
		XSSFRow monthRow = ganttSheet.createRow(rowIndex++);
		monthRow.setHeight((short) 600);

		for (int k = 0; startYear <= endYear; startYear++) {
			XSSFCell yearCell = yearRow.createCell(mergeCol);
			yearCell.setCellValue(startYear);
			yearCell.setCellStyle(headerCellStyle1);
			ganttSheet.addMergedRegion(new CellRangeAddress(0, 0, mergeCol, mergeCol + 11));
			mergeCol += 12;
		}
		mergeCol = 0;
		for (int j = 1; j <= yearDif; j++) {
			for (int i = 0; i < ganttHeaderColumns.size(); i++) {
				XSSFCell cell = (XSSFCell) monthRow.createCell(mergeCol++);
				System.out.println(mergeCol);
				cell.setCellValue(ganttHeaderColumns.get(i));
				cell.setCellStyle(headerCellStyle2);

			}
		}

		// Create the graph.

	}

	public static ResponseEntity<?> setLineInExcel() {
		try {
			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet("Node Connections");

			// Drawing the shapes
			XSSFDrawing drawing = (XSSFDrawing) sheet.createDrawingPatriarch();

			// Create first shape (Parent Node)
			XSSFClientAnchor anchor1 = new XSSFClientAnchor(0, 0, 0, 0, 1, 1, 2, 2);
			XSSFSimpleShape shape1 = drawing.createSimpleShape(anchor1);
			shape1.setText("Parent Node");
			shape1.setFillColor(128, 123, 100);

			// Create second shape (Child Node)
			XSSFClientAnchor anchor2 = new XSSFClientAnchor(0, 0, 0, 0, 4, 4, 5, 5);
			XSSFSimpleShape shape2 = drawing.createSimpleShape(anchor2);
			shape2.setText("Child Node");
			shape2.setFillColor(50, 50, 50);

			// Creating a line connector
			// Note: As of now, Apache POI does not have a direct method for creating
			// connectors between shapes.
			// You will need to manually draw lines to connect the shapes.
//			XSSFClientAnchor lineAnchor = new XSSFClientAnchor(0, 0, 0, 0, 2, 2, 4, 4);
//			XSSFSimpleShape lineShape = drawing.createSimpleShape(lineAnchor);
//			lineShape.setShapeType(ShapeType.BENT_CONNECTOR_2.nativeId);
////			lineShape.setFillColor(200, 200, 200);
//			lineShape.setLineStyleColor(0, 0, 0);
//			lineShape.setLineWidth(2);

			XSSFClientAnchor arrowAnchor = new XSSFClientAnchor(0, 0, 0, 0, 2, 2, 4, 4);
			XSSFConnector arrowShape = drawing.createConnector(arrowAnchor);
			arrowShape.setShapeType(ShapeTypes.RIGHT_ARROW);
			arrowShape.setFillColor(200, 200, 200);
			arrowShape.setLineStyleColor(0, 0, 0);
			arrowShape.setLineWidth(2);

//	        lineShape.setShapeType();
//			XSSFConnector connector = drawing.createConnector(lineAnchor);
//			connector.setShapeType(ShapeType.CURVED_DOWN_ARROW.nativeId);
//			connector.setFillColor(200, 200, 200);
//			connector.setLineStyleColor(0, 0, 0);
//			connector.setLineWidth(2);

			// Write the output to a file
//	        try (FileOutputStream fileOut = new FileOutputStream("NodeConnectors.xlsx")) {
//	            workbook.write(fileOut);
//	        }
//	        catch (Exception e) {
//	        	e.printStackTrace();
//			}
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			workbook.write(baos);
			workbook.close();
			byte[] byteArray = baos.toByteArray();
			System.out.println("Excel file with node connectors created successfully.");
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "attachment; filename=example.xlsx");

			return new ResponseEntity<>(byteArray, headers, HttpStatus.OK);
//			return ResponseEntity.ok(Base64.getEncoder().encodeToString(byteArray));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(e.fillInStackTrace());
		}

	}

	public static void generateExcel() {XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("Gantt Chart");

    // Sample data for Gantt chart
    String[] tasks = {"Task 1", "Task 2", "Task 3"};
    int[] startDates = {1, 3, 6}; // Start dates represented as day numbers
    int[] durations = {3, 4, 2};  // Duration in days

    // Create headers
    Row header = sheet.createRow(0);
    header.createCell(0).setCellValue("Task");
    header.createCell(1).setCellValue("Start Date");
    header.createCell(2).setCellValue("Duration");

    // Fill in the data
    for (int i = 0; i < tasks.length; i++) {
        Row row = sheet.createRow(i + 1);
        row.createCell(0).setCellValue(tasks[i]);
        row.createCell(1).setCellValue(startDates[i]);
        row.createCell(2).setCellValue(durations[i]);
    }

    // Create drawing canvas
    XSSFDrawing drawing = sheet.createDrawingPatriarch();
    XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 4, 1, 15, 20);

    // Create the chart object
    XSSFChart chart = drawing.createChart(anchor);
    chart.setTitleText("Gantt Chart");
    chart.setTitleOverlay(false);

    // Set chart background color
    CTChartSpace chartSpace = chart.getCTChartSpace();
    CTPlotArea plotArea = chartSpace.getChart().getPlotArea();

    // Background color
    CTSolidColorFillProperties solidFill = CTSolidColorFillProperties.Factory.newInstance();
    solidFill.addNewSrgbClr().setVal(new byte[]{(byte) 255, (byte) 255, (byte) 204}); // Light Yellow

    plotArea.addNewSpPr().addNewSolidFill().set(solidFill);

    // Remove default border line
    plotArea.getSpPr().addNewLn().addNewNoFill();

    // Write the output to a file
    try (FileOutputStream fileOut = new FileOutputStream("gantt_chart_with_background_color.xlsx")) {
        workbook.write(fileOut);
    } catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

    try {
		workbook.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}}
	
	
	public static void getDependent() {
		Workbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = (XSSFSheet) workbook.createSheet("Node Connections");

        // Create the drawing patriarch to draw on the sheet
        XSSFDrawing drawing = sheet.createDrawingPatriarch();

        // Create first shape (Parent Node)
        XSSFClientAnchor anchor1 = new XSSFClientAnchor(0, 0, 0, 0, 1, 1, 2, 2);
        XSSFSimpleShape shape1 = drawing.createSimpleShape(anchor1);
        shape1.setShapeType(XSSFSimpleShape.OBJECT_TYPE_RECTANGLE);
        shape1.setString(new XSSFRichTextString("Parent Node"));

        // Create second shape (Child Node)
        XSSFClientAnchor anchor2 = new XSSFClientAnchor(0, 0, 0, 0, 4, 4, 5, 5);
        XSSFSimpleShape shape2 = drawing.createSimpleShape(anchor2);
        shape2.setShapeType(XSSFSimpleShape.OBJECT_TYPE_RECTANGLE);
        shape2.setString(new XSSFRichTextString("Child Node"));

        // Create a connector (line) between two nodes
        XSSFConnector connector = drawing.createConnector(new XSSFClientAnchor());
        connector.setLineWidth(1.0);
        connector.setStart(anchor1.getCol1(), anchor1.getRow1());
        connector.setEnd(anchor2.getCol1(), anchor2.getRow1());

        // Calculate arrow position dynamically based on the end position of the connector
        int arrowCol = anchor2.getCol1(); // Get the ending column of the connector
        int arrowRow = anchor2.getRow1(); // Get the ending row of the connector

        // Create an arrow shape at the end position
        XSSFClientAnchor arrowAnchor = new XSSFClientAnchor(0, 0, 0, 0, arrowCol, arrowRow, arrowCol + 1, arrowRow + 1);
        XSSFSimpleShape arrow = drawing.createSimpleShape(arrowAnchor);
        arrow.setShapeType(XSSFSimpleShape.OBJECT_TYPE_TRIANGLE); // Create an arrowhead shape
        arrow.setFillColor(0, 0, 0);  // Set color of the arrow (black)
        arrow.setLineWidth(0);  // No outline for the arrow shape

        // Save the output to a file
        try (FileOutputStream fileOut = new FileOutputStream("NodeConnectorsWithDynamicArrow.xlsx")) {
            workbook.write(fileOut);
        }

        workbook.close();
	}

}
