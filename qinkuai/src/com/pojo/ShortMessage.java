package com.pojo;

import java.sql.Timestamp;

public class ShortMessage implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String type;
	private String shortMsg;
	private String tel;
	private Timestamp entryTime;
	private Timestamp endTime;
	private int state;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getShortMsg() {
		return shortMsg;
	}
	public void setShortMsg(String shortMsg) {
		this.shortMsg = shortMsg;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Timestamp getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(Timestamp entryTime) {
		this.entryTime = entryTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
}