package com.hz.school.api.classbrand_getClassStuCardList;

import com.hz.school.model.CardInfo;
import com.hz.school.model.ClassRoom;
import com.hz.school.model.Teacher;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/18.
 */
public class ApiClassStuCardService {
    public static ApiClassCardInfo generateData(ClassRoom data){
        ApiClassCardInfo api=new ApiClassCardInfo();
        api.setStuInfo(generateStuInfoList(data.getStuInfo()));
        api.setTeacherInfo(generateTeacherInfoList(data.getTeacherInfo()));
        return api;
    }
    private static List<ApiCardInfo> generateStuInfoList(List<CardInfo> dataList){
        List<ApiCardInfo> apiList=new ArrayList<ApiCardInfo>();
        for(CardInfo data:dataList){
            ApiCardInfo api=new ApiCardInfo();
            BigDecimal balance=data.getBalance();
            if(balance!=null) {
                api.setBalance(balance.toString());
            }
            api.setCard(data.getCard());
            api.setStuid(data.getStuid());
            api.setStuname(data.getStuname());
            apiList.add(api);
        }
        return apiList;
    }
    private static List<ApiTeacher> generateTeacherInfoList(List<Teacher> dataList){
        List<ApiTeacher> apiList=new ArrayList<ApiTeacher>();
        for(Teacher data:dataList){
            ApiTeacher api=new ApiTeacher();
            api.setBalance(data.getBalance().toString());
            api.setCard(data.getCard());
            api.setIconpath(data.getIconpath());
            api.setTeacherid(data.getTeacherid());
            api.setTeacherName(data.getTeacherName());
            apiList.add(api);
        }
        return apiList;
    }
}
