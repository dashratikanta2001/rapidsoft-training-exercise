package com.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.test.controller.TestController;

@SpringBootApplication
public class ExcelInitializerApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);

	public static void main(String[] args) {
		SpringApplication.run(ExcelInitializerApplication.class, args);
		
//		while(true) {
//			System.out.println("helo");
//			logger.info("Hello");
//		}
//		int i=0;
//		while(i++ !=9999)
//		{
//			logger.info("HELLO");
//			
//		}
	}

}
