package com.hz.school.model;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * 学生信息表
 */
@Entity
@Table(name = Constant.DB_PREFIX+"student_bak")
public class Student_bak extends BaseEntity2 {
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     * 男、女
     */
    private String sex;
    /**
     * 性别代码
     */
    @Column(length = 1)
    private Integer sexCode;
    /**
     * 邮政编码
     */
    private String nationCode;
    /**
     * 民族
     */
    private String nation;
    /**
     * 身高
     */
    @Column(scale = 2)
    private BigDecimal height;
    /**
     * 地址
     */
    private String address;
    /**
     * 政治面貌
     */
    private String political;
    /**
     * 体重
     */
    @Column(scale = 2)
    private BigDecimal weight;
    /**
     * 照片地址
     */
    private String picpath;
    /**
     * 班级
     */
    @ManyToOne
    private BaseInfo baseInfo;

    /**
     * 家长信息
     */
    @OneToMany(mappedBy = "student")
    private List<Parent> parentList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getSexCode() {
        return sexCode;
    }

    public void setSexCode(Integer sexCode) {
        this.sexCode = sexCode;
    }

    public String getNationCode() {
        return nationCode;
    }

    public void setNationCode(String nationCode) {
        this.nationCode = nationCode;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPolitical() {
        return political;
    }

    public void setPolitical(String political) {
        this.political = political;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getPicpath() {
        return picpath;
    }

    public void setPicpath(String picpath) {
        this.picpath = picpath;
    }

    public BaseInfo getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(BaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }

    public List<Parent> getParentList() {
        return parentList;
    }

    public void setParentList(List<Parent> parentList) {
        this.parentList = parentList;
    }
}
