package com.ssm.weixin.pojo;

public class WeixinQRCode {
	
	private String ticket; //获取二维码的ticket
	
	private int expireSeconds;  //二维码的有效时间，单位为秒，最长不超过1800秒

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public int getExpireSeconds() {
		return expireSeconds;
	}

	public void setExpireSeconds(int expireSeconds) {
		this.expireSeconds = expireSeconds;
	}
	
	

}
