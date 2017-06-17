package com.hz.school.api.classbrand_getClassStuAttendanceList;

import com.hz.school.api.ApiEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by Administrator on 2017/6/17.
 */
@Entity
public class ApiStudentAtt extends ApiEntity{
    /**
     * 出勤时间，上面日期的字符串标识
     */
    private String attTime;

    /**
     * 家长留言
     */
    private String parentWord;
    /**
     * 考勤表数据
     */
    @ManyToOne
    private ApiStudentAttendance attendance ;

    public String getAttTime() {
        return attTime;
    }

    public void setAttTime(String attTime) {
        this.attTime = attTime;
    }

    public String getParentWord() {
        return parentWord;
    }

    public void setParentWord(String parentWord) {
        this.parentWord = parentWord;
    }

    public ApiStudentAttendance getAttendance() {
        return attendance;
    }

    public void setAttendance(ApiStudentAttendance attendance) {
        this.attendance = attendance;
    }
}
