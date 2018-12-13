package com.pojo;

import java.sql.Timestamp;

/**
 * JmkNews entity. @author MyEclipse Persistence Tools
 */
public class VideoInfo implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String admin;
	private int type;
	private Timestamp entryTime;
	private Timestamp endTime;
	private String title;
	private String introduction;
	private int state;
	private String imageUrl;
	private String source;
	private String videoUrl;
	private String videoUrl2;
	private String videoUrl3;
	private String publisher;
	private int browseNum;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String  getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getBrowseNum() {
		return browseNum;
	}
	public void setBrowseNum(int browseNum) {
		this.browseNum = browseNum;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getVideoUrl2() {
		return videoUrl2;
	}
	public void setVideoUrl2(String videoUrl2) {
		this.videoUrl2 = videoUrl2;
	}
	public String getVideoUrl3() {
		return videoUrl3;
	}
	public void setVideoUrl3(String videoUrl3) {
		this.videoUrl3 = videoUrl3;
	}
	
}