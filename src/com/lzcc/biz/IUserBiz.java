package com.lzcc.biz;

import java.util.List;

import com.lzcc.po.User;

/**
 * 用户 业务逻辑层
 * 
 *
 */
public interface IUserBiz {

	User login(String number,String password);
	User update(User user);
	User addUser(User user);
	List<User> getAllUser(Integer clazzId,int pageIndex,int pageSize);
	int userTotal(Integer clazzId);
	boolean deleteUser(int id);
	User getById(int id);
	boolean deleteClazzUser(int clazzId);
	boolean isClazz(int clazzId);
}
