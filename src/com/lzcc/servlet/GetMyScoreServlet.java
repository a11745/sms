package com.lzcc.servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzcc.dao.impl.ScoreDao;
import com.lzcc.dao.impl.TermDao;
import com.lzcc.po.Score;
import com.lzcc.po.User;

public class GetMyScoreServlet extends HttpServlet {

	private ScoreDao scoreDao = new ScoreDao();
	private TermDao termDao = new TermDao();
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tid = request.getParameter("termId");
		int termId = 0;
		if(tid != null)
		{
			termId= Integer.parseInt(tid);
		}
		User user = (User) request.getSession().getAttribute("user");
		String whereCondition = "  userId = ?";
		Object[] values = {user.getId() };
		List<Score> scorelist = scoreDao.getListByConditionString(whereCondition,values);

	    Iterator<Score> iterator = scorelist.iterator();  
	    while(iterator.hasNext()){  
	    	Score s = iterator.next();  
	        if(s.getTcct().getTerm().getId()!= termId){  
	        	iterator.remove();  
	        }  
	    }  	
		int total = 0;
		for (int i = 0; i < scorelist.size(); i++) {
			
			total += scorelist.get(i).getScore();
		}
		if (scorelist != null) {
			request.setAttribute("scorelist", scorelist);
			request.setAttribute("term",termDao.getTermById(Integer.parseInt(tid)).getName() );
			request.setAttribute("termId",termId);
			request.setAttribute("total", total);
			request.getRequestDispatcher("/student/printScore.jsp").forward(
					request, response);

		}
	}


}
