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

<title>添加教学安排</title>
</head>

<body>
	<div class="container" style="margin-top: -60px;">

		<h3 align="center">添加教学安排</h3>
	<span style="color: red">${msg}</span>
		<form action="user/addTcct" method="post">
			<table class="table  table-striped  table-bordered table-hover ">
				<tr style=" text-align:center;">
					<td>学期</td>
					<td><select name="termId" class="form-control"
						style="width: 220px">
							<c:forEach var="term" items="${termList}">
								<option value="${term.id}">${term.name}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr style=" text-align:center;">
					<td>课程</td>
					<td><select name="courseId" class="form-control"
						style="width: 220px">
							<c:forEach var="course" items="${courseList}">
								<option value="${course.id}">${course.courseName}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr style=" text-align:center;">
					<td>班级</td>
					<td><select name="clazzId" class="form-control"
						style="width: 220px">
							<c:forEach var="clazz" items="${clazzList}">
								<option value="${clazz.id}">${clazz.clazzName}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr style=" text-align:center;">
					<td>教师</td>
					<td><select name="userId" class="form-control"
						style="width: 220px">
							<c:forEach var="user" items="${userList}">
								<option value="${user.id}">${user.userName}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr style=" text-align:center;">
					<td colspan="2"><input type="submit" class="btn btn-primary"
						style="width: 220px" value="提交"></td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="/module/style.jsp"></jsp:include>
</body>
</html>
