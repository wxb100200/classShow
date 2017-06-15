package com.hz.school.model;

import javax.persistence.Entity;

/**
 * 天气
 */
@Entity
public class Weather {
    /**
     * 风向
     */
    private String WD;
    /**
     * 风速
     */
    private String WS;
    /**
     *
     */
    private String altitude;
    /**
     * 城市
     */
    private String city;
    /**
     * 城市编码
     */
    private String citycode;
    /**
     * 日期
     */
    private String date;
    /**
     *最高温度
     */
    private String h_tmp;
    /**
     *最低温度
     */
    private String l_tmp;
    /**
     *纬度
     */
    private String latitude;
    /**
     *经度
     */
    private String longitude;
    /**
     * 城市拼音
     */
    private String pinyin;
    /**
     *城市编码
     */
    private String postCode;
    /**
     *日出时间
     */
    private String sunrise;
    /**
     *日落时间
     */
    private String sunset;
    /**
     * 温度
     */
    private String temp;
    /**
     * 发布时间
     */
    private String time;
    /**
     *天气
     */
    private String weather;

    public String getWD() {
        return WD;
    }

    public void setWD(String WD) {
        this.WD = WD;
    }

    public String getWS() {
        return WS;
    }

    public void setWS(String WS) {
        this.WS = WS;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getH_tmp() {
        return h_tmp;
    }

    public void setH_tmp(String h_tmp) {
        this.h_tmp = h_tmp;
    }

    public String getL_tmp() {
        return l_tmp;
    }

    public void setL_tmp(String l_tmp) {
        this.l_tmp = l_tmp;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }
}
