package com.hz.school.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 令牌表
 */
@Entity
@Table(name = Constant.DB_PREFIX+"access_token")
public class AccessToken extends BaseEntity {
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
