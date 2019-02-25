package com.lzcc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzcc.biz.impl.UserBiz;
import com.lzcc.dao.impl.ClazzDao;

public class DeleteClazzServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserBiz userBiz = new UserBiz();
	private ClazzDao clazzDao = new ClazzDao();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int clazzId = Integer.parseInt(request.getParameter("clazzId"));
		if (clazzId > 0 ) {
			if(userBiz.isClazz(clazzId))
			{
				userBiz.deleteClazzUser(clazzId);
			}
			if(clazzDao.delete(clazzId))
			{
				request.getRequestDispatcher("/user/getAllClazz?action=clazz").forward(
						request, response);	
			}

		}

	}
}
