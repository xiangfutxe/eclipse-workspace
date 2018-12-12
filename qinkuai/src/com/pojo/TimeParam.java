package com.pojo;

import java.sql.Timestamp;

/**
 * JmkParam entity. @author MyEclipse Persistence Tools
 */

public class TimeParam implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String paramName;
	private Timestamp startTime;
	private double weekNum;
	private Integer monthNum;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getId() {
		return id;
	}
	public String getParamName() {
		return paramName;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public double getWeekNum() {
		return weekNum;
	}
	public Integer getMonthNum() {
		return monthNum;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public void setWeekNum(double weekNum) {
		this.weekNum = weekNum;
	}
	public void setMonthNum(Integer monthNum) {
		this.monthNum = monthNum;
	}

	

}