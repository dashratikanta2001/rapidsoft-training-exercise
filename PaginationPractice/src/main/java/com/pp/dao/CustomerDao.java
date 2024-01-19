package com.pp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pp.dto.PaginationRequestDto;
import com.pp.dto.PaginationResponseDto;
import com.pp.entity.Customer;
import com.pp.util.SortOrder;

@Repository
@Transactional
public class CustomerDao {

	@Autowired
	private SessionFactory sessionFactory;

	public List<Customer> findAll() {
		Query query = sessionFactory.getCurrentSession().createQuery("from Customer");

		return query.getResultList();
	}

	public PaginationResponseDto<?> findAllByPagination(PaginationRequestDto requestDto) {
		// TODO Auto-generated method stub

		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Customer.class);

		int totalItems = criteria.list().size();
		int totalPages = (int) Math.ceil((double) totalItems / requestDto.getSize());

		if (requestDto.getPage() >= totalPages) {
			throw new RuntimeException("Page Index out of bound.");
		}

		criteria.setFirstResult(requestDto.getPage() * requestDto.getSize());
		criteria.setMaxResults(requestDto.getSize());
		if (requestDto.getSortOrder() == null || requestDto.getSortOrder() == SortOrder.ASC) {
			criteria.addOrder(Order.asc("id"));			
		}
		else if (requestDto.getSortOrder() == SortOrder.DESC) {
			criteria.addOrder(Order.desc("id"));
		}
		
		List<Customer> customers = criteria.list();

//		return new PaginationResponseDto(criteria.list(), totalItems, totalPages, requestDto.getPage());
		
		return new PaginationResponseDto<>(customers, requestDto.getPage(), customers.size(), requestDto.getSize(), totalPages, totalItems , requestDto.getSortOrder(), requestDto.getPage()==0, requestDto.getPage()==totalPages-1);
	}

}
