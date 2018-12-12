package com.ssm.weixin.pojo;

import java.util.List;

public class WeixinUserList {
	
	private int total;
	
	private int count;
	
	private List<String> openIdList;//OpenId列表
	
	private String nextOpenId;//拉取列表的后一个用户的OpenId;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<String> getOpenIdList() {
		return openIdList;
	}

	public void setOpenIdList(List<String> openIdList) {
		this.openIdList = openIdList;
	}

	public String getNextOpenId() {
		return nextOpenId;
	}

	public void setNextOpenId(String nextOpenId) {
		this.nextOpenId = nextOpenId;
	}
	
	

}
