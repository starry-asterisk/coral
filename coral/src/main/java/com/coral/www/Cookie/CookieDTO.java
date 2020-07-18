/* 
 * CookieDTO.java		1.0.0 2020.02.02
 * 
 * Copyright all reserved coral
 */

package com.coral.www.Cookie;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

/**
* @version			1.0.0 2020.01.31
* @author			김현우, 이창현, 박승리, 백현욱, 장지수
*/
@Repository
public class CookieDTO {
	private String id;
	private Date lastused;
	@DateTimeFormat(pattern="yy-MM-dd HH:mm:ss")
	private String token;
	private String series;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getLastused() {
		return lastused;
	}
	public void setLastused(Date lastused) {
		this.lastused = lastused;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getSeries() {
		return series;
	}
	public void setSeries(String series) {
		this.series = series;
	}
	
}
