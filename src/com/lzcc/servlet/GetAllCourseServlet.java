package com.lzcc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzcc.dao.impl.CourseDao;
import com.lzcc.po.Course;
import com.lzcc.util.Page;

public class GetAllCourseServlet extends HttpServlet {

	private CourseDao courseDao = new CourseDao();
	private Page page = new Page();

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String txtpno = request.getParameter("?pno");
		Integer pno = 1;
		int total = 0;
		if (txtpno != null) {
			pno = Integer.parseInt(txtpno);
			if (pno < 1) {
				pno = 1;
			}
		}

		String num = request.getParameter("total");
		if (num != null) {
			total = Integer.parseInt(num);
		} else {
			total = courseDao.total();
		}
		if (pno > (total / page.getPageSize() + 1)) {
			pno = total / page.getPageSize() + 1;
		}
		List<Course> courselist = courseDao.getList(pno, page.getPageSize());
		page.setPageNo(pno);
		page.setRecordCount(total);
		page.setData(courselist);
		// String action = request.getParameter("action");
		if (courselist != null) {
			request.setAttribute("courselist", courselist);
			request.setAttribute("page", page);
			page.setUrl(request.getContextPath() + "/user/getAllCourse?total="
					+ total + "&");
			request.getRequestDispatcher("/admin/courseList.jsp").forward(
					request, response);

		}
	}

}
