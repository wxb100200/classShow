package com.hz.school.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * 班级圈图片
 */
@Entity
@Table(name = Constant.DB_PREFIX+"photo")
public class Photo extends BaseEntity{
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

    @ManyToOne
    private ClassRoom classRoom;
    /**
     * 日期
     */
    private  Long cdate;

    @OneToMany(mappedBy = "photo")
    private List<PhotoList> photoList;

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

    public Long getCdate() {
        return cdate;
    }

    public void setCdate(Long cdate) {
        this.cdate = cdate;
    }

    public List<PhotoList> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<PhotoList> photoList) {
        this.photoList = photoList;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }
}
