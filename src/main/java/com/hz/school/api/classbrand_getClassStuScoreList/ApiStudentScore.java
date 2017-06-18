package com.hz.school.api.classbrand_getClassStuScoreList;

import com.hz.school.api.ApiEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by Administrator on 2017/6/18.
 */
@Entity
public class ApiStudentScore extends ApiEntity{
    /**
     * 课程id
     * 不可重复
     */
    private Long courseid;
    /**
     * 课程名称
     */
    private String course;
    /**
     * 科目分数
     */
    private String score;

    @ManyToOne
    private ApiClassStudentScore classStudentScore;

    public Long getCourseid() {
        return courseid;
    }

    public void setCourseid(Long courseid) {
        this.courseid = courseid;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public ApiClassStudentScore getClassStudentScore() {
        return classStudentScore;
    }

    public void setClassStudentScore(ApiClassStudentScore classStudentScore) {
        this.classStudentScore = classStudentScore;
    }
}
