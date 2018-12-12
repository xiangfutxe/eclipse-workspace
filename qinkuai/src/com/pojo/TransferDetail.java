package com.pojo;

import java.sql.Timestamp;

public class TransferDetail implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String userByReceiveId;
	private String userByReceiveName;
	private String userBySendId;
	private String userBySendName;
	private double amount;
	private Timestamp time;
	private int type;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getId() {
		return id;
	}
	public String getUserByReceiveId() {
		return userByReceiveId.toUpperCase();
	}
	public String getUserByReceiveName() {
		return userByReceiveName;
	}
	public String getUserBySendId() {
		return userBySendId.toUpperCase();
	}
	public String getUserBySendName() {
		return userBySendName;
	}
	public double getAmount() {
		return amount;
	}
	public Timestamp getTime() {
		return time;
	}
	public int getType() {
		return type;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setUserByReceiveId(String userByReceiveId) {
		this.userByReceiveId = userByReceiveId.toUpperCase();
	}
	public void setUserByReceiveName(String userByReceiveName) {
		this.userByReceiveName = userByReceiveName;
	}
	public void setUserBySendId(String userBySendId) {
		this.userBySendId = userBySendId.toUpperCase();
	}
	public void setUserBySendName(String userBySendName) {
		this.userBySendName = userBySendName;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public void setType(int type) {
		this.type = type;
	}

	// Constructors

}