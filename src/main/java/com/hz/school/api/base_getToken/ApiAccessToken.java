package com.hz.school.api.base_getToken;

import com.hz.school.api.ApiEntity;

import javax.persistence.Entity;

/**
 * Created by Administrator on 2017/6/16.
 */
@Entity
public class ApiAccessToken extends ApiEntity {
    /**
     * 令牌编码，64位
     */
    private String accessToken;
    /**
     * 有效期
     */
    private Long expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }
}
