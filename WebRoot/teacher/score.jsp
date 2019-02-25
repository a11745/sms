<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>score</title>
</head>

<body>
	<div class="container">
	<div align="center"><span style="color: red;" >${msg}</span></div>
		<h3 align="center">成绩信息</h3>
		<table
			class="table  table-striped  table-bordered table-hover table-condensed ">
			<tr style=" text-align:center;">
				<td>学号</td>
				<td>${score.user.number}</td>
			</tr>
			<tr style=" text-align:center;">
				<td>姓名</td>
				<td>${score.user.userName}</td>
			</tr>
			<tr style=" text-align:center;">
				<td>课程</td>
				<td>${score.tcct.course.courseName}</td>
			</tr>
			<tr style=" text-align:center;">
				<td>成绩</td>
				<td>${score.score}</td>
			</tr>
		</table>
	</div>
	<jsp:include page="/module/style.jsp"></jsp:include>
</body>
</html>
