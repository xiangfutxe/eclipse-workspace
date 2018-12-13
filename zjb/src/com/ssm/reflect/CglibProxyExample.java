package com.ssm.reflect;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxyExample implements MethodInterceptor {
	
	public Object getProxy(Class cls){
		
		//CGLIB enhanncer增强类对象
		Enhancer enhancer = new Enhancer();
		
		//设置增强类型
		enhancer.setSuperclass(cls);
		//定义代理对象为当前对象，要求当前对象实现MethodInterceptor方法
		enhancer.setCallback(this);
		
		//生成并返回代理对象
		return enhancer.create();
	}
	@Override
	public Object intercept(Object proxy, Method method, Object[] args,
			MethodProxy methodProxy) throws Throwable {
		
		System.err.println("调用真实对象前");
		Object result = methodProxy.invokeSuper(proxy, args);
		System.out.println("调用真实对象后");
		return result;
	}

}
