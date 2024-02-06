package com.test.controller;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.Service.CustomerService;
import com.test.Service.PdfServiceV1;
import com.test.entity.Address;

@RestController
@RequestMapping("/pdf")
public class PdfController {

	@Autowired
	private PdfServiceV1 pdfServiceV1;

	@GetMapping("/createPdf")
	public ResponseEntity<InputStreamResource> createPdf() {
		ByteArrayInputStream pdf = pdfServiceV1.createPdfV2();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment", "testPdf.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(pdf));
	}

	@GetMapping("/generate/pass/{id}")
	public ResponseEntity<?> generatePass(@PathVariable("id") int id) {
		ByteArrayInputStream pass = pdfServiceV1.generatePass(id);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment", "Pass_" + id + ".pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(pass));
	}
	
	@GetMapping("/generate/mail/pass/{id}")
	public ResponseEntity<?> generatePassAndMail(@PathVariable("id") int id){
		ByteArrayInputStream pass = pdfServiceV1.generatePassAndMail(id);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment", "Pass_" + id + ".pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(pass));
	}
	@PostMapping("/generate/mail")
	public ResponseEntity<?> generatePassAndMail1(@RequestBody Address address){
		
		return ResponseEntity.ok().body("Hello Here.");
	}
}
