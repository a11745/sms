package com.lzcc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginOutServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if(session == null)
		{
			response.sendRedirect("index.jsp");	
		}
		if(session.getAttribute("user") != null)
		{
			session.removeAttribute("user");	
		}
		response.setHeader("Cache-Control","no-cache"); 
		response.setHeader("Cache-Control","no-store");  //和上面的参数不一样
		response.setDateHeader("Expires", 0); 
		response.setHeader("Pragma","no-cache"); 
		session.invalidate();
		response.sendRedirect("index.jsp");
	}

}
