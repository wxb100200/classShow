package com.hz.school.api.classbrand_getBzrClassid;

import com.hz.school.api.ApiEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by Administrator on 2017/6/16.
 */
@Entity
public class ApiCommittee extends ApiEntity {
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
    private ApiClassRoom classRoom;

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

    public ApiClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ApiClassRoom classRoom) {
        this.classRoom = classRoom;
    }
}
