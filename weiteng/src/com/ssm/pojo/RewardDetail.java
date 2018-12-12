package com.ssm.pojo;

import java.sql.Timestamp;

public class RewardDetail implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Long id;
	private Integer uid;
	private String userId;
	private String userName;
	private Integer refereeId;
	private String refereeUserId;
	private String refereeUserName;
	private Integer type;
	private Timestamp startTime;
	private Timestamp endTime;
	private Timestamp entryTime;
	private Double amount;
	private Double award;
	private Integer state;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
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
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
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
	public Double getAward() {
		return award;
	}
	public void setAward(Double award) {
		this.award = award;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getRefereeId() {
		return refereeId;
	}
	public void setRefereeId(Integer refereeId) {
		this.refereeId = refereeId;
	}
	public String getRefereeUserId() {
		return refereeUserId;
	}
	public void setRefereeUserId(String refereeUserId) {
		this.refereeUserId = refereeUserId;
	}
	public String getRefereeUserName() {
		return refereeUserName;
	}
	public void setRefereeUserName(String refereeUserName) {
		this.refereeUserName = refereeUserName;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
}