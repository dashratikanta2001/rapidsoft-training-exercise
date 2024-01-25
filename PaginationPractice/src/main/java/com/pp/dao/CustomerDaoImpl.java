package com.pp.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pp.entity.Customer;

@Repository
@Transactional
public class CustomerDaoImpl implements CustomerDaoInterface {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> findAllCustomer() {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Customer.class);
		
		return criteria.list();
	}

}
