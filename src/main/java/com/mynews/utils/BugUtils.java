<<<<<<< HEAD
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
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mynews.entitys.News;
import com.mynews.service.NewsService;

import javax.lang.model.element.NestingKind;

@Component
@Lazy(false)
public class BugUtils {

	static final Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));

	@Autowired
	private NewsService newsService;

//	@Scheduled(cron="0 * * * * 1-7")
//	@Scheduled(cron="0 0 0,8,16 * * 1-7")
	@Scheduled(cron = "0 0 0,6,12,18 * * 1-7")
	public void saveAll() {
		List<News> nss1 = BugUtils.findBaiDu();
		newsService.addNewsAll(nss1);

		List<News> nss2 = BugUtils.findWangYi();
		newsService.addNewsAll(nss2);

//		List<News> nss3 = BugUtils.findJinRiTouTiao();
//		newsService.addNewsAll(nss3);

		List<News> nss4 = BugUtils.findWeiBo();
		newsService.addNewsAll(nss4);
	}

	public static List<News> findBaiDu() {
		String url = "http://top.baidu.com/";
		Elements elementsByClass = JsoupUtil.getDocument(url).select("ul#hot-list").select("a");
		List<News> nss = new ArrayList<>();
		for (int i = 0; i< elementsByClass.size(); i ++) {
			if(i % 2 != 0) {
				continue;
			}
			News news = new News();
			news.setTitle(elementsByClass.get(i).attr("title"));
			news.setUrl(elementsByClass.get(i).attr("abs:href"));
			news.setNewsType(1);//百度
			news.setYear(c.get(Calendar.YEAR));
			news.setMonth(c.get(Calendar.MONTH)+1);
			nss.add(news);
		}
		return nss;
	}

	public static List<News> findWangYi() {
		String url = "http://news.163.com/rank/";
		Elements elementsByClass = JsoupUtil.getDocument(url).select("div.tabContents.active").select("a");
		List<News> nss = new ArrayList<>();
		for (int i = 0; i< elementsByClass.size(); i ++) {
			News n = new News();
			n.setTitle(elementsByClass.get(i).text());
			n.setUrl(elementsByClass.get(i).attr("abs:href"));
			n.setNewsType(2);//网易
			n.setYear(c.get(Calendar.YEAR));
			n.setMonth(c.get(Calendar.MONTH)+1);
			nss.add(n);
			if(i == 10) {
				break;
			}
		}
		return nss;
	}

	public static List<News> findJinRiTouTiao() {

//		String url = "https://www.toutiao.com/api/pc/feed/?category=news_hot&utm_source=toutiao&widen=1&max_behot_time=0&max_behot_time_tmp=0&tadrequire=true&as=A1C5BE28C6CA946&cp=5E869A4944A62E1&_signature=SUwySgAgEBCPG4vHVxHx4UlNc1AABfMDiszpIvTVkb6ddZZ0oaGJnzlXf3cCM0C4qW6D7G4Pi4psBWWSTMOlF84fQP00wEeoyf.jjXyILeMbMi4MumcX2ywdePx-jxD-6Bs";
        String url = "https://www.toutiao.com/api/pc/feed/?category=news_hot&utm_source=toutiao&widen=1&max_behot_time=0&max_behot_time_tmp=0&tadrequire=true&as=A1C5BE28C6CA946&cp=5E869A4944A62E1&_signature=SUwySgAgEBCPG4vHVxHx4UlNc1AABfMDiszpIvTVkb6ddZZ0oaGJnzlXf3cCM0C4qW6D7G4Pi4psBWWSTMOlF84fQP00wEeoyf.jjXyILeMbMi4MumcX2ywdePx-jxD-6Bs";

        Document doc = null;
		try {
			 doc = Jsoup.connect(url)
					 .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36")
//					 .data("cookie","tt_webid=6810256054255273480; s_v_web_id=verify_k8fix6ig_nd8jAwQZ_4wXM_4o1N_8e46_ZUgtBSoYSPd1; WEATHER_CITY=%E5%8C%97%E4%BA%AC; tt_webid=6810256054255273480; csrftoken=53f262c3183396d70f784794d139d0f6; ttcid=5507b18119554bbc96bca9cca42b00eb20; SLARDAR_WEB_ID=8be0a116-bd00-4131-ba22-fe6cac78939a; __tasessionId=3lcbzc3zr1585882246440; tt_scid=hnQoWd.8Ec8FTyqFcWIr0A9A4..d7RnegZ4JzNcNi2dtAZWpqbnF6nvPY6Sl7LICa0dd")
					 .cookie("ttcid","5507b18119554bbc96bca9cca42b00eb20")
					 .cookie("csrftoken","53f262c3183396d70f784794d139d0f6")
					 .cookie("SLARDAR_WEB_ID","8be0a116-bd00-4131-ba22-fe6cac78939a")
					 .cookie("__tasessionId","3lcbzc3zr1585882246440")
					 .cookie("s_v_web_id","verify_k8fix6ig_nd8jAwQZ_4wXM_4o1N_8e46_ZUgtBSoYSPd1")
					 .data("referer","https://www.toutiao.com/ch/news_hot/")
//					 .data("category","news_hot")
//					 .data("utm_source","toutiao")
//					 .data("widen","1")
//					 .data("max_behot_time","0")
//					 .data("max_behot_time_tmp","0")
//					 .data("tadrequire","true")
//					 .data("as","479BB4B7254C150")
//					 .data("cp","7E0AC8874BB0985")
//					 .data("_signature","SUwySgAgEBCPG4vHVxHx4UlNc1AABfMDiszpIvTVkb6ddZZ0oaGJnzlXf3cCM0C4qW6D7G4Pi4psBWWSTMOlF84fQP00wEeoyf.jjXyILeMbMi4MumcX2ywdePx-jxD-6Bs")
					 .ignoreContentType(true)
					 .timeout(5000)
					 .get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(doc.toString());
		JSONObject jsonObject = JSONObject.parseObject(doc.toString());
		JSONArray data = (JSONArray)jsonObject.get("data");

		List<News> nss = new ArrayList<>();
		for (int i = 0; i <data.size() ; i++) {
			News n = new News();
//			n.setTitle();
			if(i == 0 || i ==1){
				continue;
			}
			JSONObject js = (JSONObject)data.get(i);
			System.out.println(js.get("title"));

		}

		return nss;
	}

	public static List<News> findWeiBo() {
		Document doc = JsoupUtil.getDocument("https://s.weibo.com/top/summary?cate=realtimehot");
		Elements elements = doc.select("td.td-02");
		List<News> nss = new ArrayList<>();
		for(int i = 0; i < elements.size(); i ++){
			News n = new News();
			String text = elements.get(i).select("a").text();
			String url = elements.get(i).select("a").attr("abs:href");
			n.setTitle(text);
			n.setUrl(url);
			n.setNewsType(4);//微博
			n.setYear(c.get(Calendar.YEAR));
			n.setMonth(c.get(Calendar.MONTH)+1);
			if( i == 10) {
				break;
			}
			nss.add(n);
		}
		return nss;
	}


	public static void main(String[] args) {
		System.out.println(BugUtils.findJinRiTouTiao());
//		System.out.println(BugUtils.findBaiDu());
//		System.out.println(BugUtils.findWeiBo());

	}
}
=======
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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mynews.entitys.News;
import com.mynews.service.NewsService;

@Component
public class BugUtils {

	@Autowired
	private NewsService newsService;
	
	static final List<News> nss = new ArrayList<News>();
	
	static final Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00")); 
	
//	@Scheduled(cron="0 * * * * 1-7")
//	@Scheduled(cron="0 0 0,8,16 * * 1-7")
	@Scheduled(cron = "0 0 0,6,12,18 * * 1-7")
	public  void saveAll() {
		List<News> nss1 = BugUtils.findBaiDu();
		newsService.addNewsAll(nss1);
		
		List<News> nss2 = BugUtils.findWangYi();
		newsService.addNewsAll(nss2);
		
		List<News> nss3 = BugUtils.findJinRiTouTiao();
		newsService.addNewsAll(nss3);
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
		
        String str=JSONObject.parseObject(doc.body().text()).getJSONArray("data").toString();
        
        JSONArray json = (JSONArray) JSONArray.parse(str); 
        int num =1;
        for (Object obj : json) { 
        	News n = new News();
        	JSONObject o = (JSONObject)obj; 
        	n.setTitle(o.getString("title"));
        	n.setUrl("https://www.toutiao.com"+o.getString("source_url"));
        	n.setImgurl(o.getString("image_url"));
        	n.setContent(o.getString("abstract"));
        	n.setNewsType(3);
			n.setYear(c.get(Calendar.YEAR));
			n.setMonth(c.get(Calendar.MONTH)+1);
			
        	if(num == 10) {
				break;
			}
			num++;
        	nss.add(n);
        }
        
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
>>>>>>> ffd47112538e7d0d0faeded5eafd822ac47e4e98