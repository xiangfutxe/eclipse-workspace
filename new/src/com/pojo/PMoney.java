package com.pojo;

import java.sql.Timestamp;

public class PMoney implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer uId;
	private String userId;
	private String userName;
	private String admin;
	private double amount;
	private Timestamp validtyTime;
	private String summary;
	private Timestamp entryTime;
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
	public String getUserName() {
		return userName;
	}
	public String getAdmin() {
		return admin;
	}
	public double getAmount() {
		return amount;
	}
	public Timestamp getValidtyTime() {
		return validtyTime;
	}
	public String getSummary() {
		return summary;
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
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public void setValidtyTime(Timestamp validtyTime) {
		this.validtyTime = validtyTime;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public void setEntryTime(Timestamp entryTime) {
		this.entryTime = entryTime;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Integer getuId() {
		return uId;
	}
	public void setuId(Integer uId) {
		this.uId = uId;
	}
	
}