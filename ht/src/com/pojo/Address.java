package com.pojo;

import java.sql.Timestamp;

public class Address {
  private int id;
  private int uid;
  private String userId;
  private String reciver;
  private String province;
  private String city;
  private String area;
  private String adr;
  private String tel;
  private int tag;
  private int state;
  private Timestamp entryTime;
  private Timestamp endTime;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}

public int getUid() {
	return uid;
}
public void setUid(int uid) {
	this.uid = uid;
}
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public String getReciver() {
	return reciver;
}
public void setReciver(String reciver) {
	this.reciver = reciver;
}
public String getProvince() {
	return province;
}
public void setProvince(String province) {
	this.province = province;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getArea() {
	return area;
}
public void setArea(String area) {
	this.area = area;
}
public String getTel() {
	return tel;
}
public void setTel(String tel) {
	this.tel = tel;
}
public int getTag() {
	return tag;
}
public void setTag(int tag) {
	this.tag = tag;
}
public int getState() {
	return state;
}
public void setState(int state) {
	this.state = state;
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
public String getAdr() {
	return adr;
}
public void setAdr(String adr) {
	this.adr = adr;
}

}
