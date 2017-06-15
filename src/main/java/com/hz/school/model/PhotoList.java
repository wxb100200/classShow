package com.hz.school.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Administrator on 2017/5/4.
 */
@Entity
@Table(name = Constant.DB_PREFIX+"photo_list")
public class PhotoList extends BaseEntity{
    /**
     * 班级圈id
     */
    private Long circleid;
    /**
     * 班级圈图片路径
     */
    private String photoUrl;
    /**
     * 照片顺序
     */
    private Integer pindex;

    @ManyToOne
    private Photo photo;

    public Long getCircleid() {
        return circleid;
    }

    public void setCircleid(Long circleid) {
        this.circleid = circleid;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Integer getPindex() {
        return pindex;
    }

    public void setPindex(Integer pindex) {
        this.pindex = pindex;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
}
