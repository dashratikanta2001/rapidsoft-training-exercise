package com.rd.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rd.entity.Role;


public interface RoleDao extends JpaRepository<Role, Long>{

	Role findByName(String name);
}
