package com.wanglei.springbootdemo.other;

public class Test {
    public void add(Byte b) {
        b = b++;
    }

    public void test() {
        Byte a = 127;
        Byte b = 127;

        System.out.println("a = "+(++a));
        System.out.println("a = "+(++a));
        add(b);
        System.out.println("b = "+b);
    }

    public static void main(String[] args) {
        Test test2=new Test();
        test2.test();
    }
}
