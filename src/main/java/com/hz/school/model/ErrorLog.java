package com.hz.school.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 同步接口无法处理情况记录
 * @author carlos
 *
 */
@Entity
@Table(name=Constant.DB_PREFIX+"error_log")
public class ErrorLog extends BaseEntity{

	/**
	 * 发生错误的时间
	 */
     private long errorTime;

    /**
	 * 发生错误的类
	 */
	private String name;
     
     //错误消息(例如同步站点接口: 站点编号:XXXX,站点名:XXXX)
     private String message;
     
     //错误备注(记录导致错误原因)
     @Column(name="remark",length=3000)
     private String remark;
     

	public long getErrorTime() {
		return errorTime;
	}
	public void setErrorTime(long errorTime) {
		this.errorTime = errorTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
        if(message==null) {
            this.message=null;
            return;
        }
        if(message.length()>1000){
            this.message=message.substring(0,1000);
        }else {
            this.message = message;
        }
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public static ErrorLog createSynchronyErrorLog(long errorTime , String name , String message , String remark){
		ErrorLog log = new ErrorLog();
		log.setErrorTime(errorTime);
		log.setName(name);
		log.setMessage(message);
		log.setRemark(remark);
		return log;
	}

}
