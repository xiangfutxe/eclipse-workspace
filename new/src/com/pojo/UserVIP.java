package com.pojo;
import java.sql.Timestamp;
public class UserVIP implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer uId;
	private String userId;  //用户编号
	private String useName;  //归属人
	private int floor;//第几代;
	private  int ANum; 
	private int BNum; 
	private int state;   //
	private Timestamp entryTime;
	private Timestamp time;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getId() {
		return id;
	}
	public Integer getuId() {
		return uId;
	}
	public String getUserId() {
		return userId.toUpperCase();
	}
	public String getUseName() {
		return useName;
	}
	public int getFloor() {
		return floor;
	}
	public int getANum() {
		return ANum;
	}
	public int getBNum() {
		return BNum;
	}
	public int getState() {
		return state;
	}
	public Timestamp getEntryTime() {
		return entryTime;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setuId(Integer uId) {
		this.uId = uId;
	}
	public void setUserId(String userId) {
		this.userId = userId.toUpperCase();
	}
	public void setUseName(String useName) {
		this.useName = useName;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public void setANum(int aNum) {
		ANum = aNum;
	}
	public void setBNum(int bNum) {
		BNum = bNum;
	}
	public void setState(int state) {
		this.state = state;
	}
	public void setEntryTime(Timestamp entryTime) {
		this.entryTime = entryTime;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
}