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

<title>课程列表</title>

</head>

<body>
	<div class="container" style="margin-top: -30px;">
		<h3 align="center">课程列表</h3>
		<table align="center"
			class=" table-striped  table-bordered table-hover table-condensed "
			style="width: 100%">
			<tr style=" text-align:center;">
				<td>课程编号</td>
				<td>课程名称</td>
				<td>课程描述</td>
				<td>操作</td>
			</tr>
			<c:forEach var="course" items="${courselist}">
				<tr style=" text-align:center;">
					<td>${course.courseNumber}</td>
					<td>${course.courseName}</td>
					<td>${course.describe}</td>
					<td>
							<a class="btn btn-primary btn-xs"
								onclick=" return(confirm('确定要删除此课程吗？，将会删除含有此课程的教学安排'))"
								href="${pageContext.request.contextPath }/user/deleteCourse?courseId=${course.id}">删除</a>
							<a class="btn btn-primary btn-xs"
								href="${pageContext.request.contextPath }/admin/updateCourse.jsp?courseId=${course.id}
								&courseName=${course.courseName}&courseNumber=${course.courseNumber}&describe=${course.describe}">修改</a>
				</tr>
			</c:forEach>

		</table>
		<ul class="pager">${page.html}
		</ul>
	</div>
	<jsp:include page="/module/style.jsp"></jsp:include>
</body>
</html>
