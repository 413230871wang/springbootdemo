package com.wanglei.springbootdemo.controller.JVM;

import java.util.ArrayList;
import java.util.List;

public class TestHeap {

    static class OOMObject{

    }
    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true){
            list.add(new OOMObject());
        }
    }
}
