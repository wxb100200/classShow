package com.hz.school.api.classbrand_getClassParentMsgList;

import com.hz.school.model.MsgList;
import com.hz.school.model.MsgPhoto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/18.
 */
public class ApiClassParentMsgService {
    public static List<ApiClassParentMsg> generateList(List<MsgList> dataList){
        List<ApiClassParentMsg> apiList=new ArrayList<ApiClassParentMsg>();
        for(MsgList data:dataList){
            ApiClassParentMsg api=new ApiClassParentMsg();
            api.setClassid(data.getClassid());
            api.setContent(data.getContent());
            api.setContenttype(data.getContenttype());
            api.setMsgid(data.getMsgid());
            api.setStuid(data.getStuid());
            api.setPhotoList(generatePhotoList(data.getPhotoList()));
            apiList.add(api);
        }
        return apiList;
    }
    private static List<ApiMsgPhoto> generatePhotoList(List<MsgPhoto> dataList){
        List<ApiMsgPhoto> apiList=new ArrayList<ApiMsgPhoto>();
        for(MsgPhoto data:dataList){
            ApiMsgPhoto api=new ApiMsgPhoto();
            api.setPath(data.getPath());
            apiList.add(api);
        }
        return apiList;
    }
}
