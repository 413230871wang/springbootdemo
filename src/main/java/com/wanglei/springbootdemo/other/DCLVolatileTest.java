package com.wanglei.springbootdemo.other;

/**
 * @ClassName DCLVolatileTest
 * @Description
 * @Author lay
 * @Date 2020/4/23 3:33 下午
 * @Version 1.0
 **/
public class DCLVolatileTest {
    private volatile static DCLVolatileTest instance;

    public static  DCLVolatileTest getInstance(){
        if(instance == null){
            synchronized (DCLVolatileTest.class){
                if(instance == null){
                    instance = new DCLVolatileTest();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        DCLVolatileTest.getInstance();
    }
}
