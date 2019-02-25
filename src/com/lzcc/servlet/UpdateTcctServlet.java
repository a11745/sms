package com.lzcc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzcc.dao.impl.ClazzDao;
import com.lzcc.dao.impl.CourseDao;
import com.lzcc.dao.impl.TcctDao;
import com.lzcc.dao.impl.TermDao;
import com.lzcc.dao.impl.UserDao;
import com.lzcc.po.Tcct;

public class UpdateTcctServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private TcctDao tcctDao = new TcctDao();
	private UserDao userDao = new UserDao();
	private TermDao termDao = new TermDao();
	private ClazzDao clazzDao = new ClazzDao();
	private CourseDao courseDao = new CourseDao();
	private Tcct tcct = new Tcct();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String termId= request.getParameter("termId");
		String clazzId= request.getParameter("clazzId");
		String userId= request.getParameter("userId");
		String courseId= request.getParameter("courseId");
		String tcctId= request.getParameter("tcctId");
		if(termId!= null && courseId != null && userId!= null && userId != null && tcctId != null)
		{
			tcct.setTerm(termDao.getTermById(Integer.parseInt(termId)));
			tcct.setClazz(clazzDao.getById(Integer.parseInt(clazzId)));
			tcct.setCourse(courseDao.getById(Integer.parseInt(courseId)));
			tcct.setUser(userDao.getById(Integer.parseInt(userId)));
			tcct.setId(Integer.parseInt(tcctId));
			if(tcctDao.update(tcct))
			{
				request.setAttribute("tcct", tcct);
				request.setAttribute("msg", "教学安排更新成功");
				request.getRequestDispatcher("/admin/tcct.jsp").forward(request,
						response);
			}
		}
	}


}
