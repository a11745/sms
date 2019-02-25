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

public class UpdateScoreServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;
	private ScoreDao scoreDao = new ScoreDao();
	private TcctDao tcctDao = new TcctDao();
	private UserDao userDao = new UserDao();
	private Score scoreEntity = new Score();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id= request.getParameter("id");
		String tcctId = request.getParameter("tcctId");
		String userId = request.getParameter("userId");
		String score = request.getParameter("score");
		if(userId!= null && tcctId!= null && id != null)
		{
			scoreEntity.setUser(userDao.getById(Integer.parseInt(userId)));
			scoreEntity.setScore(Integer.parseInt(score));
			scoreEntity.setTcct(tcctDao.getById(Integer.parseInt(tcctId)));
			scoreEntity.setId(Integer.parseInt(id));
			if(scoreDao.update(scoreEntity))
			{
				request.setAttribute("score", scoreEntity);
				request.setAttribute("msg", "成绩更新成功");
				request.getRequestDispatcher("/teacher/score.jsp").forward(request,
						response);
			}
		}
	}


}
