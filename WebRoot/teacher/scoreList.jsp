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
		<h3 align="center">${clazzName}${courseName}成绩单</h3>
		<table align="center"
			class=" table-striped  table-bordered table-hover table-condensed "
			style="width: 100%">

			<tr style=" text-align:center;">
				<td>学号</td>
				<td>姓名</td>
				<td>成绩</td>
				<td>操作</td>
			</tr>
			<c:forEach var="score" items="${scorelist}">
				<tr style=" text-align:center;">
					<td>${score.user.number}</td>
					<td>${score.user.userName}</td>
					<td>${score.score}</td>
					<td><a class="btn btn-primary btn-xs"
						onclick=" return(confirm('确定要删除吗？'))"
						href="${pageContext.request.contextPath }/user/deleteScore?id=${score.id}&tcctId=${param.tcctId}&clazzName=${param.clazzName}&courseName=${param.courseName}">删除</a>
						<a class="btn btn-primary btn-xs"
						onclick=" return(confirm('确定要修改吗？'))"
						href="${pageContext.request.contextPath }/teacher/updateScore.jsp?score=${score.score}&id=${score.id}&tcctId=${score.tcct.id}&userId=${score.user.id}&userName=${score.user.userName}">修改</a>
					</td>
				</tr>
			</c:forEach>
			<tr style=" text-align:center;">
				<td colspan="4">本班本科目成绩统计&emsp;&emsp; <b>最高分:</b><Strong>${max}</Strong>&emsp;&emsp;
					<b>最低分：</b><Strong>${min}</Strong>&emsp;&emsp;<b>平均分:</b><strong>${avg}</strong>
				</td>
			</tr>
		</table>
		<ul class="pager">${page.html}
		</ul>
	</div>
	<jsp:include page="/module/style.jsp"></jsp:include>
</body>
</html>
