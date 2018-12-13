package com.ssm.pojo;

import java.sql.Timestamp;

public class MemberInfo {
	
	private Long id;
	
	private Long memberId; //标题
	  
	 private String mobile;
	 
	 private String realName;
	 
	 private String sex; //性别
	  
	 private Timestamp birth;
	 
	 private String education;  //学历
	 
	 private String maritalStatus; //婚姻状况
	  	  
	 private Long version;
	  
	 private Timestamp entryTime;
	  
	 private Timestamp endTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Timestamp getBirth() {
		return birth;
	}

	public void setBirth(Timestamp birth) {
		this.birth = birth;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
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
	  
	
	
}
