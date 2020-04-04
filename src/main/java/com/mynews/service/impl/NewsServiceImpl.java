<<<<<<< HEAD
package com.mynews.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mynews.dao.NewsDao;
import com.mynews.entitys.News;
import com.mynews.service.NewsService;

@Service
public class NewsServiceImpl implements NewsService{

	
	@Autowired
	private NewsDao newsDao;

	@Override
	public Boolean addNews(News n) {
		try {
			newsDao.save(n);
			return true;
		}catch (Exception e) {
			return false;
		}
		
	}

	@Override
	public Boolean delNews(Integer id) {
		try {
			newsDao.deleteById(id);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean updateNews(News n) {
		if(null != newsDao.save(n)) {
			return true;
		}else {
			return false;
		}
	}

	@Transactional(readOnly = true)
	@Override
	public News findNewsById(Integer id) {
		return newsDao.findById(id).get();
	}

	@Transactional(readOnly = true)  // 只读事务
	@Override
	public List<News> findNewsAll(int page, int pageSize) {
		Pageable pageable =PageRequest.of(page - 1,pageSize);
		return newsDao.findAll(pageable).getContent();
	}

	@Override
	public Boolean addNewsAll(List<News> ns) {
		try {
			newsDao.saveAll(ns);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
	
}
=======
package com.mynews.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mynews.dao.NewsDao;
import com.mynews.entitys.News;
import com.mynews.service.NewsService;

@Service
public class NewsServiceImpl implements NewsService{

	
	@Autowired
	private NewsDao newsDao;

	@Override
	public Boolean addNews(News n) {
		try {
			newsDao.save(n);
			return true;
		}catch (Exception e) {
			return false;
		}
		
	}

	@Override
	public Boolean delNews(Integer id) {
		try {
			newsDao.deleteById(id);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean updateNews(News n) {
		if(null != newsDao.save(n)) {
			return true;
		}else {
			return false;
		}
	}

	@Transactional(readOnly = true)
	@Override
	public News findNewsById(Integer id) {
		return newsDao.findById(id).get();
	}

	@Transactional(readOnly = true)  // 只读事务
	@Override
	public List<News> findNewsAll(int page, int pageSize) {
		Pageable pageable =PageRequest.of(page - 1,pageSize);
		return newsDao.findAll(pageable).getContent();
	}

	@Override
	public Boolean addNewsAll(List<News> ns) {
		try {
			newsDao.saveAll(ns);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
	
}
>>>>>>> ffd47112538e7d0d0faeded5eafd822ac47e4e98