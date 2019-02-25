package com.lzcc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzcc.biz.impl.UserBiz;
import com.lzcc.po.User;
import com.lzcc.util.EncryptTool;

public class UpdatePwdServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private User user = new User();
	private UserBiz userBiz = new UserBiz();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");
		 user = (User) request.getSession().getAttribute("user");
		if (newPassword.equals(confirmPassword)) {
			if ((user.getPassword()).equals(EncryptTool.MD5(oldPassword))) {
				user.setPassword(EncryptTool.MD5(confirmPassword));
				User userTmp = userBiz.update(user);
				request.getSession().setAttribute("user", userTmp);
				request.setAttribute("msg", "密码修改成功");
				request.getRequestDispatcher("/module/user.jsp").forward(
						request, response);
			}else
			{
				request.setAttribute("msg", "密码修改失败，请重试");
				request.getRequestDispatcher("/module/updatePwd.jsp").forward(
						request, response);	
			}
		}
		
	}

}
