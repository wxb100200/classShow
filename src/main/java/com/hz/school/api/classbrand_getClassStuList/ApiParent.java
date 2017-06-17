package com.hz.school.api.classbrand_getClassStuList;

import com.hz.school.api.ApiEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by Administrator on 2017/6/17.
 */
@Entity
public class ApiParent extends ApiEntity{
    /**
     * 不可以重复
     * 家长微信昵称或者姓名
     */
    private String parentName;
    /**
     * 手机号
     */
    private String phoneNum;
    /**
     * 关系
     */
    private String relation;
    /**
     * 与学生的关系
     * 1:爸爸，2:妈妈，3:爷爷，4:奶奶，5:外公，6:外婆，7:家长，8:阿姨
     */
    private Integer relationCode;

    /**
     * 学生
     */
    @ManyToOne
    private ApiStudent student;

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public Integer getRelationCode() {
        return relationCode;
    }

    public void setRelationCode(Integer relationCode) {
        this.relationCode = relationCode;
    }

    public ApiStudent getStudent() {
        return student;
    }

    public void setStudent(ApiStudent student) {
        this.student = student;
    }
}
