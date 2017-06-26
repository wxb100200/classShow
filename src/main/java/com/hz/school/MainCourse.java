package com.hz.school;

import com.hz.school.model.TotalCourse;
import com.hz.school.util.EbeanUtil;
import com.hz.school.util.Logger;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/6/21.
 */
public class MainCourse {
    private static Logger log=Logger.getLogger(MainCourse.class);
    public static void main(String[] args){
        /*System.out.println("------->>>>>>main<<<<<<<--------------");
        log.info("--------->>>>>>main<<<<<<------------------------");
        int num= EbeanUtil.find(TotalCourse.class).findRowCount();
        System.out.println("------------>>>num:"+num);
        log.info("------------>>>num:"+num);*/

        Runnable runnable = new Runnable() {
            public void run() {
                // task to run goes here
                System.out.println("Hello !!");
                log.info("--------->>>>>>main<<<<<<------------------------");
            }
        };
        ScheduledExecutorService service = Executors
                .newSingleThreadScheduledExecutor();
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
        service.scheduleAtFixedRate(runnable, 10, 1, TimeUnit.SECONDS);
    }
}
