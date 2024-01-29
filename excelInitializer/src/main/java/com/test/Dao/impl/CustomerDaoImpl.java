package com.test.Dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
//import javax.persistence.criteria.Order;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.Dto.PaginationRequestDto;
import com.test.Dto.PaginationResponseDto;
import com.test.entity.Customer;
import com.test.util.SortOrder;

//import jakarta.persistence.EntityManager;
//import jakarta.persistence.criteria.CriteriaBuilder;
//import jakarta.persistence.criteria.CriteriaQuery;
//import jakarta.persistence.criteria.Root;
//import jakarta.transaction.Transactional;

@Repository
@Transactional
public class CustomerDaoImpl {

	@Autowired
	EntityManager entityManager;

	public PaginationResponseDto<?> findAllByPagination(PaginationRequestDto requestDto) {
		// TODO Auto-generated method stub

		Session session = entityManager.unwrap(Session.class);

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
		} else if (requestDto.getSortOrder() == SortOrder.DESC) {
			criteria.addOrder(Order.desc("id"));
		}

		List<Customer> customers = criteria.list();

		return new PaginationResponseDto<>(customers, requestDto.getPage(), customers.size(), requestDto.getSize(),
				totalPages, totalItems, requestDto.getSortOrder(), requestDto.getPage() == 0,
				requestDto.getPage() == totalPages - 1);

	}

}
