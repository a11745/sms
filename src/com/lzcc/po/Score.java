package com.lzcc.po;

/**
 * 成绩类
 * 
 *
 */
public class Score {

	private int id;
	//分数
	private int score;;
	//教学安排
	private Tcct tcct;
	//得到成绩的学生
	private User user;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Tcct getTcct() {
		return tcct;
	}
	public void setTcct(Tcct tcct) {
		this.tcct = tcct;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		return this.equals(obj);
	}
    
	
}
