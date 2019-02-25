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
import com.lzcc.dao.impl.UserDao;
import com.lzcc.po.Clazz;
import com.lzcc.po.Course;
import com.lzcc.po.Term;
import com.lzcc.po.User;

public class PreAddTcctServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;
	private UserDao userDao = new UserDao();
	private TermDao termDao = new TermDao();
	private ClazzDao clazzDao = new ClazzDao();
	private CourseDao courseDao = new CourseDao();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		List<Term> termList = termDao.getAllTerm();
		List<Clazz> clazzList = clazzDao.getAll();
		clazzList.remove(0);
		List<Course> courseList = courseDao.getAll();
		List<User> userList = userDao.getUserList(" role = 1");
		request.setAttribute("clazzList", clazzList);
		request.setAttribute("courseList", courseList);
		request.setAttribute("termList", termList);
		request.setAttribute("userList", userList);
		
		String termId= request.getParameter("termId");
		String clazzId= request.getParameter("clazzId");
		String userId= request.getParameter("userId");
		String courseId= request.getParameter("courseId");
		String tcctId= request.getParameter("tcctId");
		String flag= request.getParameter("flag");
		if(flag != null &&  flag.equals("redirect"))
		{
			//给添加页面提供数据
			request.getRequestDispatcher("/admin/addTcct.jsp").forward(request,
					response);	
			return;
		}
		//给更新页面提供数据
		if(termId!= null && courseId != null && clazzId!= null && userId != null)
		{
				request.setAttribute("termId", Integer.parseInt(termId));
				request.setAttribute("clazzId", Integer.parseInt(clazzId));
				request.setAttribute("courseId", Integer.parseInt(courseId));
				request.setAttribute("userId", Integer.parseInt(userId));
				request.setAttribute("tcctId", Integer.parseInt(tcctId));
				
				request.getRequestDispatcher("/admin/updateTcct.jsp").forward(request,
						response);
				return;
		}else
		{
			//给添加页面提供数据
			request.getRequestDispatcher("/admin/addTcct.jsp").forward(request,
					response);	
		}
		

	}

}
