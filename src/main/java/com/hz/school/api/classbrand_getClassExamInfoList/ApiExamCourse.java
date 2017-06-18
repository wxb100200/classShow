package com.hz.school.api.classbrand_getClassExamInfoList;

import com.hz.school.api.ApiEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by Administrator on 2017/6/18.
 */
@Entity
public class ApiExamCourse extends ApiEntity{
    /**
     * 科目
     */
    private String course;
    /**
     * 考试信息
     */
    @ManyToOne
    private ApiExamInfo examInfo;

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public ApiExamInfo getExamInfo() {
        return examInfo;
    }

    public void setExamInfo(ApiExamInfo examInfo) {
        this.examInfo = examInfo;
    }
}
