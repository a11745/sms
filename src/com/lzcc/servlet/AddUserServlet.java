package com.lzcc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzcc.biz.impl.UserBiz;
import com.lzcc.dao.impl.ClazzDao;
import com.lzcc.po.User;
import com.lzcc.util.EncryptTool;

public class AddUserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private User user = new User();
	private UserBiz userBiz = new UserBiz();
	private ClazzDao clazzDao = new ClazzDao();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("clazzId");
		String role0 = request.getParameter("role0");
		String role1 = request.getParameter("role1");
		String number = request.getParameter("number");
		String userName = request.getParameter("userName");
		int gender = Integer.parseInt(request.getParameter("gender"));
		String birthday = request.getParameter("birthday");
		String describe = request.getParameter("describe");
		user.setNumber(number);
		user.setUserName(userName);
		user.setGender(gender);
		user.setBirthday(birthday);
		user.setDescribe(describe);
		user.setPassword(EncryptTool.MD5("123456"));
		int role;
		// 如果添加学生
		if (role0 != null) {
			role = Integer.parseInt(role0);
			if (id != null) {
				int clazzId = Integer.parseInt(id);
				user.setClazz(clazzDao.getById(clazzId));
			}
			user.setRole(role);
			// 添加学生
			userBiz.addUser(user);
			request.setAttribute("student", user);
			request.getRequestDispatcher("/module/stuser.jsp").forward(request,
					response);

			// 如果添加教师
		}
		if (role1 != null) {
			role = Integer.parseInt(role1);
			// 用户的clazz字段，对于教师来说是冗余的。设置一个固定的Id
			user.setClazz(clazzDao.getById(1));
			user.setRole(role);
			userBiz.addUser(user);
			request.setAttribute("teacher", user);
			request.getRequestDispatcher("/module/stuser.jsp").forward(request,
					response);
		}

	}

}
