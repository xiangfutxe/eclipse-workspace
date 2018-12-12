package com.pojo;

import java.sql.Timestamp;

public class EMoneyBalance implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private int id;
	private int uid;
	private String userId;
	private String userName;
	private double declarationEpense;//报单支出
	private double shoppingEpense;//零售购物支出
	private double transfersEpense;//电子币互转支出
	private double upEpense;//升级支出
	private double fillEpense;//账户补扣
	private double cashIncome;//现金充值
	private double remitIncome;//汇款充值
	private double declarationIncome;//退单转入
	private double returnIncome;//退货转入
	private double transfersIncome;//电子币互转转入
	private double rmoneyIncome;//奖金币转入
	private double fillIncome;//账户补增
	private double upIncome;//升级退回
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
	public void setId(int id) {
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

	public double getDeclarationEpense() {
		return declarationEpense;
	}
	public double getShoppingEpense() {
		return shoppingEpense;
	}
	public double getTransfersEpense() {
		return transfersEpense;
	}
	public double getUpEpense() {
		return upEpense;
	}
	public double getFillEpense() {
		return fillEpense;
	}
	public double getCashIncome() {
		return cashIncome;
	}
	public double getRemitIncome() {
		return remitIncome;
	}
	public double getReturnIncome() {
		return returnIncome;
	}
	public double getTransfersIncome() {
		return transfersIncome;
	}
	public double getRmoneyIncome() {
		return rmoneyIncome;
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
	public void setDeclarationEpense(double declarationEpense) {
		this.declarationEpense = declarationEpense;
	}
	public void setShoppingEpense(double shoppingEpense) {
		this.shoppingEpense = shoppingEpense;
	}
	public void setTransfersEpense(double transfersEpense) {
		this.transfersEpense = transfersEpense;
	}
	public void setUpEpense(double upEpense) {
		this.upEpense = upEpense;
	}
	public void setFillEpense(double fillEpense) {
		this.fillEpense = fillEpense;
	}
	public void setCashIncome(double cashIncome) {
		this.cashIncome = cashIncome;
	}
	public void setRemitIncome(double remitIncome) {
		this.remitIncome = remitIncome;
	}
	public void setReturnIncome(double returnIncome) {
		this.returnIncome = returnIncome;
	}
	public void setTransfersIncome(double transfersIncome) {
		this.transfersIncome = transfersIncome;
	}
	public void setRmoneyIncome(double rmoneyIncome) {
		this.rmoneyIncome = rmoneyIncome;
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

	public double getDeclarationIncome() {
		return declarationIncome;
	}

	public void setDeclarationIncome(double declarationIncome) {
		this.declarationIncome = declarationIncome;
	}

	public double getUpIncome() {
		return upIncome;
	}

	public void setUpIncome(double upIncome) {
		this.upIncome = upIncome;
	}


}