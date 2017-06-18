package com.hz.school.api.classbrand_getClassStuCardList;

import com.hz.school.api.ApiEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by Administrator on 2017/6/18.
 */
@Entity
public class ApiTeacher extends ApiEntity{
    /**
     * 卡内余额
     */
    private String balance;
    /**
     * 卡号
     */
    private String card;
    /**
     * 图片地址
     */
    private String iconpath;
    /**
     * 老师id
     */
    private Long teacherid;
    /**
     * 姓名
     */
    private String teacherName;
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

    public String getIconpath() {
        return iconpath;
    }

    public void setIconpath(String iconpath) {
        this.iconpath = iconpath;
    }

    public Long getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(Long teacherid) {
        this.teacherid = teacherid;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public ApiClassCardInfo getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ApiClassCardInfo classRoom) {
        this.classRoom = classRoom;
    }
}
