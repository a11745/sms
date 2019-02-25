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

<title>更新课程信息</title>
</head>

<body>
	<div class="container" style="margin-top: -60px;">

			<h3 align="center">更新课程</h3>

		<form action="user/updateCourse" method="post">
			<table class="table  table-striped  table-bordered table-hover ">
				<tr style=" text-align:center;">
					<td>课程编号</td>
					<td><input type="text" name="courseNumber" class="form-control" value="${param.courseNumber}"
						style="width: 200px">
					</td>
				</tr>
				<tr style=" text-align:center;">
					<td>课程名称</td>
					<td><input type="text" name="courseName" class="form-control"value="${param.courseName}"
						style="width: 200px">
					</td>
				</tr>
				<tr style=" text-align:center;">
					<td>课程描述</td>
					<td>
					<input type="text" name="describe" class="form-control"style="width: 200px" value="${param.describe}"> 
					<!-- 隐藏域，保存课程Id -->
					<input type="hidden" name="courseId" value="${param.courseId}">
				</tr>
				<tr style=" text-align:center;">
					<td colspan="2"><input type="submit" class="btn btn-primary"
						style="width: 200px" value="提交">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="/module/style.jsp"></jsp:include>
</body>
</html>
