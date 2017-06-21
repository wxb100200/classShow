package com.hz.school.api.classbrand_getClassSchoolMsgList;

import com.hz.school.api.ApiEntity;

import javax.persistence.Entity;

/**
 * Created by Administrator on 2017/6/19.
 */
@Entity
public class ApiClassSchoolMsg extends ApiEntity{
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
    private Integer contenttype;
    /**
     * 内容
     */
    private String content;
    /**
     * 班级
     */
    private Long classid;

    /**
     * 发布时间的字符串显示
     */
    private String publishdate;

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

    public String getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(String publishdate) {
        this.publishdate = publishdate;
    }
}
