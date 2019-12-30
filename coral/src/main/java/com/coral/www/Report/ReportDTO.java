package com.coral.www.Report;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

@Repository
public class ReportDTO {
	private String id;//신고자 아이디
	private String object;//피신고자 아이디
	private String code;//신고대상분류코드
	private String rscode;//사유코드
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date regdate;//신고일자
	private String status;//처리상태
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date completedate;//처리일자
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getRscode() {
		return rscode;
	}
	public void setRscode(String rscode) {
		this.rscode = rscode;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCompletedate() {
		return completedate;
	}
	public void setCompletedate(Date completedate) {
		this.completedate = completedate;
	}
}
