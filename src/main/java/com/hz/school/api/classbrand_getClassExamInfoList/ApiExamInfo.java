package com.hz.school.api.classbrand_getClassExamInfoList;

import com.hz.school.api.ApiEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by Administrator on 2017/6/18.
 */
@Entity
public class ApiExamInfo extends ApiEntity{
    /**
     * 考试名称
     */
    private String examTitle;
    /**
     * 微校通考试id
     */
    private Long examid;
    /**
     * 上面考试时间字符串表示
     */
    private String examTime;
    /**
     * 班级id
     */
    private Long classid;
    /**
     * 考试科目
     */
    @OneToMany(mappedBy = "examInfo")
    private List<ApiExamCourse> courseList;

    public String getExamTitle() {
        return examTitle;
    }

    public void setExamTitle(String examTitle) {
        this.examTitle = examTitle;
    }

    public Long getExamid() {
        return examid;
    }

    public void setExamid(Long examid) {
        this.examid = examid;
    }

    public String getExamTime() {
        return examTime;
    }

    public void setExamTime(String examTime) {
        this.examTime = examTime;
    }

    public Long getClassid() {
        return classid;
    }

    public void setClassid(Long classid) {
        this.classid = classid;
    }

    public List<ApiExamCourse> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<ApiExamCourse> courseList) {
        this.courseList = courseList;
    }
}
