package com.lzcc.po;

// 类的说明：
public class Clazz {
    // 属性说明：序列号
    private int id;
    // 属性说明：班级编号
    private String clazzNumber;
    // 属性说明：班级名称
    private String clazzName;
    // 属性说明：备注
    private String describe;

    // 设置序列号属性
    public void setId( int id) {
        this.id = id;
    }
    // 获取序列号属性
    public int getId() {
        return this.id;
    }
    // 设置班级编号属性
    public void setClazzNumber( String clazzNumber) {
        this.clazzNumber = clazzNumber;
    }
    // 获取班级编号属性
    public String getClazzNumber() {
        return this.clazzNumber;
    }
    // 设置班级名称属性
    public void setClazzName( String clazzName) {
        this.clazzName = clazzName;
    }
    // 获取班级名称属性
    public String getClazzName() {
        return this.clazzName;
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
		return clazzNumber.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		  if(obj instanceof Clazz){  
			  Clazz c=(Clazz) obj;  
	            return (clazzNumber.equals(c.getClazzNumber()));  
	        }else{  
	            return super.equals(obj);  
	        }  
	}
    
}