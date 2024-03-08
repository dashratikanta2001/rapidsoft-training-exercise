package com.auth.dao.impl;

import java.util.ArrayList;
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

import com.auth.dao.UserDao;
import com.auth.entity.User;

@Repository
public class UserDaoImpl implements UserDao{

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public User saveUser(User user) {
		Session session = entityManager.unwrap(Session.class);
		Long id= (Long) session.save(user);
		if(id!=null & id>0)
			return user;
		return null;
	}

	@Override
	public User findUserByEmailId(String email) {
		Session session = entityManager.unwrap(Session.class);
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> root = cq.from(User.class);
		List<Predicate> andPredicates = new ArrayList<>();
		andPredicates.add(cb.equal(root.get("email"), email));
		andPredicates.add(cb.equal(root.get("isActive"), true));
		Predicate andPredicate = cb.and(andPredicates.toArray(new Predicate[] {}));
		cq.distinct(true).select(root).where(cb.and(andPredicate));
		Query<User> query = session.createQuery(cq);
		List<User> resultList = query.getResultList();
		System.out.println("email = "+resultList.size());
		if(resultList != null && resultList.size() > 0)
		{
			return resultList.get(0);
		}
		
		return null;
	}

}
