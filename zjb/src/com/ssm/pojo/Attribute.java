package com.ssm.pojo;

import java.sql.Timestamp;

public class Attribute {
	
	private Integer id;
	
	private String name;
	
	private String type;
	
	private Integer parentId;
	
	private Integer state;
	
	private Long version;
	
	private Timestamp entryTime;
	
	private Timestamp endTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
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
