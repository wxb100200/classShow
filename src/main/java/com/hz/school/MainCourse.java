package com.hz.school;

import com.hz.school.model.TotalCourse;
import com.hz.school.util.EbeanUtil;

/**
 * Created by Administrator on 2017/6/21.
 */
public class MainCourse {
    public static void main(String[] args){
        System.out.println("------->>>>>>main<<<<<<<--------------");
        int num= EbeanUtil.find(TotalCourse.class).findRowCount();
        System.out.println("------------>>>num:"+num);
    }
}
