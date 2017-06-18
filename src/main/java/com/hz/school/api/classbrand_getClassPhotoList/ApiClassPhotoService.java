package com.hz.school.api.classbrand_getClassPhotoList;

import com.hz.school.model.Photo;
import com.hz.school.model.PhotoList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/18.
 */
public class ApiClassPhotoService {
    public static List<ApiPhoto> generateList(List<Photo> dataList){
        List<ApiPhoto> apiList=new ArrayList<ApiPhoto>();
        for(Photo data:dataList){
            ApiPhoto api=new ApiPhoto();
            api.setCircleid(data.getCircleid());
            api.setContent(data.getContent());
            api.setPariseCount(data.getPariseCount());
            api.setClassid(data.getClassid());
            api.setPhotoList(generatePhotoList(data.getPhotoList()));
            apiList.add(api);
        }
        return apiList;
    }
    private static List<ApiPhotoList> generatePhotoList(List<PhotoList> dataList){
        List<ApiPhotoList> apiList=new ArrayList<ApiPhotoList>();
        for(PhotoList data:dataList){
            ApiPhotoList api=new ApiPhotoList();
            api.setPhotoUrl(data.getPhotoUrl());
            apiList.add(api);
        }
        return apiList;
    }
}
