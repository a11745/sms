package com.lzcc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzcc.dao.impl.TcctDao;
import com.lzcc.po.Tcct;
import com.lzcc.po.User;
import com.lzcc.util.Page;

public class GetTeacherTcctServlet extends HttpServlet {
	private TcctDao tcctDao = new TcctDao();
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
		String action = request.getParameter("action");
		Integer pno = 1;
		int total = 0;
		if (txtpno != null) {
			pno = Integer.parseInt(txtpno);
			if (pno < 1) {
				pno = 1;
			}
		}
		User user = (User) request.getSession().getAttribute("user");
		String num = request.getParameter("total");
		if (num != null) {
			total = Integer.parseInt(num);
		} else {
			total = tcctDao.teacherTotal(user.getId());
		}
		if (pno > (total / page.getPageSize() + 1)) {
			pno = total / page.getPageSize() + 1;
		}
		
		String whereCondition = "userId = ?";
		Object[] values={user.getId()};
		List<Tcct> tcctlist = tcctDao.getListByConditionString(whereCondition,values,pno, page.getPageSize());
		
		page.setPageNo(pno);
		page.setRecordCount(total);
		page.setData(tcctlist);
		// String action = request.getParameter("action");
		if (tcctlist != null) {
			request.setAttribute("tcctlist", tcctlist);
			request.setAttribute("page", page);
			page.setUrl(request.getContextPath() + "/user/getTeacherTcct?total=" + total
					+ "&action="+action+"&");
			if (action != null && action.equals("add")) {
				request.setAttribute("action", "add");
			}
			if(action != null &&action.equals("query"))
			{
				request.setAttribute("action", "query");
			}
			request.getRequestDispatcher("/teacher/tcctList.jsp").forward(
					request, response);

		}
	}


}
