package com.ssm.reflect;

public class TestCglibProxy {

	public static void main(String[] args) {
		CglibProxyExample cpe = new CglibProxyExample();
		ReflectServiceImpl proxy = (ReflectServiceImpl) cpe.getProxy(ReflectServiceImpl.class);
		proxy.sayHello("张三");
	}

}
