package com.mynews.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mynews.entity.News;
import com.mynews.service.NewsService;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.*;


@Component
public class BugUtils {

	static final Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));

	public static final String FUNNY = "http://www.toutiao.com/api/pc/feed/?utm_source=toutiao&widen=1";

	@Autowired
	private NewsService newsService;

	@Scheduled(cron = ("${my.scheduled.cron}"))
	public void saveAll() {
		List<News> nss1 = BugUtils.findBaiDu();
		newsService.addBatchNews(nss1);

		List<News> nss2 = BugUtils.findWangYi();
		newsService.addBatchNews(nss2);

		List<News> nss3 = BugUtils.findJinRiTouTiao();
		newsService.addBatchNews(nss3);

		List<News> nss4 = BugUtils.findWeiBo();
		newsService.addBatchNews(nss4);
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
		JSONObject jsonObject = JSONObject.parseObject(document.text());
		List<News> list = new ArrayList<>();
		int i = 0;
		if(jsonObject.get("message").equals("success")){
			JSONArray jsonArray = (JSONArray)jsonObject.get("data");
			for (Object o : jsonArray){
				JSONObject jObj = (JSONObject)o;
				News news = new News();
				news.setContent(jObj.getString("abstract"));
				news.setCreateTime(new Date());
				news.setImgUrl(jObj.getString("image_url"));
				news.setMonth(c.get(Calendar.MONTH)+1);
				news.setNewsType(3);
				news.setTitle(jObj.getString("title"));
				news.setUrl("https://www.toutiao.com"+jObj.getString("source_url"));
				news.setYear(c.get(Calendar.YEAR));
				list.add(news);
				i++;
				if(i == 10){
					break;
				}
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
	}
}
