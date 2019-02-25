package com.lzcc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzcc.biz.impl.UserBiz;
import com.lzcc.po.User;

public class UpdateUserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private User user = new User();
	private UserBiz userBiz = new UserBiz();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userName = request.getParameter("userName");
		int gender = Integer.parseInt(request.getParameter("gender"));
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String  birthday = request.getParameter("birthday");
		String describe = request.getParameter("describe");
		user = (User) request.getSession().getAttribute("user");
		user.setUserName(userName);
		user.setGender(gender);
		user.setBirthday(birthday);
		user.setDescribe(describe);
		User userTmp = userBiz.update(user);
		if (userTmp != null) {
			request.getSession().setAttribute("user", userTmp);
				request.getRequestDispatcher("/module/user.jsp").forward(
						request, response);
			}

		}
	}

