package com.test.Helper;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

public class PdfGeneratorV1 {

	public static ByteArrayInputStream createPdf() {

		String title = "Welcome to Pdf Service";
		String content = "Content here";

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, out);

			document.open();

//			Adding Document Title
			Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 25, Color.RED);
			Paragraph titlePara = new Paragraph(title, titleFont);
			titlePara.setAlignment(Element.ALIGN_CENTER);
			document.add(titlePara);

//			Adding Document Para.

			FontFactory.getFont(FontFactory.HELVETICA, 14, Color.BLUE);
			Paragraph paragraph = new Paragraph(content);
			paragraph.add(new Chunk("This is aded after creating paragraph."));
			document.add(paragraph);

			document.close();

		} catch (DocumentException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return new ByteArrayInputStream(out.toByteArray());
	}
}
