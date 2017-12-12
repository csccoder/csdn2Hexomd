package com.github.csccoder.csdn2Hexomd.paser;

import com.github.csccoder.csdn2Hexomd.model.Article;
import com.github.csccoder.csdn2Hexomd.util.FileUtil;
import com.github.csccoder.csdn2Hexomd.util.HttpClientUtil;
import com.github.csccoder.csdn2Hexomd.util.RegexUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CorePaser {


    /**
     * 解析博客的入口函数
     *
     * @param host   csdn域名
     * @param author csdn账号
     */
    public void parse(String host, String author) throws IOException {
        Document document = getDocument(host + "/" + author);
        String papeList = document.select("#papelist>span").text();
        int recordCount = getRecordCount(papeList);
        int pageCount = getPageCount(papeList);
        System.out.println(String.format("共需爬取记录数%d条,共%d页", recordCount, pageCount));
        for (int page = 1; page <= pageCount; page++) {
            System.out.println("正在爬去第" + page + "页");
            if (page > 1) {
                document = getDocument(host + "/" + author + "/article/list/" + page);
            }
            //获得当前页所有文章的URI
            List<String> uris = parseArticleURIs(document);
            for (String uri : uris) {
                Article article = ArticlePaser.parseArticle(host + uri);
                System.out.println("=>" + article.getId() + "  " + article.getTitle());
                FileUtil.html2HexoMd(article);
            }
        }

    }


    public static Document getDocument(String url) {
        String content = HttpClientUtil.get(url);
        Document document = Jsoup.parse(content);
        return document;
    }


    public ArrayList<String> parseArticleURIs(Document document) {
        ArrayList<String> ids = new ArrayList();
        Elements elements = document.select(".link_title>a");
        for (Element element : elements) {
            ids.add(element.attr("href"));
        }
        return ids;
    }

    public int getRecordCount(String papeList) {
        String value = RegexUtil.match("\\s*?(\\d*)条.*", papeList, 1);
        return value == null ? 0 : Integer.parseInt(value);
    }

    public int getPageCount(String pageList) {
        String value = RegexUtil.match(".*共(\\d*)页", pageList, 1);
        return value == null ? 0 : Integer.parseInt(value);
    }


}
