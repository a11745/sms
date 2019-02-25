package com.lzcc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzcc.dao.impl.ClazzDao;
import com.lzcc.po.Clazz;

/**
 * 为添加用户做准备工作。
 * 
 * @author zh
 * 
 */
public class PreAddUserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ClazzDao clazzDao = new ClazzDao();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int role = Integer.parseInt(request.getParameter("role"));
		// 得到班级列表
		List<Clazz> list = clazzDao.getAll();
		// 去掉冗余的第一个记录
		list.remove(0);
		request.setAttribute("role", role);
		request.setAttribute("clazzlist", list);
		request.getRequestDispatcher("/module/addUser.jsp").forward(request,
				response);

	}

}
