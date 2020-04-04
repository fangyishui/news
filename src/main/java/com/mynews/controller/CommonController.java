package com.mynews.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mynews.entitys.AjaxResult;
import com.mynews.utils.FileUtil;

@Controller
public class CommonController {

	AjaxResult result = new AjaxResult();
	
	
	@GetMapping("/file")
    public String file(){
        return "news/file";
    }
	
	//处理文件上传
    @PostMapping(value="/upload")
    @ResponseBody
    public AjaxResult upload(
    		@RequestParam("file") MultipartFile file,
            HttpServletRequest request) {
     
    	if (file.isEmpty()){ 
    		System.out.println("文件为空"); 
//    		return "文件为空";
    	} 
    	
    	// 文件名
    	String fileName = file.getOriginalFilename(); 
    	
    	if(FileUtil.fileSuffix(fileName) == false) {
    		System.out.println("不支持的文件类型");
//    		return "不支持的文件类型";
    	}
    	
    	result.setMessage(FileUtil.uploadFile(file, fileName,request));
    	
    	result.setFalg(true);
    	
        return result;
    }

    
    //处理多文件上传
    @PostMapping(value="/uploads")
    @ResponseBody
    public AjaxResult uploads(
    		@RequestParam("file") MultipartFile[] files,
            HttpServletRequest request) {
     
    	int num = 1;
    	for (MultipartFile file : files) {
			
    		if (file.isEmpty()){ 
        		System.out.println("第"+num+"文件为空:"+file.getOriginalFilename()+"~"); 
        		result.setMessage("第"+num+"文件为空:");
        		result.setFalg(false);
        		return result;
        	}
    		
    		
    		String fileName = file.getOriginalFilename(); 
        	
        	if(FileUtil.fileSuffix(fileName) == false) {
        		System.out.println("不支持的文件类型");
        		result.setMessage("不支持的文件类型");
        		result.setFalg(false);
        		return result;
        	}
    		
        	result.setMessage(FileUtil.uploadFile(file, fileName,request));
        	result.setFalg(true);
        	
        	num++;
		}
    	
        return result;
    }
	
    @GetMapping("/table")
    public String table() {
    	return "news/table";
    }
    
    @GetMapping("/echarts")
    public String echarts() {
    	return "echarts";
    }
    
    @GetMapping("/execl")
    public String execl() {
//    	ExcelUtil.getWorkbok(in, file)
    	return "execl";
    }
}
