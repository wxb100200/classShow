package com.hz.school.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * 考试信息表
 */
@Entity
@Table(name = Constant.DB_PREFIX+"exam_info")
public class ExamInfo extends BaseEntity{
    /**
     * 考试名称
     */
    private String examTitle;
    /**
     * 微校通考试id
     */
    private Long examid;
    /**
     * 考试时间
     */
    private Long longExamTime;
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
    private List<ExamCourse> courseList;

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

    public Long getLongExamTime() {
        return longExamTime;
    }

    public void setLongExamTime(Long longExamTime) {
        this.longExamTime = longExamTime;
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

    public List<ExamCourse> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<ExamCourse> courseList) {
        this.courseList = courseList;
    }
}
