<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <!-- 登陆Modal -->
<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h3 class="modal-title" id="myModalLabel">欢迎登陆	黑龙江科技大学-成绩管理系统</h3>
      </div>
      <div class="modal-body">
       <form class="form-signin" role="form" action="${pageContext.request.contextPath}/login" method="post">
		<div class="btn-group" data-toggle="buttons">
			<label class="btn btn-primary btnchecked">
			 <input type="radio" name="role" id="option1" value="0" checked="checked">学生
			</label>
			 <label class="btn btn-primary"> <input type="radio"name="role" id="option2" value="1">教师 
			 </label>
			  <label class="btn btn-primary"> <input type="radio" name="role" id="option3" value="2">管理员 </label>
		</div>
		<p></p>
		<input name="number" class="form-control" placeholder="用户编号" required=""
			autofocus="" type="text">
		 <input name="password" class="form-control" placeholder="用户密码" required="" type="password"> 
		 <label class="checkbox"> 
		 <input value="remember-me" type="checkbox">记住我
		</label>
		 <button class="btn btn-lg btn-primary btn-block" type="submit">登陆</button>
		 </form>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
