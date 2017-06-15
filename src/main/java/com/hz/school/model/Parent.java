package com.hz.school.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 父母关系表
 */
@Entity
@Table(name = Constant.DB_PREFIX+"parent")
public class Parent extends BaseEntity{
    /**
     * 不可以重复
     * 家长微信昵称或者姓名
     */
    private String parentName;
    /**
     * 与学生的关系
     * 1:爸爸，2:妈妈，3:爷爷，4:奶奶，5:外公，6:外婆，7:家长，8:阿姨
     */
    private Integer relationCode;
    /**
     * 关系
     */
    private String relation;
    /**
     * 手机号
     */
    private String phoneNum;
    /**
     * 学生
     */
    @ManyToOne
    private Student student;

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Integer getRelationCode() {
        return relationCode;
    }

    public void setRelationCode(Integer relationCode) {
        this.relationCode = relationCode;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
