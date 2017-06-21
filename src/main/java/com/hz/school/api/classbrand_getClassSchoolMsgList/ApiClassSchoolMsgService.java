package com.hz.school.api.classbrand_getClassSchoolMsgList;

import com.hz.school.model.SchoolMsgList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/19.
 */
public class ApiClassSchoolMsgService {
    public static List<ApiClassSchoolMsg> generateList(List<SchoolMsgList> dataList){
        List<ApiClassSchoolMsg> apiList=new ArrayList<ApiClassSchoolMsg>();
        for(SchoolMsgList data:dataList){
            ApiClassSchoolMsg api=new ApiClassSchoolMsg();
            api.setMsgid(data.getMsgid());
            api.setTitle(data.getTitle());
            api.setContenttype(data.getContenttype());
            api.setContent(data.getContent());
            api.setClassid(data.getClassid());
            api.setPublishdate(data.getPublishdate());
            apiList.add(api);
        }
        return apiList;
    }
}
