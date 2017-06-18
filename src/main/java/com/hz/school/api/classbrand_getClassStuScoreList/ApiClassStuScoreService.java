package com.hz.school.api.classbrand_getClassStuScoreList;

import com.hz.school.model.ClassStudentScore;
import com.hz.school.model.StudentScore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/18.
 */
public class ApiClassStuScoreService {
    public static List<ApiClassStudentScore> generateList(List<ClassStudentScore> dataList){
        List<ApiClassStudentScore> apiList=new ArrayList<ApiClassStudentScore>();
        for(ClassStudentScore data:dataList){
            ApiClassStudentScore api=new ApiClassStudentScore();
            api.setExamTitle(data.getExamTitle());
            api.setExamid(data.getExamid());
            api.setRanking(data.getRanking());
            api.setTeacherComment(data.getTeacherComment());
            api.setStuid(data.getStuid());
            api.setClassid(data.getClassid());
            api.setScoreList(generateStudentScoreList(data.getScoreList()));
            apiList.add(api);
        }
        return apiList;
    }
    private static List<ApiStudentScore> generateStudentScoreList(List<StudentScore> dataList){
        List<ApiStudentScore> apiList=new ArrayList<ApiStudentScore>();
        for(StudentScore data:dataList){
            ApiStudentScore api=new ApiStudentScore();
            api.setCourseid(data.getCourseid());
            api.setCourse(data.getCourse());
            api.setScore(data.getCourse());
            apiList.add(api);
        }
        return apiList;
    }
}
