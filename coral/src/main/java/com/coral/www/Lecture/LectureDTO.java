package com.coral.www.Lecture;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class LectureDTO {
	private String cl_no;
	private String cl_title;
	private String cl_tag;
	private String cl_description;
	private String id;
	private String no;
	private String title;
	private String contents;
	@DateTimeFormat(pattern="yy-MM-dd HH:mm:ss")
	private Date regdate;
	@DateTimeFormat(pattern="yy-MM-dd HH:mm:ss")
	private Date upddate;
	private int views;
	private int recommends;
	private char attachment;
	private char status;
	private int amount;
	private int page;
	private String tag="false";
	public String getCl_no() {
		return cl_no;
	}

	public void setCl_no(String cl_no) {
		this.cl_no = cl_no;
	}

	public String getCl_title() {
		return cl_title;
	}

	public void setCl_title(String cl_title) {
		this.cl_title = cl_title;
	}

	public String getCl_tag() {
		return cl_tag;
	}

	public void setCl_tag(String cl_tag) {
		this.cl_tag = cl_tag;
	}

	public String getCl_description() {
		return cl_description;
	}

	public void setCl_description(String cl_description) {
		this.cl_description = cl_description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
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

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public int getRecommends() {
		return recommends;
	}

	public void setRecommends(int recommends) {
		this.recommends = recommends;
	}

	public char getAttachment() {
		return attachment;
	}

	public void setAttachment(char attachment) {
		this.attachment = attachment;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getTag() {
		return tag;
	}
}
