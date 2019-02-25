<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'updatePwd.jsp' starting page</title>
    
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
  <div class="container">
   <div align="center"><span style="color: red;" >${msg}</span></div>
  <form action="user/updatePwd" method="post">
  <h3 align="center"> 修改密码</h3>
   <table  class="table  table-striped  table-bordered table-hover table-condensed">
   	<tr style=" text-align:center;">
   		<td>旧密码</td>
   		<td><input type="password" name="oldPassword" class="form-control" placeholder="请输入旧密码" style="width: 200px"/></td>
   	</tr >
   		<tr style=" text-align:center;">
   		<td>新密码</td>
   		<td><input type="password" name="newPassword" class="form-control" placeholder="请输入新密码" style="width: 200px"/></td>
   	</tr>
   		<tr style=" text-align:center;">
   		<td>确认密码</td>
   		<td><input type="password" name="confirmPassword" class="form-control " placeholder="确认新密码" style="width: 200px"/></td>
   	</tr>
   	<tr>
   		<td colspan="2" align="center"> 
   		<input type="submit" value="提交" class="btn btn-primary ">
   		</td>
   	</tr>
   </table>
   </form>
   </div>
   <jsp:include page="/module/style.jsp"></jsp:include>
   
  </body>
</html>
