package com.hz.school.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class ExcelUtil {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 字符
    private static DecimalFormat df = new DecimalFormat("0");// 格式化 number String  
    private static DecimalFormat nf = new DecimalFormat("0.00");// 格式化数字  

    /**
     *
     * @param excelFile
     * @param isXlsxExcel  true:xlsx 格式, false: xls 格式
     * @return
     * @throws Exception
     */
    public static Workbook openExcel(InputStream excelFile, boolean isXlsxExcel) throws Exception {
        Workbook wb;
        if(isXlsxExcel){
            wb = new XSSFWorkbook(excelFile);
        }else{
            wb = new HSSFWorkbook(excelFile);
        }
        return wb;
    }

    /**
     * 读取Cell的值
     * @param cell
     * @return
     */
    public static Object readCellValue(Cell cell){
        if(cell==null) return null;

        Object value = null;
        switch(cell.getCellType()){
            case Cell.CELL_TYPE_STRING: value = cell.getRichStringCellValue().getString();break;
            case Cell.CELL_TYPE_BOOLEAN: value = cell.getBooleanCellValue();break;
            case Cell.CELL_TYPE_FORMULA:

                switch(cell.getCachedFormulaResultType()){
                    case Cell.CELL_TYPE_BLANK: break;
                    case Cell.CELL_TYPE_ERROR: break;
                    case Cell.CELL_TYPE_STRING: value = cell.getRichStringCellValue().getString();break;
                    case Cell.CELL_TYPE_BOOLEAN: value = cell.getBooleanCellValue();break;
                    case Cell.CELL_TYPE_NUMERIC:
                        if(org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
                            value = cell.getDateCellValue();
                        }else{
                            value = cell.getNumericCellValue(); break;
                        }
                        break;
                    default :
                        value = cell.getStringCellValue();
                        break;
                }
                break;
            case Cell.CELL_TYPE_BLANK: break;
            case Cell.CELL_TYPE_ERROR: break;
            case Cell.CELL_TYPE_NUMERIC:
                if(org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
                    value = cell.getDateCellValue();
                }else{
                    value = cell.getNumericCellValue(); break;
                }
                break;
            default :
                value = cell.getStringCellValue();
                break;
        }
        return value;
    }

    public static Workbook getWorkbook(File file) throws Exception {
        String fileName = file.getName();
        String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName
                .substring(fileName.lastIndexOf(".") + 1);
        FileInputStream fis = new FileInputStream(file);
        // 根据不同的文件名返回不同类型的WorkBook  
        if ("xls".equalsIgnoreCase(extension)) {
            return new HSSFWorkbook(fis);
        } else if ("xlsx".equalsIgnoreCase(extension)) {
            return new XSSFWorkbook(fis);
        } else {
            throw new Exception("不支持该格式的文件！");
        }
    }
    /**
     * 读取excel 文件 
     * @param file
     * @param sheetIndex
     * @param startRow
     * @return
     */
    public static List<List<Object>> readExcel(File file,int sheetIndex,int startRow) {
        List<List<Object>> list = new LinkedList<List<Object>>();
        Workbook wb = null;
        try {
            wb = getWorkbook(file);
            Sheet sheet = wb.getSheetAt(sheetIndex);
            Object value = null;
            Row row = null;
            Cell cell = null;
            CellStyle cs = null;
            String csStr = null;
            Double numval = null;
            Iterator<Row> rows = sheet.rowIterator();
            while (rows.hasNext()) {
                row = (Row) rows.next();
                if(row.getRowNum() >= startRow){
                    List<Object> cellList = new LinkedList<Object>();;
                    Iterator<Cell> cells = row.cellIterator();
                    while (cells.hasNext()) {
                        cell = (Cell) cells.next();
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_NUMERIC:
                                cs = cell.getCellStyle();
                                csStr = cs.getDataFormatString();
                                numval = cell.getNumericCellValue();
                                if ("@".equals(csStr)) {
                                    value = df.format(numval);
                                } else if ("General".equals(csStr)) {
                                    value = nf.format(numval);
                                } else {
                                    value = sdf.format(DateUtil.getJavaDate(numval));
                                }
                                break;
                            case Cell.CELL_TYPE_STRING:
                                value = cell.getStringCellValue();
                                break;
                            case Cell.CELL_TYPE_FORMULA:
                                if (!cell.getStringCellValue().equals("")) {
                                    value = cell.getStringCellValue();
                                } else {
                                    value = cell.getNumericCellValue() + "";
                                }
                                break;
                            case Cell.CELL_TYPE_BLANK:
                                value = "";
                                break;
                            case Cell.CELL_TYPE_BOOLEAN:
                                value = cell.getBooleanCellValue();
                                break;
                            default:
                                value = cell.toString();
                        }
                        cellList.add(value);
                    }
                    list.add(cellList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    /**
     * 空行会被自动忽略
     * @param sheet
     * @param startRow
     * @param startColumn
     * @param endColumn
     * @return
     */
    public static List<List<Object>> readExcel(Sheet sheet, int startRow, int startColumn, int endColumn) {
        List<List<Object>> list = new LinkedList<List<Object>>();
        try {
            Row row = null;
            Cell cell = null;
            Iterator<Row> rows = sheet.rowIterator();
            while (rows.hasNext()) {
                row = rows.next();
                if(row.getRowNum() >= startRow){
                    List<Object> cellList = new LinkedList<Object>();
                    for(int i=startColumn; i<endColumn; i++){
                        cell = row.getCell(i);
                        if(cell==null){
                            cellList.add("");
                        }else{
                            cellList.add(readCellValue(cell));
                        }
                    }
                    list.add(cellList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 读取excel 文件 
     * @param file
     * @param startSheet
     * @param startRow
     * @return
     */
    public static List<List<String>> readExcelStrings(File file,int startSheet,int startRow) {
        List<List<String>> list = new LinkedList<List<String>>();
        Workbook wb = null;
        try {
            wb = getWorkbook(file);
            Sheet sheet = wb.getSheetAt(startSheet);
            String value = null;
            Row row = null;
            Cell cell = null;
            CellStyle cs = null;
            String csStr = null;
            Double numval = null;
            Iterator<Row> rows = sheet.rowIterator();
            while (rows.hasNext()) {
                row = (Row) rows.next();
                if(row.getRowNum() >= startRow){
                    List<String> cellList = new LinkedList<String>();;
                    Iterator<Cell> cells = row.cellIterator();
                    while (cells.hasNext()) {
                        cell = cells.next();
                        value = readCellString(cell);
                        cellList.add(value+"");
                    }
                    list.add(cellList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private static String readCellString(Cell cell) {
        CellStyle cs;
        String csStr;
        Double numval;
        String value;
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:
                cs = cell.getCellStyle();
                csStr = cs.getDataFormatString();
                numval = cell.getNumericCellValue();
                if ("@".equals(csStr)) {
                    value = df.format(numval);
                } else if ("General".equals(csStr)) {
                    value = nf.format(numval);
                } else {
                    value = sdf.format(DateUtil.getJavaDate(numval));
                }
                break;
            case Cell.CELL_TYPE_STRING:
                value = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_FORMULA:
                switch(cell.getCachedFormulaResultType()){

                    case Cell.CELL_TYPE_NUMERIC:
                        cs = cell.getCellStyle();
                        csStr = cs.getDataFormatString();
                        numval = cell.getNumericCellValue();
                        if ("@".equals(csStr)) {
                            value = df.format(numval);
                        } else if ("General".equals(csStr)) {
                            value = nf.format(numval);
                        } else {
                            value = sdf.format(DateUtil.getJavaDate(numval));
                        }
                        break;
                    case Cell.CELL_TYPE_STRING:
                        value = cell.getStringCellValue();
                        break;
                    case Cell.CELL_TYPE_BLANK:
                        value = "";
                        break;
                    case Cell.CELL_TYPE_BOOLEAN:
                        value = cell.getBooleanCellValue() ? "是" : "否";
                        break;
                    default:
                        value = cell.toString();
                }
                break;
            case Cell.CELL_TYPE_BLANK:
                value = "";
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                value = cell.getBooleanCellValue() ? "是" : "否";
                break;
            default:
                value = cell.toString();
        }
        return value;
    }

    private static void write() throws Exception {
        Workbook wb = new HSSFWorkbook(new FileInputStream("d:/sanWangTJB.xls"));
        //Workbook wb = new XSSFWorkbook();
        CreationHelper createHelper = wb.getCreationHelper();
//        Sheet sheet = wb.createSheet("new sheet");
        Sheet sheet = wb.getSheetAt(0);
        short MAX_ROWS = 10000;
        short MAX_COLUMNS = 200;
        
        CellStyle style = wb.createCellStyle();
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());

        for(short i=4; i<MAX_ROWS; i++ ){
            // Create a row and put some cells in it. Rows are 0 based.
            Row row = sheet.createRow(i);
            for(short j=0; j<MAX_COLUMNS; j++){
                // Create a cell and put a value in it.
                Cell cel = row.createCell(j);
                cel.setCellStyle(style);
                cel.setCellValue(
                        createHelper.createRichTextString("my string"));
            }
        }
        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("d:/workbook.xls");
        wb.write(fileOut);
        fileOut.close();
    }

    public static void main(String[] args) throws Exception {
    	System.out.println();
//    	File ff = new File(ExcelUtil.class.getResource("/2g.xls").toURI());
    	File ff = new File("/tmp/all.xlsx");
        System.out.println(readExcelStrings(ff, 1, 1));
    }

    public static void main2(String[] args) {
        File file = new File("/tmp/all.xlsx");
        List<List<Object>> list = readExcel(file, 1, 2);
        for (int i = 0; i < list.size(); i++) {
            List<Object> objects = list.get(i);
            for (int j = 0; j < objects.size(); j++) {
                System.out.print(objects.get(j)+"=====");
            }
            System.out.println();
        }
    }

    /**
     *
     * @param i 第一列为1 。
     * @return
     */
    public static String numberToAsc(int i){
        if(i<=0){
            return "";
        }
        char c = 'A';
        char d = (char) (c + i -1);
        return d+"";
    }

    /**
     * xls中 104列变成 'AZ'
     */
    public static String column2str(int i){
        if(i<=0){
            return "";
        }
        int a = i/26;
        int b = i%26;
        if(b == 0){
            b = 26;
            a = a - 1;
        }
        return numberToAsc(a) + numberToAsc(b);
    }

}
