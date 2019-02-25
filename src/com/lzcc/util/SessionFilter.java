package com.lzcc.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 处理session过期
 * 
 *
 */
public class SessionFilter implements Filter  {

	public void destroy() {
		
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
	      HttpServletRequest request = (HttpServletRequest) req;  
	              HttpServletResponse response = (HttpServletResponse) res;  
	              HttpSession session = request.getSession();
	                  if (session.getAttribute("user") ==null) {  
	                      response.setContentType("text/html;charset=utf-8");  
	                      PrintWriter out = response.getWriter();  
	                      out.println("<script language='javascript' type='text/javascript'>");  
	                      out.println("alert('由于你长时间没有操作,导致Session失效!请你重新登录!');window.top.location.href='" + request.getContextPath() + "/index.jsp'");  
	                      out.println("</script>");  
	              } else {  
	                  chain.doFilter(request, response);  
	              }  
	        

		
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

}
