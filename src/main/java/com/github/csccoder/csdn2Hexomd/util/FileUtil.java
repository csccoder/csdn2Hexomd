package com.github.csccoder.csdn2Hexomd.util;

import com.github.csccoder.csdn2Hexomd.model.Article;
import com.github.csccoder.csdn2Hexomd.util.html2markdown.HTML2Md;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {
    private static final String fileRoot = "/home/chenny/csdn2Hexomd";
    private static final String htmlDir = fileRoot + "/html/";
    private static final String mdDir = fileRoot + "/md/";

    private static void save(String content, String filePath) {
        File file = new File(filePath);
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private static void saveHtml(Article article) {
        String filePath = htmlDir + article.getTitle();
        save(article.getContent(), filePath);

    }

    private static void saveHexomd(Article article) {
        String mdhead = HexoMdUtil.getHeader(article);
        try {
            String mdContent = HTML2Md.convertFile(new File(htmlDir + article.getTitle()), "utf-8");
            String realContent = mdhead + mdContent;
            String filePath = mdDir + article.getTitle() + ".md";
            save(realContent, filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void html2HexoMd(Article article){
        saveHtml(article);
        saveHexomd(article);
    }

}
