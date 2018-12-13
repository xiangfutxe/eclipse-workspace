package com.ssm.pojo;

import java.io.Serializable;

public class UidPool  implements Serializable {
	
	private static final long serialVersionUID = 487851422265940763L;
	
	private Integer id; //id
	
	private Integer uid; //号码
	
	private Integer tag;//使用标志
	
	public UidPool(){
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getTag() {
		return tag;
	}

	public void setTag(Integer tag) {
		this.tag = tag;
	}
	
}
