/* 
 * ReportDTO.java		1.0.0 2020.02.02
 * 
 * Copyright all reserved coral
 */

package com.coral.www.Report;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

/**
* @version			1.0.0 2020.01.31
* @author			김현우, 이창현, 박승리, 백현욱, 장지수
*/
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
	private int page;
	private int amount;
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
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "ReportDTO [id=" + id + ", object=" + object + ", code=" + code + ", rscode=" + rscode + ", regdate="
				+ regdate + ", status=" + status + ", completedate=" + completedate + ", page=" + page + ", amount="
				+ amount + "]";
	}
}
