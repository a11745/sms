package com.lzcc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lzcc.dao.ITermDao;
import com.lzcc.po.Term;
import com.lzcc.util.DbHelper;

public class TermDao  implements ITermDao{

	
	public Term rs2Object(ResultSet rs) {
		  Term term = null;
	        try {
	        	term = new Term();
	        	term.setId(rs.getInt("id"));
	        	term.setName(rs.getString("name"));
	        	term.setDescribe(rs.getString("describe"));
	        }catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return term;
	}
	@Override
	public List<Term> getAllTerm() {
		 String sql = "SELECT * FROM Terms";
	        List<Term> list = new ArrayList<Term>();
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
	public Term getTermById(int id) {
		String sql = "SELECT * FROM terms WHERE id = ?";
        Object[] values = {id};
        Term term  = null;
        ResultSet rs = DbHelper.querySql(sql, values);
        try {
            if(rs.next()) {
                term = rs2Object(rs);
            }
            rs.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        DbHelper.closeResource();
        return term;
	}
	}

