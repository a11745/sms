package com.lzcc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzcc.dao.impl.ClazzDao;
import com.lzcc.po.Clazz;
import com.lzcc.util.Page;

public class GetAllClazzServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ClazzDao clazzDao = new ClazzDao();
	private Page page = new Page();

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String txtpno = request.getParameter("?pno");
		Integer pno = 1;
		int total = 0;
		if (txtpno != null) {
			pno = Integer.parseInt(txtpno);
			if(pno < 1)
			{
				pno = 1;
			}
		}
		String num = request.getParameter("total");
		if(num != null)
		{
			total = Integer.parseInt(num);
		}else
		{
			total = clazzDao.clazzTotal();
		}
		if(pno > (total/page.getPageSize()+1))
		{
			pno = total/page.getPageSize()+1;
		}
		List<Clazz> clazzlist = clazzDao.getList(pno, page.getPageSize());
		page.setPageNo(pno);
		page.setRecordCount(total);
		request.setAttribute("page", page);
		String action = request.getParameter("action");
		if (clazzlist != null) {
			if(pno == 1)
			{
				clazzlist.remove(0);	
			}
			
			page.setData(clazzlist);
			request.setAttribute("clazzlist", clazzlist);
			request.setAttribute("page", page);
			// 如果是查看学生
			if (action != null && action.equals("clazz")) {
				// 如果是查看学生，和添加学生的班级列表显示界面不同
				request.setAttribute("flag", "clazz");
				page.setUrl(request.getContextPath() + "/user/getAllClazz?total=" + total+ "&action=clazz&");
				request.getRequestDispatcher("/admin/chooseClazz.jsp").forward(
						request, response);

		}else
		{
			page.setUrl(request.getContextPath() + "/user/getAllClazz?total=" + total+ "&");
			request.getRequestDispatcher("/admin/chooseClazz.jsp").forward(
					request, response);
		}
		
	}

}
}