package com.pojo;

import java.sql.Timestamp;

public class RMoneyBalance implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields

	private Integer id;
	private int uid;
	private String userId;
	private String userName;
	private double transfersEpense;//电子币互转支出
	private double emoneyEpense;//转报单币支出
	private double fillEpense;//账户补扣
	private double cashEpense;//提现支出
	private double returnIncome;//退货转入
	private double cashIncome;//提现退回
	private double transfersIncome;//电子币互转转入
	private double settleIncome;//结算转入
	private double fillIncome;//账户补增
	private double totalEpense;//支出小计
	private double totalIncome;//收入小计
	private double balance; //余额
	private Timestamp entryTime;
	private int state;
	// Constructors
	public Integer getId() {
		return id;
	}
	public int getUid() {
		return uid;
	}
	public String getUserId() {
		return userId.toUpperCase();
	}
	public String getUserName() {
		return userName;
	}
	public double getTransfersEpense() {
		return transfersEpense;
	}
	public double getEmoneyEpense() {
		return emoneyEpense;
	}
	public double getFillEpense() {
		return fillEpense;
	}
	public double getCashEpense() {
		return cashEpense;
	}
	public double getReturnIncome() {
		return returnIncome;
	}
	public double getTransfersIncome() {
		return transfersIncome;
	}
	public double getSettleIncome() {
		return settleIncome;
	}
	public double getFillIncome() {
		return fillIncome;
	}
	public double getTotalEpense() {
		return totalEpense;
	}
	public double getTotalIncome() {
		return totalIncome;
	}
	public double getBalance() {
		return balance;
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
	public void setUid(int uid) {
		this.uid = uid;
	}
	public void setUserId(String userId) {
		this.userId = userId.toUpperCase();
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setTransfersEpense(double transfersEpense) {
		this.transfersEpense = transfersEpense;
	}
	public void setEmoneyEpense(double emoneyEpense) {
		this.emoneyEpense = emoneyEpense;
	}
	public void setFillEpense(double fillEpense) {
		this.fillEpense = fillEpense;
	}
	public void setCashEpense(double cashEpense) {
		this.cashEpense = cashEpense;
	}
	public void setReturnIncome(double returnIncome) {
		this.returnIncome = returnIncome;
	}
	public void setTransfersIncome(double transfersIncome) {
		this.transfersIncome = transfersIncome;
	}
	public void setSettleIncome(double settleIncome) {
		this.settleIncome = settleIncome;
	}
	public void setFillIncome(double fillIncome) {
		this.fillIncome = fillIncome;
	}
	public void setTotalEpense(double totalEpense) {
		this.totalEpense = totalEpense;
	}
	public void setTotalIncome(double totalIncome) {
		this.totalIncome = totalIncome;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public void setEntryTime(Timestamp entryTime) {
		this.entryTime = entryTime;
	}
	public void setState(int state) {
		this.state = state;
	}
	public double getCashIncome() {
		return cashIncome;
	}
	public void setCashIncome(double cashIncome) {
		this.cashIncome = cashIncome;
	}
	
}