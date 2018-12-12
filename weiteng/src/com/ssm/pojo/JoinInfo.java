package com.ssm.pojo;

import java.sql.Timestamp;

/**
 * JmkJoinInfo entity. @author MyEclipse Persistence Tools
 */
public class JoinInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer uid;
	private String userId;
	private String userName;
	private Integer oldRankJoin;
	private Integer newRankJoin;
	private Double price;
	private Double pv;
	private Double pmoney;
	private Integer declarationId;
	private Integer tag;
	private Integer rid;
	private Timestamp entryTime;
	private Integer state;
	private Integer isTouch;
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
	public Double getPrice() {
		return price;
	}
	public Double getPv() {
		return pv;
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
	public void setPrice(Double price) {
		this.price = price;
	}
	public void setPv(Double pv) {
		this.pv = pv;
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
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public Integer getDeclarationId() {
		return declarationId;
	}
	public void setDeclarationId(Integer declarationId) {
		this.declarationId = declarationId;
	}
	public Integer getIsTouch() {
		return isTouch;
	}
	public void setIsTouch(Integer isTouch) {
		this.isTouch = isTouch;
	}
	public Double getPmoney() {
		return pmoney;
	}
	public void setPmoney(Double pmoney) {
		this.pmoney = pmoney;
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