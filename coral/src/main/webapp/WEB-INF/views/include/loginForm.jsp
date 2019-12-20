<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="id" spellcheck="False"><input type="text" id="id"></div>
<div class="password"><input type="password" id="password"></div>
<button id="login">로그인</button>
<br><br><br><br><br><br><input type="checkbox" id="remember-me" value="remember-me" ><label style="color:white">remember-me</label><br>
<a href="/signUp?grade=teacher" style="color:white">교사가입</a>|<a href="/signUp?grade=student" style="color:white">학생가입</a>	