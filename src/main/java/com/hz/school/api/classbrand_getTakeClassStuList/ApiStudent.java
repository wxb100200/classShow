package com.hz.school.api.classbrand_getTakeClassStuList;


import com.hz.school.api.ApiEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by Administrator on 2017/6/15.
 */
@Entity
public class ApiStudent extends ApiEntity {
    @ManyToOne
    private ApiTakeClass apiTakeClass;
    /**
     * 学生卡号
     */
    private String card;
    /**
     * 学生姓名
     */
    private String name;
    /**
     * 学生顺序
     */
    private String order;
    /**
     * 学生照片地址
     */
    private String picpath;


    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getPicpath() {
        return picpath;
    }

    public void setPicpath(String picpath) {
        this.picpath = picpath;
    }

    public ApiTakeClass getApiTakeClass() {
        return apiTakeClass;
    }

    public void setApiTakeClass(ApiTakeClass apiTakeClass) {
        this.apiTakeClass = apiTakeClass;
    }
}
