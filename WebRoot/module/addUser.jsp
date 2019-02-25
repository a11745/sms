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

<title>添加用户</title>
</head>

<body>
		<div class="container" style="margin-top: -60px;">
		<c:if test="${param.role==0}">
			<h3 align="center">添加学生</h3>
		</c:if>
		<c:if test="${role==1}">
			<h3 align="center">添加教师</h3>
		</c:if>

		<form action="user/addUser" method="post">
			<table class="table  table-striped  table-bordered table-hover ">

				<c:if test="${param.role==0}">
					<tr style=" text-align:center;">
						<td>请选择班级</td>
						<td><select name="clazzId" class="form-control" style="width: 200px">
								<c:forEach var="clazz" items="${clazzlist}">
									<option value="${clazz.id}">${clazz.clazzName}</option>
								</c:forEach>
						</select>
						</td>
					</tr>
				</c:if>
				<tr style=" text-align:center;">
					<td>编号</td>
					<td><input type="text" name="number" class="form-control"
						style="width: 200px">
					</td>
				</tr>
				<tr style=" text-align:center;">
					<td>姓名</td>
					<td><input type="text" name="userName" class="form-control"
						style="width: 200px">
					</td>
				</tr>
				<tr style=" text-align:center;">
					<td>性别</td>
					<td><select name="gender" class="form-control"
						style="width: 200px">
							<option value="0">男</option>
							<option value="1">女</option>
					</select>
					</td>
				</tr>
				<tr style=" text-align:center;">
					<td>生日</td>
					<td><input type="text" name="birthday" class="form-control"
						id="addUser" data-date-format="yyyy-mm-dd"
						style="width: 200px"></td>
				</tr>
				<tr style=" text-align:center;">
					<td>备注</td>
					<td><input type="text" name="describe" class="form-control"
						style="width: 200px"> <!-- 此页面是添加 学生 隐藏域班级Id --> <c:if
							test="${param.role==0}">
							<input type="hidden" name="clazzId" value="${param.clazzId}">
							<input type="hidden" name="role0" value="0">
						</c:if> 
						<!-- 此页面是添加教师 --> <c:if test="${role==1}">
							<input type="hidden" name="role1" value="1">
						</c:if></td>
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
		$('#addUser').datetimepicker({
			language : 'zh-CN',
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 2,
			forceParse : 0,
			startDate : "1900-01-01"
		}

		);
	</script>
</body>
</html>
