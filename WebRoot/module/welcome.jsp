<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'welcome.jsp' starting page</title>

  </head>
  
  <body>
  	<div class="jumbotron" style="margin-top: -20px">
		<div class="row" >
			<div align="center">
				<h1>
					<Strong>Welcome to</Strong>
				</h1>
				<h3>HeiLongJiang Science and Technology University</h3>
				<h3>Score Management System</h3>
				
				<h2>
				<c:if test="${user.role==0 }">
				<font class="btn btn-primary">${user.userName}</font>同学  <Strong>欢迎您登陆</Strong>
				</c:if>
					<c:if test="${user.role==1 }">
				<font class="btn btn-primary">${user.userName}</font>老师  <Strong>欢迎您登陆</Strong>
				</c:if>
					<c:if test="${user.role==2 }">
				<font class="btn btn-primary" >${user.userName}</font>【系统管理员】  <Strong>欢迎您登陆</Strong>
				</c:if>	
				</h2>
					<h3><Strong>黑龙江科技大学 成绩管理系统</Strong></h3>

			</div>
		</div>
	</div>
  
   <jsp:include page="/module/style.jsp"></jsp:include>
  </body>
</html>
