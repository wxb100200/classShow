package com.hz.school.api.classbrand_getClassPhotoList;

import com.hz.school.api.ApiEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by Administrator on 2017/6/18.
 */
@Entity
public class ApiPhotoList extends ApiEntity {
    /**
     * 班级圈图片路径
     */
    private String photoUrl;
    @ManyToOne
    private ApiPhoto photo;

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public ApiPhoto getPhoto() {
        return photo;
    }

    public void setPhoto(ApiPhoto photo) {
        this.photo = photo;
    }
}
