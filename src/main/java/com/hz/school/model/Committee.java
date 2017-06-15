package com.hz.school.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 班委信息表
 */
@Entity
@Table(name = Constant.DB_PREFIX+"committee")
public class Committee extends BaseEntity{
    /**
     * 班委代码
     */
    private String committeeCode;
    /**
     * 班委职务
     */
    private String committeeName;
    /**
     * 学生id
     */
    private Long  stuid;
    /**
     * 学生姓名
     */
    private String stuName;
    /**
     * 班级信息表
     */
    @ManyToOne
    private ClassRoom classRoom;

    @ManyToOne
    private Student student;

    public String getCommitteeCode() {
        return committeeCode;
    }

    public void setCommitteeCode(String committeeCode) {
        this.committeeCode = committeeCode;
    }

    public String getCommitteeName() {
        return committeeName;
    }

    public void setCommitteeName(String committeeName) {
        this.committeeName = committeeName;
    }

    public Long getStuid() {
        return stuid;
    }

    public void setStuid(Long stuid) {
        this.stuid = stuid;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
