package com.mynews.controller;


import java.util.List;

import com.mynews.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.mynews.entitys.AjaxResult;
import com.mynews.entitys.News;
import com.mynews.service.NewsService;
import org.springframework.web.servlet.ModelAndView;

@RestController
//@Controller
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
	public ModelAndView findNewsAll(@RequestParam(defaultValue = "1") int pageNumber,
							  @RequestParam(defaultValue = "100")  int pageSize){
		ModelAndView model = getModelAndView("news/list");
		model.addObject("newss", newsService.findNewsAll(pageNumber,pageSize));
		return model;
	}

	@GetMapping("newss2")
//	@ResponseBody
	@ApiOperation(value="列表")
	public R newsAll(Model model, @RequestParam(defaultValue = "1") int pageNumber,
							  @RequestParam(defaultValue = "100")  int pageSize){
		return R.ok().put("list", newsService.findNewsAll(pageNumber,pageSize));
	}

	@GetMapping("table1")
	@ResponseBody
	public String findtable(Model model,int pageNumber,int pageSize){
		model.addAttribute("newss", newsService.findNewsAll(1, 11));
		return "news/list";
	}

	@GetMapping("table2")
	@ResponseBody
	public List<News> findtable2(Model model){
//		model.addAttribute("newss", newsService.findNewsAll(1, 11));
//		return "news/list";
		return newsService.findNewsAll(1, 11);
	}
}
