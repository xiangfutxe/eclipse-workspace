package com.ssm.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ssm.pojo.JuiceMaker;

public class JuiceMakerMain {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-cfg.xml");
		JuiceMaker juiceMaker = (JuiceMaker) ctx.getBean("juiceMaker");
		System.out.println(juiceMaker.makeJuice());
		
	}

}
