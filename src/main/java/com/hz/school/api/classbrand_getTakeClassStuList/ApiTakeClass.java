package com.hz.school.api.classbrand_getTakeClassStuList;


import com.hz.school.api.ApiEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by Administrator on 2017/6/15.
 */
@Entity
public class ApiTakeClass extends ApiEntity {
    /**
     * 教室id
     */
    private Integer classid;
    /**
     * 学生照片地址
     */
    private String picpath;
    /**
     * 第几节课程
     */
    private Integer classNum;
    /**
     * 周几
     */
    private Integer weekday;
    /**
     * 学生
     */
    @OneToMany(mappedBy = "apiTakeClass")
    private List<ApiStudent> students;

    public Integer getClassid() {
        return classid;
    }

    public void setClassid(Integer classid) {
        this.classid = classid;
    }

    public String getPicpath() {
        return picpath;
    }

    public void setPicpath(String picpath) {
        this.picpath = picpath;
    }

    public Integer getClassNum() {
        return classNum;
    }

    public void setClassNum(Integer classNum) {
        this.classNum = classNum;
    }

    public Integer getWeekday() {
        return weekday;
    }

    public void setWeekday(Integer weekday) {
        this.weekday = weekday;
    }

    public List<ApiStudent> getStudents() {
        return students;
    }

    public void setStudents(List<ApiStudent> students) {
        this.students = students;
    }
}
