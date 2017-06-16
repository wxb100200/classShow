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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            log.error("----->>>>>>>>保存一行记录错误",e);
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

    private static List<Course> generateCourseList(String str,String rname,int i){
        List<Course> courseList=new ArrayList<Course>();
        String weeks=generateWeeks(str);
        log.info("--->>>rname:"+rname+",weeks:"+weeks);
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
//        StringBuilder result= new StringBuilder();
        String result=",";
        String[] demos=strDemo.split("人]");
        if(demos.length==0) return null;
        for(String demo:demos){
            if(StringUtil.isEmpty(demo)) continue;

            String regEx="◇[^◇]*◇";
            Pattern pattern = Pattern.compile(regEx);
            Matcher m=pattern.matcher(demo);
            while (m.find()){
                String ss=m.group();
                result+=generateSS(ss);
//                result.append(generateSS(ss));
            }
        }
        return result;
    }
    private static String generateSS(String str){
        StringBuilder result= new StringBuilder();
        String regEx="\\d+-\\d+";
        Pattern pattern = Pattern.compile(regEx);
        Matcher m=pattern.matcher(str);
        while (m.find()){
            String numStr=generateNumStr(str,m.group());
            result.append(numStr);
        }
        return result.toString();
    }
    private static String generateNumStr(String str,String numStr){
        StringBuilder result= new StringBuilder();
        String[] numArray=numStr.split("-");
        int oneNum=Integer.parseInt(numArray[0]);
        int twoNum=Integer.parseInt(numArray[1]);
        /*for(;oneNum<=twoNum;oneNum++){
            result.append(oneNum).append(",");
        }*/
        if(str.contains("单") || str.contains("双")){
            for(;oneNum<=twoNum;oneNum=oneNum+2){
                result.append(oneNum).append(",");
            }
        }else{
            for(;oneNum<=twoNum;oneNum++){
                result.append(oneNum).append(",");
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
