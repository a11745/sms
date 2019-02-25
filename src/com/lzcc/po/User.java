package com.lzcc.po;


/**
 * 用户类 原来设计了三个用户类：教师、学生、管理员 为了能够避免重复代码的编写，聚合为一个用户类。
 *  通过角色来区分它们。 
 * role：0- student
 * 1-teacher 2-admin
 * 
 * 
 * 
 */
public class User{

	// 编号
	private int id;
	// 用户编号
	private String number;
	// 用户姓名
	private String userName;
	// 密码
	private String password;
	// 性别
	private int gender;
	// 生日
	private String birthday;
	// 备注
	private String describe;
	//角色： 0-学生 1-老师    2-管理员内置3个
	private  int role;
	// 学生所属的班级序列号，这里可能有点冗余
	private Clazz clazz;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public Clazz getClazz() {
		return clazz;
	}
	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
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
