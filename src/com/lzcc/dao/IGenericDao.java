package com.lzcc.dao;

import java.sql.ResultSet;
import java.util.List;

/**
 * 泛型DAO接口:提供DAO层的基本方法
 * 
 *
 */
public interface IGenericDao<T>{

	boolean add(T t);
	boolean update(T t);
	boolean delete(int id);
	T getById(int id);
	T rs2Object(ResultSet rs);
	T getByCondition(String whereCondition);
	List<T> getAll();
	List<T> getList(int pageIndex,int pageSize);
	List<T> getListByConditionString (String whereCondition,Object[] values,int pageIndex,int pageSize);

	
	
	
}
