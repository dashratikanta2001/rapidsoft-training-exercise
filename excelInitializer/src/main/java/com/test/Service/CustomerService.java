package com.test.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.test.Dao.CustomerDao;
import com.test.Dao.CustomerDao1;
import com.test.Dao.impl.CustomerDaoImpl;
import com.test.Dto.PaginationRequestDto;
import com.test.Dto.PaginationResponseDto;
import com.test.Helper.ExcelGenerateV1;
import com.test.Helper.ExcelGenerateV2;
import com.test.Helper.ExcelGenerateV3;
import com.test.Helper.ExcelUploadHelperV1;
import com.test.controller.PaginationController;
import com.test.entity.Customer;
import com.test.entity.Customer1;
import com.test.entity.Student;
import com.test.response.CustomResponse;

//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.test.Dao.CustomerDao;
//import com.test.Dao.CustomerDao1;
//import com.test.Dao.impl.CustomerDaoImpl;
//import com.test.Dto.PaginationRequestDto;
//import com.test.Dto.PaginationResponseDto;
//import com.test.Helper.ExcelGenerateV1;
//import com.test.Helper.ExcelGenerateV2;
//import com.test.Helper.ExcelGenerateV3;
//import com.test.Helper.ExcelUploadHelperV1;
//import com.test.entity.Customer;
//import com.test.entity.Customer1;
//import com.test.entity.Student;
//import com.test.response.CustomResponse;
//
//import jakarta.servlet.http.HttpServletResponse;

@Service
public class CustomerService {

	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private CustomerDaoImpl customerDaoImpl;
	
	@Autowired
	private CustomerDao1 customerDao1;
	
	private final Logger logger = LoggerFactory.getLogger(PaginationController.class);

	public List<Customer> GenerateExcelV1(HttpServletResponse response) throws IOException {
		List<Customer> customersList = customerDao.findAll();
		if (customersList.isEmpty()) {
			customersList.add(new Customer());
			ExcelGenerateV1 excelGenerate = new ExcelGenerateV1(customersList);

		}
		ExcelGenerateV1 excelGenerate = new ExcelGenerateV1(customersList);

		Student student = new Student();
		ArrayList<Student> studentList = new ArrayList<Student>();
		studentList.add(student);

//		ExcelGenerateV2 excelGenerateV2 = new ExcelGenerateV2(studentList);
//		excelGenerateV2.exportDataToExcel(response);

//		System.out.println("-----------------------------");
		excelGenerate.exportDataToExcel(response);
		return customersList;
	}

	public List<Customer> GenerateExcelV2(HttpServletResponse response) throws IOException {
		List<Customer> customersList = customerDao.findAll();
		if (customersList.isEmpty()) {
			customersList.add(new Customer());
			ExcelGenerateV2 excelGenerate = new ExcelGenerateV2(customersList);

		}
		ExcelGenerateV2 excelGenerate = new ExcelGenerateV2(customersList);

		Student student = new Student();
		ArrayList<Student> studentList = new ArrayList<Student>();
		studentList.add(student);

//		ExcelGenerateV2 excelGenerateV2 = new ExcelGenerateV2(studentList);
//		excelGenerateV2.exportDataToExcel(response);

//		System.out.println("-----------------------------");
		excelGenerate.exportDataToExcel(response);
		return customersList;
	}

	public List<Customer> GenerateExcelV3(HttpServletResponse response) throws IOException {
		List<Customer> customersList = customerDao.findAll();
		if (customersList.isEmpty()) {
			customersList.add(new Customer());
			ExcelGenerateV3 excelGenerate = new ExcelGenerateV3(customersList);
			System.out.println("is empty");

		} else {
			System.out.println("is not empty");
			ExcelGenerateV3 excelGenerate = new ExcelGenerateV3(customersList);

			Student student = new Student();
			ArrayList<Student> studentList = new ArrayList<Student>();
			studentList.add(student);

//		ExcelGenerateV2 excelGenerateV2 = new ExcelGenerateV2(studentList);
//		excelGenerateV2.exportDataToExcel(response);

//		System.out.println("-----------------------------");
			excelGenerate.exportDataToExcel(response);
		}
		return customersList;
	}

	public void saveCustomersToDatabaseV1(MultipartFile file) {
		if (ExcelUploadHelperV1.isValidExcelFile(file)) {
			try {
				List<Customer> customers = ExcelUploadHelperV1.getCustomersDataFromExcel(file.getInputStream());
				customerDao.saveAll(customers);
//				
//				for (Customer customer : customers) {
////					customerDao.save(customer);
//					
//					System.out.println(customer.getId());
//					System.out.println(customer.getName());
//					
//				}

//				Customer customer = new Customer();
//				customer.setName("Hello");
//				customer.setEmail("cust");
//				customer.setUsername("hellojava");
//				
//////				System.out.println(customer.getId());
//////				System.out.println(customer.getName());
//////				System.out.println(customer.getEmail());
////				
//				customerDao.save(customer);

//				customerDao.save

			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw new IllegalArgumentException("The file is not a valid excel file");
			}
		}
	}

	public CustomResponse<?> getCustomers() {
		
		return new CustomResponse("Customer Found", customerDao.findAll(), HttpStatus.OK.value());
		
//		return customerDao.findAll();
	}

	public List<Customer1> saveCustomerJsonToDbV1(List<Customer1> customers) {
		// TODO Auto-generated method stub

		List<Customer1> customer2 = customerDao1.saveAll(customers);

		return customer2;
	}

	public CustomResponse<?> getCustomersByPagination(PaginationRequestDto requestDto) {
		// TODO Auto-generated method stub
		
		
		if (requestDto.getPage() <0 || requestDto.getSize() <0) {
			throw new RuntimeException("Page number or page size can't be less than 0");
		}
		
		PaginationResponseDto<?> list = customerDaoImpl.findAllByPagination(requestDto);
		
		
		return new CustomResponse<>("Data Found", list, HttpStatus.OK.value());
	}

}
