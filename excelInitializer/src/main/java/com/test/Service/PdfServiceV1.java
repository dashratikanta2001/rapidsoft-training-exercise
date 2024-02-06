package com.test.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.Dao.CustomerDao;
import com.test.Helper.PdfGeneratorV1;
import com.test.entity.Customer;

@Service
public class PdfServiceV1 {

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private EmailServiceV3 emailServiceV3;

	private Logger logger = LoggerFactory.getLogger(PdfServiceV1.class);

	public ByteArrayInputStream createPdf() {
		// TODO Auto-generated method stub
		logger.info("Create Pdf Started: V1");
		return PdfGeneratorV1.createPdf();
	}

	public ByteArrayInputStream createPdfV2() {
		// TODO Auto-generated method stub
		logger.info("Create Pdf Started: V2");
		PdfGeneratorV2 pdfGeneratorV2 = new PdfGeneratorV2();
		return pdfGeneratorV2.createPdf();
	}

	public ByteArrayInputStream generatePass(int id) {
		Customer customer = customerDao.findById(id).orElseThrow(() -> new RuntimeException("Invalid user id: " + id));

		PdfGeneratorV3 pdfGeneratorV3 = new PdfGeneratorV3();

		return new ByteArrayInputStream(pdfGeneratorV3.createPdfV2(customer).toByteArray());
	}

	public ByteArrayInputStream generatePassAndMail(int id) {
		// TODO Auto-generated method stub

		Customer customer = customerDao.findById(id).orElseThrow(() -> new RuntimeException("Invalid user id: " + id));

		PdfGeneratorV3 pdfGeneratorV3 = new PdfGeneratorV3();

		ByteArrayOutputStream pass = pdfGeneratorV3.createPdfV2(customer);
		
		
		emailServiceV3.sendEmailWithPass(pass, customer);

		return new ByteArrayInputStream(pass.toByteArray());
	}
	
	
//	public ByteArrayInputStream generatePassAndMail(int id) {
//		// TODO Auto-generated method stub
//		
//		Customer customer = customerDao.findById(id).orElseThrow(() -> new RuntimeException("Invalid user id: " + id));
//		
//		PdfGeneratorV3 pdfGeneratorV3 = new PdfGeneratorV3();
//		ByteArrayInputStream pass = pdfGeneratorV3.createPdfV2(customer);
//		ByteArrayOutputStream b = new ByteArrayOutputStream();
////		b.
//		
//		
//		return null;
//	}

}
