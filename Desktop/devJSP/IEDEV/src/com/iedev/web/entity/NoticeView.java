package com.iedev.web.entity;

import java.sql.Timestamp;

public class NoticeView extends Notice {
	
	private int cmtCount;
	
	public int getCmtCount() {
		return cmtCount;
	}

	public void setCmtCount(int cmtCount) {
		this.cmtCount = cmtCount;
	}

	public NoticeView() {}

	public NoticeView(int id, String title, Timestamp regDate, String writerId, int hit, String files,
			int cmtCount) {
		super(id, title, regDate, writerId, hit, files, "");
		this.cmtCount = cmtCount;
	}

}
