package com.hz.school.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 家长与学生的消息通信图片
 */
@Entity
@Table(name = Constant.DB_PREFIX+"msg_photo")
public class MsgPhoto extends BaseEntity{
    /**
     * 家长与学生的消息通
     */
    @ManyToOne
    private MsgList msgList;

    private String path;

    public MsgList getMsgList() {
        return msgList;
    }

    public void setMsgList(MsgList msgList) {
        this.msgList = msgList;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
