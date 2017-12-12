package com.github.csccoder.csdn2Hexomd.util;

import com.github.csccoder.csdn2Hexomd.model.Article;

import java.text.SimpleDateFormat;
import java.util.Arrays;

public class HexoMdUtil {
    private static final SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     ---
     title: hexo deploy时重复输入用户名密码的问题
     date: 2017-12-12 19:17:34
     tags: hexo
     ---
     */
    public static String getHeader(Article article){
        StringBuilder sb=new StringBuilder();
        sb.append("---\n").
                append(String.format("title: %s\n",article.getTitle())).
                append(String.format("date: %s\n",dateFormat.format(article.getDate()))).
                append("tags: "+Arrays.toString(article.getTags())+"\n").
                append("---\n").
                append(String.format("[文章来源:%s](http://blog.csdn.net/qq_1017097573/article/details/%d)\n\n\n",article.getTitle(),article.getId()));
        return sb.toString();
    }

    public static String array2String(String[] array){
        String str="";
        for(String temp:array){
            str+=temp;
        }
        return str;
    }



}
