package com.rd.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rd.entity.Author;

public interface AuthorDao extends JpaRepository<Author, Integer> {

}
