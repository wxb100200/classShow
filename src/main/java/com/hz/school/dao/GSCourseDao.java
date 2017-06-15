package com.hz.school.dao;

import com.avaje.ebean.Ebean;
import com.hz.school.model.Course;
import com.hz.school.util.ExcelUtil;
import com.hz.school.util.Logger;
import com.hz.school.util.StringUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 工商课表dao
 * 每次导入课表数据时，截取表sz_course_plan，从新导入数据，原数据将会被删除
 *
 * truncate table sz_course_plan;
 */
public class GSCourseDao {
    private static Logger log=Logger.getLogger(GSCourseDao.class);

    public static void parseSheet(Sheet sheet, int startRow, int startColumn, int endColumn){
        try{
            Ebean.beginTransaction();
            Ebean.createSqlQuery("truncate table sz_course_plan").findUnique();
            Ebean.commitTransaction();
        }catch (Exception e){
            Ebean.rollbackTransaction();
        }finally {
            Ebean.endTransaction();
        }
        Row row=null;
        Iterator<Row> rows=sheet.rowIterator();
        while (rows.hasNext()){
            row=rows.next();
            if(row.getRowNum() >= startRow){
                parseOneRow(row,startColumn,endColumn);
            }
        }
    }
    private static void parseOneRow(Row row, int startColumn, int endColumn){
        String rname="";
        List<Course> courseList=new ArrayList<Course>();
        for(int i=startColumn;i<endColumn;i++){
            Cell cell=row.getCell(i);
            Object obj= ExcelUtil.readCellValue(cell);
            String str="";
            if(obj!=null){
                str=obj.toString().replaceAll("\\s*","");
            }
            log.info("--->>>cell value is:"+str);
            if(i==startColumn){
                rname=parseRname(str);
            }else{
                List<Course> courses=generateCourseList(str,rname,i);
                courseList.addAll(courses);
            }
        }
        //保存课表
        try {
            Ebean.beginTransaction();
            Ebean.save(courseList);
            Ebean.commitTransaction();
        }catch (Exception e){
            Ebean.rollbackTransaction();
        }finally {
            Ebean.endTransaction();
        }
    }
    private static String parseRname(String str){
        String[] strs=str.split("/");
        String rname=strs[0];
        if(!StringUtil.isEmpty(rname)){
            return rname;
        }else {
            log.error("-------------------------------------------》》》》>>>存在班级号为空的情况");
            return "";
        }
    }

    public static void main(String[] args){
        generateCourseList("素描基础入门（2-1）◇3节/周2-16(9,10节)◇其他课程◇司舵[30人]","wang",1);
    }
    private static List<Course> generateCourseList(String str,String rname,int i){
        List<Course> courseList=new ArrayList<Course>();
        String weeks=generateWeeks(str);
        log.info("--->>>weeks:"+weeks);
        String[] classs=generateClassz(i);
        int week=getWeek(i);
        for(String classz:classs){
            Course course = new Course();
            course.setRname(rname);
            course.setWeeks(weeks);
            course.setWeek(week);
            course.setClassz(Integer.parseInt(classz));
            course.setCname(str);
            course.setCteacher("0");
            course.setCschool("0");
            courseList.add(course);
        }
        return courseList;

    }
    private static String generateWeeks(String strDemo){
        StringBuilder result= new StringBuilder();
        String[] demos=strDemo.split("人]");
        if(demos.length==0) return null;
        for(String demo:demos){
            if(StringUtil.isEmpty(demo)) continue;
            String[] strs=demo.split("◇");
            if(strs.length>=1 && !StringUtil.isEmpty(strs[1])){
                String str=strs[1];
                log.info("--->>>>>>====str:"+str);
                result.append(generateWeeksStr(str));
            }
        }
        return result.toString();
    }
    private static String generateWeeksStr(String str){
        StringBuilder result= new StringBuilder();
        if(!str.contains("-"))return result.toString();
        String[] ss=str.split("-");
        String oneStr=ss[0].replaceAll(".*[^\\d](?=(\\d+))","");
        int oneNum=Integer.parseInt(oneStr);
        String twoStr=ss[1].split("[\\D+]")[0];
        int twoNum=Integer.parseInt(twoStr);
        if(str.contains("单") || str.contains("双")){
            for(;oneNum<=twoNum;oneNum=oneNum+2){
                result.append(",").append(oneNum);
            }
        }else{
            for(;oneNum<=twoNum;oneNum++){
                result.append(",").append(oneNum);
            }
        }
        return result.toString();
    }
    private static String[] generateClassz(int i){
        if(i%6!=0){
            int j=i%6;
            String one=(2*j-1)+"";
            String two=(2*j)+"";
            String[] class2={one,two};
            return class2;
        }else{
            String[] class3={11+"",12+""};
            return class3;
        }
    }
    private static int getWeek(int i){
        if(i>0&&i<=6){
            return 1;
        }else if(i>6 && i<=12){
            return 2;
        }else if(i>12 && i<=18){
            return 3;
        }else if(i>18 && i<=24){
            return 4;
        }else if(i>24 && i<=30){
            return 5;
        }else if(i>30 && i<=36){
            return 6;
        }else{
            return 7;
        }
    }

}
