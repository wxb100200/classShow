package com.hz.school.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * 学生考勤表
 */
@Entity
@Table(name = Constant.DB_PREFIX+"student_attendance")
public class StudentAttendance extends BaseEntity{
    /**
     * 学生id
     */
    private String stuid;

    @ManyToOne
    private Student student;
    /**
     * 微校通唯一班级标识
     */
    private String classid;

    @ManyToOne
    private ClassRoom classRoom;
    /**
     * 出勤次数
     */
    private Integer attCount;
    /**
     * 迟到次数
     */
    private Integer attLateCount;
    /**
     * 早退次数
     */
    private Integer attTardyCount;
    /**
     * 学生出勤信息列表
     */
    @OneToMany(mappedBy = "studentAttendance")
    private List<StudentAttlist> attList;

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public Integer getAttCount() {
        return attCount;
    }

    public void setAttCount(Integer attCount) {
        this.attCount = attCount;
    }

    public Integer getAttLateCount() {
        return attLateCount;
    }

    public void setAttLateCount(Integer attLateCount) {
        this.attLateCount = attLateCount;
    }

    public Integer getAttTardyCount() {
        return attTardyCount;
    }

    public void setAttTardyCount(Integer attTardyCount) {
        this.attTardyCount = attTardyCount;
    }

    public List<StudentAttlist> getAttList() {
        return attList;
    }

    public void setAttList(List<StudentAttlist> attList) {
        this.attList = attList;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }
}
