package com.lzcc.po;

// 类的说明：
public class Course {
    // 编号
    private int id;
    // 课程编号
    private String courseNumber;
    // 课程名称
    private String courseName;
    // 备注
    private String describe;

    // 设置编号属性
    public void setId( int id) {
        this.id = id;
    }
    // 获取编号属性
    public int getId() {
        return this.id;
    }
    // 设置课程编号属性
    public void setCourseNumber( String courseNumber) {
        this.courseNumber = courseNumber;
    }
    // 获取课程编号属性
    public String getCourseNumber() {
        return this.courseNumber;
    }
    // 设置课程名称属性
    public void setCourseName( String courseName) {
        this.courseName = courseName;
    }
    // 获取课程名称属性
    public String getCourseName() {
        return this.courseName;
    }
    // 设置备注属性
    public void setDescribe( String describe) {
        this.describe = describe;
    }
    // 获取备注属性
    public String getDescribe() {
        return this.describe;
    }
	@Override
	public int hashCode() {
		return courseNumber.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		  if(obj instanceof Course){  
			  Course c=(Course) obj;  
	            return (courseNumber.equals(c.getCourseNumber()));  
	        }else{  
	            return super.equals(obj);  
	        }  
	}
    
}