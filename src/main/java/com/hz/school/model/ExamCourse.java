package com.hz.school.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 考试科目
 */
@Entity
@Table(name = Constant.DB_PREFIX+"exam_course")
public class ExamCourse extends BaseEntity{
    /**
     * 科目
     */
    private String course;
    /**
     * 考试信息
     */
    @ManyToOne
    private ExamInfo examInfo;

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public ExamInfo getExamInfo() {
        return examInfo;
    }

    public void setExamInfo(ExamInfo examInfo) {
        this.examInfo = examInfo;
    }
}
