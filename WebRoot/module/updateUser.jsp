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

<title>更新用户信息</title>
</head>

<body>
	<div class="container">
		<h3 align="center">更新个人信息</h3>
		<form action="user/updateUser" method="post">
			<table class="table  table-striped  table-bordered table-hover ">
				<tr style=" text-align:center;">
					<td>姓名</td>
					<td><input type="text" name="userName" class="form-control"
						style="width: 200px" value="${user.userName}">
					</td>
				</tr>
				<tr style=" text-align:center;">
					<td>性别</td>
					<td><select name="gender" class="form-control"
						style="width: 100px">
							<option value="0"
								<c:if test="${user.gender==0 }">selected="selected"</c:if>>男</option>
							<option value="1"
								<c:if test="${user.gender==1 }">selected="selected"</c:if>>女</option>
					</select>
					</td>
				</tr>
				<tr style=" text-align:center;">
					<td>生日</td>
					<td>
				     <input type="text" name="birthday" class="form-control"  id="datetimepicker"
						data-date-format="yyyy-mm-dd" style="width: 200px"
						value="${user.birthday}"></td>
				</tr>
				<tr style=" text-align:center;">
					<td>备注</td>
					<td><input type="text" name="describe" class="form-control"
						style="width: 200px" value="${user.describe}">
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
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/js/bootstrap-datetimepicker.min.js"
		charset="UTF-8"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/js/locales/bootstrap-datetimepicker.zh-CN.js"
		charset="UTF-8"></script>
	<script type="text/javascript">
		$('#datetimepicker').datetimepicker({
			language : 'zh-CN',
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 2,
			forceParse : 0,
			startDate : "1900-01-01"	}

		);
	</script>
</body>
</html>
