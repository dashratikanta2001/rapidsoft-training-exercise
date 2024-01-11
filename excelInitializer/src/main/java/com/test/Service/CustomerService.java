package com.test.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.test.Dao.CustomerDao;
import com.test.Helper.ExcelGenerateV1;
import com.test.Helper.ExcelGenerateV2;
import com.test.Helper.ExcelUploadHelperV1;
import com.test.entity.Customer;
import com.test.entity.Student;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class CustomerService {

	
	@Autowired
	private CustomerDao customerDao;
	
	public List<Customer> GenerateExcelV1(HttpServletResponse response) throws IOException
	{
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
	public List<Customer> GenerateExcelV2(HttpServletResponse response) throws IOException
	{
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
	
	
	
	public void saveCustomersToDatabaseV1(MultipartFile file)
	{
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
	
	
	
	public List<Customer> getCustomers()
	{
		return customerDao.findAll();
	}
	
}
