package com.lzcc.servlet;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzcc.biz.impl.ScoreBiz;
import com.lzcc.dao.impl.ScoreDao;
import com.lzcc.po.Score;
import com.lzcc.po.User;
import com.lzcc.util.Page;

public class GetStudentScoreServlet extends HttpServlet {

	private ScoreDao scoreDao = new ScoreDao();
	private ScoreBiz scoreBiz= new ScoreBiz();
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
		String tcctId = request.getParameter("tcctId");
		String clazzName = request.getParameter("clazzName");
		String courseName = request.getParameter("courseName");
		String courseId = request.getParameter("courseId");
		String clazzId = request.getParameter("clazzId");
		User user = (User) request.getSession().getAttribute("user");
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
			total = scoreDao.scoreTotal(Integer.parseInt(tcctId));
		}
		if (pno > (total / page.getPageSize() + 1)) {
			pno = total / page.getPageSize() + 1;
		}
		String whereCondition = "tcctId = ?";
		Object[] values = { Integer.parseInt(tcctId) };
		List<Score> scorelist = scoreDao.getListByConditionString(
				whereCondition, values, pno, page.getPageSize());
		page.setPageNo(pno);
		page.setRecordCount(total);
		page.setData(scorelist);
		Collections.sort(scorelist, new Comparator<Score>() {
			@Override
			public int compare(Score score1, Score score2) {
				return score1.getUser().getNumber()
						.compareTo(score2.getUser().getNumber());
			}
		});
		if (scorelist != null) {
			int max = scoreBiz.max(Integer.parseInt(clazzId), Integer.parseInt(courseId),user.getId());
			int avg = scoreBiz.avg(Integer.parseInt(clazzId), Integer.parseInt(courseId),user.getId());
			int min = scoreBiz.min(Integer.parseInt(clazzId), Integer.parseInt(courseId),user.getId());
			request.setAttribute("scorelist", scorelist);
			request.setAttribute("page", page);
			request.setAttribute("clazzName", clazzName);
			request.setAttribute("courseName", courseName);
			request.setAttribute("max", max);
			request.setAttribute("avg", avg);
			request.setAttribute("min", min);
			page.setUrl(request.getContextPath()
					+ "/user/getStudentScore?total=" + total + "&clazzName="
					+ clazzName + "&tcctId=" + Integer.parseInt(tcctId)
					+ "&courseName="+courseName+"&courseId="+Integer.parseInt(courseId)+
					"&clazzId="+Integer.parseInt(clazzId)+"&");
			request.getRequestDispatcher("/teacher/scoreList.jsp").forward(
					request, response);

		}
	}

}
