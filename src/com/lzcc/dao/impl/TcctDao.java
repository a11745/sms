package com.lzcc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lzcc.dao.ITcct;
import com.lzcc.po.Tcct;
import com.lzcc.util.DbHelper;

/**
 * 教学安排表操作。
 * 
 *
 * 
 */
public class TcctDao implements ITcct {

	@Override
	public boolean add(Tcct tcct) {

		String sql = "INSERT INTO tccts(userId,courseId,clazzId,termId) VALUES(?,?,?,?)";
		Object[] values = { tcct.getUser().getId(), tcct.getCourse().getId(),
				tcct.getClazz().getId(), tcct.getTerm().getId() };
		int flag = DbHelper.updateSql(sql, values);
		DbHelper.closeResource();
		return flag > 0;
	}

	@Override
	public boolean delete(int id) {
		String sql = "DELETE FROM tccts WHERE id = ?";
		Object[] values = { id };
		int flag = DbHelper.updateSql(sql, values);
		DbHelper.closeResource();
		return flag > 0;
	}
	public boolean deleteByCourseId(int courseId) {
		String sql = "DELETE FROM tccts WHERE courseId = ?";
		Object[] values = { courseId };
		int flag = DbHelper.updateSql(sql, values);
		DbHelper.closeResource();
		return flag > 0;
	}
	@Override
	public Tcct rs2Object(ResultSet rs) {
		ClazzDao clazzDao = new ClazzDao();
		UserDao userDao = new UserDao();
		CourseDao courseDao = new CourseDao();
		TermDao termDao = new TermDao();
		Tcct tcct = null;
		try {
			tcct = new Tcct();
			tcct.setId(rs.getInt("id"));
			tcct.setUser(userDao.getById(rs.getInt("userId")));
			tcct.setClazz(clazzDao.getById(rs.getInt("clazzId")));
			tcct.setCourse(courseDao.getById(rs.getInt("courseId")));
			tcct.setTerm(termDao.getTermById(rs.getInt("termId")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tcct;
	}

	// 带分页的
	public List<Tcct> getList(int pageIndex, int pageSize) {
		int firstRecord = (pageIndex - 1) * pageSize;
		String sql = "SELECT * FROM tccts LIMIT " + firstRecord + ","
				+ pageSize;
		List<Tcct> list = new ArrayList<Tcct>();
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
	public boolean update(Tcct tcct) {
		String sql = "UPDATE tccts SET userId = ?,clazzId = ?,courseId = ?,termId = ? WHERE id = ?";
		Object[] values = { tcct.getUser().getId(), tcct.getClazz().getId(),
				tcct.getCourse().getId(), tcct.getTerm().getId(), tcct.getId() };
		int flag = DbHelper.updateSql(sql, values);
		DbHelper.closeResource();
		return flag > 0;
	}

	@Override
	public Tcct getById(int id) {
		String sql = "SELECT * FROM tccts WHERE id = ?";
		Object[] values = { id };
		Tcct tcct = null;
		ResultSet rs = DbHelper.querySql(sql, values);
		try {
			if (rs.next()) {
				tcct = rs2Object(rs);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tcct;
	}

	@Override
	public Tcct getByCondition(String whereCondition) {
		String sql = "SELECT * FROM tccts WHERE " + whereCondition;
		Object[] values = { null };
		Tcct tcct = null;
		ResultSet rs = DbHelper.querySql(sql, values);
		try {
			if (rs.next()) {
				tcct = rs2Object(rs);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbHelper.closeResource();
		return tcct;
	}

	@Override
	public List<Tcct> getAll() {
		String sql = "SELECT * FROM tccts";
		List<Tcct> list = new ArrayList<Tcct>();
		ResultSet rs = DbHelper.querySql(sql, null);
		try {
			while (rs.next()) {
				list.add(rs2Object(rs));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbHelper.closeResource();
		return list;
	}

	@Override
	public List<Tcct> getListByConditionString(String whereCondition,
			Object[] values, int pageIndex, int pageSize) {
		int firstRecord = (pageIndex - 1) * pageSize;
		String sql = "SELECT * FROM tccts WHERE " + whereCondition + " LIMIT "
				+ firstRecord + "," + pageSize;
		List<Tcct> list = new ArrayList<Tcct>();
		ResultSet rs = DbHelper.querySql(sql, values);
		try {
			while (rs.next()) {
				list.add(rs2Object(rs));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbHelper.closeResource();
		return list;
	}

	// 查询某个教师的教学安排
	public List<Tcct> getListByConditionString(String whereCondition) {

		String sql = "SELECT * FROM tccts WHERE " + whereCondition;
		List<Tcct> list = new ArrayList<Tcct>();
		ResultSet rs = DbHelper.querySql(sql, null);
		try {
			while (rs.next()) {
				list.add(rs2Object(rs));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbHelper.closeResource();
		return list;
	}

	public List<Tcct> getList(String whereCondition, Object[] values) {
		String sql = "SELECT * FROM tccts WHERE " + whereCondition;
		List<Tcct> list = new ArrayList<Tcct>();
		ResultSet rs = DbHelper.querySql(sql, values);
		try {
			while (rs.next()) {
				list.add(rs2Object(rs));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbHelper.closeResource();
		return list;
	}

	@Override
	public int total() {
		String sql = "SELECT count(*) FROM tccts ";
		int total = 0;
		ResultSet rs = DbHelper.querySql(sql, null);
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

	public int teacherTotal(int id) {
		String sql = "SELECT count(*) FROM tccts where userId = ? ";
		int total = 0;
		Object[] values = { id };
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

	public int studentTotal(int clazzId) {
		String sql = "SELECT count(*) FROM tccts where clazzId = ? ";
		int total = 0;
		Object[] values = { clazzId };
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

	public int getOneTcct(int clazzId, int courseId,int userId) {
		String sql = "SELECT * FROM tccts where clazzId = ? and courseId =? and userId=?";
		Tcct tcct = null;
		Object[] values = { clazzId, courseId ,userId};
		ResultSet rs = DbHelper.querySql(sql, values);
		try {
			if (rs.next()) {
				tcct = rs2Object(rs);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbHelper.closeResource();
		if(tcct != null)
		{
			return tcct.getId();	
		}else
		{
			return 0;
		}
		
	}
	public boolean isCourse(int courseId) {
		String sql = "SELECT count(*) FROM tccts WHERE courseId = ?";
        Object[] values = {courseId};
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
}
