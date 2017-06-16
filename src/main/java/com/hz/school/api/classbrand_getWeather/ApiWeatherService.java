package com.hz.school.api.classbrand_getWeather;

import com.hz.school.util.Logger;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Administrator on 2017/6/16.
 */
public class ApiWeatherService {
    private static Logger log=Logger.getLogger(ApiWeatherService.class);
    public static ApiWeather generateData(String cityId) throws IOException {
        String data1=commitUrl("http://www.apiWeather.com.cn/data/cityinfo/" + cityId+ ".html");
        String data2=commitUrl("http://www.apiWeather.com.cn/data/sk/" + cityId+ ".html");
        JSONObject jsonData1 = JSONObject.fromObject(data1);
        JSONObject jsonData2 = JSONObject.fromObject(data2);
        JSONObject info1 = jsonData1.getJSONObject("weatherinfo");
        JSONObject info2 = jsonData2.getJSONObject("weatherinfo");
        ApiWeather apiWeather=new ApiWeather();
        apiWeather.setWD(info2.getString("WD"));
        apiWeather.setWS(info2.getString("WS"));
        apiWeather.setAltitude("");
        apiWeather.setCity(info1.getString("city"));
        apiWeather.setCitycode(info1.getString("cityid"));
        apiWeather.setDate("");
        apiWeather.setH_tmp(info1.getString("temp2"));
        apiWeather.setL_tmp(info1.getString("temp1"));
        apiWeather.setLatitude("");
        apiWeather.setLongitude("");
        apiWeather.setPinyin("hangzhou");
        apiWeather.setPostCode("101210101");
        apiWeather.setSunrise("");
        apiWeather.setSunset("");
        apiWeather.setTemp(info2.getString("temp"));
        apiWeather.setTime(info2.getString("time"));
        apiWeather.setWeather(info1.getString("apiWeather"));
        return apiWeather;
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
}
