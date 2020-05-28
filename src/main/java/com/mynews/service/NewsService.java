package com.mynews.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mynews.entity.News;
import com.mynews.utils.PageUtils;

import java.util.List;
import java.util.Map;

public interface NewsService extends IService<News> {

	 Boolean addNews(News n);

	 Boolean delNews(Integer id);

	 Boolean updateNews(News n);

	 News findNewsById(Integer id);

	 PageUtils findNewsAll(Map<String, Object> params);

	IPage<News> findAll(int page, int pageSize);

	 Boolean addBatchNews(List<News> ns);
}
