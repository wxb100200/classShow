package com.hz.school.api.classbrand_getClassAttendanceInfo;

import com.hz.school.api.ApiEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by Administrator on 2017/6/17.
 */
@Entity
public class ApiClassAttendance extends ApiEntity{
    /**
     * 微校通唯一班级标识
     */
    private String classid;
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
     * 考勤年级迟到排名，迟到最多第一名
     */
    private Integer attLategradeRanking;
    /**
     * 考勤学校迟到排名，迟到最多第一名
     */
    private Integer attLateSchoolRanking;
    /**
     * 考勤年级早退排名，早退最多第一名
     */
    private Integer attTardyGradeRanking;
    /**
     * 考勤学校早退排名，早退最多第一名
     */
    private Integer attTardySchoolRanking;
    /**
     * 学校考勤时间设置
     */
    @OneToMany(mappedBy = "classAttendance")
    private List<ApiClassAttList> attTimeSet;

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

    public Integer getAttLategradeRanking() {
        return attLategradeRanking;
    }

    public void setAttLategradeRanking(Integer attLategradeRanking) {
        this.attLategradeRanking = attLategradeRanking;
    }

    public Integer getAttLateSchoolRanking() {
        return attLateSchoolRanking;
    }

    public void setAttLateSchoolRanking(Integer attLateSchoolRanking) {
        this.attLateSchoolRanking = attLateSchoolRanking;
    }

    public Integer getAttTardyGradeRanking() {
        return attTardyGradeRanking;
    }

    public void setAttTardyGradeRanking(Integer attTardyGradeRanking) {
        this.attTardyGradeRanking = attTardyGradeRanking;
    }

    public Integer getAttTardySchoolRanking() {
        return attTardySchoolRanking;
    }

    public void setAttTardySchoolRanking(Integer attTardySchoolRanking) {
        this.attTardySchoolRanking = attTardySchoolRanking;
    }

    public List<ApiClassAttList> getAttTimeSet() {
        return attTimeSet;
    }

    public void setAttTimeSet(List<ApiClassAttList> attTimeSet) {
        this.attTimeSet = attTimeSet;
    }
}
