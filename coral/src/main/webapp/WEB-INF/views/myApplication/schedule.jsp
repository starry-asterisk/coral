<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<a class="calendar" width="400" data-header=true schedule="${ pre_schedule}"></a>
<script>
var calendar = BuildCalendar(".calendar");
</script>