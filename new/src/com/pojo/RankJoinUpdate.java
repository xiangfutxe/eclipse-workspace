package com.pojo;

import java.sql.Timestamp;

public class RankJoinUpdate implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private int uid;
	private String userId;
	private String userName;
	private int rankJoinOld;
	private int rankJoinNew;
	private String adminName;
	private double price;
	private Timestamp entryTime;
	
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

	public int getRankJoinOld() {
		return rankJoinOld;
	}

	public void setRankJoinOld(int rankJoinOld) {
		this.rankJoinOld = rankJoinOld;
	}

	public int getRankJoinNew() {
		return rankJoinNew;
	}

	public void setRankJoinNew(int rankJoinNew) {
		this.rankJoinNew = rankJoinNew;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public Timestamp getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Timestamp entryTime) {
		this.entryTime = entryTime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
}