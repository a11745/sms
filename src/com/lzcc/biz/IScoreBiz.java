package com.lzcc.biz;

public interface IScoreBiz {

	int max(int clazzId,int courseId,int userId);
	int min(int clazzId,int courseId,int userId);
	int avg(int clazzId,int courseId,int userId);
}
