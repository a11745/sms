package com.lzcc.po;

/**
 * 教师-课程-班级类--学期==教学安排
 *
 *
 */
public class Tcct {
   
	private int id;
	//role=2 教师
	private User user;
	//课程
	private Course course;
	//班级
	private Clazz clazz;
	//学期
	private Term term;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public Clazz getClazz() {
		return clazz;
	}
	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}
	public Term getTerm() {
		return term;
	}
	public void setTerm(Term term) {
		this.term = term;
	}

	
}