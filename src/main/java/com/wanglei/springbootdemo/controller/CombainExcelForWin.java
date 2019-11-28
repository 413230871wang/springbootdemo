package com.wanglei.springbootdemo.controller;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

/**
 * @ClassName combainExcel
 * @Description
 * @Author lay
 * @Date 2019/11/12 2:13 下午
 * @Version 1.0
 **/
public class CombainExcelForWin {
    private WritableCellFormat times;
    private String createFilePath;// 合成文件存放路径
    private int beginMergerColumnIndex = 0;// 开始合并的列
    private int endMerGerColumnIndex;// 结束的合并的列
    // private long headRowIndex;//列头所在位置
    private int beginMergerRow = 1;// 开始合并的行标(例如第一行是表头第二行开始是数据，则此时从第二行有数据的开始合并即为1，也意味着表头就一行)
    private int pageCount = 0;
    //private List<String> columns = null;
    private String[][] headColumns = null;
    private String dirPath;
    private int mergerRowBeginIndex = 0;


    public CombainExcelForWin(String createFilePath, String path) {
        this.createFilePath = createFilePath;
        this.dirPath = path;
    }


    public CombainExcelForWin(String createFilePath, String path, int beginMergerRow) {
        this.createFilePath = createFilePath;
        this.dirPath = path;
        this.beginMergerRow = beginMergerRow;
    }

    public CombainExcelForWin(String createFilePath, String path, int beginMergerColumnIndex, int endMerGerColumnIndex,
                              int beginMergerRow) {
        this.createFilePath = createFilePath;
        this.dirPath = path;
        this.beginMergerColumnIndex = beginMergerColumnIndex;
        this.endMerGerColumnIndex = endMerGerColumnIndex;
        this.beginMergerRow = beginMergerRow;
    }


    /*******
     * 写文件
     * 
     * @throws IOException
     * @throws WriteException
     * @throws Exception
     */
    public String mergerExcel() {
        WritableWorkbook workbook = null;
        try {
            if (createFilePath == null) {
                return "请输入创建文件路径";
            }
            if (dirPath == null) {
                return "请输入被合并文件夹的路径";
            }
            File file = new File(createFilePath);
            WorkbookSettings wbSettings = new WorkbookSettings();
            wbSettings.setLocale(new Locale("en", "EN"));
            workbook = Workbook.createWorkbook(file, wbSettings);
            workbook.createSheet("合并_" + pageCount, pageCount);
            WritableSheet excelSheet = workbook.getSheet(pageCount);
            this.beginMergerColumn(excelSheet);
            workbook.write();
            return "合并成功";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (WriteException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    /*****
     * 开始合并文件
     * 
     * @param excelSheet
     */
    private void beginMergerColumn(WritableSheet excelSheet) {
        File srcFile = new File(dirPath);
        boolean bFile = srcFile.exists();


        if (!bFile || !srcFile.isDirectory() || !srcFile.canRead()) {
/*try {
// TODO 创建？ 
srcFile.createNewFile();
} catch (IOException e) {
e.printStackTrace();
}*/
            System.out.println("要合并的文件路径不存在！");
        } else {
            File[] file = srcFile.listFiles();
            for (int i = 0; i < file.length; i++) {
                String path = file[i].getAbsolutePath();
                if (path.indexOf(".xls") > 0) {
                    if (headColumns == null) {
                        this.createHeader(excelSheet, path); //如果此处不需要，不能直接注释，里面涉及到一些endMerGerColumnIndex、headColumns的赋值逻辑
                    }
                    System.out.println("正在读入第" + i + "个文件:" + path + "----请稍等");
                    try {
                        this.readSingleExcel(excelSheet, path);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    /*****
     * 读取单个文件
     * 
     * @param excelSheet
     * @param singFilePath
     */
    public void readSingleExcel(WritableSheet excelSheet, String singFilePath) {
        File inputWorkbook = new File(singFilePath);
        Workbook w = null;
        try {
            w = Workbook.getWorkbook(inputWorkbook);
// Get the first sheet
            Sheet sheet = w.getSheet(0);
// Loop over first 10 column and lines
            mergerRowBeginIndex = excelSheet.getRows();
            for (int i = beginMergerRow; i < sheet.getRows(); i++) {
                for (int j = beginMergerColumnIndex; j < endMerGerColumnIndex; j++) {
                    Cell cell = sheet.getCell(j, i);
                    String cell_value = cell.getContents();
                    this.addLabel(excelSheet, j, mergerRowBeginIndex, cell_value);
                }
//this.addLabel(excelSheet, excelSheet.getColumns() - 1, mergerRowBeginIndex, inputWorkbook.getName());
                mergerRowBeginIndex++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (w != null) {
                w.close();
            }
        }
    }


    /*****
     * 创建表头
     * 
     * @param excelSheet
     * @param firstFilePath
     */
    private void createHeader(WritableSheet excelSheet, String firstFilePath) {
        int column = 0;
        try {
            this.readFirstFileGetHeaders(firstFilePath);
/*for (int i = beginMergerColumnIndex; i < endMerGerColumnIndex; i++) {
this.addLabel(excelSheet, column++, 0, columns.get(i));
}*/
            for (int i = 0; i < beginMergerRow; i++) {
                for (int j = beginMergerColumnIndex; j < endMerGerColumnIndex; j++) {
                    this.addLabel(excelSheet, column++, i, headColumns[i][j]);
                }
                column = 0;
            }
//this.addLabel(excelSheet, excelSheet.getColumns() - 1, 0, "来源文件名称");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*****
     * 读取单个文件获取文件的表头信息
     * 
     * @param filePath
     */
    private void readFirstFileGetHeaders(String filePath) {
        File inputWorkbook = new File(filePath);
        Workbook w = null;
        try {
            w = Workbook.getWorkbook(inputWorkbook);
// Get the first sheet
            Sheet sheet = w.getSheet(0);
// Loop over first 10 column and lines
/*columns = new ArrayList<String>();
for (int i = 0; i < sheet.getRows(); i++) {
for (int j = 0; j < sheet.getColumns(); j++) {
Cell cell = sheet.getCell(j, i);
String cell_value = cell.getContents();
columns.add(cell_value);
}


endMerGerColumnIndex = sheet.getColumns();
break;
}*/
            endMerGerColumnIndex = sheet.getColumns();
            headColumns = new String[beginMergerRow][endMerGerColumnIndex];
            for (int i = 0; i < beginMergerRow; i++) {
                for (int j = 0; j < sheet.getColumns(); j++) {
                    Cell cell = sheet.getCell(j, i);
                    String cell_value = cell.getContents();
                    headColumns[i][j] = cell_value;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (w != null) {
                w.close();
            }
        }
    }


    /*****
     * 添加信息到excel中
     * 
     * @param sheet
     * @param column
     * @param row
     * @param s
     * @throws WriteException
     * @throws RowsExceededException
     */
    private void addLabel(WritableSheet sheet, int column, int row, String s)
            throws WriteException, RowsExceededException {
        Label label;
// Lets create a times font
        WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
// Define the cell format
        times = new WritableCellFormat(times10pt);
        label = new Label(column, row, s, times);
        sheet.addCell(label);
    }


    public static void main(String[] args) throws Exception {
        System.out.println("*********merger begin*********");

            CombainExcelForWin mergerExcel = new CombainExcelForWin("D:\\pythonData\\剩下两篇NCCN.xls", "D:\\pythonData\\1\\",
                    1);
            String inf = mergerExcel.mergerExcel();
            System.out.println("------------第1批次文件" + inf + "------------");
        System.out.println("*********merger end*********");
    }
}
