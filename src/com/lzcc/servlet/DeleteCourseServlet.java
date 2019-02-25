package com.lzcc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzcc.biz.impl.UserBiz;
import com.lzcc.dao.impl.ClazzDao;
import com.lzcc.dao.impl.CourseDao;
import com.lzcc.dao.impl.TcctDao;

public class DeleteCourseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private TcctDao tcctDao = new TcctDao();
	private CourseDao courseDao = new CourseDao();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int courseId = Integer.parseInt(request.getParameter("courseId"));
		if (courseId > 0 ) {
			if(tcctDao.isCourse(courseId))
			{
				tcctDao.deleteByCourseId(courseId);
			}
			courseDao.delete(courseId);
				request.getRequestDispatcher("/user/getAllCourse").forward(
						request, response);	
			

		}

	}
}
