package com.hz.school.api.classbrand_getClassStuList;

import com.hz.school.model.Parent;
import com.hz.school.model.Student;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/17.
 */
public class ApiGetClassStuService {
    public static List<ApiStudent> generateList(List<Student> studentList){
        List<ApiStudent> apiList=new ArrayList<ApiStudent>();
        for(Student student:studentList){
            ApiStudent api=new ApiStudent();
            api.setId(student.getId());
            api.setAddress(student.getAddress());
            api.setCard(student.getCard());
            BigDecimal height=student.getHeight();
            if(height!=null){
                api.setHeight(height.toString());
            }
            api.setName(student.getName());
            api.setNation(student.getNation());
            api.setNationCode(student.getNationCode());
            api.setOrder(student.getOrder());
            api.setParentList(generateParentList(student.getParentList()));
            api.setPicpath(student.getPicpath());
            api.setPolitical(student.getPolitical());
            api.setSex(student.getSex());
            api.setSexCode(student.getSexCode());
            apiList.add(api);
        }
        return apiList;
    }
    private static List<ApiParent> generateParentList(List<Parent> parentList){
        List<ApiParent> apiList=new ArrayList<ApiParent>();
        for(Parent parent:parentList){
            ApiParent api=new ApiParent();
            api.setParentName(parent.getParentName());
            api.setPhoneNum(parent.getPhoneNum());
            api.setRelation(parent.getRelation());
            api.setRelationCode(parent.getRelationCode());
            apiList.add(api);
        }
        return apiList;
    }
}
