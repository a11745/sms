package com.lzcc.dao;

import com.lzcc.po.User;

public interface IUserDao extends IGenericDao<User> {
	
	int userTotal(String whereCondition);
	boolean deleteClazzUser(int clazzId);
	boolean isClazz(int clazzId);
}
