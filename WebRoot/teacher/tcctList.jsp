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
				<td>课程名称</td>
				<td>授课班级</td>
				<c:if test="${action == 'add'|| action == 'query'}">
					<td>操作</td>
				</c:if>
			</tr>
			<c:forEach var="tcct" items="${tcctlist}">
				<tr style=" text-align:center;">
					<td>${tcct.term.name}</td>
					<td>${tcct.course.courseName}</td>
					<td>${tcct.clazz.clazzName}</td>
					<c:if test="${action == 'add' }">
						<td>
						<a class="btn btn-primary btn-xs"
								href="${pageContext.request.contextPath }/user/getAllUser?clazzId=${tcct.clazz.id}&tcctId=${tcct.id}&courseName=${tcct.course.courseName}">查看本班学生</a>
						</td>
					</c:if>
					<c:if test="${action == 'query' }">
						<td>
						<a class="btn btn-primary btn-xs"
								href="${pageContext.request.contextPath }/user/getStudentScore?clazzName=${tcct.clazz.clazzName}&clazzId=${tcct.clazz.id}&tcctId=${tcct.id}&courseName=${tcct.course.courseName}&courseId=${tcct.course.id}">查看本班学生成绩</a>
						</td>
					</c:if>
				</tr>
			</c:forEach>

		</table>
		<ul class="pager">${page.html}
		</ul>
	</div>
	<jsp:include page="/module/style.jsp"></jsp:include>
</body>
</html>
