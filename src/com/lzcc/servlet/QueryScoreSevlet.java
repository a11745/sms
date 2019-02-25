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
import com.lzcc.dao.impl.TcctDao;
import com.lzcc.po.Score;
import com.lzcc.po.Tcct;
import com.lzcc.po.User;
import com.lzcc.util.Page;

public class QueryScoreSevlet extends HttpServlet {
	private ScoreDao scoreDao = new ScoreDao();
	private TcctDao tcctDao = new TcctDao();
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
		String tId = request.getParameter("tcctId");
		String ced = request.getParameter("courseId");
		String czd = request.getParameter("clazzId");
		String clazzName = request.getParameter("clazzName");
		String courseName = request.getParameter("courseName");
		String term = request.getParameter("term");
		
		Integer tcctId = null;
		Integer courseId = null;
		Integer clazzId = null;
		
		User user = (User) request.getSession().getAttribute("user");
		if( tId !=	null)
		{
			tcctId = Integer.parseInt(tId);
		}else if(czd != null && ced != null) 
		{
			clazzId = Integer.parseInt(czd);
			courseId = Integer.parseInt(ced);
			
			tcctId = tcctDao.getOneTcct(clazzId,courseId,user.getId());
			if(tcctId < 1)
			{
				String msg = "本班级下的学生没有这门课程";
				request.getRequestDispatcher("/user/preQueryScore?msg="+msg).forward(
						request, response);
				
				return ;
			}
			
		}
		Tcct tcct = tcctDao.getById(tcctId);
		if(clazzName == null && courseName== null)
		{
			clazzName = tcct.getClazz().getClazzName();
			courseName = tcct.getCourse().getCourseName();
			term = tcct.getTerm().getName();
		}
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
			total = scoreDao.scoreTotal(tcctId);
		}
		if (pno > (total / page.getPageSize() + 1)) {
			pno = total / page.getPageSize() + 1;
		}
		String whereCondition = " tcctId = ?";
		Object[] values = {tcctId};
		List<Score> scorelist = scoreDao.getListByConditionString(
				whereCondition, values, pno, page.getPageSize());
		page.setPageNo(pno);
		page.setRecordCount(total);
		page.setData(scorelist);
		Collections.sort(scorelist, new Comparator<Score>() {
			@Override
			public int compare(Score score1, Score score2) {
				return (score2.getScore()+"").compareTo(score1.getScore()+"");
			}
		});
		if (scorelist != null) {
			int max = scoreBiz.max(clazzId, courseId,user.getId());
			int avg = scoreBiz.avg(clazzId, courseId,user.getId());
			int min = scoreBiz.min(clazzId, courseId,user.getId());
			int[] rank = new int[scorelist.size()];
			for (int i = 0; i < rank.length; i++) {
				rank[i]= i+1;
			}
			request.setAttribute("scorelist", scorelist);
			request.setAttribute("page", page);
			request.setAttribute("rank", rank);
			request.setAttribute("courseName", courseName);
			request.setAttribute("clazzName", clazzName);
			request.setAttribute("tcctId", tcctId);
			request.setAttribute("term", term);
			request.setAttribute("max", max);
			request.setAttribute("avg", avg);
			request.setAttribute("min", min);
			
			page.setUrl(request.getContextPath()
					+ "/user/queryScore?total=" + total +"&tcctId=" + tcctId+"&courseId="+courseId+"&clazzId="+clazzId+
					"&clazzName="+clazzName+"&courseName="+courseName+"&term="+term+"&tcctId="+tcctId+"&");
			request.getRequestDispatcher("/teacher/printScore.jsp").forward(
					request, response);

		}
	}

}
