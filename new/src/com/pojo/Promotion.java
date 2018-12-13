package com.pojo;

import java.sql.Timestamp;


public class Promotion implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private int amount_1=0;
	private int amount_2=0;
	private int amount_3=0;
	private int amount_4=0;
	private int amount_5=0;
	private int uid = 0;
	private String userId="";
	private Timestamp entryTime;
	private Timestamp startTime;
	private Timestamp endTime;
	private String comments;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getAmount_1() {
		return amount_1;
	}
	public void setAmount_1(int amount_1) {
		this.amount_1 = amount_1;
	}
	public int getAmount_2() {
		return amount_2;
	}
	public void setAmount_2(int amount_2) {
		this.amount_2 = amount_2;
	}
	public int getAmount_3() {
		return amount_3;
	}
	public void setAmount_3(int amount_3) {
		this.amount_3 = amount_3;
	}
	public int getAmount_4() {
		return amount_4;
	}
	public void setAmount_4(int amount_4) {
		this.amount_4 = amount_4;
	}
	public int getAmount_5() {
		return amount_5;
	}
	public void setAmount_5(int amount_5) {
		this.amount_5 = amount_5;
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
	public Timestamp getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(Timestamp entryTime) {
		this.entryTime = entryTime;
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
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}

}