package com.lzcc.biz.impl;

import java.util.ArrayList;
import java.util.List;

import com.lzcc.biz.IUserBiz;
import com.lzcc.dao.impl.UserDao;
import com.lzcc.po.User;
import com.lzcc.util.EncryptTool;

public class UserBiz implements IUserBiz {

	private UserDao userDao = new UserDao();
	private List<User> list = new ArrayList<User>();

	/**
	 * 用户登录
	 */
	@Override
	public User login(String number, String password) {

		if (number != null && password != null) {
			// 这里的密码进行了MD5加密
			String whereCondition = "number ='" + number + "'and password = '"
					+ EncryptTool.MD5(password) + "'";
			User userTmp = userDao.getByCondition(whereCondition);
			if (userTmp != null) {
				return userTmp;
			}
		}
		return null;
	}

	/**
	 * 更新用户
	 */
	@Override
	public User update(User user) {
		if (user != null) {
			if (userDao.update(user)) {
				return user;
			}
		}
		return null;
	}

	/**
	 * 添加用户
	 */
	@Override
	public User addUser(User user) {
		if (user != null) {
			if (userDao.add(user)) {
				return user;
			}
		}
		return null;
	}

	@Override
	public List<User> getAllUser(Integer clazzId, int pageIndex, int pageSize) {
		if (clazzId != null) {
			String whereCondition = "clazzId = ?";
			Object[] values = { clazzId };
			list = userDao.getListByConditionString(whereCondition, values,
					pageIndex, pageSize);
		} else {
			String whereCondition = "role = 1 and 1= ?";
			Object[] values = {1};
			list = userDao.getListByConditionString(whereCondition, values,
					pageIndex, pageSize);
		}
		return list;
	}

	@Override
	public int userTotal(Integer clazzId) {
		int total = 0;
		if (clazzId != null) {
			String whereCondition = "clazzId ="+clazzId;
			total = userDao.userTotal(whereCondition);
		}else
		{
			String whereCondition = "role = 1";
			total = userDao.userTotal(whereCondition);
		}
		return total;
	}

	@Override
	public boolean deleteUser(int id) {
		if( id > 0 )
		{
			return userDao.delete(id);
		}
		return false;
	}

	@Override
	public User getById(int id) {
		
		 User user = userDao.getById(id);
		 if( user != null)
		 {
			 return user;
		 }
		 return null;
		
	}

	@Override
	public boolean deleteClazzUser(int clazzId) {
		if(clazzId > 1)
		{
			return userDao.deleteClazzUser(clazzId);	
		}
		
		return false;
	}

	@Override
	public boolean isClazz(int clazzId) {
		if(clazzId > 1)
		{
			return userDao.isClazz(clazzId);
		}
		return false;
	}

}
