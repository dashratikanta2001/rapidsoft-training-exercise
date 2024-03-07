package com.auth.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.auth.dao.OtpDao;
import com.auth.entity.Otp;

@Repository
public class OtpDaoImpl implements OtpDao{

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public Otp saveOtp(Otp otp) {
		Session session = entityManager.unwrap(Session.class);
		Long id= (Long) session.save(otp);
		if(id!=null & id>0)
			return otp;
		return null;
	}

	@Override
	public Otp findOtpByUsername(String username) {
		Session session = entityManager.unwrap(Session.class);
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Otp> cq = cb.createQuery(Otp.class);
		Root<Otp> root = cq.from(Otp.class);
		Predicate predicate = cb.equal(root.get("email"), username);
		cq.distinct(true).select(root).where(predicate);
		Query<Otp> query = session.createQuery(cq);
		List<Otp> resultList = query.getResultList();
		if(resultList !=null && resultList.size()>0)
		{
			return resultList.get(0);
		}
		return null;
	}

}
























