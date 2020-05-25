package com.mynews.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mynews.entity.News;
import com.mynews.mapper.NewsMapper;
import com.mynews.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService{


	@Autowired
	private NewsMapper newsMapper;

	@Override
	public Boolean addNews(News n) {
		return newsMapper.insert(n) > 0 ? true : false;
	}

	@Override
	public Boolean delNews(Integer id) {
		return null;
	}

	@Override
	public Boolean updateNews(News n) {
		return null;
	}

	@Override
	public News findNewsById(Integer id) {
		return null;
	}

	@Override
	public IPage<News> findNewsAll(int page, int pageSize) {
		Page<News> pageN = new Page<News>(page,pageSize);
		return newsMapper.selectPage(pageN,null);
	}

	@Override
	public Boolean addNewsAll(List<News> ns) {
		try {
			for (News news : ns){
				newsMapper.insert(news);
			}
			return true;
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}


//	@Override
//	public Boolean addNews(News n) {
//		try {
//			newsMapper.save(n);
//			return true;
//		}catch (Exception e) {
//			return false;
//		}
//
//	}
//
//	@Override
//	public Boolean delNews(Integer id) {
//		try {
//			newsDao.deleteById(id);
//			return true;
//		}catch (Exception e) {
//			return false;
//		}
//	}
//
//	@Override
//	public Boolean updateNews(News n) {
//		if(null != newsDao.save(n)) {
//			return true;
//		}else {
//			return false;
//		}
//	}
//
//	@Transactional(readOnly = true)
//	@Override
//	public News findNewsById(Integer id) {
//		return newsDao.findById(id).get();
//	}
//
//	@Transactional(readOnly = true)  // 只读事务
//	@Override
//	public List<News> findNewsAll(int page, int pageSize) {
//		Pageable pageable =PageRequest.of(page - 1,pageSize);
//		return newsDao.findAll(pageable).getContent();
//	}
//
//	@Override
//	public Boolean addNewsAll(List<News> ns) {
//		try {
//			newsDao.saveAll(ns);
//			return true;
//		}catch (Exception e) {
//			return false;
//		}
//	}


}
