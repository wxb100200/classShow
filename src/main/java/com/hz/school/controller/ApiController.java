package com.hz.school.controller;

import com.alibaba.fastjson.JSONObject;
import com.hz.school.api.base_getToken.ApiAccessToken;
import com.hz.school.api.base_getToken.ApiAccessTokenService;
import com.hz.school.api.classbrand_getWeather.ApiWeather;
import com.hz.school.api.classbrand_getWeather.ApiWeatherService;
import com.hz.school.model.AccessToken;
import com.hz.school.model.ClassRoom;
import com.hz.school.util.ApiResult;
import com.hz.school.util.EbeanUtil;
import com.hz.school.util.Logger;
import com.hz.school.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


@Controller
@RequestMapping(value="/api2")
public class ApiController {
	private static Logger log=Logger.getLogger(ApiResource.class);
	/*@ResponseBody
	@RequestMapping(value="/getString",method=RequestMethod.GET)
	public String getString(){
		return "wo shi String";
	}
	@ResponseBody
	@RequestMapping(value="/getStudent",method=RequestMethod.GET)
	public String getStudent(){
		Student st=Ebean.find(Student.class,1l);
		return ApiResult.single("/getStudent", st).toJson();
	}
	@ResponseBody
	@RequestMapping(value="/getStudentFetch",method=RequestMethod.GET)
	public String getStudentFetch(){
		Student st=Ebean.find(Student.class).fetch("parentList","id,name").where().idEq(1).findUnique();
		return ApiResult.single("/getStudent", st).toJson();
	}

	@ResponseBody
	@RequestMapping(value="/getStudents",method=RequestMethod.GET)
	public String getStudents(){
		List<Student> st=Ebean.find(Student.class).findList();
		return ApiResult.list("/getStudent", st).toJson();
	}*/
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
		AccessToken accessToken= EbeanUtil.find(AccessToken.class,1l);
		ApiAccessToken apiAccessToken= ApiAccessTokenService.generateData(accessToken);
		return ApiResult.single("base_getTlisten",apiAccessToken).toJson();
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
		try {
			ApiWeather apiWeather= ApiWeatherService.generateData("101210101");
			return ApiResult.single("classbrand_getWeather",apiWeather).toJson();
		} catch (IOException e) {
			e.printStackTrace();
			return ApiResult.error("classbrand_getWeather","500","ApiWeather todayWeather error").toJson();
		}
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
}
