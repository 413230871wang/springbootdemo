package com.wanglei.springbootdemo.controller;

import com.wanglei.springbootdemo.util.PdfProcessUtil;
import com.wanglei.springbootdemo.util.WriteExcelUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestController {
    public static void main(String[] args) throws Exception{
        File file = new File("D:\\pythonData\\pdfData\\1\\单\\全文28篇-单\\32037ZN0401.pdf");
        List<Map<String,String>> list = new ArrayList();
        String content = PdfProcessUtil.pdfToString(file);
        int a = content.lastIndexOf("REFERENCES");
//        System.out.println(content.substring(a).replaceAll("guide.medlive.cn" ,"").replaceAll("Idiopathic Macular Hole PPP: ","").replaceAll("References",""));
        String subC = content.substring(a+10).replaceAll("guide.medlive.cn" ,"").replaceAll("Idiopathic Macular Hole PPP: ","").replaceAll("References","");
//        System.out.println(subC);
//        String pattern = "[0-9]+\\..*?[0-9]+\\;.*?\\.";
        String pattern = "[0-9]+\\.[\\s\\S]*?[0-9]+\\;.*?\\.";
        Pattern re = Pattern.compile(pattern);
        Matcher m = re.matcher(subC);
        while (m.find( )) {
            Map<String, String> dataMap=new HashMap<String, String>();
            String[] str = m.group().split("\\.");
            int len = str.length;
            dataMap.put("名字", str[1]);
            String title = "";
            for(int i = 2;i<len-1;i++) {
                title = title + str[i];
            }
            dataMap.put("标题", title);

            //处理year，year中包含机构、年份、期刊号
            String yearAndCode = str[len-1].trim();
            int yearStartIndex = getCodeEndIndex(yearAndCode);
            String code = yearAndCode.substring(0,yearStartIndex);
            String year = yearAndCode.substring(yearStartIndex,yearStartIndex+4);
            String issueNo = yearAndCode.substring(yearStartIndex);
            dataMap.put("机构",code);
            dataMap.put("年份",year);
            dataMap.put("刊号",issueNo);
            list.add(dataMap);
        }
        List<String> sheetName = new ArrayList<>();
        sheetName.add("1");
        String[] title = {"名字","标题","机构","年份","刊号"};
        String fileDir = "D:\\pythonData\\getPdfStr\\writeExcel.xls";
        WriteExcelUtil.createExcelXls(fileDir,sheetName,title);
        WriteExcelUtil.writeToExcelXls(fileDir,"1",list);

    }

    /**
     * 获取code结束位置
     * @return
     */
    public static int getCodeEndIndex(String str){
        Pattern p = Pattern.compile("[0-9]{4}\\;");
        Matcher m = p.matcher(str);
        int index = 0;
        while(m.find()) {
            index = m.start();
        }
        return index;
    }
}
