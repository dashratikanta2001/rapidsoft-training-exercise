package com.test.Service;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfShading;
import com.lowagie.text.pdf.PdfShadingPattern;
import com.lowagie.text.pdf.PdfStamper;
import com.test.entity.Customer;

@Service
public class PdfGeneratorV3 {

	public ByteArrayOutputStream createPdfV2(Customer customer) {
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

//		String name = "Ratikanta Dash";
//		String idNo = "RSTB036";
		String dob = currentDate.format(formatter);
		String phoneNo = "8989898989";
//		String email = "ratikanta@gmail.com";

		Map<String, Object> headerAndvalue = new LinkedHashMap<>();
		headerAndvalue.put("ID", customer.getId());
		headerAndvalue.put("DOB", dob);
		headerAndvalue.put("PH NO", phoneNo);
		headerAndvalue.put("EMAIL", customer.getEmail());

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try (PdfReader reader = new PdfReader(getClass().getClassLoader().getResourceAsStream("passTemplate.pdf"));
				PdfStamper stamper = new PdfStamper(reader, baos)) {

			PdfContentByte content = stamper.getOverContent(1);

			Font nameFont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
			nameFont.setColor(Color.BLACK);
			nameFont.setSize(20);
			content.beginText();

			content.setFontAndSize(nameFont.getBaseFont(), nameFont.getSize());
			content.setColorFill(nameFont.getColor());
			float nameWidthPoint = nameFont.getCalculatedBaseFont(true).getWidthPoint(customer.getName(), nameFont.getSize());

			float po = (266 - nameWidthPoint) / 2;
			System.out.println(nameWidthPoint + ", " + po);
			content.showTextAligned(Element.ALIGN_LEFT, customer.getName(), po, 150, 0);
			// Page width = 206,266. Make the allignment according to this.
			float lineWidth = 171f;
			float lineHeight = 10f;
			content.moveTo(35, 150 - lineHeight);
			content.setColorStroke(Color.RED.brighter());
			content.setLineWidth(2.5f);
			content.lineTo(60 + lineWidth, 150 - lineHeight);
			content.stroke();
			content.endText();
			Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.BLACK);
			Font valueFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Color.BLACK);

			// Replace the following coordinates and text with your actual template details
			float x = 30;
			float y = 100;

//        content.beginText();

			// ID Header
			int i = 4;

			for (Entry<String, Object> entry : headerAndvalue.entrySet()) {

				writeHeaderAndValue(content, entry.getKey(), entry.getValue(), x, y, headerFont, valueFont);
				y -= 20;
			}

			Image image = null;
			try {
				image = Image.getInstance(getClass().getClassLoader().getResource("images (1).jpeg"));
			} catch (Exception e) {
				e.printStackTrace();
			}

			addCircularImage(content, image, 71, 255, 121, 80);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		return baos;
	}

	private void addCircularImage(PdfContentByte content, Image image, float x, float y, float width1, float height1) {
		// TODO Auto-generated method stub
		float maxSize = 110;
		float scaleFactor = Math.min(maxSize / image.getWidth(), maxSize / image.getHeight());
		float width = image.getWidth() * scaleFactor;
		float height = image.getHeight() * scaleFactor;

		image.setAbsolutePosition(x, y);
		image.scaleAbsolute(121.5f, 121.5f);

		PdfShading shading = PdfShading.simpleAxial(content.getPdfWriter(), x, y + height / 2, x + height,
				y + height / 2, new Color(0, 0, 0, 0), new Color(0, 0, 0, 1));

		PdfShadingPattern pattern = new PdfShadingPattern(shading);
		content.setShadingFill(pattern);
		content.circle(131.5f, 316.25f, 60);
		content.clip();
		content.newPath();
		content.addImage(image);

	}

	private float writeHeaderAndValue(PdfContentByte content, String header, Object value, float x, float y,
			Font headerFont, Font valueFont) throws DocumentException {

		content.beginText();

		// Write header
		content.setFontAndSize(headerFont.getBaseFont(), headerFont.getSize());
		content.showTextAligned(Element.ALIGN_LEFT, header, x, y, 0);

		// Write value
		content.setFontAndSize(valueFont.getBaseFont(), valueFont.getSize());
		content.showTextAligned(Element.ALIGN_LEFT, " :   " + value, x + 40, y, 0);

		content.endText();

		return y;
	}


}
