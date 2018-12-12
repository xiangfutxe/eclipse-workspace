package com.pojo;

import java.sql.Timestamp;


public class WRewardDetail implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer uId;
	private String userId;
	private String userName;
	private int userById;
	private String userByUserId;
	private String userByUserName;
	private double amount;
	private int type; 
	private String remark;
	private Timestamp startTime;
	private Timestamp endTime;
	private Timestamp entryTime; 
	private int weekTag;
	private int tag=0;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getId() {
		return id;
	}
	public String getUserId() {
		return userId.toUpperCase();
	}
	
	public Integer getuId() {
		return uId;
	}
	public void setuId(Integer uId) {
		this.uId = uId;
	}
	public String getUserName() {
		return userName;
	}
	public int getType() {
		return type;
	}
	public int getUserById() {
		return userById;
	}
	public void setUserById(int userById) {
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
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
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
	public int getTag() {
		return tag;
	}
	public void setTag(int tag) {
		this.tag = tag;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getWeekTag() {
		return weekTag;
	}
	public void setWeekTag(int weekTag) {
		this.weekTag = weekTag;
	}
	
}