package com.hz.school.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 每一节课对应的走班学生
 */
@Entity
@Table(name = Constant.DB_PREFIX+"go_class_student")
public class GoClassStudent  extends BaseEntity{
    /**
     * 走班课表
     */
    @ManyToOne
    private GoClassCourse goClassCourse;
    /**
     * 学生
     */
    @ManyToOne
    private Student student;

    //导入Excel存储的数据
    /**
     * 姓名
     */
    private String name;
    /**
     * 学号
     */
    private String poNumber;
    /**
     * 周几
     */
    private Integer week;
    /**
     * 节次
     */
    private Integer Num;
    /**
     * cell里面存储的内容
     */
    private String cell;

    public GoClassCourse getGoClassCourse() {
        return goClassCourse;
    }

    public void setGoClassCourse(GoClassCourse goClassCourse) {
        this.goClassCourse = goClassCourse;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getNum() {
        return Num;
    }

    public void setNum(Integer num) {
        Num = num;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }
}
