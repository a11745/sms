package com.lzcc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzcc.biz.impl.UserBiz;
/**
 * 删除学生或者教师
 * @author zhujianfeng
 *
 */
public class DeleteUserServlet extends HttpServlet {

	
	private static final long serialVersionUID = -7642696661712887654L;
	private UserBiz userBiz = new UserBiz();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		int role = Integer.parseInt(request.getParameter("role"));
		int clazzId = Integer.parseInt(request.getParameter("clazzId"));
		if(userBiz.deleteUser(id))
		{
			request.getRequestDispatcher("/user/getAllUser?role="+role+"&clazzId="+clazzId).forward(request,
					response);
		}
	}

}
