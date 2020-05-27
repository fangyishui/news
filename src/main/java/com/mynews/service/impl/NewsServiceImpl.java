package com.mynews.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mynews.entity.News;
import com.mynews.mapper.NewsMapper;
import com.mynews.service.NewsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService{


	@Resource
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
		return this.saveBatch(ns);
	}


}
