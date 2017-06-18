package com.hz.school.api.classbrand_getClassStuCardList;

import com.hz.school.api.ApiEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by Administrator on 2017/6/18.
 */
@Entity
public class ApiCardInfo extends ApiEntity{
    /**
     * 卡内余额
     */
    private String balance;
    /**
     * 卡号
     */
    private String card;
    /**
     * 学生id
     */
    private Long stuid;
    /**
     * 学生姓名
     */
    private String stuname;
    /**
     * 班级
     */
    @ManyToOne
    private ApiClassCardInfo classRoom;

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public Long getStuid() {
        return stuid;
    }

    public void setStuid(Long stuid) {
        this.stuid = stuid;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public ApiClassCardInfo getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ApiClassCardInfo classRoom) {
        this.classRoom = classRoom;
    }
}
