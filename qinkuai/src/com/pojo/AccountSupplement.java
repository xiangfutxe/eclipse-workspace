package com.pojo;

import java.sql.Timestamp;
/**
 * JmkAccountDetail entity. @author MyEclipse Persistence Tools
 */

public class AccountSupplement implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private Long id;
	private Integer uid;
	private String userId;
	private String userName;
	private String admin;
	private double amount;
	private int type;
	private int payType;
	private String summary;
	private Timestamp entryTime;
	public Long getId() {
		return id;
	}
	public Integer getuId() {
		return uid;
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
	public int getType() {
		return type;
	}
	public int getPayType() {
		return payType;
	}
	public String getSummary() {
		return summary;
	}
	public Timestamp getEntryTime() {
		return entryTime;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
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
	public void setType(int type) {
		this.type = type;
	}
	public void setPayType(int payType) {
		this.payType = payType;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public void setEntryTime(Timestamp entryTime) {
		this.entryTime = entryTime;
	}

	// Constructors

}
