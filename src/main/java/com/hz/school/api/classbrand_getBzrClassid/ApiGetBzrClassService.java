package com.hz.school.api.classbrand_getBzrClassid;

import com.hz.school.model.ClassRoom;
import com.hz.school.model.ClassTime;
import com.hz.school.model.Committee;
import com.hz.school.model.Xqbm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/17.
 */
public class ApiGetBzrClassService {
    public static ApiClassRoom generateData(ClassRoom data){
        ApiClassRoom apiData=new ApiClassRoom();
        apiData.setCampusid(data.getCampusid());
        apiData.setClassInfo(data.getClassInfo());
        apiData.setClassName(data.getClassName());
        apiData.setClassRoomName(data.getClassRoomName());
        apiData.setClassRoomid(data.getClassRoomid());
        apiData.setClassTimeList(generateClassTimeList(data.getClassTimeList()));
        apiData.setClassid(data.getClassid());
        apiData.setCommittee(generateCommittee(data.getCommittee()));
        apiData.setHeadmasterId(data.getHeadmasterId());
        apiData.setHeadmasterName(data.getHeadmasterName());
        apiData.setXqbm(generateXqbm(data.getXqbm()));
        return apiData;
    }
    private static List<ApiClassTime> generateClassTimeList(List<ClassTime> classTimeList){
        List<ApiClassTime> apiList=new ArrayList<ApiClassTime>();
        for(ClassTime classTime:classTimeList){
            ApiClassTime api=new ApiClassTime();
            api.setClassNum(classTime.getClassNum());
            api.setStartTime(classTime.getStartTime());
            api.setEmdTime(classTime.getEmdTime());
            apiList.add(api);
        }
        return apiList;
    }
    private static List<ApiCommittee> generateCommittee(List<Committee> committees){
        List<ApiCommittee> apiList=new ArrayList<ApiCommittee>();
        for(Committee committee:committees){
            ApiCommittee api=new ApiCommittee();
            api.setCommitteeCode(committee.getCommitteeCode());
            api.setCommitteeName(committee.getCommitteeName());
            api.setStuid(committee.getStuid());
            api.setStuName(committee.getStuName());
            apiList.add(api);
        }
        return apiList;
    }
    private static ApiXqbm generateXqbm(Xqbm xqbm){
        ApiXqbm api=new ApiXqbm();
        api.setCampusid(xqbm.getCampusid());
        api.setCampusid_ch(xqbm.getCampusid_ch());
        api.setCurrentxq(xqbm.getCurrentxq());
        api.setOrgcode(xqbm.getOrgcode());
        api.setRemark(xqbm.getRemark());
        api.setWeekbegin(xqbm.getWeekbegin());
        api.setWeeknum(xqbm.getWeeknum());
        api.setXnbm(xqbm.getXnbm());
        api.setXqbm(xqbm.getXqbm());
        api.setXqgb(xqbm.getXqgb());
        api.setXqmc(xqbm.getXqmc());
        return api;
    }
}
