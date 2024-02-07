package com.rd.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.rd.entity.Product;

@Repository
public class ProductDao {

	private static final String HASH_KEY = "Product";

	@Autowired
	private RedisTemplate redisTemplate;

	public Product save(Product product) {
		redisTemplate.opsForHash().put(HASH_KEY, product.getId(), product);
		return product;
	}

	public List<Product> findAll() {
		return redisTemplate.opsForHash().values(HASH_KEY);
	}

	public Product findProductById(int id) {
		
		System.out.println("Called this method from db");
		
		Object object = redisTemplate.opsForHash().get(HASH_KEY, id);
		
		//This is using ObjectMapper
//		ObjectMapper mapper = new ObjectMapper();
//		Product product = mapper.convertValue(object, Product.class);
		
		
		//This is using Gson
		Gson gson = new Gson();
		Product fromJson = gson.fromJson(gson.toJson(object), Product.class);
		
		if (fromJson ==null) {
			return new Product();
		}
		
		return fromJson;
	}

	public String deleteProduct(int id) {
		redisTemplate.opsForHash().delete(HASH_KEY, id);
		return "Product removed";
	}
}
