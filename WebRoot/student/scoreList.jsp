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

<title>成绩列表</title>

</head>

<body>
	<div class="container" style="margin-top: -30px;">
		<h3 align="center">${user.userName}成绩单</h3>
		<table align="center"
			class=" table-striped  table-bordered table-hover table-condensed "
			style="width: 100%">
			<tr style=" text-align:center;">
				<td>学期</td>
				<td>课程</td>
				<td>成绩</td>
			</tr>
			<c:forEach var="score" items="${scorelist}" varStatus="status">
				<tr style=" text-align:center;">
					<td>${score.tcct.term.name}</td>
					<td>${score.tcct.course.courseName}</td>
					<td>
					${score.score}
					</td>
				</tr>
			</c:forEach>

		</table>
		<ul class="pager">${page.html}</ul>
	</div>
	<jsp:include page="/module/style.jsp"></jsp:include>
</body>
</html>
