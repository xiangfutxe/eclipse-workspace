package com.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

import com.service.IOrderService;
import com.service.OrderService;

public class OrderWeekTask extends TimerTask {
	private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public void run() {
        try {
             //在这里写你要执行的内容
        	IOrderService os = new OrderService();
        	String msg = os.updateOrderWeek();
        	Date date = new Date();
        	FileOutputStream fs = new FileOutputStream(new File("order_week_log_"+formatter.format(date)+".txt"));
        	PrintStream p = new PrintStream(fs);
        	p.println(msg);
        	p.close();
            System.out.println("执行情况："+msg);
        } catch (Exception e) {
            System.out.println("-------------解析信息发生异常--------------");
        }
    }
}
