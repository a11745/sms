package com.lzcc.dao;

import com.lzcc.po.Course;

/**
 * 课程 数据访问接口
 * 
 *
 */
public interface ICourseDao extends IGenericDao<Course>  {

	int total();
}