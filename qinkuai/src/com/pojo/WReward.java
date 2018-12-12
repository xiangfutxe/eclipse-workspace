package com.pojo;

import java.sql.Timestamp;


public class WReward implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer uId;
	private String userId;
	private String userName;
	private double amount;
	private double organizationAward;
	private double organizationFee;
	private double organizationActual;
	private int organizationTag;
	private double manageAward;
	private double manageFee;
	private double manageActual;
	private int manageTag;
	private double doubleAward;
	private double doubleFee;
	private double doubleActual;
	private int doubleTag;
	private int type; 
	private double newPerformance; 
	private double newPrice; 
	private double doubleAmount;
	private double tax; 
	private double totalAmount;
	private double realWages;
	private Timestamp startTime;
	private Timestamp endTime;
	private Timestamp entryTime; 
	private int state;
	private int tag=0;
	private int payTag=0;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getuId() {
		return uId;
	}
	public void setuId(Integer uId) {
		this.uId = uId;
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
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getOrganizationAward() {
		return organizationAward;
	}
	public void setOrganizationAward(double organizationAward) {
		this.organizationAward = organizationAward;
	}
	public double getOrganizationFee() {
		return organizationFee;
	}
	public void setOrganizationFee(double organizationFee) {
		this.organizationFee = organizationFee;
	}
	public double getOrganizationActual() {
		return organizationActual;
	}
	public void setOrganizationActual(double organizationActual) {
		this.organizationActual = organizationActual;
	}
	public double getOrganizationTag() {
		return organizationTag;
	}
	public void setOrganizationTag(int organizationTag) {
		this.organizationTag = organizationTag;
	}
	public double getManageAward() {
		return manageAward;
	}
	public void setManageAward(double manageAward) {
		this.manageAward = manageAward;
	}
	public double getManageFee() {
		return manageFee;
	}
	public void setManageFee(double manageFee) {
		this.manageFee = manageFee;
	}
	public double getManageActual() {
		return manageActual;
	}
	public void setManageActual(double manageActual) {
		this.manageActual = manageActual;
	}
	public double getManageTag() {
		return manageTag;
	}
	public void setManageTag(int manageTag) {
		this.manageTag = manageTag;
	}
	public double getDoubleAward() {
		return doubleAward;
	}
	public void setDoubleAward(double doubleAward) {
		this.doubleAward = doubleAward;
	}
	public double getDoubleFee() {
		return doubleFee;
	}
	public void setDoubleFee(double doubleFee) {
		this.doubleFee = doubleFee;
	}
	public double getDoubleActual() {
		return doubleActual;
	}
	public void setDoubleActual(double doubleActual) {
		this.doubleActual = doubleActual;
	}
	public double getDoubleTag() {
		return doubleTag;
	}
	public void setDoubleTag(int doubleTag) {
		this.doubleTag = doubleTag;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public double getNewPerformance() {
		return newPerformance;
	}
	public void setNewPerformance(double newPerformance) {
		this.newPerformance = newPerformance;
	}
	public double getNewPrice() {
		return newPrice;
	}
	public void setNewPrice(double newPrice) {
		this.newPrice = newPrice;
	}
	public double getDoubleAmount() {
		return doubleAmount;
	}
	public void setDoubleAmount(double doubleAmount) {
		this.doubleAmount = doubleAmount;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public double getRealWages() {
		return realWages;
	}
	public void setRealWages(double realWages) {
		this.realWages = realWages;
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
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getTag() {
		return tag;
	}
	public void setTag(int tag) {
		this.tag = tag;
	}
	public int getPayTag() {
		return payTag;
	}
	public void setPayTag(int payTag) {
		this.payTag = payTag;
	}
	
	
}