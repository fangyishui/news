package com.mynews.controller;

import com.mynews.entity.AjaxResult;
import com.mynews.entity.News;
import com.mynews.service.NewsService;
import com.mynews.utils.PageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;


@RestController
@Api("swaggerDemoController相关的api")
public class NewsController extends AbstractController {

	@Autowired
	private NewsService newsService;

	AjaxResult result = new AjaxResult();

	/**
	 * 添加页面
	 */
	@GetMapping("news")
	public String addNewsPage(Model model) {
		return "news/add";
	}


	@ApiOperation(value = "接口名称", httpMethod = "POST")
	@PostMapping("news")
	public String addNews(News news) {
		newsService.addNews(news);
		return "redirect:/newss";
	}

	@DeleteMapping("news/{id}")
	public String delNews(@PathVariable("id") Integer id) {
		newsService.delNews(id);
		return "redirect:/newss";
	}

	/**
	 * 修改页面
	 */
	@GetMapping("news/{id}")
	public ModelAndView updateNewsPage(@PathVariable("id") Integer id,Model model) {
		ModelAndView mv = getModelAndView("news/update");
		mv.addObject("news", newsService.findNewsById(id));
		return mv;
	}

	/**
	 * 修改
	 * @param news
	 * @return
	 */
	@PutMapping("news")
	public ModelAndView updateNews(News news) {
		ModelAndView model = getModelAndView("redirect:/newss");
		newsService.updateNews(news);
		return model;
	}

	@GetMapping("newss")
	public ModelAndView findNewsAll(@RequestBody Map<String,Object> map){
		ModelAndView model = getModelAndView("news/news");
		PageUtils newsAll = newsService.findNewsAll(map);
		model.addObject("list", newsAll.getList());
		model.addObject("current", newsAll.getCurrPage());
		model.addObject("pages", newsAll.getPageSize());
		return model;
	}

}
