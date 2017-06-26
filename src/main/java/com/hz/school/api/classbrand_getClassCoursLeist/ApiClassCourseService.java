package com.hz.school.api.classbrand_getClassCoursLeist;


import com.hz.school.model.CourseInfo;
import com.hz.school.model.GoClassCourse;
import com.hz.school.model.Teacher;
import com.hz.school.model.TotalCourse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/15.
 */
public class ApiClassCourseService {
    public static List<ApiClassCourse>  generateList(List<TotalCourse> totalCourseList, List<GoClassCourse> goClassCourseList){
        List<ApiClassCourse> apiClassCourseList =new ArrayList<ApiClassCourse>();
        
        for(TotalCourse totalCourse:totalCourseList){
            ApiClassCourse api=new ApiClassCourse();
            api.setClassNum(totalCourse.getClassNum());
            api.setClassid(totalCourse.getClassRoom().getClassid());
            api.setCourseName(totalCourse.getCourseName());
            CourseInfo courseInfo=totalCourse.getCourse();
            if(courseInfo!=null){
                api.setCourseid(courseInfo.getId());
            }
            api.setTeacherName(totalCourse.getTeacherName());
            Teacher teacher=totalCourse.getTeacher();
            if(teacher!=null){
                api.setTeacherid(teacher.getId());
            }
            api.setTimeInterval(totalCourse.getTimeInterval());
            api.setType(1);
            api.setWeekday(totalCourse.getWeekday());
            apiClassCourseList.add(api);
        }
        for(GoClassCourse goClassCourse:goClassCourseList){
            ApiClassCourse api =new ApiClassCourse();
            api.setClassNum(goClassCourse.getClassNum());
            api.setClassid(goClassCourse.getClassRoom().getClassid());
            api.setCourseName(goClassCourse.getCourseName());
            api.setCourseid(goClassCourse.getCourseid());
            api.setTeacherName(goClassCourse.getTeacherName());
            api.setTeacherid(goClassCourse.getTeacherid());
            api.setTimeInterval(goClassCourse.getTimeInterval());
            api.setType(2);
            api.setWeekday(goClassCourse.getWeekday());
            apiClassCourseList.add(api);
        }
        return apiClassCourseList;
    }
}
