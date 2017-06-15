package com.hz.school.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * 学生考试分数
 */
@Entity
@Table(name = Constant.DB_PREFIX+"student_score")
public class StudentScore extends BaseEntity{
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
    @Column(scale = 1)
    private BigDecimal score;

    @ManyToOne
    private ClassStudentScore classStudentScore;

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

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public ClassStudentScore getClassStudentScore() {
        return classStudentScore;
    }

    public void setClassStudentScore(ClassStudentScore classStudentScore) {
        this.classStudentScore = classStudentScore;
    }
}
