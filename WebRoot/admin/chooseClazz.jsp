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

<title>选择班级</title>

</head>

<body>
	<div class="container" style="margin-top: -30px;">
		<h3 align="center">班级列表</h3>
		<table align="center"
			class=" table-striped  table-bordered table-hover table-condensed "
			style="width: 100%">
			<tr style=" text-align:center;">
				<td>班级编号</td>
				<td>班级名称</td>
				<td>班级描述</td>
				<c:if test="${flag == 'clazz'}">
					<td>操作</td>
				</c:if>


				<c:if test="${flag != 'clazz'}">
					<td>查看本班学生</td>
				</c:if>

			</tr>
			<c:forEach var="clazz" items="${clazzlist}">
				<tr style=" text-align:center;">
					<td>${clazz.clazzNumber}</td>
					<td>${clazz.clazzName}</td>
					<td>${clazz.describe}</td>
					<td><c:if test="${flag != 'clazz'}">
							<a class="btn btn-primary btn-xs"
								href="${pageContext.request.contextPath }/user/getAllUser?clazzId=${clazz.id}&role=0">查看学生</a>
						</c:if> <c:if test="${flag == 'clazz'}">
							<a class="btn btn-primary btn-xs" onclick=" return(confirm('确定要删除吗？删除班级将删除整班级下的所有学生！！'))"
								href="${pageContext.request.contextPath }/user/deleteClazz?clazzId=${clazz.id}">删除</a>
							<a class="btn btn-primary btn-xs"
								href="${pageContext.request.contextPath }/admin/updateClazz.jsp?clazzId=${clazz.id}
								&clazzName=${clazz.clazzName}&clazzNumber=${clazz.clazzNumber}&describe=${clazz.describe}">修改</a>
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
