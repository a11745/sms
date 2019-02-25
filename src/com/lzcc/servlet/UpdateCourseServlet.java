package com.lzcc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzcc.dao.impl.CourseDao;
import com.lzcc.po.Course;

public class UpdateCourseServlet extends HttpServlet {


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
		String courseId = request.getParameter("courseId");
		if(courseNumber!= null && courseName!= null && describe != null && courseId != null)
		{
			course = courseDao.getById(Integer.parseInt(courseId));
			course.setCourseName(courseName);
			course.setCourseNumber(courseNumber);
			course.setDescribe(describe);
			if(courseDao.update(course))
			{
				request.setAttribute("course", course);
				request.setAttribute("msg", "课程信息更新成功");
				request.getRequestDispatcher("/admin/course.jsp").forward(request,
						response);
			}
		}
	}

}
