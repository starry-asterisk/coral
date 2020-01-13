package com.coral.www.like;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ReplyDTO {
	private String bno;
	private int no;
	private String id; 
	private String content;
	@DateTimeFormat(pattern="yy-MM-dd HH:mm:ss")
	private Date regdate;
	@DateTimeFormat(pattern="yy-MM-dd HH:mm:ss")
	private Date upddate;
	private char status;
	private String path;
	public String getBno() {
		return bno;
	}
	public void setBno(String bno) {
		this.bno = bno;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Date getUpddate() {
		return upddate;
	}
	public void setUpddate(Date upddate) {
		this.upddate = upddate;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
