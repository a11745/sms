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

<title>clazz</title>
</head>

<body>
	<div class="container">
	<div align="center"><span style="color: red;" >${msg}</span></div>
		<h3 align="center">班级信息</h3>
		<table
			class="table  table-striped  table-bordered table-hover table-condensed ">
			<tr style=" text-align:center;">
				<td>班级编号</td>
				<td>${clazz.clazzNumber}</td>
			</tr>
			<tr style=" text-align:center;">
				<td>班级名称</td>
				<td>${clazz.clazzName}</td>
			</tr>
			<tr style=" text-align:center;">
				<td>备注</td>
				<td>${clazz.describe}</td>
			</tr>
		</table>
	</div>
	<jsp:include page="/module/style.jsp"></jsp:include>
</body>
</html>
