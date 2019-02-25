package com.lzcc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzcc.biz.impl.UserBiz;
import com.lzcc.po.User;
/**
 * 用户登录servlet
 * 根据页面传来的角色判断登录后跳转的页面。
 * @author zhujianfeng
 *
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserBiz userBiz = new UserBiz();
	private User user = new User();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		int role = Integer.parseInt(req.getParameter("role"));
		String number = req.getParameter("number");
		String password = req.getParameter("password");
		user = userBiz.login(number, password);
		if (user != null) {
			req.getSession().setAttribute("user", user);
			switch (role) {
			case 0:
				if(user.getRole() == 0)
				{
				req.getRequestDispatcher("/student/main.jsp").forward(req, resp);
					break;	
				}
			case 1:
				if(user.getRole() == 1)
				{
				req.getRequestDispatcher("/teacher/main.jsp").forward(req, resp);
				break;
				}
			case 2:
				if(user.getRole() == 2)
				{
				req.getRequestDispatcher("/admin/main.jsp").forward(req,resp);
				break;
				}
			default:
				req.setAttribute("failMsg", "登录失败，请检查后重新登录");
				req.getRequestDispatcher("/index.jsp").forward(req, resp);
				break;
			}

		} else {
			req.setAttribute("failMsg", "登录失败，请检查后重新登录");
			req.getRequestDispatcher("/index.jsp").forward(req, resp);
		}

	}

}