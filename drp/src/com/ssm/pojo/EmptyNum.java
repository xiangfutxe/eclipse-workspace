package com.ssm.pojo;

import java.sql.Timestamp;

import com.ssm.utils.StringUtil;
/**
 * JmkAccountDetail entity. @author MyEclipse Persistence Tools
 */

public class EmptyNum implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer uid;
	private String userId;
	private String userName;
	private String admin;
	private Integer amount;
	private Integer balance;
	private Integer type;
	private String summary;
	private Integer state;
	private Timestamp entryTime;
	private Timestamp startTime;
	private Timestamp endTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUserId() {
		return StringUtil.notNull(userId).toUpperCase();
	}
	public void setUserId(String userId) {
		this.userId = StringUtil.notNull(userId).toUpperCase();
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getBalance() {
		return balance;
	}
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
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
	
	
}
