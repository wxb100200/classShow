package com.hz.school.api.classbrand_getClassPhotoList;

import com.hz.school.api.ApiEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by Administrator on 2017/6/18.
 */
@Entity
public class ApiPhoto extends ApiEntity{
    /**
     * 班级圈id
     */
    private Long circleid;
    /**
     * 班级圈说明
     */
    private String content;
    /**
     * 点赞数量
     */
    private Integer pariseCount;
    /**
     * 班级id
     */
    private Long classid;

    @OneToMany(mappedBy = "photo")
    private List<ApiPhotoList> photoList;

    public Long getCircleid() {
        return circleid;
    }

    public void setCircleid(Long circleid) {
        this.circleid = circleid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getPariseCount() {
        return pariseCount;
    }

    public void setPariseCount(Integer pariseCount) {
        this.pariseCount = pariseCount;
    }

    public Long getClassid() {
        return classid;
    }

    public void setClassid(Long classid) {
        this.classid = classid;
    }

    public List<ApiPhotoList> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<ApiPhotoList> photoList) {
        this.photoList = photoList;
    }
}
