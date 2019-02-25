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
	<div class="container" style="margin-top: -30px;">
		<h3 align="center">教学安排列表</h3>
		<table align="center"
			class=" table-striped  table-bordered table-hover table-condensed "
			style="width: 100%">
			<tr style=" text-align:center;">
				<td>时间/学期</td>
				<td>授课教师</td>
				<td>课程名称</td>
				<td>授课班级</td>
				<td>操作</td>
			</tr>
			<c:forEach var="tcct" items="${tcctlist}">
				<tr style=" text-align:center;">
					<td>${tcct.term.name}</td>
					<td>${tcct.user.userName}</td>
					<td>${tcct.course.courseName}</td>
					<td>${tcct.clazz.clazzName}</td>
					<td>
							<a class="btn btn-primary btn-xs"
								onclick=" return(confirm('确定要删除此教学吗'))"
								href="${pageContext.request.contextPath }/user/deleteTcct?tcctId=${tcct.id}">删除</a>
							<a class="btn btn-primary btn-xs"
								href="${pageContext.request.contextPath }/user/preAddTcct?tcctId=${tcct.id}
								&termId=${tcct.term.id}&courseId=${tcct.course.id}&userId=${tcct.user.id}&clazzId=${tcct.clazz.id}">修改</a>
				</tr>
			</c:forEach>

		</table>
		<ul class="pager">${page.html}
		</ul>
	</div>
	<jsp:include page="/module/style.jsp"></jsp:include>
</body>
</html>
