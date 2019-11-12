package com.wanglei.springbootdemo.controller;

import com.wanglei.springbootdemo.util.PdfProcessUtil;
import com.wanglei.springbootdemo.util.WriteExcelUtil;
import com.wanglei.springbootdemo.util.WriteExcelUtilForWin;

import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestControllerForWin1 {

    public static final String REG = "([hH][tT]{2}[pP]:/*|[hH][tT]{2}[pP][sS]:/*|[fF][tT][pP]:/*)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+(\\?{0,1}(([A-Za-z0-9-~]+\\={0,1})([A-Za-z0-9-~]*))*)";

    public static void main(String[] args) throws Exception{
        //获取所有pdf文件路径的list
        LinkedList<File> fileList = PdfProcessUtil.folderMethod1("D:\\pythonData\\guide",".docx");
        System.out.println("文件list长度位："+fileList.size());
        int sumRows = 0;
        for(File file:fileList){
            int a = doFileToExcel(file,sumRows);
            sumRows=sumRows+a;
        }

    }

    /**
     * @Author lay
     * @Description 将file导出到Excel
     * @Date 17:47 2019-11-05
     * @Param [file]
     * @return void
     **/
    public static int doFileToExcel(File file,int sumRows) throws Exception{

        String fileName = file.getName();
        String parentFilename = file.getParentFile().getName();
        try {
            List<Map<String, String>> list = new ArrayList();
            String content = PdfProcessUtil.getPhoneNum(file);
            //根据正则匹配到References的开头
            Pattern p = Pattern.compile("R(EFERENCES|eferences)[\\s\\S]*?1\\.");
            Matcher mm = p.matcher(content);
            int start = 0;
            while (mm.find()) {
                start = mm.start();
            }
//        System.out.println(content.substring(a).replaceAll("guide.medlive.cn" ,"").replaceAll("Idiopathic Macular Hole PPP: ","").replaceAll("References",""));
            String subC = content.substring(start + 10).replaceAll("guide.medlive.cn", "").replaceAll("Idiopathic Macular Hole PPP: ", "").replaceAll("References", "");
//        System.out.println(subC);
            subC = subC.replaceAll(REG,"");
//        String pattern = "[0-9]+\\..*?[0-9]+\\;.*?\\.";
//            String pattern = "([0-9]{1}|[0-9]{2}|[0-9]{3})\\.[\\s\\S]*?[0-9]{4}(\\;[\\s\\S]*?\\.|\\:[\\s\\S]*?\\.|\\.)|http[\\s\\S]*?[0-9]+\\.";
            String pattern = "([0-9]{1}|[0-9]{2}|[0-9]{3})\\.[\\s\\S]*?([0-9]{4}\\;[\\s\\S]*?\\.|[0-9]{4}\\:[\\s\\S]*?\\.|\\, [0-9]{4}\\.)";
            Pattern re = Pattern.compile(pattern);
            Matcher m = re.matcher(subC);
            //创建一个dataMap装载数据
            while (m.find()) {
                Map<String, String> dataMap = new HashMap<String, String>();
                String[] str = m.group().split("\\.");
                int len = str.length;
                dataMap.put("文件名", fileName);
                dataMap.put("名字", str[1]);
                String title = "";
                for (int i = 2; i < len - 2; i++) {
                    title = title + str[i];
                }
                dataMap.put("标题", title);

                //处理year，year中包含机构、年份、期刊号
                String yearAndCode = str[len - 1].trim();
                int yearStartIndex = getCodeEndIndex(yearAndCode);
                String code = str[len-2];
                String year = yearAndCode.substring(yearStartIndex, yearStartIndex + 4);
                String issueNo = yearAndCode.substring(yearStartIndex);
                dataMap.put("机构", code);
                dataMap.put("年份", year);
                dataMap.put("刊号", issueNo);
                list.add(dataMap);
                dataMap = null;
            }
            List<String> sheetName = new ArrayList<>();
            sheetName.add("1");
            String[] title = {"文件名", "名字", "标题", "机构", "年份", "刊号"};
            //开始写文件
            String fileDir = "D:\\pythonData\\export\\"+parentFilename+"\\"+fileName+".xls";
            File goalFile = new File(fileDir);
            if (!goalFile.exists()) {
                WriteExcelUtil.createExcelXls(fileDir, sheetName, title);
                WriteExcelUtil.writeToExcelXls(fileDir, "1", list);
            } else {
                WriteExcelUtil.writeToExcelXls(fileDir, "1", list);
            }
            return list.size();
        }catch (Exception e){
            System.out.println("fileName="+fileName);
        }
        return 0;
    }

    /**
     * 获取code结束位置
     * @return
     */
    public static int getCodeEndIndex(String str){
        Pattern p = Pattern.compile("(19|20)[1-9]{2}");
        Matcher m = p.matcher(str);
        int index = 0;
        while(m.find()) {
            index = m.start();
        }
        return index;
    }
}
