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

<title>tcct</title>
</head>

<body>
	<div class="container">
		<div align="center">
			<span style="color: red;">${msg}</span>
		</div>
		<h3 align="center">教学安排信息</h3>
		<table
			class="table  table-striped  table-bordered table-hover table-condensed ">
			<tr style=" text-align:center;">
				<td>时间/学期</td>
				<td>${tcct.term.name}</td>
			</tr>
			<tr style=" text-align:center;">
				<td>授课教师</td>
				<td>${tcct.user.userName}</td>
			</tr>
			<tr style=" text-align:center;">
				<td>课程名称</td>
				<td>${tcct.course.courseName}</td>
			</tr>
			<tr style=" text-align:center;">
				<td>授课班级</td>
				<td>${tcct.clazz.clazzName}</td>
			</tr>
		</table>
	</div>
	<jsp:include page="/module/style.jsp"></jsp:include>
</body>
</html>
