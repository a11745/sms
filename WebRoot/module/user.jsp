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

<title>My JSP 'user.jsp' starting page</title>
</head>

<body>
	<div class="container">
	<div align="center"><span style="color: red;" >${msg}</span></div>
		<h3 align="center">个人信息</h3>
		<table
			class="table  table-striped  table-bordered table-hover table-condensed ">
			<tr style=" text-align:center;">
				<td>编号</td>
				<td>${user.number}</td>
			</tr>
			<tr style=" text-align:center;">
				<td>姓名</td>
				<td>${user.userName}</td>
			</tr>
			<tr style=" text-align:center;">
				<td>性别</td>
				<td><c:if test="${user.gender==0}">男</c:if> <c:if
						test="${user.gender==1}">女</c:if></td>
			</tr>
			<tr style=" text-align:center;">
				<td>生日</td>
				<td>${user.birthday}</td>
			</tr>
			<c:if test="${user.clazz.id !=1 }">
			<tr style=" text-align:center;">
				<td>班级</td>
				<td>${user.clazz.clazzName}</td>
			</tr>
			</c:if>
			<tr style=" text-align:center;">
				<td>备注</td>
				<td>${user.describe}</td>
			</tr>
			<tr style=" text-align:center;">
				<td colspan="2"><a class="btn btn-primary" href="${pageContext.request.contextPath}/module/updateUser.jsp">更新个人信息</a></td>
			</tr>
		</table>
	</div>
	<jsp:include page="/module/style.jsp"></jsp:include>
</body>
</html>
