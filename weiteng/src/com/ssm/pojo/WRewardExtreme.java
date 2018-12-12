package com.ssm.pojo;

import java.sql.Timestamp;


public class WRewardExtreme implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer uid;
	private String userId;
	private String userName;
	private Double amount_1;
	private Double amount_2;
	private Double amount_3;
	private Double amount_4;
	private Integer surplus; //剩余期数
	private Timestamp startTime;
	private Timestamp endTime;
	private Timestamp entryTime; 
	private Integer state;
	private Integer payTag;
	private Integer weekTag;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public Double getAmount_1() {
		return amount_1;
	}
	public void setAmount_1(Double amount_1) {
		this.amount_1 = amount_1;
	}
	public Double getAmount_2() {
		return amount_2;
	}
	public void setAmount_2(Double amount_2) {
		this.amount_2 = amount_2;
	}
	public Double getAmount_3() {
		return amount_3;
	}
	public void setAmount_3(Double amount_3) {
		this.amount_3 = amount_3;
	}
	public Double getAmount_4() {
		return amount_4;
	}
	public void setAmount_4(Double amount_4) {
		this.amount_4 = amount_4;
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
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getSurplus() {
		return surplus;
	}
	public void setSurplus(Integer surplus) {
		this.surplus = surplus;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getWeekTag() {
		return weekTag;
	}
	public void setWeekTag(Integer weekTag) {
		this.weekTag = weekTag;
	}
	public Integer getPayTag() {
		return payTag;
	}
	public void setPayTag(Integer payTag) {
		this.payTag = payTag;
	}
	
	
}