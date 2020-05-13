package com.wanglei.springbootdemo.other;

/**
 * @ClassName SugarTest
 * @Description
 * @Author lay
 * @Date 2020/4/28 5:42 下午
 * @Version 1.0
 **/
public class SugarTest {

    public static void main(String[] args) {
        int x =1;
        Integer a = 1;
        Integer b = 2;
        Integer c = 2;
        Integer d = 1;
        Integer e = 1;
        Integer f = 1;
        Long g = 3L;
        System.out.println(c == d);
        System.out.println(e == f);
        System.out.println(c == (a+b));
        System.out.println(c.equals(a+b));
        System.out.println(g == (a+b));
        System.out.println(g.equals(a+b));

        if(true){
            System.out.println("1");
        }else{
            System.out.println("2");
        }

        if(x==1){
            System.out.println("3");
        }else{
            System.out.println("4");
        }
        while(true){
            System.out.println("5");
        }

    }
}
