package com.ssm.main;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.ssm.pojo.News;
import com.ssm.service.NewsService;

public class TestMain {
		public static void main(String[] args) {
			Logger log = Logger.getLogger(TestMain.class);
			Timestamp date = new Timestamp(new Date().getTime());
			ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-cfg.xml");
			NewsService newsService = ctx.getBean(NewsService.class);
			List<News> list = new ArrayList<News>();
			for(int i=20;i<25;i++){
				News news = new News();
				news.setTitle("测试用例_"+i);
				news.setContents("123自由人");
				news.setPublisher("admin");
				news.setState(1);
				news.setClicks(new Long(0));
				news.setEntryTime(date);
				news.setEndTime(date);
				list.add(news);
			}
			int count=0;
			if(list.size()>0){
				count=newsService.insertNewsList(list);
			}
			if(count>0)
				log.info("success:"+count);
			else log.error("false");
			/*
			News news = new News();
			int pageNo = 1;
			int pageSize = 2;
			Pager pager= new Pager();
			pager.setPageNo(pageNo);
			pager.setPageSize(pageSize);
			pager= newsService.findByPager(news, pager);
			System.out.println(pager.getPageCount());
			System.out.println(MD5.GetMD5Code("zhkc2018"));
			*/
		}

}
