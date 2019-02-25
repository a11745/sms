package com.lzcc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzcc.dao.impl.ClazzDao;
import com.lzcc.po.Clazz;

public class UpdateClazzServlet extends HttpServlet {

	
	private static final long serialVersionUID = 1L;
	private ClazzDao clazzDao = new ClazzDao();
	private Clazz clazz = new Clazz();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String clazzNumber= request.getParameter("clazzNumber");
		String clazzName = request.getParameter("clazzName");
		String describe = request.getParameter("describe");
		String clazzId = request.getParameter("clazzId");
		if(clazzNumber!= null && clazzName!= null && describe != null && clazzId != null)
		{
			clazz = clazzDao.getById(Integer.parseInt(clazzId));
			clazz.setClazzName(clazzName);
			clazz.setClazzNumber(clazzNumber);
			clazz.setDescribe(describe);
			if(clazzDao.update(clazz))
			{
				request.setAttribute("clazz", clazz);
				request.setAttribute("msg", "班级信息更新成功");
				request.getRequestDispatcher("/admin/clazz.jsp").forward(request,
						response);
			}
		}
	}

}
