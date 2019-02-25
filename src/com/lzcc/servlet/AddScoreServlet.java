package com.lzcc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzcc.dao.impl.ScoreDao;
import com.lzcc.dao.impl.TcctDao;
import com.lzcc.dao.impl.UserDao;
import com.lzcc.po.Score;

public class AddScoreServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ScoreDao scoreDao = new ScoreDao();
	private UserDao userDao = new UserDao();
	private TcctDao tcctDao = new TcctDao();
	private Score scoreEntity = new Score();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String score= request.getParameter("score");
		String userId= request.getParameter("userId");
		String tcctId= request.getParameter("tcctId");
		if(score!= null&& userId!= null && tcctId != null)
		{
			scoreEntity.setUser(userDao.getById(Integer.parseInt(userId)));
			scoreEntity.setScore(Integer.parseInt(score));
			int id =  Integer.parseInt(tcctId);
			scoreEntity.setTcct(tcctDao.getById(id));
			if(scoreDao.add(scoreEntity))
			{
				request.setAttribute("score", scoreEntity);
				request.getRequestDispatcher("/teacher/score.jsp").forward(request,
						response);
			}
		}
		
		
	}
}
