package com.github.csccoder.csdn2Hexomd.paser;

import com.github.csccoder.csdn2Hexomd.model.Article;
import com.github.csccoder.csdn2Hexomd.util.RegexUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ArticlePaser {
    //2017-10-07 23:13
    private static final SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm");

    public static Article parseArticle(String url){
        Document document=CorePaser.getDocument(url);
        Article article = new Article();

        String articleId=parseArticleId(url);
        String articleTitle=document.select(".link_title>a").text().trim();
        String articleContent=document.select("#article_content").html();
        String tags[]=parseTags(document);
        article.setId(Integer.parseInt(articleId));
        article.setTitle(articleTitle);
        article.setContent(articleContent);
        try {
            article.setDate(dateFormat.parse(document.select(".link_postdate").get(0).text().trim()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        article.setTags(tags);

        return article;
    }

    public static String parseArticleId(String uri){
        return RegexUtil.match(".*/(\\d*)",uri,1);
    }

    public static String[] parseTags(Document document){
        ArrayList<String> list = new ArrayList<String>();
        Elements elements = document.select(".category_r>label");
        for(Element element:elements){
            String text=element.select("span").text().trim();
            String tag=RegexUtil.match("(.*)（.*",text,1);//javaEE（17）
            list.add(tag);
        }
        return list.toArray(new String[0]);
    }
}
