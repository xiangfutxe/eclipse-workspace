package com.ssm.pojo;

import java.sql.Timestamp;

public class UserLog {
  private Integer id;
  private Integer uid;
  private String userId;
  private String userName;
  private String contents;
  private String  type;
  private Integer state;
  private Timestamp entryTime;
  private Timestamp startTime;
  private Timestamp endTime;

public Integer getId() {
	return id;
}
public void setId(Integer id) {
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
public String getContents() {
	return contents;
}
public void setContents(String contents) {
	this.contents = contents;
}
public Timestamp getEntryTime() {
	return entryTime;
}
public void setEntryTime(Timestamp entryTime) {
	this.entryTime = entryTime;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
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
public Integer getState() {
	return state;
}
public void setState(Integer state) {
	this.state = state;
}
  
}
