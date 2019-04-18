package com.mynews.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mynews.entitys.News;

public interface NewsDao  extends JpaRepository<News, Integer>{

	
	
}
