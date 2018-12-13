package com.weixin.pojo;

import java.sql.Timestamp;

public class Token {

	//接口访问凭证
	private  String accessToken;
	//凭证有效期，单位，秒；
	private int expiresIn;
	
	private int state;
	
	private Timestamp entryTime;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Timestamp getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Timestamp entryTime) {
		this.entryTime = entryTime;
	}
	
	
}
