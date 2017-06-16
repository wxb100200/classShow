package com.hz.school.api.classbrand_getBzrClassid;

import com.hz.school.api.ApiEntity;

import javax.persistence.Entity;

/**
 * Created by Administrator on 2017/6/16.
 */
@Entity
public class ApiXqbm extends ApiEntity {
    private Long campusid;

    private String campusid_ch;

    private Boolean currentxq;

    private Long orgcode;

    private String remark;

    private String weekbegin;

    private Integer weeknum;

    private String xnbm;

    private String xqbm;

    private String xqgb;

    private String xqmc;

    public Long getCampusid() {
        return campusid;
    }

    public void setCampusid(Long campusid) {
        this.campusid = campusid;
    }

    public String getCampusid_ch() {
        return campusid_ch;
    }

    public void setCampusid_ch(String campusid_ch) {
        this.campusid_ch = campusid_ch;
    }

    public Boolean getCurrentxq() {
        return currentxq;
    }

    public void setCurrentxq(Boolean currentxq) {
        this.currentxq = currentxq;
    }

    public Long getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(Long orgcode) {
        this.orgcode = orgcode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getWeekbegin() {
        return weekbegin;
    }

    public void setWeekbegin(String weekbegin) {
        this.weekbegin = weekbegin;
    }

    public Integer getWeeknum() {
        return weeknum;
    }

    public void setWeeknum(Integer weeknum) {
        this.weeknum = weeknum;
    }

    public String getXnbm() {
        return xnbm;
    }

    public void setXnbm(String xnbm) {
        this.xnbm = xnbm;
    }

    public String getXqbm() {
        return xqbm;
    }

    public void setXqbm(String xqbm) {
        this.xqbm = xqbm;
    }

    public String getXqgb() {
        return xqgb;
    }

    public void setXqgb(String xqgb) {
        this.xqgb = xqgb;
    }

    public String getXqmc() {
        return xqmc;
    }

    public void setXqmc(String xqmc) {
        this.xqmc = xqmc;
    }
}
