package com.hz.school.api.classbrand_getBzrClassid;

import com.hz.school.api.ApiEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 */
@Entity
public class ApiClassRoom extends ApiEntity{
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
     * 教室名称
     */
    private String classRoomName;
    /**
     * 教室id
     */
    private String classRoomid;
    /**
     * 班级时间
     */
    @OneToMany(mappedBy = "classRoom")
    private List<ApiClassTime> classTimeList;
    /**
     * 班级id
     */
    private String classid;
    /**
     * 班委信息
     */
    @OneToMany(mappedBy = "classRoom")
    private List<ApiCommittee> committee;
    /**
     * 班主任id
     */
    private Long headmasterId;
    /**
     * 班主任姓名
     */
    private String headmasterName;

    @ManyToOne
    private ApiXqbm xqbm;

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

    public String getClassRoomName() {
        return classRoomName;
    }

    public void setClassRoomName(String classRoomName) {
        this.classRoomName = classRoomName;
    }

    public String getClassRoomid() {
        return classRoomid;
    }

    public void setClassRoomid(String classRoomid) {
        this.classRoomid = classRoomid;
    }

    public List<ApiClassTime> getClassTimeList() {
        return classTimeList;
    }

    public void setClassTimeList(List<ApiClassTime> classTimeList) {
        this.classTimeList = classTimeList;
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public List<ApiCommittee> getCommittee() {
        return committee;
    }

    public void setCommittee(List<ApiCommittee> committee) {
        this.committee = committee;
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

    public ApiXqbm getXqbm() {
        return xqbm;
    }

    public void setXqbm(ApiXqbm xqbm) {
        this.xqbm = xqbm;
    }
}
