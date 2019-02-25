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

<title>教学安排列表</title>

</head>

<body>
	<div class="container" style="margin-top: -50px;">
		<h3 align="center">${clazzName}-教学安排表</h3>
		<table align="center"
			class=" table-striped  table-bordered table-hover table-condensed "
			style="width: 80%">
			<tr style=" text-align:center;">
				<td>时间/学期</td>
				<td>课程名称</td>
			</tr>
			<c:forEach var="tcct" items="${tcctlist}">
				<tr style=" text-align:center;">
					<td>${tcct.term.name}</td>
					<td>${tcct.course.courseName}</td>
				</tr>
			</c:forEach>

		</table>
		<ul class="pager">${page.html}
		</ul>
	</div>
	<jsp:include page="/module/style.jsp"></jsp:include>
</body>
</html>
