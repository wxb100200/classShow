package com.hz.school.api.classbrand_getClassStuAttendanceList;

import com.hz.school.api.ApiEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by Administrator on 2017/6/17.
 */
@Entity
public class ApiStudentAttendance extends ApiEntity{
    /**
     * 学生id
     */
    private String stuid;

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
     * 学生出勤信息列表
     */
    @OneToMany(mappedBy = "attendance")
    private List<ApiStudentAtt> attList;

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

    public List<ApiStudentAtt> getAttList() {
        return attList;
    }

    public void setAttList(List<ApiStudentAtt> attList) {
        this.attList = attList;
    }
}
