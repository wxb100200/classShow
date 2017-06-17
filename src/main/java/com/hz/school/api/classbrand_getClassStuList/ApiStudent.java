package com.hz.school.api.classbrand_getClassStuList;

import com.hz.school.api.ApiEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by Administrator on 2017/6/17.
 */
@Entity
public class ApiStudent  extends ApiEntity{
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
    private String height;
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

    private Integer order;
    /**
     * 家长信息
     */
    @OneToMany(mappedBy = "student")
    private List<ApiParent> parentList;
    /**
     * 照片地址
     */
    private String picpath;
    /**
     * 政治面貌
     */
    private String political;
    /**
     * 性别
     * 男、女
     */
    private String sex;
    /**
     * 性别代码
     * 1、男，2、女
     */
    private Integer sexCode;

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

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
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

    public List<ApiParent> getParentList() {
        return parentList;
    }

    public void setParentList(List<ApiParent> parentList) {
        this.parentList = parentList;
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
}
