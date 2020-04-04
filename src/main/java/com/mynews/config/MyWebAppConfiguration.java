<<<<<<< HEAD
package com.mynews.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

//@Configuration
public class MyWebAppConfiguration extends WebMvcConfigurationSupport{

	@Override
	protected void addViewControllers(ViewControllerRegistry registry) {

		registry.addViewController("/").setViewName("/user/login");
		
		super.addViewControllers(registry);
	}
	
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {

		/**
		 * @Description: 对文件的路径进行配置,创建一个虚拟路径/Path/** ，即只要在<img src="/Path/picName.jpg" />便可以直接引用图片
		 *这是图片的物理路径  "file:/+本地图片的地址"
		 */  
		 registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
		
		 registry.addResourceHandler("/Img/**").addResourceLocations("file:/D:/tts/");

		 registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/");
		 
		 super.addResourceHandlers(registry);
		
	}
	
//	@Override
//	protected void addFormatters(FormatterRegistry registry) {
//
//		registry.addFormatter(datef);
//		super.addFormatters(registry);
//	}
	
//	@Override
//	protected void addInterceptors(InterceptorRegistry registry) {
//		super.addInterceptors(registry);
//	}
	
	
}
=======
package com.mynews.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

//@Configuration
public class MyWebAppConfiguration extends WebMvcConfigurationSupport{

	@Override
	protected void addViewControllers(ViewControllerRegistry registry) {

		registry.addViewController("/").setViewName("/user/login");
		
		super.addViewControllers(registry);
	}
	
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {

		/**
		 * @Description: 对文件的路径进行配置,创建一个虚拟路径/Path/** ，即只要在<img src="/Path/picName.jpg" />便可以直接引用图片
		 *这是图片的物理路径  "file:/+本地图片的地址"
		 */  
		 registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
		
		 registry.addResourceHandler("/Img/**").addResourceLocations("file:/D:/tts/");

		 registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/");
		 
		 super.addResourceHandlers(registry);
		
	}
	
//	@Override
//	protected void addFormatters(FormatterRegistry registry) {
//
//		registry.addFormatter(datef);
//		super.addFormatters(registry);
//	}
	
//	@Override
//	protected void addInterceptors(InterceptorRegistry registry) {
//		super.addInterceptors(registry);
//	}
	
	
}
>>>>>>> ffd47112538e7d0d0faeded5eafd822ac47e4e98
