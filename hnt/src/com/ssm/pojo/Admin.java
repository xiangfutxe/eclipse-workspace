package com.ssm.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

public class Admin  implements Serializable  {
	private static final long serialVersionUID = -8623814343188263878L;
private int id;
  private String adminName;
  private String password;
  private String password2;
  private String state;
  private String rank;
  private String realName;
  private String deptName;
  private String jobName;
  private Integer jobId;
  private Integer deptId;
  private String tel;
  private Timestamp entryTime;
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
public String getState() {
	return state;
}
public void setState(String state) {
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

public String getTel() {
	return tel;
}
public void setTel(String tel) {
	this.tel = tel;
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
  
}
