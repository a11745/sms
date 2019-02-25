package com.lzcc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.lzcc.dao.IClazzDao;
import com.lzcc.po.Clazz;
import com.lzcc.util.DbHelper;
/**
 * 班级DAO类
 *  增删改查基本方法
 * 带分页的查询方法
 * 
 *
 */
// DAO类：ClazzDao
public class ClazzDao implements IClazzDao {
    // 按主键查询实体对象的方法
	@Override
	public boolean add(Clazz clazz) {
		 String sql = "INSERT INTO clazzs(clazzNumber,clazzName,`describe`) VALUES(?,?,?)";
	        Object[] values = {clazz.getClazzNumber(),clazz.getClazzName(),clazz.getDescribe()};
	        int flag = DbHelper.updateSql(sql, values) ;
	        DbHelper.closeResource();
	        return flag > 0;
		
	}
	@Override
	public boolean update(Clazz clazz) {
		String sql = "UPDATE clazzs SET clazzNumber = ?,clazzName = ?,`describe` = ? WHERE id = ?";
        Object[] values = {clazz.getClazzNumber(),clazz.getClazzName(),clazz.getDescribe(),clazz.getId()};
        int flag = DbHelper.updateSql(sql, values) ;
        DbHelper.closeResource();
        return flag > 0;
	}
	@Override
	public Clazz getById(int id) {
		 String sql = "SELECT * FROM clazzs WHERE id = ?";
	        Object[] values = {id};
	        Clazz clazz = null;
	        ResultSet rs = DbHelper.querySql(sql, values);
	        try {
	            if(rs.next()) {
	                clazz = rs2Object(rs);
	            }
	            rs.close();
	        }catch (SQLException e) {
	            e.printStackTrace();
	        }
	        DbHelper.closeResource();
	        return clazz;
	}
	@Override
	public Clazz rs2Object(ResultSet rs) {
		 Clazz clazz = null;
	        try {
	            clazz = new Clazz();
	            clazz.setId(rs.getInt("id"));
	            clazz.setClazzNumber(rs.getString("clazzNumber"));
	            clazz.setClazzName(rs.getString("clazzName"));
	            clazz.setDescribe(rs.getString("describe"));
	        }catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return clazz;
	}
	@Override
	public Clazz getByCondition(String whereCondition) {
		String sql = "SELECT * FROM Clazzs WHERE "+ whereCondition;
        Object[] values = {null};
        Clazz clazz = null;
        ResultSet rs = DbHelper.querySql(sql, values);
        try {
            if(rs.next()) {
                clazz = rs2Object(rs);
            }
            rs.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        DbHelper.closeResource();
        return clazz;
	}
	@Override
	public List<Clazz> getAll() {
		 String sql = "SELECT * FROM clazzs where 1= 1  order by clazzNumber";
	        List<Clazz> list = new ArrayList<Clazz>();
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
	public List<Clazz> getList(int pageIndex, int pageSize) {
		 int firstRecord = (pageIndex  - 1) * pageSize;
	        String sql = "SELECT * FROM clazzs where 1= 1  order by clazzNumber"+ " LIMIT " + firstRecord + "," + pageSize ;
	        List<Clazz> list = new ArrayList<Clazz>();
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
	        Collections.sort(list,new Comparator<Clazz>() {
				@Override
				public int compare(Clazz clazz1, Clazz clazz2) {
					return clazz1.getClazzNumber().compareTo(clazz2.getClazzNumber());
				}
			});
	        return list;
	}
	@Override
	public List<Clazz> getListByConditionString(String whereCondition,
			Object[] values, int pageIndex, int pageSize) {
		   int firstRecord = (pageIndex  - 1) * pageSize;
	        String sql = "SELECT * FROM clazzs WHERE " + whereCondition+ " LIMIT " + firstRecord + "," + pageSize;
	        List<Clazz> list = new ArrayList<Clazz>();
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
		  String sql = "DELETE FROM clazzs WHERE id = ?";
	        Object[] values = {id};
	        int flag = DbHelper.updateSql(sql, values) ;
	        DbHelper.closeResource();
	        return flag > 0;
	}
	public int clazzTotal() {
		
		String sql = "SELECT count(*) FROM clazzs ";
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
}
