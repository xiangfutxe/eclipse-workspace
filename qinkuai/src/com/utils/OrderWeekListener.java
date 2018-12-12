package com.utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class OrderWeekListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO 自动生成的方法存根
		new TimerManager();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO 自动生成的方法存根

	}

}
