package com.lzcc.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzcc.dao.impl.TcctDao;
import com.lzcc.po.Clazz;
import com.lzcc.po.Course;
import com.lzcc.po.Tcct;
import com.lzcc.po.User;

public class PreQueryScoreServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private TcctDao tcctDao = new TcctDao();
	private List<Course> courseList = new ArrayList<Course>();
	private List<Clazz> clazzList = new ArrayList<Clazz>();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		courseList.clear();
		clazzList.clear();
		User user = (User) request.getSession().getAttribute("user");
		// 教学安排列表
		List<Tcct> tcctList = tcctDao.getListByConditionString(" userId="
				+ user.getId());
		for (int i = 0; i < tcctList.size(); i++) {
			courseList.add(tcctList.get(i).getCourse());
			clazzList.add(tcctList.get(i).getClazz());
		}
		 HashSet<Course> ce = new HashSet<Course>(courseList);
		 courseList.clear();
		 courseList.addAll(ce);
		 HashSet<Clazz> cz = new HashSet<Clazz>(clazzList);
		 clazzList.clear();
		 clazzList.addAll(cz);
//		for (int i = 0; i < courseList.size() - 1; i++) {
//			for (int j = courseList.size() - 1; j > i; j--) {
//				if (courseList.get(j).equals(courseList.get(i))) {
//					courseList.remove(j);
//				}
//			}
//		}
//		for (int i = 0; i < clazzList.size() - 1; i++) {
//			for (int j = clazzList.size() - 1; j > i; j--) {
//				if (clazzList.get(j).equals(clazzList.get(i))) {
//					clazzList.remove(j);
//				}
//			}
//		}
		request.setAttribute("courseList", courseList);
		request.setAttribute("clazzList", clazzList);
		String msg = request.getParameter("msg");
		request.setAttribute("msg", msg);
		request.getRequestDispatcher("/teacher/queryScore.jsp").forward(
				request, response);

	}

}
