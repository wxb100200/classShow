package com.hz.school.api;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by Administrator on 2017/6/15.
 */
@MappedSuperclass
public class ApiEntity {
    @Id
    private  Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
