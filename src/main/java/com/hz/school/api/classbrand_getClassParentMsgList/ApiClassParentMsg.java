package com.hz.school.api.classbrand_getClassParentMsgList;

import com.hz.school.api.ApiEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by Administrator on 2017/6/18.
 */
@Entity
public class ApiClassParentMsg extends ApiEntity{
    /**
     * 班级id
     */
    private Long classid;
    /**
     * 内容
     */
    private String content;
    /**
     * 信息模式
     * 1、语音，2、文字
     */
    private Integer contenttype;
    /**
     * 消息id
     */
    private Long msgid;

    @OneToMany(mappedBy = "msgList")
    private List<ApiMsgPhoto> photoList;
    /**
     * 学生id
     */
    private Long stuid;

    public Long getClassid() {
        return classid;
    }

    public void setClassid(Long classid) {
        this.classid = classid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getContenttype() {
        return contenttype;
    }

    public void setContenttype(Integer contenttype) {
        this.contenttype = contenttype;
    }

    public Long getMsgid() {
        return msgid;
    }

    public void setMsgid(Long msgid) {
        this.msgid = msgid;
    }


    public Long getStuid() {
        return stuid;
    }

    public void setStuid(Long stuid) {
        this.stuid = stuid;
    }

    public List<ApiMsgPhoto> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<ApiMsgPhoto> photoList) {
        this.photoList = photoList;
    }
}
