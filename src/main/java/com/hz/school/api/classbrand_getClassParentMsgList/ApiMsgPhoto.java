package com.hz.school.api.classbrand_getClassParentMsgList;

import com.hz.school.api.ApiEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by Administrator on 2017/6/18.
 */
@Entity
public class ApiMsgPhoto extends ApiEntity{
    private String path;
    /**
     * 家长与学生的消息通
     */
    @ManyToOne
    private ApiClassParentMsg msgList;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
