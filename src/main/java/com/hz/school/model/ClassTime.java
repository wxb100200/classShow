package com.hz.school.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 班级
 */
@Entity
@Table(name = Constant.DB_PREFIX+"class_time")
public class ClassTime extends BaseEntity{
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
    private ClassRoom classRoom;

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

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }
}
