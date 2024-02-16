package com.test.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.test.Service.CustomerService;
import com.test.entity.Customer1;
import com.test.util.ImageUtilV1;
import com.test.util.ImageUtilV2;

@RestController
@RequestMapping("/customer")
public class TestController {

	@Autowired
	private CustomerService customerService;
	
//	@GetMapping
//	public ResponseEntity<?> test()
//	{
//		return ResponseEntity.ok().body("Welcome");
//	}
	
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@GetMapping("generate-excel")
	public ResponseEntity<?> generateExcel(HttpServletResponse response)
	{
		
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=Customers_Information.xlsx";
		
		response.setHeader(headerKey, headerValue);
//		
		try {
//			customerService.GenerateExcelV1(response);
			customerService.GenerateExcelV2(response);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in excel creation");
			e.printStackTrace();
		}
		
		return ResponseEntity.ok().body("Hello");
	}
	
	@PostMapping("upload-customers-data")
	public ResponseEntity<?> uploadCustomerData(@ModelAttribute("file") MultipartFile file)
	{
		customerService.saveCustomersToDatabaseV1(file);
		
		return ResponseEntity.ok("Data Uploaded successfully");
	}
	 
	@PostMapping("/upload-json-data")
	public ResponseEntity<?> postMethodName(@RequestBody List<Customer1> customer) {
		//TODO: process POST request
		
		List<Customer1> customer2 =customerService.saveCustomerJsonToDbV1(customer);
		
		return ResponseEntity.ok(customer2);
	}
	
	
	@PostMapping
	public ResponseEntity<?> getAllCustomers() {
		return new ResponseEntity<>(customerService.getCustomers(), HttpStatus.OK);
	}
	
	@GetMapping("/upload")
	public ResponseEntity<?> getMethodName(@RequestParam("file") MultipartFile image) throws IOException {
		
		 InputStream inputStream = image.getInputStream();
		    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		    float imageQuality = 0.7f;

		    // Create the buffered image
		    BufferedImage bufferedImage = ImageIO.read(inputStream);

		    // Get image writers
		    Iterator<ImageWriter> imageWriters = ImageIO.getImageWritersByFormatName("jpg"); // Input your Format Name here

		    if (!imageWriters.hasNext())
		        throw new IllegalStateException("Writers Not Found!!");

		    ImageWriter imageWriter = imageWriters.next();
		    ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(outputStream);
		    imageWriter.setOutput(imageOutputStream);

		    ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam();

		    // Set the compress quality metrics
		    imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		    imageWriteParam.setCompressionQuality(imageQuality);

		    // Compress and insert the image into the byte array.
		    imageWriter.write(null, new IIOImage(bufferedImage, null, null), imageWriteParam);
		    
//		    ImageIO.write(bufferedImage, "jpg", outputStream);
		    

		    byte[] imageBytes = outputStream.toByteArray();
		    
		    System.out.println("image = "+ imageBytes.length);
		    
		    ByteArrayResource resource = new ByteArrayResource(imageBytes);
		    
		    System.out.println(" Resource = \n"+"");

          

		    // close all streams
		    inputStream.close();
		    outputStream.close();
		    imageOutputStream.close();
		    imageWriter.dispose();


//		    return imageBytes;
		    
		    return ResponseEntity.ok()
                    .contentLength(resource.contentLength())
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(imageBytes);
		    
	}
	
	
//	@PostMapping("/upload")
//    public ResponseEntity<?> uploadAndCompressImage(@RequestParam("file") MultipartFile file) {
//        if (file.isEmpty()) {
//            return new ResponseEntity<>("Please select a file to upload.", HttpStatus.BAD_REQUEST);
//        }
//
//        try {
//            // Read the uploaded image
//            BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
//
//            // Calculate the target size (200KB)
//            long targetSizeBytes = 200 * 1024;
//
//            // Initialize the compressedImage variable
//            BufferedImage compressedImage = originalImage;
//
//            // Compress the image iteratively until the size is below the target
//            float compressionQuality = 1f; // Initial quality
//            ByteArrayOutputStream outputStream ;
//            int i=0;
//            do {
//                // Create a byte array output stream to write the compressed image
//            	outputStream = new ByteArrayOutputStream();
//                // Get the JPEG ImageWriter
//                ImageWriter imageWriter = ImageIO.getImageWritersByFormatName("jpg").next();
//
//                // Specify the compression quality
//                imageWriter.setOutput(ImageIO.createImageOutputStream(outputStream));
//                ImageWriteParam param = imageWriter.getDefaultWriteParam();
//                param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
//                param.setCompressionQuality(compressionQuality);
//
//                // Write the compressed image
//                imageWriter.write(null, new IIOImage(compressedImage, null, null), param);
//
//                // Close the ImageWriter
//                imageWriter.dispose();
//
//                // Get the compressed image bytes
//                byte[] compressedImageBytes = outputStream.toByteArray();
//
//                // Check the size of the compressed image
//                if (compressedImageBytes.length <= targetSizeBytes) {
//                    // If the size is within the target, break the loop
//                    compressedImage = ImageIO.read(new ByteArrayInputStream(compressedImageBytes));
//                    System.out.println("Break");
//                    break;
//                }
//                compressedImage = ImageIO.read(new ByteArrayInputStream(compressedImageBytes));
//
//                // Reduce the compression quality for the next iteration
//                compressionQuality -= 0.03f;
//                System.out.println(i++ +"  -> "+ compressedImageBytes.length+" -> "+compressionQuality);
//            } while (compressionQuality>0);
//
//            // Return the compressed image as a Resource
//            ByteArrayOutputStream outputStream2 = new ByteArrayOutputStream();
//            ImageIO.write(compressedImage, "jpg", outputStream2);
//            byte[] compressedImageBytes = outputStream2.toByteArray();
//            ByteArrayResource resource = new ByteArrayResource(compressedImageBytes);
//
//            return ResponseEntity.ok()
//                    .contentLength(compressedImageBytes.length)
//                    .contentType(org.springframework.http.MediaType.IMAGE_JPEG)
//                    .body(resource);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return new ResponseEntity<>("Failed to upload and compress the image.", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
	
	
	@PostMapping("/v1/upload")
	public ResponseEntity<?> uploadAndCompressImageV1(@RequestParam("file") MultipartFile file) throws IOException {
//		file.getInputStream()	
		byte[] bytes = file.getBytes();
		byte[] compressImage = ImageUtilV1.compressImage(bytes, 1);
		
		
		ByteArrayResource resource = new ByteArrayResource(compressImage);
		
		            return ResponseEntity.ok()
//		                    .contentLength(compressImage.length)
		                    .contentType(MediaType.IMAGE_PNG)
		                    .body(resource);
	}
	
	
	@PostMapping("/v2/upload")
	public ResponseEntity<?> imageSizereducerByThumbnaiator(@RequestParam("file") MultipartFile file) throws IOException {

		if (file.isEmpty() || file == null) {
			return ResponseEntity.badRequest().body("No image found");
		}
		
		System.out.println("File size before compress = "+ file.getSize()/1000.0);
		
//		InputStreamResource resource = new ImageUtilV2().compressImage(file);
		InputStreamResource resource = new ImageUtilV2().compressImage(file);
		
		

//		System.out.println("File size after compress = "+resource.contentLength()/1000.0);
//		return ResponseEntity.ok()
//				.body(resource);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
//		headers.setContentLength(imageBytes.length);

//		return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
		return new ResponseEntity<>(resource, headers, HttpStatus.OK);

	}
	
	
	
}
