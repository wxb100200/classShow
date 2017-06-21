package com.hz.school.dao.xuejun;

import com.avaje.ebean.Ebean;
import com.hz.school.model.ClassRoom;
import com.hz.school.model.GoClassCourse;
import com.hz.school.model.TotalCourse;
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
        totalCourse.setTeacherName(teacherName);
        totalCourse.setTimeInterval(timeInterval);
        totalCourse.setWeekday(weekday);
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
            case 6:return 1;
            case 7:return 2;
            case 8:return 3;
            default:return 4;
        }
    }

}
