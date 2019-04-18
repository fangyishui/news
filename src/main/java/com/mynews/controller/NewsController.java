package com.mynews.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mynews.entitys.AjaxResult;
import com.mynews.entitys.News;
import com.mynews.service.NewsService;
import com.mynews.utils.FileUtil;

@Controller
public class NewsController {

	@Autowired
	private NewsService newsService;
	
	AjaxResult result = new AjaxResult();
	
	@PostMapping("news")
	public String addNews(News news) {
//		news.setCreatetime(new Date());
		newsService.addNews(news);
		
		return "redirect:/newss";
	}
	
	@DeleteMapping("news/{id}")
	public Boolean delNews(@PathVariable("id") Integer id) {
		
		return newsService.delNews(id);
	}
	
	@PutMapping("news")
	public Boolean updateNews(News news) {
		return newsService.updateNews(news);
	}
	
//	@GetMapping("news/{id}")
//	public News findNewsById(@PathVariable("id") Integer id) {
//		return newsService.findNewsById(id);
//	}
	
//	@GetMapping("newss")
//	public String findNewsAll(@PathVariable("page") int page,
//			@PathVariable("pageSize") int pageSize){
//		return newsService.findNewsAll(page, pageSize);
//		return "news/list";
//	}
	
	@GetMapping("newss")
	public String findNewsAll(Model model){
		model.addAttribute("news", newsService.findNewsById(1));
		return "news/list";
	}
	
	/**
	 * 添加页面
	 */
	@GetMapping("news")
	public String addNewsPage(Model model) {
		
		return "news/add";
	}
	
	/**
	 * 修改页面
	 */
	@GetMapping("news/{id}")
	public String updateNewsPage(@PathVariable Integer id,Model model) {
		
		model.addAttribute("news", newsService.findNewsById(id));
		
		return "";
	}
//	@Scheduled(cron="0 * * * * MON-SAT")
//	public void hello() {
//		System.out.println("@Scheduled(cron=\"0 * * * * MOn-SAT\")");
//	}
	
	//处理文件上传
    @PostMapping(value="/upload")
    public @ResponseBody String upload(
    		@RequestParam("file") MultipartFile file,
            HttpServletRequest request) {
     
    	if (file.isEmpty()){ 
    		System.out.println("文件为空"); 
    		return "文件为空";
    	} 
    	
    	// 文件名
    	String fileName = file.getOriginalFilename(); 
    	
    	if(FileUtil.fileSuffix(fileName) == false) {
    		System.out.println("不支持的文件类型");
    		return "不支持的文件类型";
    	}
    	
    	FileUtil.uploadFile(file, fileName);
    	
    	
        return null;
    }

}
