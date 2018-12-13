package com.ssm.reflect;

public class ReflectServiceImpl {
	public void sayHello(String name){
		System.out.println("Hello "+name);
	}
	
	public ReflectServiceImpl getInstance(){
		ReflectServiceImpl object = null;
		try{
			object = (ReflectServiceImpl)Class.forName("com.ssm.reflect.ReflectServiceImpl").newInstance();
		}catch(ClassNotFoundException | InstantiationException | IllegalAccessException ex){
			ex.printStackTrace();
		}
		return object;
	}
	
}
