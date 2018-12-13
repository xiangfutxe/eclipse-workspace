package com.pojo;

import java.sql.Timestamp;

public class DeclarationCenter implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String centerId;
	private String userId;
	private String adminName;
	private String password;
	private String centerName;
	private String province;
	private String city;
	private String address;
	private String phone;
	private String area;
	private String type;
	private Integer tag;
	private Timestamp reviewTime;
	private Timestamp endTime;
	private Timestamp entryTime;
	private String state;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getId() {
		return id;
	}
	public String getCenterId() {
		return centerId;
	}
	public String getUserId() {
		return userId;
	}
	public String getAdminName() {
		return adminName;
	}
	public String getPassword() {
		return password;
	}
	public String getCenterName() {
		return centerName;
	}
	public String getProvince() {
		return province;
	}
	public String getCity() {
		return city;
	}
	public String getAddress() {
		return address;
	}
	public String getPhone() {
		return phone;
	}
	public String getArea() {
		return area;
	}
	public String getType() {
		return type;
	}
	public Integer getTag() {
		return tag;
	}
	public Timestamp getReviewTime() {
		return reviewTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public Timestamp getEntryTime() {
		return entryTime;
	}
	public String getState() {
		return state;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setTag(Integer tag) {
		this.tag = tag;
	}
	public void setReviewTime(Timestamp reviewTime) {
		this.reviewTime = reviewTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public void setEntryTime(Timestamp entryTime) {
		this.entryTime = entryTime;
	}
	public void setState(String state) {
		this.state = state;
	}
}