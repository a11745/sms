package com.lzcc.servlet;

import java.io.IOException;
import java.util.List;

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

public class AddTcctServlet extends HttpServlet {

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
		String termId = request.getParameter("termId");
		String clazzId = request.getParameter("clazzId");
		String userId = request.getParameter("userId");
		String courseId = request.getParameter("courseId");
		Object[] values = { Integer.parseInt(termId),
				Integer.parseInt(clazzId), Integer.parseInt(userId),
				Integer.parseInt(courseId) };
		List<Tcct> list = tcctDao.getList(
				"termId = ? and clazzId = ? and userId = ? and courseId = ?",
				values);
		if (list.size() > 0 || list == null) {
			request.setAttribute("msg", "此教学安排已存在，请查看教学安排后，重试!");
			request.getRequestDispatcher("/user/preAddTcct?flag=redirect").forward(request,
					response);
		
		}else
		{
			if (termId != null && courseId != null && userId != null
					&& userId != null) {
				tcct.setTerm(termDao.getTermById(Integer.parseInt(termId)));
				tcct.setClazz(clazzDao.getById(Integer.parseInt(clazzId)));
				tcct.setCourse(courseDao.getById(Integer.parseInt(courseId)));
				tcct.setUser(userDao.getById(Integer.parseInt(userId)));
				if (tcctDao.add(tcct)) {
					request.setAttribute("tcct", tcct);
					request.getRequestDispatcher("/admin/tcct.jsp").forward(
							request, response);
				}
			}
			
		}
		

	}

}
