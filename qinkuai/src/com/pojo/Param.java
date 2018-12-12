package com.pojo;

import java.sql.Timestamp;


public class Param implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	
	private String paramName;
	private double amount_1=0;
	private double amount_2=0;
	private double amount_3=0;
	private double amount_4=0;
	private double amount_5=0;
	private double amount_6=0;
	private double amount_7=0;
	private double amount_8=0;
	private double amount_9=0;
	private double amount_10=0;
	private String unit; 
	private Timestamp entryTime;
	private int tag=0;
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
	public double getAmount_1() {
		return amount_1;
	}
	public void setAmount_1(double amount_1) {
		this.amount_1 = amount_1;
	}
	public double getAmount_2() {
		return amount_2;
	}
	public void setAmount_2(double amount_2) {
		this.amount_2 = amount_2;
	}
	public double getAmount_3() {
		return amount_3;
	}
	public void setAmount_3(double amount_3) {
		this.amount_3 = amount_3;
	}
	public double getAmount_4() {
		return amount_4;
	}
	public void setAmount_4(double amount_4) {
		this.amount_4 = amount_4;
	}
	public double getAmount_5() {
		return amount_5;
	}
	public void setAmount_5(double amount_5) {
		this.amount_5 = amount_5;
	}
	public double getAmount_6() {
		return amount_6;
	}
	public void setAmount_6(double amount_6) {
		this.amount_6 = amount_6;
	}
	public double getAmount_7() {
		return amount_7;
	}
	public void setAmount_7(double amount_7) {
		this.amount_7 = amount_7;
	}
	public double getAmount_8() {
		return amount_8;
	}
	public void setAmount_8(double amount_8) {
		this.amount_8 = amount_8;
	}
	public double getAmount_9() {
		return amount_9;
	}
	public void setAmount_9(double amount_9) {
		this.amount_9 = amount_9;
	}
	public double getAmount_10() {
		return amount_10;
	}
	public void setAmount_10(double amount_10) {
		this.amount_10 = amount_10;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public int getTag() {
		return tag;
	}
	public void setTag(int tag) {
		this.tag = tag;
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
	
}