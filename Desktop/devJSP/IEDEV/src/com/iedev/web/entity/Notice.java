package com.iedev.web.entity;

import java.util.Date;

public class Notice {
	
	private int no;
	private String title; 
	private Date regDate; 
	private String writerId; 
	private int views; 
	private String files; 
	private String content;
	
	public Notice(){}

	public Notice(int no, String title, Date regDate, String writerId, int views, String files, String content) {
		this.no = no;
		this.title = title;
		this.regDate = regDate;
		this.writerId = writerId;
		this.views = views;
		this.files = files;
		this.content = content;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Notice [no=" + no + ", title=" + title + ", writerId=" + writerId + ", views=" + views + ", files=" + files
				+ ", content=" + content + "]";
	}
}
	
