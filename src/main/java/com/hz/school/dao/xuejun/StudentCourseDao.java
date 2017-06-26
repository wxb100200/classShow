package com.hz.school.dao.xuejun;

import com.avaje.ebean.Ebean;
import com.hz.school.model.GoClassCourse;
import com.hz.school.model.GoClassStudent;
import com.hz.school.model.Student;
import com.hz.school.util.EbeanUtil;
import com.hz.school.util.ExcelUtil;
import com.hz.school.util.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/14.
 */
public class StudentCourseDao {
    private static Logger log=Logger.getLogger(GoClassCourseDao.class);
    private static Map<String,Student> studentMap=(Map<String,Student>) EbeanUtil.find(Student.class).where().setMapKey("poNumber").findMap();

    public static void parseSheet(Sheet sheet, int startRow, int startColumn, int endColumn){
        try{
            Ebean.beginTransaction();
            Ebean.createSqlQuery("truncate table sc_go_class_student").findUnique();
            Ebean.commitTransaction();
        }catch (Exception e){
            Ebean.rollbackTransaction();
        }finally {
            Ebean.endTransaction();
        }
        Row row=null;
        int rowNum=0;
        List<Row> rowList=new ArrayList<Row>();
        Iterator<Row> rows=sheet.rowIterator();
        while(rows.hasNext()){
            row=rows.next();
            rowNum=row.getRowNum();
            rowList.add(row);
            if((rowNum+1)%14==0){
                parseRowList(rowList,startColumn,endColumn);
                log.info("-------------------------->>>一个学生的课表了");
                rowList.clear();
            }
        }
    }
    private static void parseRowList(List<Row> rows,int startColumn,int endColumn){
        List<GoClassStudent> goClassStudentList=new ArrayList<GoClassStudent>();
        Student student=new Student();
        int rowNum=0;
        String name="";
        String poNumber="";
        for(Row row:rows) {
            rowNum=row.getRowNum();
            if(rowNum%14==0){
                name=generateStudentName(row.getCell(0));
                poNumber=generateStudentPoNumber(row.getCell(0));
                student=studentMap.get(poNumber);
                continue;
            }
            if(rowNum%14==1)continue;
            List<GoClassStudent> goClassStudents=parseOneRow(row,startColumn,endColumn,name,poNumber,student);
            if(!goClassStudents.isEmpty()) goClassStudentList.addAll(goClassStudents);
        }
        //保存数据
        try {
            Ebean.beginTransaction();
            Ebean.save(goClassStudentList);
            Ebean.commitTransaction();
        }catch (Exception e){
            Ebean.rollbackTransaction();
        }finally {
            Ebean.endTransaction();
        }

    }
    private static  List<GoClassStudent> parseOneRow(Row row,int startColumn,int endColumn,String name,String poNumber,Student student){
        List<GoClassStudent> goClassStudentList=new ArrayList<GoClassStudent>();
        int rowNum=row.getRowNum();
        int num=0;//第几节次
        for(int i=startColumn;i<endColumn;i++){
            Cell cell=row.getCell(i);
            Object obj= ExcelUtil.readCellValue(cell);
            if(obj==null)continue;
            if(i==startColumn){
                String str=obj.toString();
                num=Integer.parseInt(str.substring(0,str.indexOf(".")));
                continue;
            }
            GoClassStudent goClassStudent=generateGoClassStudent(obj,name,poNumber,num,i,student);
            if(goClassStudent!=null) goClassStudentList.add(goClassStudent);
        }
        return goClassStudentList;
    }
    private static GoClassStudent generateGoClassStudent(Object obj,String name,String poNumber,int num,int i,Student student){
        log.info("-------------->>>>>>>>>>>>>obj:"+obj.toString().replaceAll("\\s*","")+"name:"+name+",poNumber:"+poNumber+",num:"+num+",i:"+i);
        GoClassStudent goClassStudent=new GoClassStudent();
        goClassStudent.setGoClassCourse(generateGoClassCourse(obj,num,i));
        goClassStudent.setStudent(student);
        goClassStudent.setName(name);
        goClassStudent.setPoNumber(poNumber);
        goClassStudent.setWeek(i);
        goClassStudent.setNum(num);
        goClassStudent.setCell(obj.toString());
        return goClassStudent;
    }
    private static GoClassCourse generateGoClassCourse(Object obj,int num,int week){
        String str=obj.toString().replaceAll("\\s*","");
        String classRoomName=str.replaceAll(".*[^\\d](?=(\\d+))","");
        Integer classNum=num;
        Integer timeInterval=generateTimeInterval(num);
        GoClassCourse goClassCourse=EbeanUtil.find(GoClassCourse.class).where()
                .eq("classRoom.classRoomName",classRoomName).eq("weekday",week).eq("classNum",classNum).eq("timeInterval",timeInterval)
                .setMaxRows(1).findUnique();
        return goClassCourse;
    }
    private static int generateTimeInterval(int num){
        if(num>0 && num<6){
            return 1;
        }else{
            return 2;
        }
    }
    /*private static Integer generateClassNum(int num){
        switch (num){
            case 1:return 1;
            case 2:return 2;
            case 3:return 3;
            case 4:return 4;
            case 5:return 5;
            case 6:return 1;
            case 7:return 2;
            case 8:return 3;
            case 9:return 4;
            case 10:return 5;
            case 11:return 6;
            default:return 7;
        }
    }*/

    private static String generateStudentPoNumber(Cell cell){
        String str= cell.toString();
        return str.substring(str.lastIndexOf("：")+1);
    }
    private static String generateStudentName(Cell cell){
        String str= cell.toString();
        return str.substring(str.indexOf("：")+1,str.indexOf("学号"));
    }

}
