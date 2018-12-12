package com.pojo;

import java.sql.Timestamp;

public class RankManage implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private int uId;
	private String userId;
	private String userName;
	private int oldRankManage;
	private int newRankManage;
	private String adminName;
	private Timestamp entryTime;
	
	private int state;
	
	public Integer getId() {
		return id;
	}
	public String getUserId() {
		return userId.toUpperCase();
	}
	public String getUserName() {
		return userName;
	}

	public Timestamp getEntryTime() {
		return entryTime;
	}
	public int getState() {
		return state;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setUserId(String userId) {
		this.userId = userId.toUpperCase();
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void setEntryTime(Timestamp entryTime) {
		this.entryTime = entryTime;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public int getOldRankManage() {
		return oldRankManage;
	}
	
	public void setOldRankManage(int oldRankManage) {
		this.oldRankManage = oldRankManage;
	}
	public int getNewRankManage() {
		return newRankManage;
	}
	public void setNewRankManage(int newRankManage) {
		this.newRankManage = newRankManage;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	
}