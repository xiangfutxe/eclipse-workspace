package com.ssm.pojo;

import java.sql.Timestamp;

public class Member {
	
	private Long id;
	
	private String nickName; //标题
	
	private String head;//头像
		 
	 private String password;
	  
	 private String password2;

	 private Integer state;
	  
	 private Integer rank;//会员等级（默认0）
	 
	 private Double emoney; //账户余额
	 
	 private Integer integral;//积分
	 
	 private Long viewNum;//访问次数
	  	 
	 private Long version;
	  
	 private Timestamp entryTime;
	  
	 private Timestamp endTime;
	  
	 private Timestamp startTime;
	  
	 //会员基本信息
	 
	 private Long infoId;
	 
	 private String mobile;
	 
	 private String realName;
	 
	 private String sex; //性别
	  
	 private Timestamp birth;
	 
	 private String education;  //学历
	 
	 private String maritalStatus; //婚姻状况

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	
	
	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Double getEmoney() {
		return emoney;
	}

	public void setEmoney(Double emoney) {
		this.emoney = emoney;
	}

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	public Long getViewNum() {
		return viewNum;
	}

	public void setViewNum(Long viewNum) {
		this.viewNum = viewNum;
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

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
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

	public Long getInfoId() {
		return infoId;
	}

	public void setInfoId(Long infoId) {
		this.infoId = infoId;
	}
	 
	 
	
}
