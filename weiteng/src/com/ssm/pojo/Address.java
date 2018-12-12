package com.ssm.pojo;

import java.sql.Timestamp;

import com.ssm.utils.StringUtil;

public class Address implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer uid;
	private String userId;
	private Timestamp endTime;
	private Timestamp entryTime;
	private String province;
	private String city;
	private String area;
	private String reciver;
	private String address;
	private String phone;
	private Integer tag;
	private Integer state;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getId() {
		return id;
	}
	public String getUserId() {
		return StringUtil.notNull(userId).toUpperCase();
	}
	
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public Timestamp getEntryTime() {
		return entryTime;
	}
	public String getReciver() {
		return reciver;
	}
	public String getAddress() {
		return address;
	}
	public String getPhone() {
		return phone;
	}
	public Integer getTag() {
		return tag;
	}
	public Integer getState() {
		return state;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setUserId(String userId) {
		this.userId =StringUtil.notNull(userId).toUpperCase();
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public void setEntryTime(Timestamp entryTime) {
		this.entryTime = entryTime;
	}
	public void setReciver(String reciver) {
		this.reciver = reciver;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setTag(Integer tag) {
		this.tag = tag;
	}
	public void setState(Integer state) {
		this.state = state;
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

}