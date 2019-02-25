<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<title>add Score</title>
</head>

<body>
	<div class="container">
					<h4  align="center">添加成绩</h4>
					<form action="user/addScore" method="post">
						<table class="table table-striped  table-bordered table-hover " style="width: 60%"
							 align="center">
							<tr style=" text-align:center;">
								<td>学号</td>
								<td>${param.number}</td>
							</tr>
							<tr style=" text-align:center;">
								<td>姓名</td>
								<td>${param.userName}</td>
							</tr>
							<tr style=" text-align:center;">
								<td>课程</td>
								<td>${param.courseName}</td>
							</tr>
							<tr style=" text-align:center;">
								<td>成绩</td>
								<td align="center"><input type="text" name="score" placeholder="请输入学生成绩(0~150分)"
									class="form-control" style="width: 200px"> <input
									type="hidden" name="userId" value="${param.userId}" /> <input
									type="hidden" name="tcctId" value="${param.tcctId}" />
								</td>
							</tr>
							<tr style=" text-align:center;">
								<td colspan="2"><input type="submit" style="width: 200px"
									class="btn btn-primary " value="提交">
								</td>
							</tr>
						</table>
					</form>
					</div>
	<jsp:include page="/module/style.jsp"></jsp:include>
</body>
</html>
