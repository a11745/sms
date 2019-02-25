package com.lzcc.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzcc.biz.impl.UserBiz;
import com.lzcc.dao.impl.ScoreDao;
import com.lzcc.po.Score;
import com.lzcc.po.User;
import com.lzcc.util.Page;

public class GetAllUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserBiz userBiz = new UserBiz();
	private List<User> list = new ArrayList<User>();
	private ScoreDao scoreDao = new ScoreDao();
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
		int total = 0;
		String r = request.getParameter("role");
		Integer role = null;
		if(r != null)
		{
			role  = Integer.parseInt(r);	
			request.setAttribute("role", role);
		}
		
		String txtpno = request.getParameter("?pno");
		Integer pno = 1;
		if (txtpno != null) {
			pno = Integer.parseInt(txtpno);
			if(pno < 1)
			{
				pno = 1;
			}
		}
		String id = request.getParameter("clazzId");
		String tid = request.getParameter("tcctId");
		String courseName = request.getParameter("courseName");
		Integer clazzId = null;
		Integer tcctId = null;
		if (id != null && !id.equals("null")&&(Integer.parseInt(id) >1)) {
			clazzId = Integer.parseInt(id);
			//表明返回的是学生列表
			request.setAttribute("flag", "student");
		}
		String t = request.getParameter("total");
		if(t != null)
		{
			total = Integer.parseInt(t);
		}else
		{
			total = userBiz.userTotal(clazzId);
		}
		if(pno > (total/page.getPageSize()+1))
		{
			pno = total/page.getPageSize()+1;
		}
		if(tid!= null)
		{
			tcctId = Integer.parseInt(tid);
			request.setAttribute("flag", "teacher");
			request.setAttribute("tcctId", tcctId);
			request.setAttribute("courseName", courseName);
		}

		list = userBiz.getAllUser(clazzId, pno, page.getPageSize());
		//按编号升序排列
		Collections.sort(list,new Comparator<User>() {
			@Override
			public int compare(User user1, User user2) {
				return user1.getNumber().compareTo(user2.getNumber());
			}
		});
		page.setPageNo(pno);
		if (list != null) {
			page.setData(list);
			page.setRecordCount(total);
			if(role != null)
			{
				page.setUrl(request.getContextPath() + "/user/getAllUser?total="+total+"&clazzId="+clazzId+"&role="+role+"&");
				
			}else
			{
				//判断成绩是否已经添加
				int[] scorearr = new int[list.size()];
				for (int i = 0; i < list.size(); i++) {
					User user = list.get(i);
					Score score = scoreDao.getByCondition(" userId ="+user.getId()+"  and tcctId="+tcctId);
					if(score != null)
					{
						scorearr[i] = score.getScore();
					}else
					{
						scorearr[i] = 0;	
					}
					
				}
				request.setAttribute("scorearr", scorearr);
				page.setUrl(request.getContextPath() + "/user/getAllUser?total="+total+"&clazzId="+clazzId+"&tcctId="+tcctId+"&courseName="+courseName+"&");	
			}
			request.setAttribute("page", page);
			request.setAttribute("tcctId", tcctId);
			request.setAttribute("list", list);
			request.getRequestDispatcher("/module/userList.jsp").forward(request,
					response);
			
		}
	}

}
