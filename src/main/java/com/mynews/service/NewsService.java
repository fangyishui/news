<<<<<<< HEAD
package com.mynews.service;

import java.util.List;

import com.mynews.entitys.News;

public interface NewsService {

	 Boolean addNews(News n);
	 Boolean delNews(Integer id);
	 Boolean updateNews(News n);
	 News findNewsById(Integer id);
	 List<News> findNewsAll(int page,int pageSize);
	 
	 Boolean addNewsAll(List<News> ns);
}
=======
package com.mynews.service;

import java.util.List;

import com.mynews.entitys.News;

public interface NewsService {

	 Boolean addNews(News n);
	 Boolean delNews(Integer id);
	 Boolean updateNews(News n);
	 News findNewsById(Integer id);
	 List<News> findNewsAll(int page,int pageSize);
	 
	 Boolean addNewsAll(List<News> ns);
}
>>>>>>> ffd47112538e7d0d0faeded5eafd822ac47e4e98
