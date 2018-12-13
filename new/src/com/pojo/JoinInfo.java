package com.pojo;

import java.sql.Timestamp;

/**
 * JmkJoinInfo entity. @author MyEclipse Persistence Tools
 */
public class JoinInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private int uid;
	private String userId;
	private String userName;
	private int oldRank;
	private int newRank;
	private double price;
	private Timestamp entryTime;
	private Timestamp endTime;
	private int state;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
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
	public int getOldRank() {
		return oldRank;
	}
	public void setOldRank(int oldRank) {
		this.oldRank = oldRank;
	}
	public int getNewRank() {
		return newRank;
	}
	public void setNewRank(int newRank) {
		this.newRank = newRank;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Timestamp getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(Timestamp entryTime) {
		this.entryTime = entryTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	
}