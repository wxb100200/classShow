package com.hz.school.model;

import javax.persistence.*;

/**
 * 课表类
 */
@Entity
@Table(name ="sz_course_plan")
public class Course {
    @Id
    @GeneratedValue
    private Long id;
    @Column(length = 11)
    private int rid;
    /**
     * 班级号
     */
    @Column(length = 50)
    private String rname;
    /**
     * 周情况
     * 那些周有课
     */
    private String weeks;
    /**
     * 星期几
     * 1-星期一下，2-星期二，3-星期三，..7-星期日
     */
    @Column(length = 11)
    private int week;
    /**
     * 第几节课
     * 1-12节课
     */
    @Column(name = "class",length = 11)
    private int classz;
    /**
     * cell里面存储的全部内容
     */
    private String cname;
    /**
     * 暂时都放0
     */
    @Column(length = 50)
    private String cteacher;
    /**
     * 暂时都放0
     */
    @Column(length = 50)
    private String cschool;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getWeeks() {
        return weeks;
    }

    public void setWeeks(String weeks) {
        this.weeks = weeks;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getClassz() {
        return classz;
    }

    public void setClassz(int classz) {
        this.classz = classz;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCteacher() {
        return cteacher;
    }

    public void setCteacher(String cteacher) {
        this.cteacher = cteacher;
    }

    public String getCschool() {
        return cschool;
    }

    public void setCschool(String cschool) {
        this.cschool = cschool;
    }
}
