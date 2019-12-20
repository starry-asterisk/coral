<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>error</title>
</head>

<body>
<h1>${ErrorMSG}</h1>
<hr>
<br>
</body>
<script>${Code}<%=request.getParameter("Code")%></script>
</html>