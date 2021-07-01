package com.iedev.web.entity;

import java.util.Date;

public class Apply {

	private int cno;
	private String content;
	private Date regDate;
	private String writerId;
	private int pno;
	
	public Apply() {}

	public Apply(int cno, String content, Date regDate, String writerId, int pno) {
		super();
		this.cno = cno;
		this.content = content;
		this.regDate = regDate;
		this.writerId = writerId;
		this.pno = pno;
	}

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public int getPno() {
		return pno;
	}

	public void setPno(int pno) {
		this.pno = pno;
	};
}
