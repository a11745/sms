package com.lzcc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzcc.dao.impl.ScoreDao;

public class DeleteScoreServlet extends HttpServlet {

	private static final long serialVersionUID = -7642696661712887654L;
	private ScoreDao scoreDao = new ScoreDao();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tcctId = request.getParameter("tcctId");
		String clazzName = request.getParameter("clazzName");
		String courseName = request.getParameter("courseName");
		String id = request.getParameter("id");
		if(scoreDao.delete(Integer.parseInt(id)))
		{
			request.getRequestDispatcher("/user/getStudentScore?clazzName="+clazzName+"&tcctId="+Integer.parseInt(tcctId)+"&courseName="+courseName).forward(request,
					response);
		}
	}


}
