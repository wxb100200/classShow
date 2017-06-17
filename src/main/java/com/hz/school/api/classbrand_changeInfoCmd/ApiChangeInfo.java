package com.hz.school.api.classbrand_changeInfoCmd;

import com.hz.school.api.ApiEntity;

import javax.persistence.Entity;

/**
 * Created by Administrator on 2017/6/17.
 */
@Entity
public class ApiChangeInfo extends ApiEntity{
    /**
     * 学校代码
     */
    private String campuscode;
    /**
     * 微校通侧班级id,32位字符串
     */
    private String classid;
    /**
     * 接口方法
     */
    private String apiMethod;

    public String getCampuscode() {
        return campuscode;
    }

    public void setCampuscode(String campuscode) {
        this.campuscode = campuscode;
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public String getApiMethod() {
        return apiMethod;
    }

    public void setApiMethod(String apiMethod) {
        this.apiMethod = apiMethod;
    }
}
