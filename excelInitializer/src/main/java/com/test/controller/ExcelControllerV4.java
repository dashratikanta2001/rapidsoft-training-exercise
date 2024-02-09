package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.Dto.CountryDto;
import com.test.Dto.StateDto;
import com.test.Dto.TestDto;
import com.test.Dto.UserDto;
import com.test.util.ExcelOperation;
import com.test.util.ExcelOperation2;
import com.test.util.ExcelOperationImpl;
import com.test.util.ExcelTest;
import com.test.util.FormatingCellColor;

@RestController
@RequestMapping("/excel")
public class ExcelControllerV4 {

	@Autowired
	private ExcelOperation excelOperation;

	@Autowired
	private ExcelOperation2 excelOperation2;

	@Autowired
	private FormatingCellColor excelCellColor;

	@Autowired
	private ExcelTest excelTest;

	private int i = 1;

	@GetMapping()
	public ResponseEntity<?> showExcel() throws Exception {
		return ResponseEntity
				.ok(excelOperation.readData(new CountryDto(), "/home/rapidsoft/Desktop/excel/countries.xlsx"));
//		return ResponseEntity.ok(new ExcelOperationImpl<>().readData(new CountryDto()));
	}

	@GetMapping("/1")
	public ResponseEntity<?> showExcel1() throws Exception {
		return ResponseEntity.ok(excelOperation.readData(new StateDto(), "/home/rapidsoft/Desktop/excel/states.xlsx"));
	}

	@GetMapping("/2")
	public ResponseEntity<?> showExcel2() throws Exception {
		return ResponseEntity.ok(excelOperation.readData(new UserDto(), "/home/rapidsoft/Desktop/excel/user.xlsx"));
	}

	@GetMapping("/3")
	public ResponseEntity<?> showExcel3() throws Exception {
		return ResponseEntity.ok(excelOperation.readData(new TestDto(), "/home/rapidsoft/Desktop/excel/test.xlsx"));
	}

	@GetMapping("/write")
	public ResponseEntity<?> writeExcel1() throws Exception {
		return ResponseEntity.ok(
				excelOperation2.writeFormulaData(new TestDto(), "/home/rapidsoft/Desktop/excel/test" + i + ".xlsx"));
	}

	@GetMapping("/write/color")
	public ResponseEntity<?> writeExcelCellColor() throws Exception {
		return ResponseEntity.ok(excelCellColor.formatExcelCellColor());
	}

	@GetMapping("/write/fromHashMap")
	public ResponseEntity<?> writeExcelCellFromHashMap() throws Exception {
		return ResponseEntity.ok(excelCellColor.hashMapToExcel());
	}

	@GetMapping("/write/dateFormat")
	public ResponseEntity<?> writeExcelCellToDateFormats() throws Exception {
		return ResponseEntity.ok(excelCellColor.workingWithDateCells());
	}

	@GetMapping("/read/fromExcel")
	public ResponseEntity<?> readExcelCellToHashMap() throws Exception {
		return ResponseEntity.ok(excelCellColor.readDataToHashMap());
	}

	@GetMapping("/read/fromExcel1")
	public ResponseEntity<?> readExcelCellToHashMap1() throws Exception {
		return ResponseEntity.ok(excelTest.timeSheet());
	}

}
