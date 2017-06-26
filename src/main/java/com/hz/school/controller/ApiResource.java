package com.hz.school.controller;

import com.alibaba.fastjson.JSONObject;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.text.json.JsonContext;
import com.hz.school.api.classbrand_getClassCoursLeist.ApiClassCourse;
import com.hz.school.api.classbrand_getClassCoursLeist.ApiClassCourseService;
import com.hz.school.api.classbrand_getTakeClassStuList.ApiTakeClass;
import com.hz.school.api.classbrand_getTakeClassStuList.ApiTakeClassStuService;
import com.hz.school.model.*;
import com.hz.school.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * api统一进入接口
 */
@Controller
@RequestMapping(value="/api2",produces="text/html;charset=UTF-8")
public class ApiResource {
    private static Logger log=Logger.getLogger(ApiResource.class);

    /**
     * 一、获取AccessTlisten令牌接口（延后）
     */
    @ResponseBody
    @RequestMapping(value="/base_getTlisten",method= RequestMethod.POST)
    public String baseGetTlisten(HttpServletRequest request){
        String apiparams=request.getParameter("apiparams");
        JSONObject jApiparams= JSONObject.parseObject(apiparams);
        String params=jApiparams.getString("params");
        JSONObject jParams=JSONObject.parseObject(params);
        String appId=jParams.getString("appId");
        String appSecret=jParams.getString("appSecret");
        log.info("----->>>>>>baseGetTlisten appId:"+appId+",appSecret:"+appSecret);
        AccessToken accessTlisten= EbeanUtil.find(AccessToken.class,1l);
        return ApiResult.single("base_getTlisten",accessTlisten).toJson();
    }
    /**
     * 通过
     *  二、 设备测试接口网络及获取当前时间情况
     * 1、主要测试电子班牌和微校通网络是否畅通
     */
    @ResponseBody
    @RequestMapping(value="/classbrand_getServerTime",method= RequestMethod.POST)
    public String ServerTime(HttpServletRequest request){
        String apiparams=request.getParameter("apiparams");
        log.info("apiparams="+apiparams);
        JSONObject jApiparams= JSONObject.parseObject(apiparams);
        String params=jApiparams.getString("params");
        JSONObject jParams=JSONObject.parseObject(params);
        String init=jParams.getString("init");
        log.info("----->>>>>>ServerTime init:"+init);
        String currentTime= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        if(StringUtil.isEmpty(init)){
            log.error("网络异常");
            return "{\"api\":\"classbrand_getServerTime\",\"data\":\"\",\"ret\":{\"code\":\"400 \",\"msg\":\"网络异常\"},\"v\":\"\"}";
        }
        return "{\"api\":\"classbrand_getServerTime\",\"data\":\""+currentTime+"\",\"ret\":{\"code\":\"200\",\"msg\":\"\"},\"v\":\"\"}";
    }

    /**
     * 三、 设备获取天气情况情况
     * 1、主要测试电子班牌和微校通网络是否畅通
     * apiparams={"params":{"cityname":"杭州","classid":"2958","accessTlisten":"ccesstlisten000001"}}
     */
    @ResponseBody
    @RequestMapping(value="/classbrand_getWeather",method= RequestMethod.POST)
    public String Weather(HttpServletRequest request) {
        String apiparams=request.getParameter("apiparams");
        log.info("apiparams="+apiparams);
        JSONObject jApiparams= JSONObject.parseObject(apiparams);
        String params=jApiparams.getString("params");
        JSONObject jParams=JSONObject.parseObject(params);
        String accessTlisten=jParams.getString("accessTlisten");//令牌
        String cityname=jParams.getString("cityname");//城市名称
        String classid=jParams.getString("classid");//班级id
        log.info("----->>>>>>Weather accessTlisten:"+accessTlisten+",cityname:"+cityname+",classid:"+classid);
        return todayWeather("classbrand_getWeather",request,"101210101");
    }
    /**
     * http://www.weather.com.cn/data/cityinfo/101210101.html
     * http://www.weather.com.cn/data/sk/101210101.html
     */
    private static String todayWeather(String api, HttpServletRequest request, String cityId){
        try {
            String data1=commitUrl("http://www.weather.com.cn/data/cityinfo/" + cityId+ ".html");
            String data2=commitUrl("http://www.weather.com.cn/data/sk/" + cityId+ ".html");
            log.info(data1);
            log.info(data2);
            net.sf.json.JSONObject jsonData1 = net.sf.json.JSONObject.fromObject(data1);
            net.sf.json.JSONObject jsonData2 = net.sf.json.JSONObject.fromObject(data2);
            net.sf.json.JSONObject info1 = jsonData1.getJSONObject("weatherinfo");
            net.sf.json.JSONObject info2 = jsonData2.getJSONObject("weatherinfo");
            Weather weather=new Weather();
            weather.setWD(info2.getString("WD"));
            weather.setWS(info2.getString("WS"));
            weather.setAltitude("");
            weather.setCity(info1.getString("city"));
            weather.setCitycode(info1.getString("cityid"));
            weather.setDate("");
            weather.setH_tmp(info1.getString("temp2"));
            weather.setL_tmp(info1.getString("temp1"));
            weather.setLatitude("");
            weather.setLongitude("");
            weather.setPinyin("hangzhou");
            weather.setPostCode("101210101");
            weather.setSunrise("");
            weather.setSunset("");
            weather.setTemp(info2.getString("temp"));
            weather.setTime(info2.getString("time"));
            weather.setWeather(info1.getString("weather"));
            return ApiResult.single(api,weather).toJson();
        } catch (IOException e) {
            log.error("ApiWeather todayWeather error",e);
            return ApiResult.error(api,"500","ApiWeather todayWeather error").toJson();
        }
    }
    private static String commitUrl(String url)throws IOException {
        URL u=new URL(url);
        URLConnection connectionData = u.openConnection();
        connectionData.setConnectTimeout(1000);
        BufferedReader br = new BufferedReader(new InputStreamReader(
                connectionData.getInputStream(), "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = br.readLine()) != null)
            sb.append(line);
        return sb.toString();
    }
    /**
     * 问题：报Access violation at address 00ACF6BC in module  'classshow.exe'...
     *四、 设备与班级关联接口
     * http://zjgbanpai.hzxjhs.com/weixt/api/classbrand_getBzrClassid
     * apiparams={"params":{"classroomid":"","accessTlisten":"ccesstlisten000001","SN":"587DA6830CE47FF2632D1EDFA0E302D4","card":"1967948798"}}
     */
    @ResponseBody
    @RequestMapping(value="/classbrand_getBzrClassid",method= RequestMethod.POST)
    public String BzrClassid(HttpServletRequest request) {
        String apiparams=request.getParameter("apiparams");
        log.info("apiparams="+apiparams);
        JSONObject jApiparams= JSONObject.parseObject(apiparams);
        String params=jApiparams.getString("params");
        JSONObject jParams=JSONObject.parseObject(params);
        String accessTlisten=jParams.getString("accessTlisten");//令牌
        String card=jParams.getString("card");//班主任卡号
        String SN=jParams.getString("SN");//CB电子班牌唯一设备号
        log.info("----->>>>>>BzrClassid accessTlisten:"+accessTlisten+",card:"+card+",SN:"+SN);
        if(StringUtil.isEmpty(card)){
            log.error("卡号不能为空");
            return "{\"api\":\"classbrand_getBzrClassid\",\"data\":\"\",\"ret\":{\"code\":\"400 \",\"msg\":\"卡号不能为空\"},\"v\":\"\"}";

        }
        ClassRoom classRoom=EbeanUtil.find(ClassRoom.class).where()
                .select("campusid,classInfo,className,classRoomName,classRoomid,classid,headmasterId,headmasterName")
                .fetch("classTimeList","classNum,emdTime,startTime")
                .fetch("committee","committeeCode,committeeName,stuName,stuid")
                .fetch("xqbm","id,campusid,campusid_ch,currentxq,orgcode,remark,weekbegin,weeknum,xnbm,xqbm,xqgb,xqmc")
                .setMaxRows(1).findUnique();
        System.out.println("{\"api\":\"classbrand_getBzrClassid\",\"data\":{\"class\":"+ApiResult.objectToJson(classRoom)+",\"classSelection\":null},\"ret\":{\"code\":\"200\",\"msg\":\"\"},\"v\":\"\"}".trim());
        return "{\"api\":\"classbrand_getBzrClassid\",\"data\":{\"class\":"+ApiResult.objectToJson(classRoom)+",\"classSelection\":null},\"ret\":{\"code\":\"200\",\"msg\":\"\"},\"v\":\"\"}".trim();

    }

    /**
     * 问题：这个接口该如何触发
     *五、 数据变动通知接口
     */
    @ResponseBody
    @RequestMapping(value="/classbrand_changeInfoCmd",method= RequestMethod.POST)
    public String ChangeInfoCmd(HttpServletRequest request) {
        String apiparams=request.getParameter("apiparams");
        log.info("apiparams="+apiparams);
        JSONObject jApiparams= JSONObject.parseObject(apiparams);
        String params=jApiparams.getString("params");
        JSONObject jParams=JSONObject.parseObject(params);
        String accessTlisten=jParams.getString("accessTlisten");
        String classid=jParams.getString("classid");
        log.info("----->>>>>>ChangeInfoCmd accessTlisten:"+accessTlisten+",classid:"+classid);

        if(StringUtil.isEmpty(classid)){
            return ApiResult.error("classbrand_changeInfoCmd","400" ,"卡号不存在").toJson();
        }
        List<ChangeInfo> changeInfoList=EbeanUtil.find(ChangeInfo.class).where().eq("classid",classid).findList();
        return ApiResult.list("classbrand_changeInfoCmd",changeInfoList).toJson();
    }

    /**
     * 通过 
     *六、 班级学生列表数据接口
     */
    @ResponseBody
    @RequestMapping(value="/classbrand_getClassStuList",method= RequestMethod.POST)
    public String ClassStuList(HttpServletRequest request) {
        String apiparams=request.getParameter("apiparams");
        log.info("apiparams="+apiparams);
        JSONObject jApiparams= JSONObject.parseObject(apiparams);
        String params=jApiparams.getString("params");
        JSONObject jParams=JSONObject.parseObject(params);
        String accessTlisten=jParams.getString("accessTlisten");
        String classId=jParams.getString("classid");
        log.info("----->>>>>>ClassStuList accessTlisten:"+accessTlisten+",classid:"+classId);
        if(StringUtil.isEmpty(classId)){
            return ApiResult.error("classbrand_getClassStuList","400" ,"数据请求错误").toJson();
        }
        List<Student> studentList=EbeanUtil.find(Student.class).where().eq("classRoom.classid",classId)
                .select("address,card,height,id,name,nation,nationCode,order,picpath,political,sexCode,sex")
                .fetch("parentList","parentName,relationCode,relation,phoneNum").findList();
        return ApiResult.list("classbrand_getClassStuList",studentList).toJson();
    }

    /**
     * 通过
     * 生产环境没法返回数据
     *七、 班级学生考勤列表数据接口
     */
    @ResponseBody
    @RequestMapping(value="/classbrand_getClassStuAttendanceList",method= RequestMethod.POST)
    public String ClassStuAttendanceList(HttpServletRequest request) {
        String apiparams=request.getParameter("apiparams");
        log.info("apiparams="+apiparams);
        JSONObject jApiparams= JSONObject.parseObject(apiparams);
        String params=jApiparams.getString("params");
        JSONObject jParams=JSONObject.parseObject(params);
        String accessTlisten=jParams.getString("accessTlisten");
        String classId=jParams.getString("classid");
        String starttime=jParams.getString("starttime");
        String endtime=jParams.getString("endtime");
        log.info("----->>>>>>ClassStuAttendanceList accessTlisten:"+accessTlisten+",classid:"+classId+",startTime:"+starttime+",endTime:"+endtime);
        Long longStartTime= DateUtil.parseDate(starttime,"yyyy-MM-dd").getTime();
        Long longEndTime= DateUtil.parseDate(endtime,"yyyy-MM-dd").getTime();
        if(StringUtil.isEmpty(classId) && StringUtil.isEmpty(starttime) && StringUtil.isEmpty(endtime)){
            return ApiResult.error("classbrand_getClassStuAttendanceList","400" ,"数据请求错误").toJson();
        }
        List<StudentAttendance> studentAttendanceList=EbeanUtil.find(StudentAttendance.class).where()
                .eq("classRoom.classid",classId).ge("attList.longAttTime",longStartTime).lt("attList.longAttTime",longEndTime)
                .select("id,stuid,classid,attCount,attLateCount,attTardyCount")
                .fetch("attList","attTime,parentWord").findList();
        return ApiResult.list("classbrand_getClassStuAttendanceList",studentAttendanceList).toJson();
    }
    /**
     *八、 班级学生考勤信息数据接口
     */

    /**
     * 可以插入数据
     * 请求生产服务报错
     *这个地方如何根据时间段进行筛选
     *九、 班级考勤统计及考勤设置
     */
    @ResponseBody
    @RequestMapping(value="/classbrand_getClassAttendanceInfo",method= RequestMethod.POST)
    public String ClassAttendanceInfo(HttpServletRequest request) {
        String apiparams=request.getParameter("apiparams");
        log.info("apiparams="+apiparams);
        JSONObject jApiparams= JSONObject.parseObject(apiparams);
        String params=jApiparams.getString("params");
        JSONObject jParams=JSONObject.parseObject(params);
        String accessTlisten=jParams.getString("accessTlisten");
        String classId=jParams.getString("classid");
        String starttime=jParams.getString("starttime");
        String endtime=jParams.getString("endtime");
        log.info("----->>>>>>ClassAttendanceInfo accessTlisten:"+accessTlisten+",classid:"+classId+",startTime:"+starttime+",endTime:"+endtime);
        if(StringUtil.isEmpty(classId)){
            return ApiResult.error("classbrand_getClassAttendanceInfo","400" ,"数据请求错误").toJson();
        }
        ClassAttendance classAttendance=EbeanUtil.find(ClassAttendance.class).where()
                .eq("classRoom.classid",classId)
                .select("id,classid,attCount,attLateCount,attTardyCount,attLategradeRanking,attLateSchoolRanking,attTardyGradeRanking,attTardySchoolRanking")
                .fetch("attTimeSet","configcode,configvalue,configname").setMaxRows(1).findUnique();
        return ApiResult.single("classbrand_getClassAttendanceInfo",classAttendance).toSingleJson();
    }
    /**
     * 生产返回数据为空
     * 这个接口太乱了，返回的数据跟需要插入的sz_examInfo表的结构相差很大
     *十、 班级考试考场信息数据接口（优先级低，延后）
     *  pdf请求参数写的是classid,实际请求参数是classroomid
     */
    @ResponseBody
    @RequestMapping(value="/classbrand_getClassExamInfoList",method= RequestMethod.POST)
    public String ClassExamInfoList(HttpServletRequest request) {
        String apiparams=request.getParameter("apiparams");
        log.info("apiparams="+apiparams);
        JSONObject jApiparams= JSONObject.parseObject(apiparams);
        String params=jApiparams.getString("params");
        JSONObject jParams=JSONObject.parseObject(params);
        String accessTlisten=jParams.getString("accessTlisten");
        String classroomid=jParams.getString("classroomid");
        log.info("----->>>>>> ClassExamInfoList accessTlisten:"+accessTlisten+",classroomid:"+classroomid);
        if(StringUtil.isEmpty(classroomid)){
            return ApiResult.error("classbrand_getClassExamInfoList","400" ,"数据请求错误").toJson();
        }
        List<ExamInfo> examInfoList=EbeanUtil.find(ExamInfo.class).where().eq("classid",classroomid)
                .select("id,examTitle,examid,examTime,classid")
                .fetch("courseList","course").findList();
        return ApiResult.list("classbrand_getClassExamInfoList",examInfoList).toJson();
    }
    /**
     * 通过
     *十一、 班级学生成绩列表数据接口
     */
    @ResponseBody
    @RequestMapping(value="/classbrand_getClassStuScoreList",method= RequestMethod.POST)
    public String ClassStuScoreList(HttpServletRequest request) {
        String apiparams=request.getParameter("apiparams");
        log.info("apiparams="+apiparams);
        JSONObject jApiparams= JSONObject.parseObject(apiparams);
        String params=jApiparams.getString("params");
        JSONObject jParams=JSONObject.parseObject(params);
        String accessTlisten=jParams.getString("accessTlisten");
        String classId=jParams.getString("classid");
        String stuid=jParams.getString("stuid");
        log.info("----->>>>>> ClassStuScoreList classId:"+classId+",stuid:"+stuid);
        if(StringUtil.isEmpty(classId)){
            return ApiResult.error("classbrand_getClassStuScoreList","400" ,"数据请求错误").toJson();
        }
        List<ClassStudentScore> examStudentInfoList=EbeanUtil.find(ClassStudentScore.class).where()
                .eq("classRoom.classid",classId).eq("student.stuid",stuid)
                .select("id,examTitle,examid,ranking,teacherComment,stuid,classid")
                .fetch("scoreList","courseid,course,score").findList();
        return ApiResult.list("classbrand_getClassStuScoreList",examStudentInfoList).toJson();
    }
    /**
     * 通过
     *十二、 班级学校园一卡通列表信息
     */
    @ResponseBody
    @RequestMapping(value="/classbrand_getClassStuCardList",method= RequestMethod.POST)
    public String ClassStuCardList(HttpServletRequest request) {
        String apiparams=request.getParameter("apiparams");
        log.info("apiparams="+apiparams);
        JSONObject jApiparams= JSONObject.parseObject(apiparams);
        String params=jApiparams.getString("params");
        JSONObject jParams=JSONObject.parseObject(params);
        String accessTlisten=jParams.getString("accessTlisten");
        String classId=jParams.getString("classid");
        log.info("----->>>>>> ClassStuCardList accessTlisten:"+accessTlisten+",classid:"+classId);

        if(StringUtil.isEmpty(classId)){
            return ApiResult.error("classbrand_getClassStuCardList","400" ,"数据请求错误").toSingleJson();
        }
        ClassRoom aClass=EbeanUtil.find(ClassRoom.class).where().eq("classid",classId)
                .select("stuInfo,teacherInfo")
                .fetch("stuInfo","card,stuid,stuname,balance")
                .fetch("teacherInfo","card,teacherid,teacherName,iconpath,balance").setMaxRows(1).findUnique();
        return ApiResult.single("classbrand_getClassStuCardList",aClass).toSingleJson();
    }
    /**
     * 能够插入数据
     *十三、 获取班级班级圈列表数据接口
     */
    @ResponseBody
    @RequestMapping(value="/classbrand_getClassPhotoList",method= RequestMethod.POST)
    public String ClassPhotoList(HttpServletRequest request) {
        String apiparams=request.getParameter("apiparams");
        log.info("apiparams="+apiparams);
        JSONObject jApiparams= JSONObject.parseObject(apiparams);
        String params=jApiparams.getString("params");
        JSONObject jParams=JSONObject.parseObject(params);
        String accessTlisten=jParams.getString("accessTlisten");
        String classId=jParams.getString("classid");
        String ymonth=jParams.getString("ymonth");
        log.info("----->>>>>> ClassPhotoList accessTlisten:"+accessTlisten+",classid:"+classId+",ymonth:"+ymonth);

        if(StringUtil.isEmpty(classId)){
            return ApiResult.error("classbrand_getClassPhotoList","400" ,"数据请求错误").toJson();
        }
        List<Photo> photoList=EbeanUtil.find(Photo.class).where().eq("classRoom.classid",classId)
                .select("id,circleid,content,pariseCount,classid")
                .fetch("photoList","photoUrl").findList();
        return ApiResult.list("classbrand_getClassPhotoList",photoList).toJson();
    }
    /**
     *十四、 班级班级圈点赞接口
     */
    @ResponseBody
    @RequestMapping(value="/classbrand_classCircleParise",method= RequestMethod.POST)
    public String CircleParise(HttpServletRequest request) {
        String apiparams=request.getParameter("apiparams");
        log.info("apiparams="+apiparams);
        JSONObject jApiparams= JSONObject.parseObject(apiparams);
        String params=jApiparams.getString("params");
        JSONObject jParams=JSONObject.parseObject(params);
        String accessTlisten=jParams.getString("accessTlisten");
        String classId=jParams.getString("classid");
        String circleid=jParams.getString("circleid");
        String stuid=jParams.getString("stuid");
        log.info("----->>>>>>CircleParise accessTlisten:"+accessTlisten+",classId:"+classId+",circleid"+circleid+",stuid"+stuid);

        if(StringUtil.isEmpty(classId)){
            return ApiResult.error("classbrand_classCircleParise","400" ,"数据请求错误").toJson();
        }
        //这里应该涉及到修改数据
        List<Photo> photoList=EbeanUtil.find(Photo.class).where().eq("classid",classId)
                .select("id,circleid").findList();
        return ApiResult.list("classbrand_classCircleParise",photoList).toJson();
    }
    /**
     * 可以插入数据
     *十五、 获取班级内家长与学生的通信消息
     * 十五和十六的请求url肯定有一个有问题，因为两个一样
     */
    @ResponseBody
    @RequestMapping(value="/classbrand_getClassParentMsgList",method= RequestMethod.POST)
    public String ClassParentMsgList(HttpServletRequest request) {
        String apiparams=request.getParameter("apiparams");
        log.info("apiparams="+apiparams);
        JSONObject jApiparams= JSONObject.parseObject(apiparams);
        String params=jApiparams.getString("params");
        JSONObject jParams=JSONObject.parseObject(params);
        String accessTlisten=jParams.getString("accessTlisten");
        String classId=jParams.getString("classid");
        log.info("----->>>>>>ClassParentMsgList accessTlisten:"+accessTlisten+",classId:"+classId);
        if(StringUtil.isEmpty(classId)){
            return ApiResult.error("classbrand_getClassParentMsgList","400" ,"数据请求错误").toJson();
        }
        List<MsgList> msgLists=EbeanUtil.find(MsgList.class).where().eq("classid",classId)
                .select("id,msgid,contenttype,content,classid,stuid,photoList")
                .fetch("photoList","id").findList();
        return ApiResult.list("classbrand_getClassParentMsgList",msgLists).toJson();
    }
    /**
     *十六、 获取班级内家长与学校的通信消息
     */
    @ResponseBody
    @RequestMapping(value="/classbrand_getClassParentMsgList2",method= RequestMethod.POST)
    public String ClassParentMsgList2(HttpServletRequest request) {
        String apiparams=request.getParameter("apiparams");
        log.info("apiparams="+apiparams);
        JSONObject jApiparams= JSONObject.parseObject(apiparams);
        String params=jApiparams.getString("params");
        JSONObject jParams=JSONObject.parseObject(params);
        String accessTlisten=jParams.getString("accessTlisten");
        String classId=jParams.getString("classid");
        log.info("----->>>>>>ClassParentMsgList2 accessTlisten:"+accessTlisten+",classId:"+classId);
        if(StringUtil.isEmpty(classId)){
            return ApiResult.error("classbrand_getClassParentMsgList2","400" ,"数据请求错误").toJson();
        }
        List<MsgList> msgLists=EbeanUtil.find(MsgList.class).where().eq("classid",classId)
                .select("id,msgid,contenttype,content,classid,publishdate").findList();
        return ApiResult.list("classbrand_getClassParentMsgList2",msgLists).toJson();
    }
    /**
     *十七、 获取班级内学校发送通信消息
     */
    @ResponseBody
    @RequestMapping(value="/classbrand_getClassSchoolMsgList",method= RequestMethod.POST)
    public String ClassSchoolMsgList(HttpServletRequest request) {
        String apiparams=request.getParameter("apiparams");
        log.info("apiparams="+apiparams);
        JSONObject jApiparams= JSONObject.parseObject(apiparams);
        String params=jApiparams.getString("params");
        JSONObject jParams=JSONObject.parseObject(params);
        String accessTlisten=jParams.getString("accessTlisten");
        String classId=jParams.getString("classid");
        log.info("----->>>>>>ClassSchoolMsgList accessTlisten:"+accessTlisten+",classId:"+classId);

        if(StringUtil.isEmpty(classId)){
            return ApiResult.error("classbrand_getClassSchoolMsgList","400" ,"数据请求错误").toJson();
        }
        List<SchoolMsgList> schoolMsgLists=EbeanUtil.find(SchoolMsgList.class).where().eq("classRoom.classid",classId)
                .select("id,msgid,title,contenttype,content,classid,publishdate").findList();
        return ApiResult.list("classbrand_getClassSchoolMsgList",schoolMsgLists).toJson();
    }
    /**
     *十八、 学生留言信息给家长
     */
    @ResponseBody
    @RequestMapping(value="/classbrand_stuSendMsg",method= RequestMethod.POST)
    public String StuSendMsg(HttpServletRequest request) {
        String apiparams=request.getParameter("apiparams");
        log.info("apiparams="+apiparams);
        JSONObject jApiparams= JSONObject.parseObject(apiparams);
        String params=jApiparams.getString("params");
        JSONObject jParams=JSONObject.parseObject(params);

        String accessTlisten=jParams.getString("accessTlisten");
        String classId=jParams.getString("classid");
        String stuid=jParams.getString("stuid");
        String publishdate=jParams.getString("publishdate");
        String contenttype=jParams.getString("contenttype");
        log.info("----->>>>>>baseGetTlisten accessTlisten:"+accessTlisten+",classId:"+classId+",stuid"+stuid+",publishdate"+publishdate+",contenttype"+contenttype);


        return null;
    }
    /**
     *十九、班级课表接口：classbrand_getClassCourseList（班级课程表信息）
     * 入参：
     * 教室ID（classroomid）、学期（term）
     */
    @ResponseBody
    @RequestMapping(value="/classbrand_getClassCourseList",method= RequestMethod.POST)
    public String getClassCourseList(HttpServletRequest request) {
        String apiparams=request.getParameter("apiparams");
        log.info("apiparams="+apiparams);
        JSONObject jApiparams= JSONObject.parseObject(apiparams);
        String params=jApiparams.getString("params");
        JSONObject jParams=JSONObject.parseObject(params);
        String classroomid=jParams.getString("classroomid");
        String term=jParams.getString("term");
        log.info("----->>>>>>getClassCourseList classroomid:"+classroomid+",term:"+term);
        List<TotalCourse> totalCourseList=EbeanUtil.find(TotalCourse.class).where().
                eq("classRoom.classRoomid",classroomid).eq("weekInfo","5.2——5.6第十二周").findList();//根据当前时间获取第几周的课表
        List<GoClassCourse> goClassCourseList=EbeanUtil.find(GoClassCourse.class).where().eq("classRoom.classRoomid",classroomid).findList();

        List<ApiClassCourse> apiClassCourseList = ApiClassCourseService.generateList(totalCourseList,goClassCourseList);
        return ApiResult.list("classbrand_getClassCourseList", apiClassCourseList).toJson();
    }

    /**
     *获取走班课程
     *接口：classbrand_getTakeClassStuList（班级课程走班课表信息）
     *入参：
     *accessToken（保留）、教室ID（classid）
     */
    @ResponseBody
    @RequestMapping(value="/classbrand_getTakeClassStuList",method= RequestMethod.POST)
    public String getTakeClassStuList(HttpServletRequest request) {
        String apiparams=request.getParameter("apiparams");
        log.info("apiparams="+apiparams);
        JSONObject jApiparams= JSONObject.parseObject(apiparams);
        String params=jApiparams.getString("params");
        JSONObject jParams=JSONObject.parseObject(params);
        String classid=jParams.getString("classid");
        String accessToken=jParams.getString("accessToken");
        log.info("----->>>>>>getClassCourseList classid:"+classid+",accessToken:"+accessToken);
        List<GoClassCourse> goClassCourseList=EbeanUtil.find(GoClassCourse.class).where().eq("classRoom.classid",classid)
                .findList();
        List<ApiTakeClass> apiTakeClassList = ApiTakeClassStuService.generateList(goClassCourseList);
        return ApiResult.list("classbrand_getClassCourseList", apiTakeClassList).toJson();
    }

}
