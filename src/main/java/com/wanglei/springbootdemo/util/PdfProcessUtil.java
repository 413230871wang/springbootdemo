package com.wanglei.springbootdemo.util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * pdf文件处理
 */
public class PdfProcessUtil {

    public static String pdfToString(File file) {

        String content = null;
        InputStream input = null;
        PDDocument document = null;
        try {
            input = new FileInputStream(file);
            boolean sort = false;
            // 开始提取页数
            int startPage = 1;
            // 结束提取页数
            int endPage = Integer.MAX_VALUE;
            document = PDDocument.load(input);
            PDFTextStripper pts = new PDFTextStripper();
            pts.setSortByPosition(sort);
            endPage = document.getNumberOfPages();
            System.out.println("Total Page: " + endPage);
            pts.setStartPage(startPage);
            pts.setEndPage(endPage);
            content = pts.getText(document);
            return content;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidPasswordException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            try {
                input.close();
                document.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        return "";
    }

    /**
     * 获取word中的文本
     * @param filePath
     * @return
     */
    public static String getPhoneNum(File filePath) {
        String text = "";

        String fileName = filePath.getName().toLowerCase();// 得到名字小写
        try {
            FileInputStream in = new FileInputStream(filePath);
            if (fileName.endsWith(".doc")) { // doc为后缀的

                WordExtractor extractor = new WordExtractor(in);
                text = extractor.getText();
            }
            if (fileName.endsWith(".docx")) { // docx为后缀的

                XWPFWordExtractor docx = new XWPFWordExtractor(new XWPFDocument(in));
                text = docx.getText();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return text;
    }

    /**
     * @Author lay
     * @Description
     * @Date 17:26 2019-11-05
     * @Param [path, fileType]
     * @return List
     **/
    public static LinkedList<File> folderMethod1(String path,String fileType) {

        File file = new File(path);
        LinkedList<File> list = new LinkedList<>();

        //保存所有pdf文件的对象
        LinkedList<File> pdfList = new LinkedList<File>();

        //该路径对应的文件或文件夹是否存在
        if (file.exists()) {

            //如果该路径为---文件或空文件夹
            if (null == file.listFiles()) {
//            	System.out.println(file.getAbsolutePath());
                if (file.getAbsolutePath().endsWith(fileType))
                    pdfList.add(file);
            }

            //如果该路径为非空文件夹
            else {
                //将该路径下的所有文件（文件或文件夹）对象加入队列
                list.addAll(Arrays.asList(file.listFiles()));
                //遍历该队列
                while (!list.isEmpty()) {

                    File firstF = list.removeFirst();

                    //这里不论是文件夹还是文件，只需判断是否以“.pdf”结尾
                    if (firstF.getAbsolutePath().endsWith(fileType))
                        pdfList.add(firstF);

                    File[] files = firstF.listFiles();

                    if (null == files) {
                        //System.out.println(firstF.getAbsolutePath());
                        continue;
                    }
                    for (File f : files) {
                        if (f.isDirectory()) {
                            //System.out.println("文件夹:" + f.getAbsolutePath());
                            list.add(f);
                        } else {
                            //System.out.println("文件:" + f.getAbsolutePath());

                            if (f.getAbsolutePath().endsWith(fileType))
                                pdfList.add(f);

                        }
                    }
                }
            }

        } else {
            System.out.println("文件不存在!");
        }
        return pdfList;
    }

}
