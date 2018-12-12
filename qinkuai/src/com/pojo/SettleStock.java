package com.pojo;

import java.sql.Timestamp;

public class SettleStock {
  private int id;
  private int weekTag;
  private int state;
  private Timestamp entryTime;
  private Timestamp startTime;
  private Timestamp endTime;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getWeekTag() {
	return weekTag;
}
public void setWeekTag(int weekTag) {
	this.weekTag = weekTag;
}
public Timestamp getEntryTime() {
	return entryTime;
}
public void setEntryTime(Timestamp entryTime) {
	this.entryTime = entryTime;
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
public int getState() {
	return state;
}
public void setState(int state) {
	this.state = state;
}

}
