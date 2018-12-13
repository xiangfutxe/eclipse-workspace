package com.ssm.pojo;

import java.sql.Timestamp;

public class News {
	
	private Long id;
	
	private String title; //标题
	
	private String contents;//正文
	
	private String publisher;//发布者
	
	private String source;//新闻来源
	
	private String sort;//新闻类型
	
	private Long clicks; //点击次数
	
	private Integer state; //当前状态
	
	private Long version; //当前状态
	
	private Timestamp entryTime;//发布时间
	
	private Timestamp endTime;//最后修改时间
	
	private Timestamp startTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getPublisher() {
		return publisher;
	}

	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Long getClicks() {
		return clicks;
	}

	public void setClicks(Long clicks) {
		this.clicks = clicks;
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

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
	
	
}
