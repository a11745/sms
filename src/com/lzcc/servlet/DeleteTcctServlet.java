package com.lzcc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzcc.dao.impl.TcctDao;

public class DeleteTcctServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private TcctDao tcctDao = new TcctDao();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int tcctId = Integer.parseInt(request.getParameter("tcctId"));
		if (tcctId > 0 ) {
			if(tcctDao.delete(tcctId))
			{
				request.getRequestDispatcher("/user/getAllTcct").forward(
						request, response);	
			}

		}

	}

}
