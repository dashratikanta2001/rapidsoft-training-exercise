package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.Dto.CountryDto;
import com.test.util.ExcelOperation;


@RestController
@RequestMapping("/excel")
public class ExcelControllerV4 {
	
	@Autowired
	private ExcelOperation excelOperation;

	@GetMapping()
	public ResponseEntity<?> showExcel() throws Exception {
		return ResponseEntity.ok(excelOperation.readData(new CountryDto()));
	}
	
	
	
}
