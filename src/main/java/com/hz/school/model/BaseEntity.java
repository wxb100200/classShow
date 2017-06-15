package com.hz.school.model;


import org.apache.log4j.Logger;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 基础类
 */
@MappedSuperclass
public class BaseEntity implements Serializable{
    /**
     * 给对象方法使用的日志
     */
    @Transient
    protected Logger log = Logger.getLogger(this.getClass());

    @Id
    @GeneratedValue
    private  Long id;
   /* @Id
    private  String  id=generateUUId();
    private static String  generateUUId(){
        return UUID.randomUUID().toString().replace("-","");
    }*/

    /**
     * 是否有效
     * 1:有效，0：无效
     */
    @Column(length = 1)
    private String IsInactive="1";
    /**
     * 创建时间
     */
    private  Long createTime=new Date().getTime();

    /**
     * 修改时间
     */
    private  Long changeTime=new Date().getTime();

    public BaseEntity() {
    }

    public Logger getLog() {
        return log;
    }

    public void setLog(Logger log) {
        this.log = log;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsInactive() {
        return IsInactive;
    }

    public void setIsInactive(String isInactive) {
        IsInactive = isInactive;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Long changeTime) {
        this.changeTime = changeTime;
    }
}
