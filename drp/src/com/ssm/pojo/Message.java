package com.ssm.pojo;

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
	private Integer state;
	private Timestamp startTime;
	private Timestamp endTime;
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
	public Timestamp getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(Timestamp replyTime) {
		this.replyTime = replyTime;
	}
	public Timestamp getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(Timestamp entryTime) {
		this.entryTime = entryTime;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public String getUserMsg() {
		return userMsg;
	}
	public void setUserMsg(String userMsg) {
		this.userMsg = userMsg;
	}
	public String getAdminMsg() {
		return adminMsg;
	}
	public void setAdminMsg(String adminMsg) {
		this.adminMsg = adminMsg;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
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
	

}