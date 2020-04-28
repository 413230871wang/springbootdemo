package com.wanglei.springbootdemo.other;

import static java.lang.Thread.sleep;

/**
 * @ClassName FinalizeTest
 * @Description
 * @Author lay
 * @Date 2020/4/8 3:16 下午
 * @Version 1.0
 **/
public class FinalizeTest {
    public static int a = 10;

    public void isAlive(){
        System.out.println("我活着！");
    }

    public static void main(String[] args) throws InterruptedException {
        for(int i=0;i<=2;i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"+"+a--);
                }
            });
            thread.start();
        }
    }
}
