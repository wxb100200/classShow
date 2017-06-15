package com.hz.school.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * 学生卡信息表
 */
@Entity
@Table(name = Constant.DB_PREFIX+"cardinfo")
public class CardInfo extends BaseEntity{
    /**
     * xxx
     * 班级id
     */
    private String classid;
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
     * 卡内余额
     */
    @Column(scale = 1)
    private BigDecimal balance;
    /**
     * xxxx
     * 卡类型
     * 1学生卡、2班主任卡、3其他老师卡
     */
    @Column(length = 1)
    private String type;
    /**
     * 班级
     */
    @ManyToOne
    private ClassRoom classRoom;


    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }
}
