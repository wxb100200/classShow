package com.hz.school.api.classbrand_classCircleParise;

import com.hz.school.model.Photo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/18.
 */
public class ApiClassCirclePariseService {
    public static List<ApiClassCircleParise> generateList(List<Photo> dataList){
        List<ApiClassCircleParise> apiList=new ArrayList<ApiClassCircleParise>();
        for(Photo data:dataList){
            ApiClassCircleParise api=new ApiClassCircleParise();
            api.setCircleid(data.getCircleid());
            apiList.add(api);
        }
        return apiList;
    }
}
