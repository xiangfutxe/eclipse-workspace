package com.ssm.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;

import com.ssm.main.TestMain;

public class ReflectServiceMain {
	
	public static void main(String[] args) {
		Logger log = Logger.getLogger(ReflectServiceMain.class);

	
		ReflectServiceImpl object = null;
		try{
			object = (ReflectServiceImpl)Class.forName("com.ssm.reflect.ReflectServiceImpl").newInstance();
			Method method = object.getClass().getMethod("sayHello", String.class);
			method.invoke(object, "张三利斯");
		}catch(ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException ex){
			ex.printStackTrace();
		}
		
	}
	
}
