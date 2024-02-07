package com.rd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rd.dao.ProductDao;
import com.rd.entity.Product;

@RestController
@RequestMapping("/product")
@EnableCaching
public class ProductController {

	@Autowired
	private ProductDao productDao;

	@PostMapping
	public Product save(@RequestBody Product product) {
		return productDao.save(product);
	}

	@GetMapping
	public List<Product> getAllProducts() {
		return productDao.findAll();
	}

	@GetMapping("/{id}")
	@Cacheable(key = "#id", value = "Product", unless = "#result.price<4000")
	public Product findProduct(@PathVariable int id) {
		return productDao.findProductById(id);
	}

	@DeleteMapping("/{id}")
	@CacheEvict(key = "#id", value = "Product")
	public String deleteProduct(@PathVariable int id) {
		return productDao.deleteProduct(id);
	}

}
