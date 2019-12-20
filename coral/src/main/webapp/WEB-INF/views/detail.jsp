<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<a onclick="history.back()">${bno}</a>
<style>
a:hover{
cursor:pointer;
}
</style>
<jsp:include page="${contextPath }\include\footer.jsp"></jsp:include>
<script src="${contextPath}/js/web-functions.js" type="text/javascript" charset="utf-8"></script>
<script>${Code}<%=request.getParameter("Code")%></script>