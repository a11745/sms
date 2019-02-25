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

public class PreGetMyScoreServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private TcctDao tcctDao = new TcctDao();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		// 教学安排列表
		List<Tcct> tcctList = tcctDao.getListByConditionString("id IN (SELECT tcctId FROM scores AS s WHERE s.userId ="
				+ user.getId()+" )");
		for (int i = 0; i < tcctList.size() - 1; i++) {
			for (int j = tcctList.size() - 1; j > i; j--) {
				if (tcctList.get(j).getTerm().getName().equals(tcctList.get(i).getTerm().getName())) {
				tcctList.remove(j);
			}
		}
	}
		if(tcctList.size() < 1 || tcctList == null)
		{
			request.setAttribute("msg", "没有查到教学安排，请联系管理员修正。");			
		}else
		{
		
			request.setAttribute("tcctList", tcctList);	
		}
		
		request.getRequestDispatcher("/student/getMyScore.jsp").forward(
				request, response);

	}


}
