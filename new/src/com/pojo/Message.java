package com.pojo;

import java.sql.Timestamp;

public class Message implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String userId;
	private String userName;
	private Timestamp replyTime;
	private Timestamp entryTime;
	private String admin;
	private String userMsg;
	private String adminMsg;
	private int state;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getId() {
		return id;
	}
	public String getUserId() {
		return userId.toUpperCase();
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
	
	public void setEntryTime(Timestamp entryTime) {
		this.entryTime = entryTime;
	}

	public void setState(int state) {
		this.state = state;
	}
	public String getUserName() {
		return userName;
	}
	public Timestamp getReplyTime() {
		return replyTime;
	}
	public String getAdmin() {
		return admin;
	}
	public String getUserMsg() {
		return userMsg;
	}
	public String getAdminMsg() {
		return adminMsg;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setReplyTime(Timestamp replyTime) {
		this.replyTime = replyTime;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public void setUserMsg(String userMsg) {
		this.userMsg = userMsg;
	}
	public void setAdminMsg(String adminMsg) {
		this.adminMsg = adminMsg;
	}
	

}