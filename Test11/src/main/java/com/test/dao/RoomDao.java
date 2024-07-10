package com.test.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.model.Room;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface RoomDao extends JpaRepository<Room, Long>{

}
