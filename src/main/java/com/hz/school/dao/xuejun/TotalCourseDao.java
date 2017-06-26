package com.hz.school.dao.xuejun;

import com.avaje.ebean.Ebean;
import com.hz.school.model.*;
import com.hz.school.util.EbeanUtil;
import com.hz.school.util.ExcelUtil;
import com.hz.school.util.Logger;
import com.hz.school.util.StringUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/21.
 */
public class TotalCourseDao {
    private static Logger log=Logger.getLogger(GoClassCourseDao.class);
    private static Map<String,ClassRoom> classRoomMap= (Map<String,ClassRoom>)EbeanUtil.find(ClassRoom.class).where().setMapKey("className").findMap();
    private static Map<String,CourseInfo> courseInfoMap=(Map<String,CourseInfo>)EbeanUtil.find(CourseInfo.class).where().setMapKey("courseName").findMap();
    private static Map<String,Teacher> teacherMap=(Map<String,Teacher>)EbeanUtil.find(Teacher.class).where().setMapKey("teacherName").findMap();
    public static void parseSheet(Sheet sheet, String sheetName,int startRow, int startColumn, int endColumn){
        Row row=null;
        Row row2=null;
        Iterator<Row> rows=sheet.rowIterator();
        while(rows.hasNext()){
            row=rows.next();
            if(row.getRowNum()>=startRow){
                rows.hasNext();
                row2=rows.next();
                parseOneRow(row,row2,sheetName,startColumn,endColumn);
            }
        }

    }
    private static void parseOneRow(Row row,Row row2,String sheetName,int startColumn,int endColumn){
        String className="";
        ClassRoom classRoom=new ClassRoom();
        List<TotalCourse> totalCourseList=new ArrayList<TotalCourse>();
        for(int i=startColumn;i<endColumn;i++){
            Cell cell=row.getCell(i);
            Object obj= ExcelUtil.readCellValue(cell);
            Cell cell2=row2.getCell(i);
            Object obj2= ExcelUtil.readCellValue(cell2);
            if(i==startColumn){
                className=obj.toString();
                classRoom=classRoomMap.get(className);
                continue;
            }
            if(obj==null){
                continue;
            }
            TotalCourse totalCourse=generateTotalCourse(sheetName,classRoom,obj,obj2,i);
            if(totalCourse!=null){
                totalCourseList.add(totalCourse);
            }
        }
        //保存走班课表
        try {
            Ebean.beginTransaction();
            Ebean.save(totalCourseList);
            Ebean.commitTransaction();
        }catch (Exception e){
            Ebean.rollbackTransaction();
        }finally {
            Ebean.endTransaction();
        }
    }
    private static TotalCourse generateTotalCourse(String sheetName,ClassRoom classRoom,Object obj1,Object obj2,int i){
        if(obj1==null)return null;
        int classNum=generateClassNum(i);
        String courseName=obj1.toString();
        if(StringUtil.isEmpty(courseName))return null;
        String teacherName="";
        if(obj2!=null){
            teacherName=obj2.toString();
        }
        int timeInterval=generateTimeInterval(i);
        int weekday=generateWeekday(i);
        log.info("---->>>generate data is classNum:"+classNum+",classid:"+classRoom.getClassid()+",courseName:"+courseName+",teacherName:"+teacherName+",timeInterval:"+timeInterval+",weekday:"+weekday);
        TotalCourse totalCourse=new TotalCourse();
        totalCourse.setClassNum(classNum);
        totalCourse.setClassRoom(classRoom);
        totalCourse.setCourseName(courseName);
        totalCourse.setCourse(courseInfoMap.get(courseName));
        totalCourse.setTeacherName(teacherName);
        totalCourse.setTeacher(teacherMap.get(teacherName));
        totalCourse.setTimeInterval(timeInterval);
        totalCourse.setWeekday(weekday);
        totalCourse.setNumWeek(generateNumWeek(sheetName));
        totalCourse.setWeekInfo(sheetName);
        return totalCourse;
    }

    private static int generateWeekday(int i){
        if(i>0&&i<=9){
            return 1;
        }else if(i>9 && i<=18){
            return 2;
        }else if(i>18 && i<=27){
            return 3;
        }else if(i>27 && i<=36){
            return 4;
        }else if(i>36 && i<=45){
            return 5;
        }else if(i>45 && i<=54){
            return 6;
        }else{
            return 7;
        }
    }
    private static int generateTimeInterval(int i){
        int index=i%9;
        if(index>0 && index<6){
            return 1;
        }else{
            return 2;
        }
    }
    private static int generateClassNum(int i){
        int index=i%9;
        switch (index){
            case 1:return 1;
            case 2:return 2;
            case 3:return 3;
            case 4:return 4;
            case 5:return 5;
            case 6:return 6;
            case 7:return 7;
            case 8:return 8;
            default:return 9;
        }
    }
    private static int generateNumWeek(String sheetName){
        String num=sheetName.substring(sheetName.indexOf("第")+1,sheetName.indexOf("周"));
        return chineseNumber2Int(num);
    }
    private static int chineseNumber2Int(String chineseNumber){
        int result = 0;
        int temp = 1;//存放一个单位的数字如：十万
        int count = 0;//判断是否有chArr
        char[] cnArr = new char[]{'一','二','三','四','五','六','七','八','九'};
        char[] chArr = new char[]{'十','百','千','万','亿'};
        for (int i = 0; i < chineseNumber.length(); i++) {
            boolean b = true;//判断是否是chArr
            char c = chineseNumber.charAt(i);
            for (int j = 0; j < cnArr.length; j++) {//非单位，即数字
                if (c == cnArr[j]) {
                    if(0 != count){//添加下一个单位之前，先把上一个单位值添加到结果中
                        result += temp;
                        temp = 1;
                        count = 0;
                    }
                    // 下标+1，就是对应的值
                    temp = j + 1;
                    b = false;
                    break;
                }
            }
            if(b){//单位{'十','百','千','万','亿'}
                for (int j = 0; j < chArr.length; j++) {
                    if (c == chArr[j]) {
                        switch (j) {
                            case 0:
                                temp *= 10;
                                break;
                            case 1:
                                temp *= 100;
                                break;
                            case 2:
                                temp *= 1000;
                                break;
                            case 3:
                                temp *= 10000;
                                break;
                            case 4:
                                temp *= 100000000;
                                break;
                            default:
                                break;
                        }
                        count++;
                    }
                }
            }
            if (i == chineseNumber.length() - 1) {//遍历到最后一个字符
                result += temp;
            }
        }
        return result;
    }
}
