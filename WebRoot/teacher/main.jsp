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

<title>教师后台</title>

</head>


<frameset rows="50,*,100" cols="*" frameborder="no" border="0"
	framespacing="1">

	<frame src="${pageContext.request.contextPath}/module/top.jsp"
		name="topFrame" scrolling="no" noresize="noresize" id="topFrame"
		title="topFrame" />

	<frameset cols="180,*" frameborder="no" border="0" framespacing="1">
		<frame src="${pageContext.request.contextPath}/teacher/left.jsp"
			name="leftFrame" scrolling="no" noresize="noresize" id="leftFrame"
			title="leftFrame" frameborder="1" />
		<frame src="${pageContext.request.contextPath}/module/welcome.jsp "
			name="mainFrame" noresize="noresize" id="mainFrame" title="mainFrame" />
	</frameset>
	<frame src="${pageContext.request.contextPath}/module/foot.jsp"
		name="footFrame" scrolling="no" noresize="noresize" id="footFrame" 
		title="footFrame" />
</frameset>

<noframes>由于您的浏览器不支持frameset。所以内容无法显示。
</noframes>
<body>

</body>
</html>
