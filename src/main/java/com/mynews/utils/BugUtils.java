package com.mynews.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mynews.entity.News;
import com.mynews.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
//@Lazy(false)
@Slf4j
public class BugUtils {

	static final Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));

	public static final String FUNNY = "http://www.toutiao.com/api/pc/feed/?utm_source=toutiao&widen=1";


	@Autowired
	private NewsService newsService;

	@Scheduled(cron="0 * * * * 1-7")
//	@Scheduled(cron="0 0 0,8,16 * * 1-7")
//	@Scheduled(cron = "0 0 0,6,12,18 * * 1-7")
	public void saveAll() {
		List<News> nss1 = BugUtils.findBaiDu();
		newsService.addNewsAll(nss1);

		List<News> nss2 = BugUtils.findWangYi();
		newsService.addNewsAll(nss2);

		List<News> nss3 = BugUtils.findJinRiTouTiao();
		newsService.addNewsAll(nss3);

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
			news.setCreateTime(new Date());
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
			n.setCreateTime(new Date());
			nss.add(n);
			if(i == 10) {
				break;
			}
		}
		return nss;
	}

	public static List<News> findJinRiTouTiao() {
//		String url = "https://www.toutiao.com/api/pc/feed/?min_behot_time=0&category=__all__&utm_source=toutiao&widen=1&tadrequire=true&as=A185BE2C1C4BD89&cp=5ECC5B3DC899CE1&_signature=0zoThgAgEBAVbaoLXpl6bNM7UpAAI3.j24IiUcDjYTQAczDddaKJLZcuCzRY7lZ0e70wpveb-YI76nrxCVwP-WgEZQ1i64NKxjJza-zcCuwsuosiQEpkJuJAeiC63ZVFAIF";
		String url = FUNNY + "&max_behot_time=" + "0"
				+ "&max_behot_time_tmp=" + "0";
		JSONObject param = TouTiaoCrawler.getUrlParam(); // 获取用js代码得到的as和cp的值
		// 定义接口访问的模块
		/*
		 * __all__ : 推荐 news_hot: 热点 funny：搞笑
		 */
		String module = "news_hot";
		url += "&as=" + param.get("as") + "&cp=" + param.get("cp")
				+ "&category=" + module;
		System.out.println(url);
		Document document = JsoupUtil.getDocument(url);
		String text = document.text();
		JSONObject jsonObject = JSONObject.parseObject(text);
		List<News> list = new ArrayList<>();
		if(jsonObject.get("message").equals("success")){
			JSONArray jsonArray = (JSONArray)jsonObject.get("data");
			for (Object o : jsonArray){
				JSONObject jObj = (JSONObject)o;
				News news = new News();
				news.setContent(jObj.getString("abstract"));
				news.setCreateTime(new Date());
				news.setImgUrl(jObj.getString("image_url"));
			//	http://p1.pstatp.com/large/pgc-image/836a620ae45b4762a77ed24f3ff999cf
				news.setMonth(c.get(Calendar.MONTH)+1);
				news.setNewsType(3);
				news.setTitle(jObj.getString("title"));
				news.setUrl("https://www.toutiao.com/"+jObj.getString("source_url"));
				news.setYear(c.get(Calendar.YEAR));
				list.add(news);
			}
		}
		return list;
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
			n.setCreateTime(new Date());
			if( i == 10) {
				break;
			}
			nss.add(n);
		}
		return nss;
	}



	public static void main(String[] args) {
		BugUtils.findJinRiTouTiao();
//		System.out.println(BugUtils.findBaiDu());
//		System.out.println(BugUtils.findWeiBo());

	}
}
