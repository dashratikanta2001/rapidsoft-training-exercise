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

@RestController
@RequestMapping("/excel")
public class ExcelControllerV4 {

	@Autowired
	private ExcelOperation excelOperation;
	
	@Autowired
	private ExcelOperation2 excelOperation2;
	private int i=1;

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
		return ResponseEntity.ok(excelOperation2.writeFormulaData(new TestDto(), "/home/rapidsoft/Desktop/excel/test"+i +".xlsx"));
	}

}
