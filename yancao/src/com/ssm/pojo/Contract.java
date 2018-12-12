package com.ssm.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

public class Contract implements Serializable {
	private static final long serialVersionUID = -8623814343188263878L;
	private Long id;
	private String contractId;
	private String contractName;
	private Integer adminId;
	private String adminName;
	private String code;
	private String partyA;
	private String partyB;
	private Integer level;
	private String dept;
	private String rank;
	private Long borrowNum;
	private Integer borrowTag;
	private Integer state;
	private String remark;
	private Timestamp endTime;
	private Timestamp startTime;
	private Timestamp entryTime;
	private String pdfUrl;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getContractName() {
		return contractName;
	}
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPartyA() {
		return partyA;
	}
	public void setPartyA(String partyA) {
		this.partyA = partyA;
	}
	public String getPartyB() {
		return partyB;
	}
	public void setPartyB(String partyB) {
		this.partyB = partyB;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public Long getBorrowNum() {
		return borrowNum;
	}
	public void setBorrowNum(Long borrowNum) {
		this.borrowNum = borrowNum;
	}
	public Integer getBorrowTag() {
		return borrowTag;
	}
	public void setBorrowTag(Integer borrowTag) {
		this.borrowTag = borrowTag;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public Timestamp getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(Timestamp entryTime) {
		this.entryTime = entryTime;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getPdfUrl() {
		return pdfUrl;
	}
	public void setPdfUrl(String pdfUrl) {
		this.pdfUrl = pdfUrl;
	}
	
	
}
