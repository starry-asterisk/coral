package com.coral.www.Report;

import org.springframework.stereotype.Repository;

@Repository
public class ReportDTO {
	private String code;
	private String content;
	private int due;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getDue() {
		return due;
	}
	public void setDue(int due) {
		this.due = due;
	}
}
