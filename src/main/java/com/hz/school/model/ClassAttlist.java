package com.hz.school.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 学校考勤时间设置
 */
@Entity
@Table(name = Constant.DB_PREFIX+"class_attlist")
public class ClassAttlist extends BaseEntity{
    /**
     * 设置代码
     */
    private String configcode;
    /**
     * 开始时间.
     */
    private String configvalue;
    /**
     * 考勤名称
     */
    private String configname;
    /**
     * 班级考勤
     */
    @ManyToOne
    private ClassAttendance classAttendance;

    public String getConfigcode() {
        return configcode;
    }

    public void setConfigcode(String configcode) {
        this.configcode = configcode;
    }

    public String getConfigvalue() {
        return configvalue;
    }

    public void setConfigvalue(String configvalue) {
        this.configvalue = configvalue;
    }

    public String getConfigname() {
        return configname;
    }

    public void setConfigname(String configname) {
        this.configname = configname;
    }

    public ClassAttendance getClassAttendance() {
        return classAttendance;
    }

    public void setClassAttendance(ClassAttendance classAttendance) {
        this.classAttendance = classAttendance;
    }
}
