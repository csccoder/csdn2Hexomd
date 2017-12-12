package com.github.csccoder.csdn2Hexomd;

import com.github.csccoder.csdn2Hexomd.paser.CorePaser;

import java.io.IOException;


public class Main {

    private static String host = "http://blog.csdn.net";

    public static void main(String args[]) throws IOException {
        //input your blog username
        String author = "qq_1017097573";
        new CorePaser().parse(host, author);
    }
}
