package com.mynews.utils;

import com.mynews.entity.News;
import com.mynews.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
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
		String url = "https://www.toutiao.com/ch/news_hot/";
//		Document document = JsoupUtil.getDocument("https://m.toutiao.com/?channel=news_hot&W2atIF=1#channel=news_hot");
		String body = HttpClientUtil.doGet(url, null);
		Document parse = Jsoup.parse(body);

//		parse.select()

//		System.out.println(document.text());
		//		for (Element a : Jsoup.parse(htmlString).select("a[href~=/group/.*]:not(.comment)")) {
//			String href = a.attr("href");
//			String title = StringUtils.isNotBlank(a.select("p").text()) ?
//					a.select("p").text() : a.text();
//			String image = a.select("img").attr("src");
//			log.info("heref {} , title {} , image {} " ,href,title,image );
//		}
		return null;
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
		System.out.println(BugUtils.findJinRiTouTiao());
//		System.out.println(BugUtils.findBaiDu());
//		System.out.println(BugUtils.findWeiBo());

	}
}
