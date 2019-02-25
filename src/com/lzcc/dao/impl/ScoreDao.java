package com.lzcc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lzcc.dao.IScore;
import com.lzcc.po.Score;
import com.lzcc.util.DbHelper;

public class ScoreDao implements IScore {

	@Override
	public boolean add(Score score) {
		 String sql = "INSERT INTO scores(score,tcctId,userId) VALUES(?,?,?)";
	        Object[] values = {score.getScore(),score.getTcct().getId(),score.getUser().getId()};
	        int flag = DbHelper.updateSql(sql, values) ;
	        DbHelper.closeResource();
	        return flag > 0;
		
	}
	@Override
	public boolean update(Score score) {
		String sql = "UPDATE scores SET score = ?,tcctId = ?,userId = ? WHERE id = ?";
        Object[] values = {score.getScore(),score.getTcct().getId(),score.getUser().getId(),score.getId()};
        int flag = DbHelper.updateSql(sql, values) ;
        DbHelper.closeResource();
        return flag > 0;
	}
	@Override
	public Score getById(int id) {
		 String sql = "SELECT * FROM scores WHERE id = ?";
	        Object[] values = {id};
	        Score score = null;
	        ResultSet rs = DbHelper.querySql(sql, values);
	        try {
	            if(rs.next()) {
	                score = rs2Object(rs);
	            }
	            rs.close();
	        }catch (SQLException e) {
	            e.printStackTrace();
	        }
	        DbHelper.closeResource();
	        return score;
	}
	@Override
	public Score rs2Object(ResultSet rs) {
		 Score score = null;
		 TcctDao tcctDao = new TcctDao();
		 UserDao userDao = new UserDao();
	        try {
	            score = new Score();
	            score.setId(rs.getInt("id"));
	            score.setScore(rs.getInt("score"));
	            score.setTcct(tcctDao.getById(rs.getInt("tcctId")));
	            score.setUser(userDao.getById(rs.getInt("userId")));
	        }catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return score;
	}
	@Override
	public Score getByCondition(String whereCondition) {
		String sql = "SELECT * FROM Scores WHERE "+ whereCondition;

        Score score = null;
        ResultSet rs = DbHelper.querySql(sql, null);
        try {
            if(rs.next()) {
                score = rs2Object(rs);
            }
            rs.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        DbHelper.closeResource();
        return score;
	}
	@Override
	public List<Score> getAll() {
		 String sql = "SELECT * FROM scores where 1= 1  order by score";
	        List<Score> list = new ArrayList<Score>();
	        ResultSet rs = DbHelper.querySql(sql, null);
	        try {
	            while(rs.next()) {
	                list.add(rs2Object(rs));
	            }
	            rs.close();
	        }catch (SQLException e) {
	            e.printStackTrace();
	        }
	        DbHelper.closeResource();
	        return list;
	}
	@Override
	public List<Score> getList(int pageIndex, int pageSize) {
		 int firstRecord = (pageIndex  - 1) * pageSize;
	        String sql = "SELECT * FROM scores where 1= 1  order by score"+ " LIMIT " + firstRecord + "," + pageSize ;
	        List<Score> list = new ArrayList<Score>();
	        ResultSet rs = DbHelper.querySql(sql, null);
	        try {
	            while(rs.next()) {
	                list.add(rs2Object(rs));
	            }
	            rs.close();
	        }catch (SQLException e) {
	            e.printStackTrace();
	        }
	        DbHelper.closeResource();
	        return list;
	}
	@Override
	public List<Score> getListByConditionString(String whereCondition,
			Object[] values, int pageIndex, int pageSize) {
		   int firstRecord = (pageIndex  - 1) * pageSize;
	        String sql = "SELECT * FROM scores WHERE " + whereCondition+ " LIMIT " + firstRecord + "," + pageSize;
	        List<Score> list = new ArrayList<Score>();
	        ResultSet rs = DbHelper.querySql(sql, values);
	        try {
	            while(rs.next()) {
	                list.add(rs2Object(rs));
	            }
	            rs.close();
	        }catch (SQLException e) {
	            e.printStackTrace();
	        }
	        DbHelper.closeResource();
	        return list;
	}
	//查询所有
	public List<Score> getListByConditionString(String whereCondition,
			Object[] values) {
	        String sql = "SELECT * FROM scores WHERE " + whereCondition;
	        List<Score> list = new ArrayList<Score>();
	        ResultSet rs = DbHelper.querySql(sql, values);
	        try {
	            while(rs.next()) {
	                list.add(rs2Object(rs));
	            }
	            rs.close();
	        }catch (SQLException e) {
	            e.printStackTrace();
	        }
	        DbHelper.closeResource();
	        return list;
	}
	@Override
	public boolean delete(int id) {
		  String sql = "DELETE FROM scores WHERE id = ?";
	        Object[] values = {id};
	        int flag = DbHelper.updateSql(sql, values) ;
	        DbHelper.closeResource();
	        return flag > 0;
	}
	@Override
	public int Total() {
		
		String sql = "SELECT count(*) FROM scores ";
        int total= 0;
        ResultSet rs = DbHelper.querySql(sql, null);
        try {
            if(rs.next()) {
            	total = rs.getInt(1);
            }
            rs.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        DbHelper.closeResource();
        return total;
	}
	public int scoreTotal(int tcctId) {
		String sql = "SELECT count(*) FROM scores where tcctId = ? ";
		int total = 0;
		Object[] values = {tcctId};
		ResultSet rs = DbHelper.querySql(sql, values);
		try {
			if (rs.next()) {
				total = rs.getInt(1);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbHelper.closeResource();
		return total;
	}
	public int studentTotal(int userId) {
		String sql = "SELECT count(*) FROM scores where userId = ? ";
		int total = 0;
		Object[] values = {userId};
		ResultSet rs = DbHelper.querySql(sql, values);
		try {
			if (rs.next()) {
				total = rs.getInt(1);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbHelper.closeResource();
		return total;
	}
	
	//查询本班本科目最高分，最低分。平均分.
	public int max(int tcctId)
	{
		
		String sql = "SELECT max(score) FROM scores where tcctId = ? ";
		int max = 0;
		Object[] values = {tcctId};
		ResultSet rs = DbHelper.querySql(sql, values);
		try {
			if (rs.next()) {
				max = rs.getInt(1);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbHelper.closeResource();
		return max;
	}
	public int min(int tcctId)
	{
		
		String sql = "SELECT min(score) FROM scores where tcctId = ? ";
		int min = 0;
		Object[] values = {tcctId};
		ResultSet rs = DbHelper.querySql(sql, values);
		try {
			if (rs.next()) {
				min = rs.getInt(1);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbHelper.closeResource();
		return min;
	}
	public int avg(int tcctId)
	{
		
		String sql = "SELECT avg(score) FROM scores where tcctId = ? ";
		int avg = 0;
		Object[] values = {tcctId};
		ResultSet rs = DbHelper.querySql(sql, values);
		try {
			if (rs.next()) {
				avg = rs.getInt(1);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbHelper.closeResource();
		return avg;
	}
	}
