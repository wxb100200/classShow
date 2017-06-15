package com.hz.school.model;

import javax.persistence.*;
import java.util.List;

/**
 * 家长与学生的消息通信
 */
@Entity
@Table(name = Constant.DB_PREFIX+"msg_list")
public class MsgList extends BaseEntity{
    /**
     * 班级id
     */
    private Long classid;

    @ManyToOne
    private ClassRoom classRoom;
    /**
     * 学生id
     */
    private Long stuid;

    private Student student;

    /**
     * 消息id
     */
    private Long msgid;
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
     * 发布时间
     */
    private Long publishdate;

    @OneToMany(mappedBy = "msgList")
    private List<MsgPhoto> photoList;

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

    public Long getMsgid() {
        return msgid;
    }

    public void setMsgid(Long msgid) {
        this.msgid = msgid;
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

    public Long getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(Long publishdate) {
        this.publishdate = publishdate;
    }

    public List<MsgPhoto> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<MsgPhoto> photoList) {
        this.photoList = photoList;
    }

    public Long getStuid() {
        return stuid;
    }

    public void setStuid(Long stuid) {
        this.stuid = stuid;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
