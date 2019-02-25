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

<title></title>

</head>

<body>
	<jsp:include page="/module/style.jsp"></jsp:include>
	<div id="wrapper" class="modifyRow" style="margin-top: -50px">
		<div class="row">
			<div class="col-md-2">
				<h2 style="padding-left: 15px;">
					<small>导航菜单</small>
				</h2>
				<div class="panel-group" id="accordion">
					<div class="panel  panel-primary">
						<div class="panel-heading">
							<h6 class="panel-title">
								<a data-toggle="collapse" data-toggle="collapse"
									data-parent="#accordion" href="#collapseOne"> 个人信息管理 </a>
							</h6>
						</div>
						<div id="collapseOne" class="panel-collapse collapse in">
							<div class="panel-body" style="background-color: #F9F9F9 "
								style="background-color: #F9F9F9 ">
								<ul class="nav nav-pills  nav-stacked ">
									<li><a target="mainFrame" onclick=""
										href="${pageContext.request.contextPath}/module/user.jsp">用户信息&emsp;
											<span class=" glyphicon glyphicon-chevron-right"></span> </a>
									</li>
									<li><a target="mainFrame"
										href="${pageContext.request.contextPath}/module/updatePwd.jsp">修改密码
											&emsp;<span class=" glyphicon glyphicon-chevron-right"></span>
									</a>
									</li>
								</ul>
							</div>
						</div>
					</div>
					<div class="panel  panel-primary">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-toggle="collapse"
									data-parent="#accordion" href="#collapseTwo"> 学生管理 </a>
							</h4>
						</div>
						<div id="collapseTwo" class="panel-collapse collapse">
							<div class="panel-body" style="background-color: #F9F9F9 ">
								<ul class="nav nav-pills  nav-stacked ">
									<li><a target="mainFrame"
										href="${pageContext.request.contextPath}/user/preAddUser?role=0">添加学生
											&emsp;<span class=" glyphicon glyphicon-chevron-right"></span>
									</a>
									</li>
									<li><a target="mainFrame"
										href="${pageContext.request.contextPath}/user/getAllClazz">查看学生
											&emsp;<span class=" glyphicon glyphicon-chevron-right"></span>
									</a>
									</li>
								</ul>
							</div>
						</div>
					</div>
					<div class="panel  panel-primary">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-toggle="collapse"
									data-parent="#accordion" href="#collapseThree"> 教师管理 </a>
							</h4>
						</div>
						<div id="collapseThree" class="panel-collapse collapse">
							<div class="panel-body" style="background-color: #F9F9F9 ">
								<ul class="nav nav-pills  nav-stacked ">
									<li><a target="mainFrame"
										href="${pageContext.request.contextPath}/user/preAddUser?role=1">添加教师
											&emsp;<span class=" glyphicon glyphicon-chevron-right"></span>
									</a>
									</li>
									<li><a target="mainFrame"
										href="${pageContext.request.contextPath}/user/getAllUser?role=1">查看教师
											&emsp;<span class=" glyphicon glyphicon-chevron-right"></span>
									</a>
									</li>
								</ul>
							</div>
						</div>
					</div>
					<div class="panel  panel-primary">
						<div class="panel-heading">
							<h6 class="panel-title">
								<a data-toggle="collapse" data-toggle="collapse"
									data-parent="#accordion" href="#course"> 课程管理 </a>
							</h6>
						</div>
						<div id="course" class="panel-collapse collapse">
							<div class="panel-body" style="background-color: #F9F9F9 "
								style="background-color: #F9F9F9 ">
								<ul class="nav nav-pills  nav-stacked ">
									<li><a target="mainFrame"
										href="${pageContext.request.contextPath}/admin/addCourse.jsp">添加课程&emsp;
											<span class=" glyphicon glyphicon-chevron-right"></span> </a>
									</li>
									<li><a target="mainFrame"
										href="${pageContext.request.contextPath}/user/getAllCourse">查看课程&emsp;
											<span class=" glyphicon glyphicon-chevron-right"></span> </a>
									</li>
								</ul>
							</div>
						</div>
					</div>
					<div class="panel  panel-primary">
						<div class="panel-heading">
							<h6 class="panel-title">
								<a data-toggle="collapse" data-toggle="collapse"
									data-parent="#accordion" href="#clazz"> 班级管理 </a>
							</h6>
						</div>
						<div id="clazz" class="panel-collapse collapse">
							<div class="panel-body" style="background-color: #F9F9F9 "
								style="background-color: #F9F9F9 ">
								<ul class="nav nav-pills  nav-stacked ">
									<li><a target="mainFrame"
										href="${pageContext.request.contextPath}/admin/addClazz.jsp">添加班级&emsp;
											<span class=" glyphicon glyphicon-chevron-right"></span> </a>
									</li>
									<li><a target="mainFrame"
										href="${pageContext.request.contextPath}/user/getAllClazz?action=clazz">查看班级&emsp;
											<span class=" glyphicon glyphicon-chevron-right"></span> </a>
									</li>
								</ul>
							</div>
						</div>
					</div>
									<div class="panel  panel-primary">
					<div class="panel-heading">
						<h6 class="panel-title">
							<a data-toggle="collapse" data-toggle="collapse"
								data-parent="#accordion" href="#tcct"> 教学管理 </a>
						</h6>
					</div>
					<div id="tcct" class="panel-collapse collapse">
						<div class="panel-body" style="background-color: #F9F9F9 "
							style="background-color: #F9F9F9 ">
							<ul class="nav nav-pills  nav-stacked ">
								<li><a target="mainFrame" style="margin-right: -20px"
									href="${pageContext.request.contextPath}/user/preAddTcct">添加教学安排
										<span class=" glyphicon glyphicon-chevron-right"></span> </a>
								</li>
								<li><a target="mainFrame" style="margin-right: -20px"
									href="${pageContext.request.contextPath}/user/getAllTcct">查看教学安排
										<span class=" glyphicon glyphicon-chevron-right"></span> </a>
								</li>
							</ul>
						</div>
					</div>
				</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	</div>
</body>
</html>
