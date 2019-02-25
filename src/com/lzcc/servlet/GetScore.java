package com.lzcc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzcc.dao.impl.ScoreDao;
import com.lzcc.po.Score;
import com.lzcc.po.User;
import com.lzcc.util.Page;

public class GetScore extends HttpServlet {

	private ScoreDao scoreDao = new ScoreDao();
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
		User user = (User) request.getSession().getAttribute("user");
		String num = request.getParameter("total");
		if (num != null) {
			total = Integer.parseInt(num);
		} else {
			total = scoreDao.studentTotal(user.getId());
		}
		if (pno > (total / page.getPageSize() + 1)) {
			pno = total / page.getPageSize() + 1;
		}
		String whereCondition = "userId = ?";
		Object[] values = { user.getId() };
		List<Score> scorelist = scoreDao.getListByConditionString(
				whereCondition, values, pno, page.getPageSize());
		page.setPageNo(pno);
		page.setRecordCount(total);
		page.setData(scorelist);
		if (scorelist != null) {
			request.setAttribute("scorelist", scorelist);
			request.setAttribute("page", page);
			page.setUrl(request.getContextPath() + "/user/getScore?total="+ total +"&");
			request.getRequestDispatcher("/student/scoreList.jsp").forward(
					request, response);

		}
	}

}
