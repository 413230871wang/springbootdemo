package com.wanglei.springbootdemo.other;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName InternTest
 * @Description
 * @Author lay
 * @Date 2020/4/7 1:47 下午
 * @Version 1.0
 **/
public class InternTest {
    public static void main(String[] args) {
        String a = "ab";
        String b = "a"+"b";
        String c = new String("ab");
        String d = c.intern();
        System.out.println(a==b);
        System.out.println(a==c);
        System.out.println(a==d);
        System.out.println(c.intern()==new String("ab").intern());



        String s1 = new StringBuilder("ja").append("av").toString();

        System.out.println(s1.intern() == s1); // true

        String s2 = new StringBuilder("ja").append("sa").toString();

        System.out.println(s2.intern() == s2);

        String s3 = new StringBuilder("ja").append("sa").toString();

        System.out.println(s3.intern() == s3);

        System.out.println(Integer.MAX_VALUE+1);

        Map map = new LinkedHashMap(100,1000,true);
    }
}
