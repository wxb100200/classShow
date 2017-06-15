package com.hz.school.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 班级基本信息表
 */
@Entity
@Table(name = Constant.DB_PREFIX+"baseInfo")
public class BaseInfo extends BaseEntity{
    /**
     * 班级
     */
    @ManyToOne
    private ClassRoom classRoom;

    private String classSelection;

    private String SN;

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }

    public String getClassSelection() {
        return classSelection;
    }

    public void setClassSelection(String classSelection) {
        this.classSelection = classSelection;
    }

    public String getSN() {
        return SN;
    }

    public void setSN(String SN) {
        this.SN = SN;
    }
}
