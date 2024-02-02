package com.test.Service;

import java.io.ByteArrayInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.test.Helper.PdfGeneratorV1;

@Service
public class PdfServiceV1 {

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

}
