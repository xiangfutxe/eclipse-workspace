package com.ssm.pojo;

import java.sql.Timestamp;


public class Reward implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Integer uid;
	private String userId;
	private String userName;
	private Double groupPrice;
	private Double areaPrice;
	private Double amount;
	private Double amount_1;
	private Double amount_2;
	private Double amount_3;
	private Integer rankJoin;
	private Integer agentTag;
	private Timestamp startTime;
	private Timestamp endTime;
	private Timestamp entryTime; 
	private Integer state;
	private Integer monthTag;
	private Integer payTag;
	
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
	public Double getGroupPrice() {
		return groupPrice;
	}
	public void setGroupPrice(Double groupPrice) {
		this.groupPrice = groupPrice;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getAmount_1() {
		return amount_1;
	}
	public void setAmount_1(Double amount_1) {
		this.amount_1 = amount_1;
	}
	public Double getAmount_2() {
		return amount_2;
	}
	public void setAmount_2(Double amount_2) {
		this.amount_2 = amount_2;
	}
	public Double getAmount_3() {
		return amount_3;
	}
	public void setAmount_3(Double amount_3) {
		this.amount_3 = amount_3;
	}
	public Integer getRankJoin() {
		return rankJoin;
	}
	public void setRankJoin(Integer rankJoin) {
		this.rankJoin = rankJoin;
	}
	public Integer getAgentTag() {
		return agentTag;
	}
	public void setAgentTag(Integer agentTag) {
		this.agentTag = agentTag;
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
	public Timestamp getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(Timestamp entryTime) {
		this.entryTime = entryTime;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getMonthTag() {
		return monthTag;
	}
	public void setMonthTag(Integer monthTag) {
		this.monthTag = monthTag;
	}
	public Integer getPayTag() {
		return payTag;
	}
	public void setPayTag(Integer payTag) {
		this.payTag = payTag;
	}
	public Double getAreaPrice() {
		return areaPrice;
	}
	public void setAreaPrice(Double areaPrice) {
		this.areaPrice = areaPrice;
	}
	
	
}