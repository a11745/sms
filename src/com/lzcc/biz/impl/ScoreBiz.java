package com.lzcc.biz.impl;

import com.lzcc.biz.IScoreBiz;
import com.lzcc.dao.impl.ScoreDao;
import com.lzcc.dao.impl.TcctDao;

public class ScoreBiz implements IScoreBiz {

	private ScoreDao scoreDao = new ScoreDao();
	private TcctDao tcctDao = new TcctDao();
	@Override
	public int max(int clazzId, int courseId,int userId) {
		int score = 0;
		if(clazzId > 0 && courseId > 0)
		{
			int tcctId = tcctDao.getOneTcct(clazzId, courseId,userId);
			 score= scoreDao.max(tcctId);
		}
		return score;
	}

	@Override
	public int min(int clazzId, int courseId,int userId) {
		int score = 0;
		if(clazzId > 0 && courseId > 0)
		{
			int tcctId = tcctDao.getOneTcct(clazzId, courseId,userId);
			 score= scoreDao.min(tcctId);
		}
		return score;
	}

	@Override
	public int avg(int clazzId, int courseId,int userId) {
		int score = 0;
		if(clazzId > 0 && courseId > 0)
		{
			int tcctId = tcctDao.getOneTcct(clazzId, courseId,userId);
			 score= scoreDao.avg(tcctId);
		}
		return score;
	}

}
