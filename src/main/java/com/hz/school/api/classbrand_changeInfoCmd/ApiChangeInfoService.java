package com.hz.school.api.classbrand_changeInfoCmd;

import com.hz.school.model.ChangeInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/17.
 */
public class ApiChangeInfoService {
    public static List<ApiChangeInfo> generateList(List<ChangeInfo> changeInfoList){
        List<ApiChangeInfo> apiList=new ArrayList<ApiChangeInfo>();
        for(ChangeInfo info:changeInfoList){
            ApiChangeInfo api=new ApiChangeInfo();
            api.setCampuscode(info.getCampuscode());
            api.setClassid(info.getClassid());
            api.setApiMethod(info.getApiMethod());
            apiList.add(api);
        }
        return apiList;
    }
}
