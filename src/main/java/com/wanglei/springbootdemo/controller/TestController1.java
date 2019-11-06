package com.wanglei.springbootdemo.controller;

import com.wanglei.springbootdemo.util.PdfProcessUtil;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestController1 {
    public static void main(String[] args) {
        String content = "1. Siegel RL, Miller KD, Jemal A. Cancer statistics, 2015. CA Cancer J \n" +
                "Clin 2015;65:5-29. ";
//        System.out.println(content.substring(a).replaceAll("guide.medlive.cn" ,"").replaceAll("Idiopathic Macular Hole PPP: ","").replaceAll("References",""));
        String pattern = "[0-9]+\\.[\\s\\S]*?[0-9]+\\;.*?\\.|http.*?[0-9]+\\.";
        Pattern re = Pattern.compile(pattern);
        Matcher m = re.matcher(content);
        while (m.find( )) {
            System.out.println("Found value: " + m.group() );
        }
    }
}
