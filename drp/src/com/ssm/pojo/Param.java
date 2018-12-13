package com.ssm.pojo;

import java.sql.Timestamp;


public class Param implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String paramName;
	private Double amount_1;
	private Double amount_2;
	private Double amount_3;
	private Double amount_4;
	private Double amount_5;
	private Double amount_6;
	private Double amount_7;
	private Double amount_8;
	private Double amount_9;
	private Double amount_10;
	private String unit; 
	private Timestamp entryTime;
	private Integer tag;
	private Integer state;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getId() {
		return id;
	}
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public Double getAmount_1() {
		return amount_1;
	}
	public void setAmount_1(Double amount_1) {
		this.amount_1 = amount_1;
	}
	public Double getAmount_2() {
		return amount_2;
	}
	public void setAmount_2(Double amount_2) {
		this.amount_2 = amount_2;
	}
	public Double getAmount_3() {
		return amount_3;
	}
	public void setAmount_3(Double amount_3) {
		this.amount_3 = amount_3;
	}
	public Double getAmount_4() {
		return amount_4;
	}
	public void setAmount_4(Double amount_4) {
		this.amount_4 = amount_4;
	}
	public Double getAmount_5() {
		return amount_5;
	}
	public void setAmount_5(Double amount_5) {
		this.amount_5 = amount_5;
	}
	public Double getAmount_6() {
		return amount_6;
	}
	public void setAmount_6(Double amount_6) {
		this.amount_6 = amount_6;
	}
	public Double getAmount_7() {
		return amount_7;
	}
	public void setAmount_7(Double amount_7) {
		this.amount_7 = amount_7;
	}
	public Double getAmount_8() {
		return amount_8;
	}
	public void setAmount_8(Double amount_8) {
		this.amount_8 = amount_8;
	}
	public Double getAmount_9() {
		return amount_9;
	}
	public void setAmount_9(Double amount_9) {
		this.amount_9 = amount_9;
	}
	public Double getAmount_10() {
		return amount_10;
	}
	public void setAmount_10(Double amount_10) {
		this.amount_10 = amount_10;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public Timestamp getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(Timestamp entryTime) {
		this.entryTime = entryTime;
	}

	public Integer getTag() {
		return tag;
	}
	public void setTag(Integer tag) {
		this.tag = tag;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
	
}