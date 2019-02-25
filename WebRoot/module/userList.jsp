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

<title>用户列表</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<div class="container" style="margin-top: -80px;">
		<c:if test="${empty role || role ==0}">
			<h3 align="center">学生列表</h3>
		</c:if>
		<c:if test="${role == 1}">
			<h3 align="center">教师列表</h3>
		</c:if>
		<table
			class="table  table-striped  table-bordered table-hover table-condensed">
			<tr style=" text-align:center;">
				<td>编号</td>
				<td>姓名</td>
				<td>性别</td>
				<td>生日</td>
				<td>备注</td>
				<c:if test="${flag == 'student'}">
					<td>班级</td>
					<td>操作</td>
				</c:if>
				<c:if test="${flag == 'teacher'}">
					<td>班级</td>
					<td>成绩</td>
				</c:if>
				<c:if test="${role == 1 }">
					<td>操作</td>
				</c:if>
			</tr>
			<c:forEach var="user" items="${list}" varStatus="status">
				<tr style=" text-align:center;">
					<td>${user.number}</td>
					<td>${user.userName}</td>
					<td><c:if test="${user.gender==0}">男</c:if> <c:if
							test="${user.gender==1}">女</c:if></td>
					<td>${user.birthday}</td>
					<td>${user.describe}</td>
					<c:if test="${flag == 'student'}">
						<td>${user.clazz.clazzName}</td>
					</c:if>
					<c:if test="${flag == 'teacher'}">
						<td>${user.clazz.clazzName}</td>
					</c:if>
					<td><c:if test="${flag != 'teacher'}">
							<a class="btn btn-primary btn-xs"
								onclick=" return(confirm('确定要删除吗？'))"
								href="${pageContext.request.contextPath}/user/deleteUser?id=${user.id}&clazzId=${user.clazz.id}&role=${user.role}">删除</a>
							<a class="btn btn-primary btn-xs"
								onclick="return(confirm('确定要重置密码？'))"
								href="${pageContext.request.contextPath}/user/resetPwd?id=${user.id}&clazzId=${user.clazz.id}&role=${user.role}"">重置密码</a>
						</c:if> 
							<c:if test="${flag == 'teacher'}">
								<c:if test="${scorearr[status.index] == 0}">
									<a class="btn btn-primary btn-xs"
										href="${pageContext.request.contextPath}/teacher/addScore.jsp?userName=${user.userName}&number=${user.number}&courseName=${courseName}&userId=${user.id}&tcctId=${tcctId}">添加【${courseName}】成绩</a>
								</c:if>
								<c:if test="${scorearr[status.index] > 0}">
									${scorearr[status.index]}  
								</c:if>
							</c:if>
					
					</td>
				</tr>
			</c:forEach>

		</table>
		<ul class="pager">${page.html}
		</ul>
	</div>
	<jsp:include page="/module/style.jsp"></jsp:include>
</body>
</html>
