package com.test.Service;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

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

public class PdfGeneratorV2 {

	public ByteArrayInputStream createPdf() {
//		
		/*
		 * String title = "Welcome to Pdf Service"; // String content = "Content here";
		 * 
		 * ByteArrayOutputStream out = new ByteArrayOutputStream(); // Document document
		 * = new Document(PageSize.A4); // Document document = new Document(PageSize.A4,
		 * 0, 0, 0, 0);
		 * 
		 * // float width = 112.86f * 72f / 25.4f; // float height = 169.33f * 72f /
		 * 25.4f; // float width = PageSize.A4.getWidth(); // float height
		 * =PageSize.A4.getHeight();
		 * 
		 * float width=556; float height=449; Rectangle rect = new Rectangle(width,
		 * height); Document document = new Document(rect, 0, 0, 0, 0); //
		 * PdfWriter.getInstance(doc, YOUR_OUTPUT_STREAM); // doc.open();
		 * 
		 * 
		 * try { PdfWriter.getInstance(document, out);
		 * 
		 * document.open(); // document.setPageSize(Rectangle.)
		 * 
		 * // Adding Document Title // Font titleFont =
		 * FontFactory.getFont(FontFactory.HELVETICA_BOLD, 25, Color.RED); // Paragraph
		 * titlePara = new Paragraph(title, titleFont); //
		 * titlePara.setAlignment(Element.ALIGN_CENTER); // document.add(titlePara);
		 * 
		 * // Adding Document Para.
		 * 
		 * // FontFactory.getFont(FontFactory.HELVETICA, 14, Color.BLUE); // Paragraph
		 * paragraph = new Paragraph(content); // paragraph.add(new
		 * Chunk("\nThis is aded after creating paragraph.\n")); // paragraph.add(new
		 * Chunk(Image.getInstance(R), 0, 0))
		 * 
		 * ResourceLoader resourceLoader = new DefaultResourceLoader();
		 * 
		 * Resource templateImage = resourceLoader.getResource("classpath:/card.png");
		 * // Resource templateImage =
		 * resourceLoader.getResource("classpath:/darkTemplate.jpg");
		 * 
		 * // InputStream templateImageInputStream = templateImage.getInputStream();
		 * 
		 * // Paragraph paragraph = new Paragraph(new
		 * Chunk(Image.getInstance(templateImage.getURL()), 130, 200));
		 * 
		 * // paragraph.add(new Chunk(Image.getInstance(templateImage.getURL()), 130,
		 * 200));
		 * 
		 * // document.add(paragraph);
		 * 
		 * Image image = Image.getInstance(templateImage.getURL()); //
		 * image.setAlignment(Element.ALIGN_CENTER);
		 * 
		 * // image.scalePercent(100); image.scaleAbsoluteHeight(height);
		 * image.scaleAbsoluteWidth(width); // image.scaleToFit(height, width); //
		 * PageSize.A4.get
		 * System.out.println("Width = "+PageSize.A4.getWidth()+"\nHeight = "+PageSize.
		 * A4.getHeight()); // image.scaleToFit(PageSize.A4.getWidth(),
		 * PageSize.A4.getWidth()); // image.setAbsolutePosition(0, 0);
		 * 
		 * 
		 * 
		 * // Chunk chunk = new Chunk(image, 100, 200); // //
		 * chunk.setBackground(Color.RED); //// chunk.append("Ratikanta Dash"); //
		 * Paragraph p = new Paragraph(); // p.add(chunk);
		 * 
		 * 
		 * 
		 * 
		 * // document.setMargins(0, 0, 0, 0); document.add(image); // document.add(p);
		 * 
		 * 
		 * // document.add
		 * 
		 * // document.add()
		 * 
		 * Paragraph p = new Paragraph(title); // document.add(p);
		 * 
		 * document.close();
		 * 
		 * } catch (DocumentException | IOException e) { // TODO: handle exception
		 * e.printStackTrace(); }
		 * 
		 * return new ByteArrayInputStream(out.toByteArray());
		 */

		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		String name = "Ratikanta Dash";
		String idNo = "RSTB036";
		String dob = currentDate.format(formatter);
		String phoneNo = "12345678990";
		String email = "ratikanta@gmail.com";

		Map<String, Object> headerAndvalue = new LinkedHashMap<>();
		headerAndvalue.put("ID", idNo);
		headerAndvalue.put("DOB", dob);
		headerAndvalue.put("PH NO", phoneNo);
		headerAndvalue.put("EMAIL", email);

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
			float nameWidthPoint = nameFont.getCalculatedBaseFont(true).getWidthPoint(name, nameFont.getSize());

			float po = (266 - nameWidthPoint) / 2;
			System.out.println(nameWidthPoint + ", " + po);
			content.showTextAligned(Element.ALIGN_LEFT, name, po, 150, 0);
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

//            content.beginText();

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

		return new ByteArrayInputStream(baos.toByteArray());
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
