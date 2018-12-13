package com.ssm.pojo;

import java.sql.Timestamp;

public class Promotion implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Double num1;
	private Double num2;
	private Double num3;
	private Double num4;
	private Double num5;
	private String summary;
	private Timestamp startTime;
	private Timestamp endTime;
	private Timestamp entryTime;
	private Integer state;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getNum1() {
		return num1;
	}
	public void setNum1(Double num1) {
		this.num1 = num1;
	}
	public Double getNum2() {
		return num2;
	}
	public void setNum2(Double num2) {
		this.num2 = num2;
	}
	public Double getNum3() {
		return num3;
	}
	public void setNum3(Double num3) {
		this.num3 = num3;
	}
	public Double getNum4() {
		return num4;
	}
	public void setNum4(Double num4) {
		this.num4 = num4;
	}
	public Double getNum5() {
		return num5;
	}
	public void setNum5(Double num5) {
		this.num5 = num5;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public Timestamp getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(Timestamp entryTime) {
		this.entryTime = entryTime;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	

}