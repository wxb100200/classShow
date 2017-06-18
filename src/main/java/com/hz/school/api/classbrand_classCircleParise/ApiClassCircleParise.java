package com.hz.school.api.classbrand_classCircleParise;

import com.hz.school.api.ApiEntity;

import javax.persistence.Entity;

/**
 * Created by Administrator on 2017/6/18.
 */
@Entity
public class ApiClassCircleParise extends ApiEntity{
    /**
     * 班级圈id
     */
    private Long circleid;

    public Long getCircleid() {
        return circleid;
    }

    public void setCircleid(Long circleid) {
        this.circleid = circleid;
    }
}
