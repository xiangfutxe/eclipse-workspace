package com.ssm.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

public class Job  implements Serializable {
	
	private static final long serialVersionUID = 487851422265940763L;
	
	private Integer id; //id
	
	private String name; //职位名称
	
	private String remark;//详细描述
	
	private Integer state;
	
	private Timestamp entryTime;
	
	private Timestamp endTime;
	
	public Job(){
		super();
	}

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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
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
	
	

}
