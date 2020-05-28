package com.mynews.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mynews.dao.NewsMapper;
import com.mynews.entity.News;
import com.mynews.service.NewsService;
import com.mynews.utils.PageUtils;
import com.mynews.utils.Query;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService{


	@Resource
	private NewsMapper newsMapper;

	@Override
	public Boolean addNews(News n) {
		return this.save(n);
	}

	@Override
	public Boolean delNews(Integer id) {
		return this.delNews(id);
	}

	@Override
	public Boolean updateNews(News n) {
		return this.updateById(n);
	}

	@Override
	public News findNewsById(Integer id) {
		return this.findNewsById(id);
	}

	@Override
	public PageUtils findNewsAll(Map<String, Object> params) {

		String serverIp = (String) params.get("serverIp");
		String createTimeStart = (String) params.get("createTimeStart");
		String createTimeEnd = (String) params.get("createTimeEnd");

		IPage<News> page = this.page(
				new Query<News>().getPage(params),
				new QueryWrapper<News>()
						.eq(StrUtil.isNotBlank(serverIp),"server_Ip", serverIp)
						.eq("DELETE_MARK", 0)
						.eq("IS_ENABLED", 1)
						.ge(StrUtil.isNotBlank(createTimeStart),"CREATE_TIME", createTimeStart)
						.le(StrUtil.isNotBlank(createTimeEnd),"CREATE_TIME", createTimeEnd)
						.orderByDesc("SORT_CODE", "UPDATE_TIME")
		);
		return new PageUtils(page);
	}

	@Override
	public Boolean addBatchNews(List<News> ns) {
		return this.saveBatch(ns);
	}


}
