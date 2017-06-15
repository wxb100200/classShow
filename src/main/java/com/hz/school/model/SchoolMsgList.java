package com.hz.school.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 学校通知
 */
@Entity
@Table(name = Constant.DB_PREFIX+"school_msg_list")
public class SchoolMsgList extends BaseEntity{
    /**
     * 消息id
     */
    private Long msgid;
    /**
     * 消息标题
     */
    private String title;
    /**
     * 信息模式
     * 1、语音，2、文字
     */
    @Column(length = 1)
    private Integer contenttype;
    /**
     * 内容
     */
    private String content;
    /**
     * 班级
     */
    private Long classid;

    @ManyToOne
    private ClassRoom classRoom;
    /**
     * 发布时间
     */
    private Long longpublishdate;
    /**
     * 发布时间的字符串显示
     */
    private String publishdate;
    /**
     * 级别
     */
    private String blevel;
    /**
     *
     */
    private String bshow;


    public Long getMsgid() {
        return msgid;
    }

    public void setMsgid(Long msgid) {
        this.msgid = msgid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getContenttype() {
        return contenttype;
    }

    public void setContenttype(Integer contenttype) {
        this.contenttype = contenttype;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getClassid() {
        return classid;
    }

    public void setClassid(Long classid) {
        this.classid = classid;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }

    public Long getLongpublishdate() {
        return longpublishdate;
    }

    public void setLongpublishdate(Long longpublishdate) {
        this.longpublishdate = longpublishdate;
    }

    public String getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(String publishdate) {
        this.publishdate = publishdate;
    }

    public String getBlevel() {
        return blevel;
    }

    public void setBlevel(String blevel) {
        this.blevel = blevel;
    }

    public String getBshow() {
        return bshow;
    }

    public void setBshow(String bshow) {
        this.bshow = bshow;
    }
}
