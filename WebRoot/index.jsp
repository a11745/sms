<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<base href="<%=basePath%>">
<title>黑龙江科技大学成绩管理系 首页</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- 为了确保适当的绘制和触屏缩放，需要在<head>之中添加viewport元数据标签。-->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%--IE 兼容性--%>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- 最新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/index.css">
<link rel="stylesheet" href="css/application.css">

</head>

<body>
	<!--nav  navbar-inverse -->
	<nav class="navbar navbar-default my navbar-fixed-top" id="#nav"
		role="navigation">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-ex1-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand " href="#logo">成绩管理系统</a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->

		<div class="collapse navbar-collapse navbar-ex1-collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="#scene">校园风光</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">学校概况 <b class="caret"></b> </a>
					<ul class="dropdown-menu">
						<li><a href="#jianjie">学校简介</a></li>
						<li><a href="#chengjiu">取得成就</a></li>
						<li class="divider"></li>
						<li><a href="#linian">学校理念</a></li>
						<li class="divider"></li>
						<li><a href="#xiaoxun">黑科大校训</a></li>
					</ul>
				</li>

				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">关于 <b class="caret"></b> </a>
					<ul class="dropdown-menu">
						<li></li>
						<li><a href="#jishu">建站技术</a></li>
						<li class="divider"></li>
						<li><a href="#xinde">建站心得</a></li>
					</ul>
				</li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
			
				<c:if test="${user == null }">
					<li>
						<p class="navbar-text"><span style="color: red">${failMsg}&emsp;</span>请先登录</p></li>
					<li style="width: 100px;margin-right: 20px">
						<button type="button"
							class="btn navbar-btn btn-primary btn-default btn-block "
							data-toggle="modal" data-target="#loginModal">登录</button>
					</li>
				</c:if>
				<c:if test="${user != null }">
					<li>
						<p class="navbar-text">${user.userName }</p></li>
					<c:if test="${user.role ==0}">
						<li style="width: 100px;margin-right: 20px"><a
							href="${pageContext.request.contextPath}/student/main.jsp">后台管理</a>
						</li>
					</c:if>
					<c:if test="${user.role ==1}">
						<li style="width: 100px;margin-right: 20px"><a
							href="${pageContext.request.contextPath}/teacher/main.jsp">后台管理</a>
						</li>
					</c:if>
					<c:if test="${user.role ==2}">
						<li style="width: 100px;margin-right: 20px"><a
							href="${pageContext.request.contextPath}/admin/main.jsp">后台管理</a>
						</li>
					</c:if>
				</c:if>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</nav>
	<!-- 学校logo -->
	<jsp:include page="index/logo.jsp"></jsp:include>
	<!-- 大屏幕介绍和轮播 -->
	<jsp:include page="index/hello.jsp"></jsp:include>
	<!-- 内容 -->
	<div class="container">
		<!-- 学校的介绍信息 -->
		<jsp:include page="index/scene.jsp"></jsp:include>
	</div>
	<a href="#" class="back-to-top" style="text-decoration : none "> <img
		src="images/top.png" /> </a>
	<!-- footer -->
	<footer class="footer">
		<p>copyright @lzcc.edu.cn</p>
	</footer>

	<!--登录的模态框  -->
	<jsp:include page="index/login.jsp"></jsp:include>
	<script src="js/jquery-2.1.0.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/application.js"></script>
</body>
</html>
