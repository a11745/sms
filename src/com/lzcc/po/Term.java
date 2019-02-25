package com.lzcc.po;

/**
 * 学期类：数据内置到数据库
 *
 *
 */
public class Term {

	private int id;
	//学期名称：2014-2015学年-第一学期
	private String name;
	//备注，备用字段
	private String describe;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
	
}
