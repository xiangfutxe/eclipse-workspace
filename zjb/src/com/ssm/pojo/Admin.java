package com.ssm.pojo;

import java.sql.Timestamp;

public class Admin {
	
	private Integer id;
	
	private String adminName; //标题
	
	 private String password;
	 
	  private String password2;
	  
	  private Integer state;
	  
	  private String rank;
	  
	  private Integer type;
	  
	  private String realName;
	  
	  private String deptName;
	  
	  private String jobName;
	  
	  private Integer jobId;
	  
	  private Integer deptId;
	  
	  private String mobile;
	  
	  private Long viewNum;
	  
	  private Long version;
	  
	  private Timestamp entryTime;
	  
	  private Timestamp endTime;
	  
	  private Timestamp startTime;
	  
	public Integer getId() {
		
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
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
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public Integer getJobId() {
		return jobId;
	}
	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
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
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	
}
