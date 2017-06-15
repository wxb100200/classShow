package com.hz.school.model;

import java.math.BigDecimal;

/**
 * xx
 * 服务器时间
 */
public class ServerTime {

    private String init;

    /**
     * 系统时间
     */
    private String timeInfo;
    /**
     * 级别
     */
    private Integer level;
    /**
     * 精度
     */
    private BigDecimal accuracy;

    public String getInit() {
        return init;
    }

    public void setInit(String init) {
        this.init = init;
    }

    public String getTimeInfo() {
        return timeInfo;
    }

    public void setTimeInfo(String timeInfo) {
        this.timeInfo = timeInfo;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public BigDecimal getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(BigDecimal accuracy) {
        this.accuracy = accuracy;
    }
}
