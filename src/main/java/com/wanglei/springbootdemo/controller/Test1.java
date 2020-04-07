package com.wanglei.springbootdemo.controller;

import com.wanglei.springbootdemo.util.PdfProcessUtil;

import java.io.File;

/**
 * @ClassName Test1
 * @Description
 * @Author lay
 * @Date 2019/12/6 5:14 下午
 * @Version 1.0
 **/
public class Test1 {
    public static void main(String[] args) {
        File file = new File("/Users/mfhj-dz-001-068/Desktop/wanglei.pdf");
        String content = PdfProcessUtil.pdfToString(file);
        System.out.println(content);
    }
}
