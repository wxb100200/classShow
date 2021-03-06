package com.hz.school.model;

import javax.persistence.*;
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
     * 周几
     */
    private Integer weekday;

    @ManyToOne
    private ClassRoom classRoom;

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

    public Integer getWeekday() {
        return weekday;
    }

    public void setWeekday(Integer weekday) {
        this.weekday = weekday;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }

    public List<GoClassStudent> getGoClassStudents() {
        return goClassStudents;
    }

    public void setGoClassStudents(List<GoClassStudent> goClassStudents) {
        this.goClassStudents = goClassStudents;
    }
}
