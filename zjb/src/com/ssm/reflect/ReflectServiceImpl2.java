package com.ssm.reflect;

import java.lang.reflect.InvocationTargetException;

public class ReflectServiceImpl2 {
	private String name;
	
	
	public ReflectServiceImpl2(String name){
		this.name = name;
	}
	
	public void sayHello(){
		System.out.println("hello "+name);
	}
	
	public ReflectServiceImpl2 getInstance(){
		ReflectServiceImpl2 object = null;
		try{
			object = (ReflectServiceImpl2)Class.forName("com.ssm.reflect.ReflectServiceImpl2").getConstructor(String.class).newInstance("张三");
		}catch(ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex){
			ex.printStackTrace();
		}
		return object;
	}
}
