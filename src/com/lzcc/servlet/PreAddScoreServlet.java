package com.lzcc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzcc.dao.impl.ClazzDao;
import com.lzcc.dao.impl.CourseDao;
import com.lzcc.dao.impl.TermDao;
import com.lzcc.po.Clazz;
import com.lzcc.po.Course;
import com.lzcc.po.Term;

public class PreAddScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TermDao termDao = new TermDao();
	private ClazzDao clazzDao = new ClazzDao();
	private CourseDao courseDao = new CourseDao();
	

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//User user = (User) request.getSession().getAttribute("user");
		//Object[]values = {user.getId()};
		//String whereCondition = "userId = ?";
		//List<Tcct> tcctList = tcctDao.getList(whereCondition, values);
		List<Term> termList = termDao.getAllTerm();
		List<Clazz> clazzList = clazzDao.getAll();
		clazzList.remove(0);
		List<Course> courseList = courseDao.getAll();
		request.setAttribute("clazzList", clazzList);
		request.setAttribute("courseList", courseList);
		request.setAttribute("termList", termList);
		
//		String termId= request.getParameter("termId");
//		String clazzId= request.getParameter("clazzId");
//		String courseId= request.getParameter("courseId");
//		String tcctId= request.getParameter("tcctId");
//		//给更新页面提供数据
//		if(termId!= null && courseId != null )
//		{
//				request.setAttribute("termId", Integer.parseInt(termId));
//				request.setAttribute("clazzId", Integer.parseInt(clazzId));
//				request.setAttribute("courseId", Integer.parseInt(courseId));
//				request.setAttribute("tcctId", Integer.parseInt(tcctId));
//				
//				request.getRequestDispatcher("/teacher/updateTcct.jsp").forward(request,
//						response);
//				return;
//		}else
//		{
//			//给添加页面提供数据
//			request.getRequestDispatcher("/teacher/addTcct.jsp").forward(request,
//					response);	
//		}
		
		request.getRequestDispatcher("/teacher/addTcct.jsp").forward(request,
			response);	
	}


}
