package com.pojo;

import java.sql.Timestamp;

public class Modular {
	
	private int id;
	
	private String type;
	
	private String title;
	
	private String contents;
	
	private String publisher;
	
	private Timestamp entryTime;
	
	private Timestamp endTime;
	
	private String source;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public void setPublisher(String publisher) {
		this.publisher = publisher;
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

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
