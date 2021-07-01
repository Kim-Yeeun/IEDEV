package com.iedev.web.entity;

import java.sql.Timestamp;

public class Portfolio {

	private String id;
	private String name;
	private String birth;
	private String email;
	private String phone;
	private String certificate;
	private String language;
	private String project;
	private String introduce;
	private Timestamp regDate;
	
	public Portfolio() {}

	public Portfolio(String id, String name, String birth, String email, String phone, String certificate,
			String language, String project, String introduce, Timestamp regDate) {
		this.id = id;
		this.name = name;
		this.birth = birth;
		this.email = email;
		this.phone = phone;
		this.certificate = certificate;
		this.language = language;
		this.project = project;
		this.introduce = introduce;
		this.regDate = regDate;
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

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
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

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "Portfolio [id=" + id + ", name=" + name + ", birth=" + birth + ", email=" + email + ", phone=" + phone
				+ ", certificate=" + certificate + ", language=" + language + ", project=" + project + ", introduce="
				+ introduce + ", regDate=" + regDate + "]";
	};
	
}
