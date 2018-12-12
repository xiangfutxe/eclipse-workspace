package com.ssm.pojo;

import java.sql.Timestamp;


public class WRewardDetail implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer uId;
	private String userId;
	private String userName;
	private Integer userById;
	private String userByUserId;
	private String userByUserName;
	private Double amount;
	private Integer type; 
	private String remark;
	private Timestamp startTime;
	private Timestamp endTime;
	private Timestamp entryTime; 
	private Integer weekTag;
	private Integer tag;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getuId() {
		return uId;
	}
	public void setuId(Integer uId) {
		this.uId = uId;
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
	public Integer getUserById() {
		return userById;
	}
	public void setUserById(Integer userById) {
		this.userById = userById;
	}
	public String getUserByUserId() {
		return userByUserId;
	}
	public void setUserByUserId(String userByUserId) {
		this.userByUserId = userByUserId;
	}
	public String getUserByUserName() {
		return userByUserName;
	}
	public void setUserByUserName(String userByUserName) {
		this.userByUserName = userByUserName;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public Integer getWeekTag() {
		return weekTag;
	}
	public void setWeekTag(Integer weekTag) {
		this.weekTag = weekTag;
	}
	public Integer getTag() {
		return tag;
	}
	public void setTag(Integer tag) {
		this.tag = tag;
	}
	
}