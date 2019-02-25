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
<title>教师/学生信息显示页面</title>
</head>

<body>
	<div class="container">
	<!-- 学生信息 -->
	<c:if test="${student.role == 0}">
		<h3 align="center">学生信息</h3>
				<table
			class="table  table-striped  table-bordered table-hover table-condensed ">
			<tr style=" text-align:center;">
				<td>编号</td>
				<td>${student.number}</td>
			</tr>
			<tr style=" text-align:center;">
				<td>姓名</td>
				<td>${student.userName}</td>
			</tr>
			<tr style=" text-align:center;">
				<td>性别</td>
				<td><c:if test="${student.gender==0}">男</c:if> <c:if
						test="${student.gender==1}">女</c:if></td>
			</tr>
			<tr style=" text-align:center;">
				<td>生日</td>
				<td>${student.birthday}</td>
			</tr>
			<c:if test="${student.clazz.id !=1}">
			<tr style=" text-align:center;">
				<td>班级</td>
				<td>${student.clazz.clazzName}</td>
			</tr>
			</c:if>
			<tr style=" text-align:center;">
				<td>备注</td>
				<td>${student.describe}</td>
			</tr>
		</table>
	</c:if>
	<!-- 教师信息 -->
	<c:if test="${teacher.role == 1}">
	<h3 align="center">教师信息</h3>
		<table
			class="table  table-striped  table-bordered table-hover table-condensed ">
			<tr style=" text-align:center;">
				<td>编号</td>
				<td>${teacher.number}</td>
			</tr>
			<tr style=" text-align:center;">
				<td>姓名</td>
				<td>${teacher.userName}</td>
			</tr>
			<tr style=" text-align:center;">
				<td>性别</td>
				<td><c:if test="${teacher.gender==0}">男</c:if> <c:if
						test="${teacher.gender==1}">女</c:if></td>
			</tr>
			<tr style=" text-align:center;">
				<td>生日</td>
				<td>${teacher.birthday}</td>
			</tr>
			<tr style=" text-align:center;">
				<td>备注</td>
				<td>${teacher.describe}</td>
			</tr>
		</table>
		
		</c:if>
		</div>
	<jsp:include page="/module/style.jsp"></jsp:include>
</body>
</html>
