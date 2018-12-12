package com.ssm.pojo;

import java.sql.Timestamp;

public class AdminLog {
  private int id;
  private String adminName;
  private String contents;
  private String  type;
  private Integer state;
  private Timestamp entryTime;
  private Timestamp startTime;
  private Timestamp endTime;

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getAdminName() {
	return adminName;
}
public void setAdminName(String adminName) {
	this.adminName = adminName;
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
