package com.iedev.web.entity;

import java.util.Date;

public class Member {

	private int type;
	private String id;
	private String name;
	private String password;
	private String email;
	private String phone;
	private Date regDate;
	
	public Member() {};
	
	public Member(int type, String id, String name, String password, String email, String phone, Date regDate) {
		this.type = type;
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.regDate = regDate;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "Member [type=" + type + ", id=" + id + ", name=" + name + ", password=" + password + ", email=" + email
				+ ", phone=" + phone + ", regDate=" + regDate + "]";
	}
}
