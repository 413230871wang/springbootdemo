package com.wanglei.springbootdemo.other;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName PrintGCApplicationStoppedTimeTest
 * @Description
 * @Author lay
 * @Date 2020/4/15 1:41 下午
 * @Version 1.0
 **/
public class PrintGCApplicationStoppedTimeTest {
    static class A{
        public byte[] placeholder = new byte[1024*1024];
    }

    public static void main(String[] args) {
        List<A> list = new ArrayList<>();
        while(true){
            list.add(new A());
        }
    }
}
