package com.mynews.utils;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.HashMap;
import java.util.Map;

public class JsoupUtil {

    /** 连接超时时间 */
    public static int timeout = 30 * 1000;
    /** 连接重试次数 */
    public static int times = 10;
    /** UA */
    public static String UserAgent[] = {
            "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_8; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50",
            "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50",
            "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; .NET4.0C; .NET4.0E; .NET CLR 2.0.50727; .NET CLR 3.0.30729; .NET CLR 3.5.30729; InfoPath.3; rv:11.0) like Gecko",
            "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E)",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.87 Safari/537.36 OPR/37.0.2178.32",
            "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 UBrowser/5.6.12150.8 Safari/537.36",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2486.0 Safari/537.36 Edge/13.10586",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.87 Safari/537.36 OPR/37.0.2178.32" };

    /** 获取随机UA */
    public static String getRandomUA() {
        return UserAgent[(int) (Math.random() * (UserAgent.length))];
    }

    /** 在这里进行连接 如果失败会继续重试 */
    public static Document getDocument(String url) {
        Document doc = null;
        for (int i = 0; i < times; i++) {
            try {
                doc = Jsoup.connect(url).header("User-Agent", getRandomUA())
                        .ignoreContentType(true)
                        .timeout(timeout).get();
                if (doc != null)
                    break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return doc;
    }


    public static Document getDocument(String url,Map<String, String> cookies){

        Document doc = null;
        for (int i = 0; i < times; i++) {
            try {
                doc = Jsoup.connect(url)
                        .header("User-Agent", getRandomUA())
                        .cookies(cookies)
                        .timeout(timeout)
                        .get();
                if (doc != null)
                    break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return doc;
    }

    /**
     * 获取cookie
     * @param url
     * @return
     */
//    public static Map<String, String>  getCookie(String url){
//
//        if(Constant.COMMON_SWITCH == 2){
//            Map<String, String> map = new HashMap<String, String>();
//            map.put("Cookie",Constant.NGA_COOKIE);
//            return map;
//        }
//
//        Map<String, String> cookies = null;
//        Connection.Response res = null;
//        try {
//            res = Jsoup.connect(url)
//                    .header("User-Agent", getRandomUA())
//                    .timeout(timeout)
//                    .execute();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        cookies = res.cookies();
//
//        return cookies;
//    }
}
