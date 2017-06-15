package com.hz.school.controller;

import com.hz.school.util.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ApiResource2 {
    private static Logger log=Logger.getLogger(ApiResource2.class);
    /**
     * 一、获取AccessTlisten令牌接口（延后）
     */
    @ResponseBody
    @RequestMapping(value="/iclock/cdata2",method= RequestMethod.POST,produces="text/html;charset=UTF-8")
    public String baseGetTlisten(HttpServletRequest request){
        String apiparams=request.getParameter("apiparams");
       /* log.info("apiparams="+apiparams);
        JSONObject jApiparams= JSONObject.parseObject(apiparams);
        String params=jApiparams.getString("params");
        JSONObject jParams=JSONObject.parseObject(params);
        String appId=jParams.getString("appId");
        String appSecret=jParams.getString("appSecret");
        log.info("----->>>>>>baseGetTlisten appId:"+appId+",appSecret:"+appSecret);
        AccessToken accessTlisten= EbeanUtil.find(AccessToken.class,1l);*/
        return "";
    }

}
