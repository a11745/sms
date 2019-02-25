package com.lzcc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lzcc.dao.ICourseDao;
import com.lzcc.po.Course;
import com.lzcc.util.DbHelper;

// DAO类：CourseDao
public class CourseDao implements ICourseDao {
	
	public boolean update(Course course) {
		String sql = "UPDATE courses SET courseNumber = ?,courseName = ?,`describe` = ? WHERE id = ?";
		Object[] values = { course.getCourseNumber(), course.getCourseName(),
				course.getDescribe(), course.getId() };
		return DbHelper.updateSql(sql, values) > 0;
	}

	public boolean delete(int id) {
		String sql = "DELETE FROM courses WHERE id = ?";
		Object[] values = { id };
		return DbHelper.updateSql(sql, values) > 0;
	}
	@Override
	public boolean add(Course course) {
		String sql = "INSERT INTO courses(courseNumber,courseName,`describe`) VALUES(?,?,?)";
		Object[] values = { course.getCourseNumber(), course.getCourseName(),
				course.getDescribe() };
		return DbHelper.updateSql(sql, values) > 0;
	}

	@Override
	public Course getById(int id) {
		String sql = "SELECT * FROM courses WHERE id = ?";
		Object[] values = { id };
		Course course = null;
		ResultSet rs = DbHelper.querySql(sql, values);
		try {
			if (rs.next()) {
				course = rs2Object(rs);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return course;
	}

	@Override
	public Course rs2Object(ResultSet rs) {
		Course course = null;
		try {
			course = new Course();
			course.setId(rs.getInt("id"));
			course.setCourseNumber(rs.getString("courseNumber"));
			course.setCourseName(rs.getString("courseName"));
			course.setDescribe(rs.getString("describe"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return course;
	}

	@Override
	public Course getByCondition(String whereCondition) {
		return null;
	}

	@Override
	public List<Course> getAll() {
		String sql = "SELECT * FROM courses";
		List<Course> list = new ArrayList<Course>();
		ResultSet rs = DbHelper.querySql(sql, null);
		try {
			while (rs.next()) {
				list.add(rs2Object(rs));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Course> getList(int pageIndex, int pageSize) {
		int firstRecord = (pageIndex - 1) * pageSize;
		String sql = "SELECT * FROM courses where 1= 1 order by courseNumber LIMIT " + firstRecord + ","
				+ pageSize;
		List<Course> list = new ArrayList<Course>();
		ResultSet rs = DbHelper.querySql(sql, null);
		try {
			while (rs.next()) {
				list.add(rs2Object(rs));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Course> getListByConditionString(String whereCondition,
			Object[] values, int pageIndex, int pageSize) {
		int firstRecord = (pageIndex - 1) * pageSize;
		String sql = "SELECT * FROM courses WHERE " + whereCondition
				+ " LIMIT " + firstRecord + "," + pageSize;
		List<Course> list = new ArrayList<Course>();
		ResultSet rs = DbHelper.querySql(sql, values);
		try {
			while (rs.next()) {
				list.add(rs2Object(rs));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int total() {
		
		String sql = "SELECT count(*) FROM courses ";
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
