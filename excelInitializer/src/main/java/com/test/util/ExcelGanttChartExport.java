package com.test.util;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.ShapeTypes;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.util.Units;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFSimpleShape;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.test.Dto.GrantChartResponse;

public class ExcelGanttChartExport {

	public static void export() {
		// Sample data (replace with your data source)
		List<GrantChartResponse> grantChartResponse = getGrantChartResponse();

		// Create a new workbook
		XSSFWorkbook workbook = new XSSFWorkbook();

		// Create a sheet for the Gantt chart data
		XSSFSheet ganttSheet = workbook.createSheet("Gantt Chart");

		// Populate the Gantt chart sheet
		createGanttChartSheet(ganttSheet, grantChartResponse);

		// Write the output to a file
		try (FileOutputStream outputStream = new FileOutputStream(
				"/home/rapidsoft/Desktop/TTEST/template/GanttChart.xlsx")) {
			workbook.write(outputStream);
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void createGanttChartSheet(XSSFSheet sheet, List<GrantChartResponse> data) {
		int rowNum = 0;

		// Headers
		Row headerRow = sheet.createRow(rowNum++);
		String[] headers = { "Task Name", "Start Date", "End Date", "Duration", "Start Offset" };
		for (int i = 0; i < headers.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(headers[i]);
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate projectStartDate = LocalDate.parse(data.get(0).getStart(), formatter);

		for (GrantChartResponse task : data) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(task.getName());
			row.createCell(1).setCellValue(task.getStart());
			row.createCell(2).setCellValue(task.getEnd());

			// Calculate duration in days
			LocalDate startDate = LocalDate.parse(task.getStart(), formatter);
			LocalDate endDate = LocalDate.parse(task.getEnd(), formatter);
			long duration = ChronoUnit.DAYS.between(startDate, endDate) + 1; // +1 to include both start and end dates

			row.createCell(3).setCellValue(duration);

			// Calculate offset from project start date in days
			long offset = ChronoUnit.DAYS.between(projectStartDate, startDate);
			row.createCell(4).setCellValue(offset);
		}

		// Adjust column widths
		for (int i = 0; i < headers.length; i++) {
			sheet.autoSizeColumn(i);
		}
	}

	private static String formatDateTime(String dateTime, DateTimeFormatter formatter) {

//    	DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate projectStartDate = LocalDate.parse(data.get(0).getStart(), formatter);
		LocalDateTime ldt = LocalDateTime.parse(dateTime);
		return ldt.format(formatter);
	}

	// Mock data (replace with your actual data source)
	private static List<GrantChartResponse> getGrantChartResponse() {
		List<GrantChartResponse> chartResponses = new ArrayList<>();
		GrantChartResponse response1 = new GrantChartResponse();
		response1.setId("1171");
		response1.setCode("1.57.1");
		response1.setName("CMPCT.a");
		response1.setType("task");
		response1.setStart("2024-07-31");
		response1.setEnd("2024-08-09");
		response1.setProgress(0);
		response1.setDisplayOrder(2);
		response1.setDependencies(new ArrayList<>()); // Empty list for dependencies
		response1.setProject("DEMO SITE");
//    	response1.setStyles(Map.of("progressColor", "#6c757d", "progressSelectedColor", "#ff9e0d"));

		chartResponses.add(response1);

		GrantChartResponse response2 = new GrantChartResponse();
		response2.setId("1172");
		response2.setCode("1.57.2");
		response2.setName("CMPCT.b");
		response2.setType("task");
		response2.setStart("2024-08-07");
		response2.setEnd("2024-08-12");
		response2.setProgress(0);
		response2.setDisplayOrder(3);
		response2.setDependencies(Arrays.asList("1171"));
		response2.setProject("DEMO SITE");
//    	response2.setStyles(Map.of("progressColor", "#6c757d", "progressSelectedColor", "#ff9e0d"));

		chartResponses.add(response2);

		GrantChartResponse response3 = new GrantChartResponse();
		response3.setId("1173");
		response3.setCode("1.57.3");
		response3.setName("CMPCT.c");
		response3.setType("task");
		response3.setStart("2024-08-10");
		response3.setEnd("2024-08-15");
		response3.setProgress(0);
		response3.setDisplayOrder(4);
		response3.setDependencies(Arrays.asList("1172"));
		response3.setProject("DEMO SITE");
//    	response3.setStyles(Map.of("progressColor", "#6c757d", "progressSelectedColor", "#ff9e0d"));

		chartResponses.add(response3);

		GrantChartResponse response4 = new GrantChartResponse();
		response4.setId("1174");
		response4.setCode("1.57.4");
		response4.setName("CMPCT.d");
		response4.setType("task");
		response4.setStart("2024-08-13");
		response4.setEnd("2024-08-16");
		response4.setProgress(0);
		response4.setDisplayOrder(5);
		response4.setDependencies(Arrays.asList("1173"));
		response4.setProject("DEMO SITE");
//    	response4.setStyles(Map.of("progressColor", "#6c757d", "progressSelectedColor", "#ff9e0d"));

		chartResponses.add(response4);

		GrantChartResponse response5 = new GrantChartResponse();
		response5.setId("1175");
		response5.setCode("1.57.5");
		response5.setName("CMPCT.e");
		response5.setType("task");
		response5.setStart("2024-08-15");
		response5.setEnd("2024-08-20");
		response5.setProgress(0);
		response5.setDisplayOrder(6);
		response5.setDependencies(Arrays.asList("1174"));
		response5.setProject("DEMO SITE");
//    	response5.setStyles(Map.of("progressColor", "#6c757d", "progressSelectedColor", "#ff9e0d"));

		chartResponses.add(response5);

		return chartResponses;
	}

//    ------------------------------------------------------------------------------------------------------------------------------------
	private static final int ROW_HEIGHT_CM = 36;

	public static ResponseEntity<?> createGanttChart() {
		try (XSSFWorkbook workbook = new XSSFWorkbook()) {
//            XSSFWorkbook workbook = new XSSFWorkbook();
			
			

//            CompletableFuture<Void> sheet1 = CompletableFuture.runAsync(() -> writeToTable(dataSheet));
//            CompletableFuture<Void> sheet2 = CompletableFuture.runAsync(() -> writeToGantt(ganttSheet));
//
//            CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(sheet1, sheet2);
//            combinedFuture.get();  // Wait for both tasks to complete

			// Write the output to a file
//            try (FileOutputStream fileOut = new FileOutputStream("/home/rapidsoft/Desktop/TTEST/template/GanttChart5.xlsx")) {
//                synchronized (workbook) {
//                    workbook.write(fileOut);
//                }
//            }
//
			XSSFSheet dataSheet = workbook.createSheet("Plan Sheet");
			XSSFSheet ganttSheet = workbook.createSheet("Gantt Chart");
//            workbook.close();
//			CountDownLatch latch = new CountDownLatch(2);
//			Thread thread1 = new Thread(() -> {
//				try {
					writeToTable(dataSheet);
//				}
//				catch (Exception e) {
//					e.printStackTrace();
//				}finally {
//					latch.countDown();
//				}
//			});

//			Thread thread2 = new Thread(() -> {
//				try {
//					writeToGantt(ganttSheet);
//				}
//				catch (Exception e) {
//					e.printStackTrace();
//				}
//				finally {
//					latch.countDown();
//				}
//			});

			System.out.println(new Date().getTime());
//			thread1.start();
//			thread2.start();
//			latch.await();
			System.out.println(new Date().getTime());
			
//			 ExecutorService executor = Executors.newFixedThreadPool(2);
//			 
//			 Future<?> future1 = executor.submit(() -> {
//		            // Populate sheet1
//				 writeToTable(dataSheet);
//		        });
//			 Future<?> future2 = executor.submit(() -> {
//				 // Populate sheet1
//				 writeToGantt(ganttSheet);
//			 });
//			 
//			 future1.get();
//		        future2.get();
//
//		        executor.shutdown();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			synchronized workbook) {
				workbook.write(baos);
//			}
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

	private static void writeToTable(XSSFSheet dataSheet) {
		// Add padding at the top
//		XSSFSheet dataSheet = workbook.createSheet("Plan Sheet");
		for (int i = 0; i < 2; i++) {
			dataSheet.createRow(i).setHeightInPoints(ROW_HEIGHT_CM);
		}

		// Create header row
		Row headerRow = dataSheet.createRow(0);
		headerRow.setHeightInPoints(ROW_HEIGHT_CM);
		for (int i = 0; i < 14; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(i + 1);
		}

		// Fill in task rows with data (as an example)
		synchronized (headerRow) {
			createTaskRow(dataSheet, "DEMO SITE", 1, 2, 14, 255, 165, 0);
			createTaskRow(dataSheet, "PLAN 101", 2, 2, 6, 128, 128, 128);
			createTaskRow(dataSheet, "PLAN 101", 2, 2, 4, 0, 0, 255);
			createTaskRow(dataSheet, "PLAN 201", 3, 6, 8, 0, 0, 255);
			createTaskRow(dataSheet, "PLAN 301", 4, 8, 11, 128, 128, 128);
			createTaskRow(dataSheet, "PLAN 401", 5, 11, 12, 128, 128, 128);
			createTaskRow(dataSheet, "PLAN 501", 6, 12, 14, 128, 128, 128);
		}

		// Add padding at the bottom
		for (int i = 9; i < 11; i++) {
			dataSheet.createRow(i).setHeightInPoints(ROW_HEIGHT_CM);
		}
//		System.out.println("table");
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void writeToGantt(XSSFSheet dataSheet) {
		// Add padding at the top
//		XSSFSheet dataSheet = workbook.createSheet("Gantt Chart");
//		XSSFSheet dataSheet = workbook.createSheet("Gantt Chart");
		for (int i = 0; i < 2; i++) {
			dataSheet.createRow(i).setHeightInPoints(ROW_HEIGHT_CM);
		}

		// Create header row
		Row headerRow = dataSheet.createRow(0);
		headerRow.setHeightInPoints(ROW_HEIGHT_CM);
		for (int i = 0; i < 14; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(i + 1);
		}

		// Fill in task rows with data (as an example)
		synchronized (headerRow) {
			createTaskRow(dataSheet, "DEMO SITE", 3, 2, 14, 255, 165, 0);
			createTaskRow(dataSheet, "PLAN 1", 4, 2, 6, 128, 128, 128);
			createTaskRow(dataSheet, "PLAN 1", 4, 2, 4, 0, 0, 255);
			createTaskRow(dataSheet, "PLAN 2", 5, 6, 8, 0, 0, 255);
			createTaskRow(dataSheet, "PLAN 3", 6, 8, 11, 128, 128, 128);
			createTaskRow(dataSheet, "PLAN 4", 7, 11, 12, 128, 128, 128);
			createTaskRow(dataSheet, "PLAN 5", 8, 12, 14, 128, 128, 128);

		}

		// Add padding at the bottom
		for (int i = 9; i < 11; i++) {
			dataSheet.createRow(i).setHeightInPoints(ROW_HEIGHT_CM);
		}
//		System.out.println("gantt");
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void createTaskRow(XSSFSheet sheet, String taskName, int rowIndex, int start, int end, int r, int g,
			int b) {
		Row row = sheet.createRow(rowIndex);System.out.println(sheet.getSheetName());
		row.setHeightInPoints(ROW_HEIGHT_CM);

		Cell cell = row.createCell(0);
		cell.setCellValue(taskName);

		// Create a drawing canvas for the sheet
		XSSFDrawing drawing = sheet.createDrawingPatriarch();

		// Calculate positions for the rectangle
		int col1 = start - 1;
		int col2 = end - 1;
		int row1 = rowIndex;
		int row2 = rowIndex;

		// Anchor the rectangle to the desired position
		XSSFClientAnchor anchor = new XSSFClientAnchor(10, 110, Units.EMU_PER_PIXEL, Units.toEMU(20), col1, row1, col2,
				row2);

		// Create the rectangle shape
		XSSFSimpleShape shape = drawing.createSimpleShape(anchor);
		shape.setVerticalAlignment(VerticalAlignment.BOTTOM);
		shape.setShapeType(ShapeTypes.RECT);
		shape.setFillColor(r, g, b);
		shape.setText(taskName);
//		shape.setLineStyleColor(0, 0, 0); // Black border
//		shape.setLineWidth(1); // Border width

//		System.out.println("Hello : " + sheet.getSheetName() + rowIndex);
	}

//	private static final int ROW_HEIGHT_CM = 36;
//
//	public static ResponseEntity<?> createGanttChart() {
//		try (XSSFWorkbook workbook = new XSSFWorkbook()) {
//			XSSFSheet dataSheet = workbook.createSheet("Plan Sheet");
//			XSSFSheet ganttSheet = workbook.createSheet("Gantt Chart");
//			XSSFDrawing dataDrawing = dataSheet.createDrawingPatriarch();
//			XSSFDrawing ganttDrawing = ganttSheet.createDrawingPatriarch();
//
//			// Write to sheets sequentially to avoid concurrent modification issues
//			CompletableFuture<Void> sheet1 = CompletableFuture.runAsync(() -> writeToTable(dataSheet,dataDrawing));
//        CompletableFuture<Void> sheet2 = CompletableFuture.runAsync(() -> writeToGantt(ganttSheet,ganttDrawing));
//
//        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(sheet1, sheet2);
//        combinedFuture.get();  // Wait for both tasks to complete
////			CountDownLatch latch = new CountDownLatch(2);
////			Thread thread1 = new Thread(() -> {
////				try {
////					writeToTable(dataSheet,dataDrawing);
////				} finally {
////					latch.countDown();
////				}
////			});
////			Thread thread2 = new Thread(() -> {
////				try {
////					writeToGantt(ganttSheet,ganttDrawing);
////				} finally {
////					latch.countDown();
////				}
////			});
////			thread1.start();
////			thread2.start();
////			latch.await();
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			synchronized (workbook) {
//				workbook.write(baos);
//			}
//			workbook.close();
//
//			byte[] excelBytes = baos.toByteArray();
//
//			HttpHeaders headers = new HttpHeaders();
//			headers.add("Content-Disposition", "attachment; filename=example.xlsx");
//
//			return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return ResponseEntity.badRequest().body(e.getMessage());
//		}
//	}
//
//	private static void writeToTable(XSSFSheet dataSheet,XSSFDrawing drawing) {
//		for (int i = 0; i < 2; i++) {
//			dataSheet.createRow(i).setHeightInPoints(ROW_HEIGHT_CM);
//		}
//
//		Row headerRow = dataSheet.createRow(0);
//		headerRow.setHeightInPoints(ROW_HEIGHT_CM);
//		for (int i = 0; i < 14; i++) {
//			Cell cell = headerRow.createCell(i);
//			cell.setCellValue(i + 1);
//		}
//
////		XSSFDrawing drawing = dataSheet.createDrawingPatriarch();
//		createTaskRow(dataSheet,drawing, "DEMO SITE", 1, 2, 14, 255, 165, 0);
//		createTaskRow(dataSheet,drawing, "PLAN 101", 2, 2, 6, 128, 128, 128);
//		createTaskRow(dataSheet,drawing, "PLAN 101", 2, 2, 4, 0, 0, 255);
//		createTaskRow(dataSheet,drawing, "PLAN 201", 3, 6, 8, 0, 0, 255);
//		createTaskRow(dataSheet,drawing, "PLAN 301", 4, 8, 11, 128, 128, 128);
//		createTaskRow(dataSheet,drawing, "PLAN 401", 5, 11, 12, 128, 128, 128);
//		createTaskRow(dataSheet,drawing, "PLAN 501", 6, 12, 14, 128, 128, 128);
//
//		for (int i = 9; i < 11; i++) {
//			dataSheet.createRow(i).setHeightInPoints(ROW_HEIGHT_CM);
//		}
//	}
//
//	private static void writeToGantt(XSSFSheet ganttSheet,XSSFDrawing drawing) {
//		for (int i = 0; i < 2; i++) {
//			ganttSheet.createRow(i).setHeightInPoints(ROW_HEIGHT_CM);
//		}
//
//		Row headerRow = ganttSheet.createRow(0);
//		headerRow.setHeightInPoints(ROW_HEIGHT_CM);
//		for (int i = 0; i < 14; i++) {
//			Cell cell = headerRow.createCell(i);
//			cell.setCellValue(i + 1);
//		}
////		XSSFDrawing drawing = ganttSheet.createDrawingPatriarch();
//
//		createTaskRow(ganttSheet,drawing, "GANTT CHART", 3, 2, 14, 255, 165, 0);
//		createTaskRow(ganttSheet,drawing, "PLAN 1", 4, 2, 6, 128, 128, 128);
//		createTaskRow(ganttSheet,drawing, "PLAN 1", 4, 2, 4, 0, 0, 255);
//		createTaskRow(ganttSheet,drawing, "PLAN 2", 5, 6, 8, 0, 0, 255);
//		createTaskRow(ganttSheet,drawing, "PLAN 3", 6, 8, 11, 128, 128, 128);
//		createTaskRow(ganttSheet,drawing, "PLAN 4", 7, 11, 12, 128, 128, 128);
//		createTaskRow(ganttSheet,drawing, "PLAN 5", 8, 12, 14, 128, 128, 128);
//
//		for (int i = 9; i < 11; i++) {
//			ganttSheet.createRow(i).setHeightInPoints(ROW_HEIGHT_CM);
//		}
//	}
//
//	private static void createTaskRow(XSSFSheet sheet, XSSFDrawing drawing, String taskName, int rowIndex, int start, int end, int r, int g, int b) {
//	    Row row = sheet.createRow(rowIndex);
//	    row.setHeightInPoints(ROW_HEIGHT_CM);
//
//	    Cell cell = row.createCell(0);
//	    cell.setCellValue(taskName);
//
//	    // Get cell dimensions
//	    int col1 = start - 1;
//	    int col2 = end - 1;
//	    int row1 = rowIndex;
//
//	    // Get the cell width and height
//	    double cellWidth = sheet.getColumnWidthInPixels(start) * Units.EMU_PER_PIXEL;
//	    double cellHeight = row.getHeightInPoints() * Units.toEMU(1);
//
//	    // Calculate shape dimensions as a percentage of cell dimensions
//	    double shapeWidthPercentage = 0.20; // 20% of the cell's width
//	    double shapeHeight = 20; // Fixed height in points or use any desired value
//
//	    int shapeWidth = (int) (cellWidth * shapeWidthPercentage);
//	    int shapeHeightInEMU = Units.toEMU(shapeHeight);
//
//	    // Set the anchor's coordinates
//	    int anchorCol1 = col1;
//	    int anchorRow1 = row1;
//	    int anchorCol2 = col1 + 1; // End column is the next column to anchor
//	    int anchorRow2 = row1 + 1; // End row is the next row to anchor
//
//	    // Create anchor with the desired shape size and position
//	    XSSFClientAnchor anchor = new XSSFClientAnchor(
//	        0, 0, 
//	        shapeWidth, shapeHeightInEMU, 
//	        anchorCol1, anchorRow1, 
//	        anchorCol1 + 1, anchorRow1 + 1
//	    );
//
//	    // Create the rectangle shape
//	    XSSFSimpleShape shape = drawing.createSimpleShape(anchor);
//	    shape.setVerticalAlignment(VerticalAlignment.BOTTOM);
//	    shape.setShapeType(ShapeTypes.RECT);
//	    shape.setFillColor(r, g, b);
//	    shape.setText(taskName);
//	}



}
