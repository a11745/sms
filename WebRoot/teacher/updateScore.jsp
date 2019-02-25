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

<title>更新成绩</title>
</head>

<body>
	<div class="container">
		
		<form action="user/updateScore" method="post">

			<table class="table  table-striped  table-bordered table-hover " style="width: 80%">
			<h3 align="center" style="width: 80%">更新成绩</h3>
				<tr style=" text-align:center;">
					<td >姓名</td>
					<td align="left">${param.userName}</td>
					</tr>
				<tr style=" text-align:center;">
					<td>成绩</td>
					<td><input  type="text" name="score" class="form-control"
						style="width: 200px" value="${param.score}"> <input
						type="hidden" name="userId" value="${param.userId}"> <input
						type="hidden" name="tcctId" value="${param.tcctId}"> <input
						type="hidden" name="id" value="${param.id}">
					</td>
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
