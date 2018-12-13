package com.pojo;

import java.sql.Timestamp;

public class AdminLog {
  private int id;
  private String adminName;
  private String contents;
  private Timestamp entryTime;
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

  
}
