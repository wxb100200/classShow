package com.hz.school.api.classbrand_getClassAttendanceInfo;

import com.hz.school.api.ApiEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by Administrator on 2017/6/17.
 */
@Entity
public class ApiClassAttList extends ApiEntity{
    /**
     * 设置代码
     */
    private String configcode;
    /**
     * 开始时间.
     */
    private String configvalue;
    /**
     * 考勤名称
     */
    private String configname;
    /**
     * 班级考勤
     */
    @ManyToOne
    private ApiClassAttendance classAttendance;

    public String getConfigcode() {
        return configcode;
    }

    public void setConfigcode(String configcode) {
        this.configcode = configcode;
    }

    public String getConfigvalue() {
        return configvalue;
    }

    public void setConfigvalue(String configvalue) {
        this.configvalue = configvalue;
    }

    public String getConfigname() {
        return configname;
    }

    public void setConfigname(String configname) {
        this.configname = configname;
    }

    public ApiClassAttendance getClassAttendance() {
        return classAttendance;
    }

    public void setClassAttendance(ApiClassAttendance classAttendance) {
        this.classAttendance = classAttendance;
    }
}
