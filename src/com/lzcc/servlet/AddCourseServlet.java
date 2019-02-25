package com.lzcc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzcc.dao.impl.CourseDao;
import com.lzcc.po.Course;

public class AddCourseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private CourseDao courseDao = new CourseDao();
	private Course course = new Course();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String courseNumber= request.getParameter("courseNumber");
		String courseName = request.getParameter("courseName");
		String describe = request.getParameter("describe");
		if(courseNumber!= null && courseName!= null && describe != null)
		{
			course.setCourseName(courseName);
			course.setCourseNumber(courseNumber);
			course.setDescribe(describe);
			if(courseDao.add(course))
			{
				request.setAttribute("course", course);
				request.getRequestDispatcher("/admin/course.jsp").forward(request,
						response);
			}
		}
		
		
	}

}
