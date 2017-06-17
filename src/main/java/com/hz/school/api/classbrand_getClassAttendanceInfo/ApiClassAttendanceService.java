package com.hz.school.api.classbrand_getClassAttendanceInfo;

import com.hz.school.model.ClassAttendance;
import com.hz.school.model.ClassAttlist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/17.
 */
public class ApiClassAttendanceService {
    public static ApiClassAttendance generateData(ClassAttendance att){
        ApiClassAttendance api=new ApiClassAttendance();
        api.setClassid(att.getClassid());
        api.setAttCount(att.getAttCount());
        api.setAttLateCount(att.getAttLateCount());
        api.setAttTardyCount(att.getAttTardyCount());
        api.setAttLategradeRanking(att.getAttLategradeRanking());
        api.setAttLateSchoolRanking(att.getAttLateSchoolRanking());
        api.setAttTardyGradeRanking(att.getAttTardyGradeRanking());
        api.setAttTardySchoolRanking(att.getAttTardySchoolRanking());
        api.setAttTimeSet(generateAttTimeList(att.getAttTimeSet()));
        return api;
    }
    private static List<ApiClassAttList> generateAttTimeList(List<ClassAttlist> attList){
        List<ApiClassAttList> apiList=new ArrayList<ApiClassAttList>();
        for(ClassAttlist att:attList){
            ApiClassAttList api=new ApiClassAttList();
            api.setConfigcode(att.getConfigcode());
            api.setConfigvalue(att.getConfigvalue());
            api.setConfigname(att.getConfigname());
            apiList.add(api);
        }
        return apiList;
    }
}
