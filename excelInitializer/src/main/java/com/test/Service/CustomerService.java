package com.test.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.Dao.CustomerDao;
import com.test.Helper.ExcelGenerateV1;
import com.test.Helper.ExcelGenerateV2;
import com.test.entity.Customer;
import com.test.entity.Student;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class CustomerService {

	
	@Autowired
	private CustomerDao customerDao;
	
	public List<Customer> GenerateExcel(HttpServletResponse response) throws IOException
	{
		List<Customer> customersList = customerDao.findAll();
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
	
}
