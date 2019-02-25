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
<title>My JSP 'top.jsp' starting page</title>
</head>
<body>
<!--nav  navbar-inverse -->
	<nav class="navbar navbar-default my navbar-fixed-top"
		role="navigation">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-ex1-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand " href="${pageContext.request.contextPath }/#logo"  target="_top">成绩管理系统</a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->

		<div class="collapse navbar-collapse navbar-ex1-collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a target="_top"
					href="${pageContext.request.contextPath }/#logo"  target="_top">首页</a>
				</li>
			</ul>
			<form class="navbar-form navbar-left" role="search" action="http://www.google.com/search" target="_blank">
					<div class="form-group">
						<input type="text" name="q" class="form-control" placeholder="请输入关键字">
					</div>
					<button type="submit" class="btn btn-default">搜索</button>
				</form>
			<ul class="nav navbar-nav navbar-right" style="padding-right: 10px;">
						<li><a class="link"  href="${pageContext.request.contextPath }/module/user.jsp" target="mainFrame"  >${user.userName}</a>
						</li>
						<li><a href="${pageContext.request.contextPath }/loginOut" class="btn" target="_top">退出登陆</a>
						</li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</nav>
	<jsp:include page="/module/style.jsp"></jsp:include>
</body>
</html>
