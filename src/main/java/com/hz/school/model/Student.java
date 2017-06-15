package com.hz.school.model;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * 学生信息表
 */
@Entity
@Table(name = Constant.DB_PREFIX+"student")
public class Student extends BaseEntity {
    /***
     * 学生id
     */
    private Long stuid;
    /**
     * 地址
     */
    private String address;
    /**
     * 卡号
     */
    private String card;
    /**
     * 身高
     */
    @Column(scale = 2)
    private BigDecimal height;
    /**
     * 体重
     */
    @Column(scale = 2)
    private BigDecimal weight;
    /**
     * 姓名
     */
    private String name;
    /**
     * 民族
     */
    private String nation;
    /**
     * 邮政编码
     */
    private Long nationCode;

    @Column(name="s_order")
    private Integer order;
    /**
     * 照片地址
     */
    private String picpath;
    /**
     * 政治面貌
     */
    private String political;
    /**
     * 性别代码
     * 1、男，2、女
     */
    @Column(length = 1)
    private Integer sexCode;
    /**
     * 性别
     * 男、女
     */
    private String sex;
    /**
     * 班级
     */
    @ManyToOne
    private ClassRoom classRoom;
    /**
     * 家长信息
     */
    @OneToMany(mappedBy = "student")
    private List<Parent> parentList;

    /**
     * 学号
     */
    private String poNumber;

    public Long getStuid() {
        return stuid;
    }

    public void setStuid(Long stuid) {
        this.stuid = stuid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public Long getNationCode() {
        return nationCode;
    }

    public void setNationCode(Long nationCode) {
        this.nationCode = nationCode;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getPicpath() {
        return picpath;
    }

    public void setPicpath(String picpath) {
        this.picpath = picpath;
    }

    public String getPolitical() {
        return political;
    }

    public void setPolitical(String political) {
        this.political = political;
    }

    public Integer getSexCode() {
        return sexCode;
    }

    public void setSexCode(Integer sexCode) {
        this.sexCode = sexCode;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }

    public List<Parent> getParentList() {
        return parentList;
    }

    public void setParentList(List<Parent> parentList) {
        this.parentList = parentList;
    }

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }
}
