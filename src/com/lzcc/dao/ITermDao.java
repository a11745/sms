package com.lzcc.dao;

import java.util.List;

import com.lzcc.po.Term;

public interface ITermDao {

	List<Term> getAllTerm();
	Term getTermById(int id);
	
}
