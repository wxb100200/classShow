package com.hz.school.api.classbrand_getClassStuAttendanceList;

import com.hz.school.model.StudentAttendance;
import com.hz.school.model.StudentAttlist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/17.
 */
public class ApiStuAttendanceService {
    public static List<ApiStudentAttendance> generateList(List<StudentAttendance> attList){
        List<ApiStudentAttendance> apiList=new ArrayList<ApiStudentAttendance>();
        for(StudentAttendance att:attList){
            ApiStudentAttendance api=new ApiStudentAttendance();
            api.setStuid(att.getStuid());
            api.setClassid(att.getClassid());
            api.setAttCount(att.getAttCount());
            api.setAttLateCount(att.getAttLateCount());
            api.setAttTardyCount(att.getAttTardyCount());
            api.setAttList(generateAttList(att.getAttList()));
            apiList.add(api);
        }
        return apiList;
    }
    private static List<ApiStudentAtt> generateAttList(List<StudentAttlist> attList){
        List<ApiStudentAtt> apiList=new ArrayList<ApiStudentAtt>();
        for(StudentAttlist att:attList){
            ApiStudentAtt api=new ApiStudentAtt();
            api.setAttTime(att.getAttTime());
            api.setParentWord(att.getParentWord());
            apiList.add(api);
        }
        return apiList;
    }
}
