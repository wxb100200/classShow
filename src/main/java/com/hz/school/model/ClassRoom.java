package com.hz.school.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * 班级
 */
@Entity
@Table(name = Constant.DB_PREFIX+"class_room")
public class ClassRoom extends BaseEntity{
    /**
     * 班级id
     */
    private String classid;

    private Long campusid;
    /**
     * 班级口号
     */
    private String classInfo;
    /**
     * 班级名称
     */
    private String className;
    /**
     * 教室id
     */
    private String classRoomid;
    /**
     * 教室名称
     */
    private String classRoomName;
    /**
     * 班主任id
     */
    private Long headmasterId;
    /**
     * 班主任姓名
     */
    private String headmasterName;
    /**
     * 班级时间
     */
    @OneToMany(mappedBy = "classRoom")
    private List<ClassTime> classTimeList;

    /**
     * 班委信息
     */
    @OneToMany(mappedBy = "classRoom")
    private List<Committee> committee;

    @ManyToOne
    private Xqbm xqbm;


    /**
     * 学生卡信息
     */
    @OneToMany(mappedBy = "classRoom")
    private List<CardInfo> stuInfo;

    /**
     * 教师信息
     */
    @OneToMany(mappedBy = "classRoom")
    private List<Teacher> teacherInfo;

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public Long getCampusid() {
        return campusid;
    }

    public void setCampusid(Long campusid) {
        this.campusid = campusid;
    }

    public String getClassInfo() {
        return classInfo;
    }

    public void setClassInfo(String classInfo) {
        this.classInfo = classInfo;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassRoomid() {
        return classRoomid;
    }

    public void setClassRoomid(String classRoomid) {
        this.classRoomid = classRoomid;
    }

    public String getClassRoomName() {
        return classRoomName;
    }

    public void setClassRoomName(String classRoomName) {
        this.classRoomName = classRoomName;
    }

    public Long getHeadmasterId() {
        return headmasterId;
    }

    public void setHeadmasterId(Long headmasterId) {
        this.headmasterId = headmasterId;
    }

    public String getHeadmasterName() {
        return headmasterName;
    }

    public void setHeadmasterName(String headmasterName) {
        this.headmasterName = headmasterName;
    }

    public List<ClassTime> getClassTimeList() {
        return classTimeList;
    }

    public void setClassTimeList(List<ClassTime> classTimeList) {
        this.classTimeList = classTimeList;
    }

    public List<Committee> getCommittee() {
        return committee;
    }

    public void setCommittee(List<Committee> committee) {
        this.committee = committee;
    }

    public Xqbm getXqbm() {
        return xqbm;
    }

    public void setXqbm(Xqbm xqbm) {
        this.xqbm = xqbm;
    }

    public List<CardInfo> getStuInfo() {
        return stuInfo;
    }

    public void setStuInfo(List<CardInfo> stuInfo) {
        this.stuInfo = stuInfo;
    }

    public List<Teacher> getTeacherInfo() {
        return teacherInfo;
    }

    public void setTeacherInfo(List<Teacher> teacherInfo) {
        this.teacherInfo = teacherInfo;
    }
}
