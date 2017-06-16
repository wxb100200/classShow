package com.hz.school.api.classbrand_getBzrClassid;

import com.hz.school.api.ApiEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by Administrator on 2017/6/16.
 */
@Entity
public class ApiClassTime extends ApiEntity{
    private Long  classNum;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String emdTime;
    /**
     * 班级
     */
    @ManyToOne
    private ApiClassRoom classRoom;

    public Long getClassNum() {
        return classNum;
    }

    public void setClassNum(Long classNum) {
        this.classNum = classNum;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEmdTime() {
        return emdTime;
    }

    public void setEmdTime(String emdTime) {
        this.emdTime = emdTime;
    }

    public ApiClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ApiClassRoom classRoom) {
        this.classRoom = classRoom;
    }
}
