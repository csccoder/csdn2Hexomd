package com.github.csccoder.csdn2Hexomd.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {
    public static String match(String regex,String content,int group){
        String str=null;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        if(matcher.matches()){
            str=matcher.group(group);
        }
        return str;
    }
}
