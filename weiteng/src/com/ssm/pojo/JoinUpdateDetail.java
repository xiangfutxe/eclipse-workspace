package com.ssm.pojo;

import java.sql.Timestamp;

/**
 * JmkJoinInfo entity. @author MyEclipse Persistence Tools
 */
public class JoinUpdateDetail implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer uid;
	private String userId;
	private String userName;
	private Integer oldRankJoin;
	private Integer newRankJoin;
	private Integer tag;
	private Timestamp entryTime;
	private Integer state;
	private Timestamp startTime;
	private Timestamp endTime;
	public Integer getId() {
		return id;
	}
	public String getUserId() {
		return userId.toUpperCase();
	}
	public String getUserName() {
		return userName;
	}
	public Integer getOldRankJoin() {
		return oldRankJoin;
	}
	public Integer getNewRankJoin() {
		return newRankJoin;
	}
	
	public Integer getTag() {
		return tag;
	}
	public Timestamp getEntryTime() {
		return entryTime;
	}
	public Integer getState() {
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
	public void setOldRankJoin(Integer oldRankJoin) {
		this.oldRankJoin = oldRankJoin;
	}
	public void setNewRankJoin(Integer newRankJoin) {
		this.newRankJoin = newRankJoin;
	}
	
	public void setTag(Integer tag) {
		this.tag = tag;
	}
	public void setEntryTime(Timestamp entryTime) {
		this.entryTime = entryTime;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
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