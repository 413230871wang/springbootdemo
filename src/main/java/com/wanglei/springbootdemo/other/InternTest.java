package com.wanglei.springbootdemo.other;

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
    }
}
