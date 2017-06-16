package com.hz.school.api.base_getToken;

import com.hz.school.model.AccessToken;

/**
 * Created by Administrator on 2017/6/16.
 */
public class ApiAccessTokenService {
    public static ApiAccessToken generateData(AccessToken accessToken){
        ApiAccessToken apiAccessToken=new ApiAccessToken();
        apiAccessToken.setAccessToken(accessToken.getAccessToken());
        apiAccessToken.setExpiresIn(accessToken.getExpiresIn());
        return apiAccessToken;
    }
}
