package com.hz.school.api.classbrand_getClassCoursLeist;


import com.hz.school.model.GoClassCourse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/15.
 */
public class ApiClassCourseService {
    public static List<ApiClassCourse>  generateList(List<GoClassCourse> goClassCourseList){
        List<ApiClassCourse> apiClassCourseList =new ArrayList<ApiClassCourse>();
        for(GoClassCourse goClassCourse:goClassCourseList){
            ApiClassCourse apiClassCourse =new ApiClassCourse();
            apiClassCourse.setClassNum(goClassCourse.getClassNum());
            apiClassCourse.setClassid(goClassCourse.getClassid());
            apiClassCourse.setCourseName(goClassCourse.getCourseName());
            apiClassCourse.setCourseid(goClassCourse.getCourseid());
            apiClassCourse.setTeacherName(goClassCourse.getTeacherName());
            apiClassCourse.setTeacherid(goClassCourse.getTeacherid());
            apiClassCourse.setTimeInterval(goClassCourse.getTimeInterval());
            apiClassCourse.setType(goClassCourse.getType());
            apiClassCourse.setWeekday(goClassCourse.getWeekday());
            apiClassCourseList.add(apiClassCourse);
        }
        return apiClassCourseList;
    }
}
