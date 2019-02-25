package com.lzcc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzcc.biz.impl.UserBiz;
import com.lzcc.po.User;
import com.lzcc.util.EncryptTool;
/**
 * 重置密码
 * @author zhujianfeng
 *
 */
public class ResetPwdServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
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
		User user = userBiz.getById(id);
		if( user != null)
		{
			user.setPassword(EncryptTool.MD5("123456"));
			userBiz.update(user);
			
				request.getRequestDispatcher("/user/getAllUser?role="+role+"&clazzId="+clazzId).forward(request,
						response);
		}
		
	}

}
