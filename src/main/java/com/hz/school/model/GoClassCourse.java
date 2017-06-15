package com.hz.school.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * 走班课表
 */
@Entity
@Table(name = Constant.DB_PREFIX+"go_class_course")
public class GoClassCourse  extends BaseEntity{
    /**
     * 第几节课
     */
    private Integer classNum;
    /**
     * 班级id
     */
    private String classid;
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 课程id
     */
    private Long courseid;
    /**
     * 教师姓名
     */
    private String teacherName;
    /**
     * 教师id
     */
    private Long teacherid;
    /**
     * 时段
     * 1：上午；2：下午
     */
    @Column(length = 1)
    private Integer timeInterval;
    /**
     * 班级类型
     * 1：基础班；2：走班
     */
    @Column(length = 1)
    private Integer type;
    /**
     * 周几
     */
    private Integer weekday;

    /**
     * 关联的学生
     */
    @OneToMany(mappedBy = "goClassCourse")
    private List<GoClassStudent> goClassStudents;

    public Integer getClassNum() {
        return classNum;
    }

    public void setClassNum(Integer classNum) {
        this.classNum = classNum;
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Long getCourseid() {
        return courseid;
    }

    public void setCourseid(Long courseid) {
        this.courseid = courseid;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Long getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(Long teacherid) {
        this.teacherid = teacherid;
    }

    public Integer getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(Integer timeInterval) {
        this.timeInterval = timeInterval;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getWeekday() {
        return weekday;
    }

    public void setWeekday(Integer weekday) {
        this.weekday = weekday;
    }

    public List<GoClassStudent> getGoClassStudents() {
        return goClassStudents;
    }

    public void setGoClassStudents(List<GoClassStudent> goClassStudents) {
        this.goClassStudents = goClassStudents;
    }
}
