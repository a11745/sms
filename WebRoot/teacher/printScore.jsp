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
		<table align="center"
			class=" table-striped  table-bordered table-hover table-condensed "
			style="width: 70%">
			<tr style=" text-align:center;">
				<td colspan="4">${term}&emsp;${clazzName}&emsp;${courseName}&emsp;成绩单&emsp;
				<a class="btn btn-primary btn-xs" onclick=" return(confirm('成绩单将导出到Excel表格中！'))"
				href="${pageContext.request.contextPath }/user/exportClazzScore?term=${term}&clazzName=${clazzName}
				&courseName=${courseName}&tcctId=${tcctId}&max=${max}&min=${min}&avg=${avg}"
				>导出成绩单</a></td>
			</tr>
			<tr style=" text-align:center;">
				<td>姓名</td>
				<td>成绩</td>
				<td>名次</td>
			</tr>
			<c:forEach var="score" items="${scorelist}" varStatus="status">
				<tr style=" text-align:center;">
					<td>${score.user.userName}</td>
					<td>${score.score}</td>
					<td>${rank[status.index]}</td>
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
