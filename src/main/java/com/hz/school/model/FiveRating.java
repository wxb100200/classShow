package com.hz.school.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 五项评比
 */
@Entity
@Table(name = Constant.DB_PREFIX+"five_rating")
public class FiveRating extends BaseEntity{
    private String weekday;
    private String week;
    @Column(scale = 2)
    private double score;
    private String title;
    private String typeid;

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }
}

