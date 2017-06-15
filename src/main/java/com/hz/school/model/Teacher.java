package com.hz.school.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * 老师信息表
 */
@Entity
@Table(name = Constant.DB_PREFIX+"teacher")
public class Teacher extends BaseEntity{
    /**
     * 老师id
     */
    private Long teacherid;
    /**
     * 姓名
     */
    private String teacherName;
    /**
     * 图片地址
     */
    private String iconpath;
    /**
     * 卡号
     */
    private String card;
    /**
     * 卡内余额
     */
    @Column(scale = 1)
    private BigDecimal balance;
    /**
     * 班级
     */
    @ManyToOne
    private ClassRoom classRoom;

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

    public String getIconpath() {
        return iconpath;
    }

    public void setIconpath(String iconpath) {
        this.iconpath = iconpath;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }
}
