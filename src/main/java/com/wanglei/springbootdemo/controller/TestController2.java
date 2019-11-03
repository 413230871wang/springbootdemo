package com.wanglei.springbootdemo.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestController2 {
    public static void main(String[] args) {
        String a = "25. Blain P, Paques M, Massin P, et al. Epiretinal membranes surrounding idiopathic macular holes. Retina \n" +
                "1998;18:316-21.";
        String[] str = a.split("\\.");
        for(String s:str){
            System.out.println(s);
        }

        Pattern p = Pattern.compile("[0-9]{4}\\;");
        Matcher m = p.matcher(a);
        while(m.find()) {
            System.out.println(m.start());
        }
    }
}
