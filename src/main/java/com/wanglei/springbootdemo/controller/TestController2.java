package com.wanglei.springbootdemo.controller;

import com.wanglei.springbootdemo.util.PdfProcessUtil;

import java.io.File;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestController2 {
    public static void main(String[] args) {
//        String a = "vI. REFERENCES\n" +
//                "\n" +
//                "1.";
//
//        Pattern p = Pattern.compile("R(EFERENCES|eferences)[\\s\\S]*?1\\.");
//        Matcher m = p.matcher(a);
//        while(m.find()) {
//            System.out.println(m.group());
//            System.out.println(m.start());
//        }


        LinkedList<File> list = PdfProcessUtil.folderMethod1("/Users/mfhj-dz-001-068/pythonData/getFromPdf/guide",".pdf");
        System.out.println(list.size());
        for(File file:list){
            System.out.println(file.getName());
        }
    }
}
