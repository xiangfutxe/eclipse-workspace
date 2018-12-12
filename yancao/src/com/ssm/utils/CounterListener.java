package com.ssm.utils;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class CounterListener implements HttpSessionListener{

	public static int count;
	public CounterListener(){
		count = 0;
	}
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		count++;
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		if(count>0) count--;
	}

}
