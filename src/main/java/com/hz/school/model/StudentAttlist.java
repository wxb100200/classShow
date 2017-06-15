package com.hz.school.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 学生出勤信息列表
 */
@Entity
@Table(name = Constant.DB_PREFIX+"student_attlist")
public class StudentAttlist extends BaseEntity{
    /**
     * 出勤时间
     */
    private Long longAttTime;
    /**
     * 出勤时间，上面日期的字符串标识
     */
    private String attTime;
    /**
     * 家长留言
     */
    private String parentWord;
    /**
     * 考勤表数据
     */
    @ManyToOne
    private StudentAttendance studentAttendance;

    public Long getLongAttTime() {
        return longAttTime;
    }

    public void setLongAttTime(Long longAttTime) {
        this.longAttTime = longAttTime;
    }

    public String getAttTime() {
        return attTime;
    }

    public void setAttTime(String attTime) {
        this.attTime = attTime;
    }

    public String getParentWord() {
        return parentWord;
    }

    public void setParentWord(String parentWord) {
        this.parentWord = parentWord;
    }

    public StudentAttendance getStudentAttendance() {
        return studentAttendance;
    }

    public void setStudentAttendance(StudentAttendance studentAttendance) {
        this.studentAttendance = studentAttendance;
    }
}
