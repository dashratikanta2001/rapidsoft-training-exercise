package com.test.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnails;

public class ImageUtilV2 {

	public InputStreamResource compressImage(MultipartFile file) throws IOException {
		
//		refer this site for thumbnailator
//		https://github.com/coobird/thumbnailator/wiki/Examples
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
//		BufferedImage watermark = ImageIO.read(new File("/home/rapidsoft/Downloads/result-image.jpeg"));
		Thumbnails.of(file.getInputStream())
						.size(1280, 720)
//						.watermark(Positions.TOP_LEFT, watermark, 0.8f)
						.outputFormat(MediaType.IMAGE_JPEG.getSubtype())
						.toOutputStream(os);
		System.out.println("Media type = "+ MediaType.IMAGE_JPEG.getSubtype());
		
		System.out.println("File size before compress = "+ os.size()/1000.0);
		
		return  new InputStreamResource( new ByteArrayInputStream(os.toByteArray()));
	}

	
	
}
