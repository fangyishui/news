package com.mynews.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.mynews.entitys.News;
import com.mynews.service.NewsService;

@Component
public class BugUtils {

	@Autowired
	private NewsService newsService;
	
	static final List<News> nss = new ArrayList<News>();
	
	static final Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00")); 
	
//	@Scheduled(cron="0 0 0,8,16 1/1 * ? *")
	@Scheduled(cron="0 0 0,8,16 * * 1-7")
	public  void saveAll() {
		List<News> nss1 = BugUtils.findBaiDu();
		newsService.addNewsAll(nss1);
		
		List<News> nss2 = BugUtils.findWangYi();
		newsService.addNewsAll(nss2);
		
//		List<News> nss3 = BugUtils.findJinRiTouTiao();
//		newsService.addNewsAll(nss3);
	}
	
	public static List<News> findBaiDu() {
		
		String url = "https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&tn=95662003_hao_pg&wd=%E7%83%AD%E7%82%B9&oq=%25E6%2596%25B0%25E9%2597%25BB&rsv_pq=be0c175e00011061&rsv_t=a77085mZLd2D4JUHI3dTG16cgHzrsO%2Bonrwr73ppaiK1Vxg80gBaAUeU0geTNd9bQ2WMpcq%2F&rqlang=cn&rsv_enter=0&rsv_sug3=12&rsv_sug1=13&rsv_sug7=100&rsv_sug2=0&inputT=25&rsv_sug4=5429";
		
		Elements elementsByClass = getDoc(url).select("table.c-table.opr-toplist1-table").select("a");
		
		int num =1;
		for (Element element : elementsByClass) {
			News news = new News();
			news.setTitle(element.text());
			news.setUrl(element.attr("abs:href"));
			news.setNewsType(1);
			news.setYear(c.get(Calendar.YEAR));
			news.setMonth(c.get(Calendar.MONTH)+1);
			nss.add(news);
			if(num == 10) {
				break;
			}
			num++;
		}
		
		return nss;
	}
	
	
	public static List<News> findWangYi() {
		
		String url = "http://news.163.com/rank/";
		
		Elements elementsByClass = getDoc(url).select("div.tabContents.active").select("a");
		
		int num =1;
		for (Element element : elementsByClass) {
			News n = new News();
			n.setTitle(element.text());
			n.setUrl(element.attr("abs:href"));
			n.setNewsType(2);
			n.setYear(c.get(Calendar.YEAR));
			n.setMonth(c.get(Calendar.MONTH)+1);
			
			nss.add(n);
			
			if(num == 10) {
				break;
			}
			
			num++;
		}
		return nss;
	}
	
	public static List<News> findJinRiTouTiao() {

		String url = "https://www.toutiao.com/api/pc/feed/?category=news_hot&utm_source=toutiao&widen=1&max_behot_time=0&max_behot_time_tmp=0&tadrequire=true&as=A1059CFB8906F4C&cp=5CB9D61F149C5E1&_signature=gtjEsgAA3m5ELohO6xa2zoLYxK";
		Document doc = null;
		try {
			 doc = Jsoup.connect(url)
					.userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:57.0) Gecko/20100101 Firefox/57.0")
					.header("Accept", "text/javascript, text/html, application/xml, text/xml, */*")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.data("category", "news_hot")
					.data("utm_source", "toutiao")
					.data("widen", "1")
					.data("max_behot_time", "0")
					.data("max_behot_time_tmp", "0")
					.data("as", "A1059CFB8906F4C")
					.data("cp", "5CB9D61F149C5E1")
					.data("_signature", "gtjEsgAA3m5ELohO6xa2zoLYxK")
					.ignoreContentType(true)
					.timeout(5000).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//        System.out.println("返回的json字符串：" + doc.text());
        
        JSONObject object=JSONObject.parseObject(doc.text()); 
        System.out.println(object.getJSONObject("data").get(1).toString());   
        
//		Elements elementsByClass = getDoc(url).select("div.title-box").select("a");
		
//		int num =1;
//		for (Element element : elementsByClass) {
//			News n = new News();
//			n.setTitle(element.text());
//			n.setUrl(element.attr("abs:href"));
//			n.setNewsType(3);
//			n.setYear(c.get(Calendar.YEAR));
//			n.setMonth(c.get(Calendar.MONTH)+1);
//			nss.add(n);
//			if(num == 10) {
//				break;
//			}
//			
//			num++;
//		}
		
		return nss;
	}
	
	public static Document getDoc(String url) {
		
		Document document =null;
		try {
			 document = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return document;
	}
	
	public static void main(String[] args) {
		
		System.out.println(BugUtils.findJinRiTouTiao());
	}
}
