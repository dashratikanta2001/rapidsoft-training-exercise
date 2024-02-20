package com.rd.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rd.entity.Video;

public interface VideoDao extends JpaRepository<Video, Integer>{

}
