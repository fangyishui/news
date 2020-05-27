package com.mynews.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mynews.entity.News;
import java.util.List;

public interface NewsService extends IService<News> {

	 Boolean addNews(News n);
	 Boolean delNews(Integer id);
	 Boolean updateNews(News n);
	 News findNewsById(Integer id);
//	 IPage<News> findNewsAll(int page, int pageSize);
	 IPage<News> findNewsAll(int page, int pageSize);

	 Boolean addNewsAll(List<News> ns);
}
