package com.lzcc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lzcc.dao.IUserDao;
import com.lzcc.po.User;
import com.lzcc.util.DbHelper;

/**
 * 用户DAO层
 * 增删改查基本方法
 * 带分页的查询方法
 * 
 * 
 * 
 */
public class UserDao implements IUserDao {

	@Override
	public boolean add(User user) {

		String sql = "INSERT INTO users(number,userName,password,gender,birthday,`describe`,role,clazzId) VALUES(?,?,?,?,?,?,?,?)";
		Object[] values = { user.getNumber(), user.getUserName(),
				user.getPassword(), user.getGender(), user.getBirthday(),
				user.getDescribe(), user.getRole(), user.getClazz().getId() };
		   int flag = DbHelper.updateSql(sql, values) ;
	        DbHelper.closeResource();
	        return flag > 0;

	}

	@Override
	public boolean update(User user) {
		String sql = "UPDATE users SET number = ?,userName = ?,password = ?,gender = ?,birthday = ?," +
				"`describe` = ?,role=?,clazzId = ? WHERE id = ?";
		Object[] values = { user.getNumber(), user.getUserName(),
				user.getPassword(), user.getGender(), user.getBirthday(),
				user.getDescribe(), user.getRole(), user.getClazz().getId(),user.getId() };
		   int flag = DbHelper.updateSql(sql, values) ;
	        DbHelper.closeResource();
	        return flag > 0;
	}

	@Override
	public User getById(int id) {
		String sql = "SELECT * FROM Users WHERE id = ?";
        Object[] values = {id};
        User entityUser = null;
        ResultSet rs = DbHelper.querySql(sql, values);
        try {
            if(rs.next()) {
                entityUser = rs2Object(rs);
            }
            rs.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        DbHelper.closeResource();
        return entityUser;
	}

	@Override
	public List<User> getAll() {
		  String sql = "SELECT * FROM Users";
	        List<User> list = new ArrayList<User>();
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
	public boolean delete(int id) {
		String sql = "DELETE FROM users WHERE id = ?";
        Object[] values = {id};
        int flag = DbHelper.updateSql(sql, values);
        DbHelper.closeResource();
        return  flag >0;
	}
	@Override
	public User rs2Object(ResultSet rs) {
		  User entityUser = null;
	        try {
	            entityUser = new User();
	            entityUser.setId(rs.getInt("id"));
	            entityUser.setNumber(rs.getString("number"));
	            entityUser.setUserName(rs.getString("UserName"));
	            entityUser.setPassword(rs.getString("password"));
	            entityUser.setGender(rs.getInt("gender"));
	            entityUser.setBirthday(rs.getString("birthday"));
	            entityUser.setDescribe(rs.getString("describe"));
	            entityUser.setRole(rs.getInt("role"));
	            ClazzDao clazzDao = new ClazzDao();
	            entityUser.setClazz(clazzDao.getById(rs.getInt("clazzId")));
	        }catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return entityUser;
	}

	@Override
	public User getByCondition(String whereCondition) {
		String sql = "SELECT * FROM users WHERE "+ whereCondition;
        User entityUser = null;
        ResultSet rs = DbHelper.querySql(sql, null);
        try {
            if(rs.next()) {
                entityUser = rs2Object(rs);
            }
            rs.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        DbHelper.closeResource();
        return entityUser;
	}

	@Override
	public List<User> getList(int pageIndex, int pageSize) {
		int firstRecord = (pageIndex  - 1) * pageSize;
        String sql = "SELECT * FROM users where 1= 1  order by number LIMIT " + firstRecord + "," + pageSize;
        List<User> list = new ArrayList<User>();
        ResultSet rs = DbHelper.querySql(sql, null);
        try {
            while(rs.next()) {
                list.add(rs2Object(rs));
            }
            rs.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
	}

	@Override
	public List<User> getListByConditionString(String whereCondition,
			Object[] values, int pageIndex, int pageSize) {
		int firstRecord = (pageIndex  - 1) * pageSize;
        String sql = "SELECT * FROM users WHERE " + whereCondition+ " order by number  LIMIT " + firstRecord + "," + pageSize;
        List<User> list = new ArrayList<User>();
        ResultSet rs = DbHelper.querySql(sql, values);
        try {
            while(rs.next()) {
                list.add(rs2Object(rs));
            }
            rs.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
	}

	@Override
	public int userTotal(String whereCondition) {
		
		String sql = "SELECT count(*) FROM users WHERE "+ whereCondition;
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

	@Override
	public boolean deleteClazzUser(int clazzId) {
		String sql = "DELETE FROM users WHERE clazzId = ?";
        Object[] values = {clazzId};
        int flag = DbHelper.updateSql(sql, values);
        DbHelper.closeResource();
        return  flag >0;
	}

	@Override
	public boolean isClazz(int clazzId) {
		String sql = "SELECT count(*) FROM users WHERE clazzId = ?";
        Object[] values = {clazzId};
        int num = 0;
        ResultSet rs = DbHelper.querySql(sql, values);
        try {
            if(rs.next()) {
            	num = rs.getInt(1);
            }
            rs.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        DbHelper.closeResource();
		return (num > 0);
	}
	/**
	 * 条件查询用户列表
	 * @param whereCondition
	 * @return
	 */
	public List<User> getUserList(String whereCondition)
	{
        String sql = "SELECT * FROM users WHERE " +whereCondition;
        List<User> list = new ArrayList<User>();
        ResultSet rs = DbHelper.querySql(sql, null);
        try {
            while(rs.next()) {
                list.add(rs2Object(rs));
            }
            rs.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
	}


}
