package com.ssm.main;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ssm.mapper.MemberMapper;
import com.ssm.pojo.Member;
import com.ssm.pojo.MemberInfo;
import com.ssm.pojo.News;
import com.ssm.service.MemberService;
import com.ssm.service.NewsService;
import com.utils.MD5;

public class MemberMain {
		public static void main(String[] args) {
			Logger log = Logger.getLogger(MemberMain.class);
			Timestamp date = new Timestamp(new Date().getTime());
			ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-cfg.xml");
			MemberService memberService = ctx.getBean(MemberService.class);
			Member member = new Member();
			MemberInfo info = new MemberInfo();
			member.setNickName("ceshi2");
			member.setEmoney((double) 1000);
			member.setIntegral(1000);
			member.setRank(0);
			member.setState(1);
			member.setEntryTime(date);
			member.setEndTime(date);
			member.setVersion((long) 0);
			member.setViewNum((long) 0);
			member.setPassword(MD5.GetMD5Code("123456"));
			member.setPassword2(MD5.GetMD5Code("123456"));
			info.setBirth(date);
			info.setEducation("大学本科");
			info.setEndTime(date);
			info.setEntryTime(date);
			info.setMobile("13800000000");
			info.setMaritalStatus("未婚");
			info.setRealName("绝世神偷");
			info.setSex("男");
			long count  = memberService.insert(member, info);
				
			if(count>0)
				log.info("success!");
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
