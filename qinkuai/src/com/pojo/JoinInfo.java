package com.pojo;

import java.sql.Timestamp;

/**
 * JmkJoinInfo entity. @author MyEclipse Persistence Tools
 */
public class JoinInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private int uid;
	private String userId;
	private String userName;
	private int oldRankJoin;
	private int newRankJoin;
	private double price;
	private double pv;
	private int declarationId;
	private int tag;
	private int rid;
	private Timestamp entryTime;
	private int state;
	public Integer getId() {
		return id;
	}
	public String getUserId() {
		return userId.toUpperCase();
	}
	public String getUserName() {
		return userName;
	}
	public int getOldRankJoin() {
		return oldRankJoin;
	}
	public int getNewRankJoin() {
		return newRankJoin;
	}
	public double getPrice() {
		return price;
	}
	public double getPv() {
		return pv;
	}
	public int getTag() {
		return tag;
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
	public void setOldRankJoin(int oldRankJoin) {
		this.oldRankJoin = oldRankJoin;
	}
	public void setNewRankJoin(int newRankJoin) {
		this.newRankJoin = newRankJoin;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setPv(double pv) {
		this.pv = pv;
	}
	public void setTag(int tag) {
		this.tag = tag;
	}
	public void setEntryTime(Timestamp entryTime) {
		this.entryTime = entryTime;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getDeclarationId() {
		return declarationId;
	}
	public void setDeclarationId(int declarationId) {
		this.declarationId = declarationId;
	}
	
}