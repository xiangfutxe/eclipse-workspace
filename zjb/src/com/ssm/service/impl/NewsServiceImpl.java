package com.ssm.service.impl;

import java.sql.Timestamp;
import java.util.Date;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import uk.ltd.getahead.dwr.util.Logger;

import com.ssm.mapper.NewsMapper;
import com.ssm.pojo.News;
import com.ssm.service.NewsService;
import com.utils.Constants;
import com.utils.Pager;

@Service
public class NewsServiceImpl implements NewsService {
	
	Logger log = Logger.getLogger(NewsServiceImpl.class);
	
	@Autowired
	private NewsMapper newsMapper = null;
	
	@Override
	public int insertNews(News news) {
		// TODO 自动生成的方法存根
		int count = 0;
		WebApplicationContext contextLoader = ContextLoader.getCurrentWebApplicationContext();
	      DataSourceTransactionManager tr = contextLoader.getBean(
	    	"transactionManager", DataSourceTransactionManager.class);
	      DefaultTransactionDefinition def = new DefaultTransactionDefinition();
	      def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
	      TransactionStatus status = tr.getTransaction(def);
	      try{
	    	 count = newsMapper.insert(news);
	    	 tr.commit(status);   
	      }catch(Exception e){
	    	  e.printStackTrace();
	    	  tr.rollback(status);
	      }
		return count;
	}
	
	@Override
	public int updateNews(News news) {
		// TODO 自动生成的方法存根
		int count = 0;
		WebApplicationContext contextLoader = ContextLoader.getCurrentWebApplicationContext();
	      DataSourceTransactionManager tr = contextLoader.getBean(
	    	"transactionManager", DataSourceTransactionManager.class);
	      DefaultTransactionDefinition def = new DefaultTransactionDefinition();
	      def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
	      TransactionStatus status = tr.getTransaction(def);
	      try{
	    	  count = newsMapper.update(news);
	    	  tr.commit(status);   
	      }catch(Exception e){
	    	  e.printStackTrace();
	    	  tr.rollback(status);
	      }
		return count;
	}
	
	@Override
	public int deleteAll(String str) {
		// TODO 自动生成的方法存根
		int count = 0;
		WebApplicationContext contextLoader = ContextLoader.getCurrentWebApplicationContext();
	      DataSourceTransactionManager tr = contextLoader.getBean(
	    	"transactionManager", DataSourceTransactionManager.class);
	      DefaultTransactionDefinition def = new DefaultTransactionDefinition();
	      def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
	      TransactionStatus status = tr.getTransaction(def);
	      try{
	    	  String sql = "delete from "+Constants.NEWSTABLE+" where id IN "+str;
	    	  count = newsMapper.deleteBySql(sql);
	    	  tr.commit(status);   
	      }catch(Exception e){
	    	  e.printStackTrace();
	    	  tr.rollback(status);
	      }
		return count;
	}
	

	
	@Override
	public int removeAll(String str) {
		// TODO 自动生成的方法存根
		int count = 0;
		WebApplicationContext contextLoader = ContextLoader.getCurrentWebApplicationContext();
	      DataSourceTransactionManager tr = contextLoader.getBean(
	    	"transactionManager", DataSourceTransactionManager.class);
	      DefaultTransactionDefinition def = new DefaultTransactionDefinition();
	      def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
	      TransactionStatus status = tr.getTransaction(def);
	      Timestamp date = new Timestamp(new Date().getTime());
	      try{
	    	  String sql = "update "+Constants.NEWSTABLE+" set state='0',end_time='"+date+"' where id IN "+str;
	    	  count = newsMapper.updateBySql(sql);
	    	  tr.commit(status);   
	      }catch(Exception e){
	    	  e.printStackTrace();
	    	  tr.rollback(status);
	      }
		return count;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED)
	public int insertNewsList(List<News> newsList) {
		int count = 0;
		Map<String,Object> params = new HashMap<>();
		params.put("tableName",Constants.NEWSTABLE);
		params.put("list",newsList);
		count = newsMapper.insertAll(params);
		return count;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED)
	public Pager findByPager(News news, Pager pager) {
		Map<String,Object> params = new HashMap<>();
		params.put("news",news);
		int recordCount = newsMapper.count(params);
		pager.setRowCount(recordCount);
		if(recordCount>0){
			params.put("pageModel", pager);
		}
		List<News> nlist = newsMapper.findByList(params);
		pager.setResultList(nlist);
		return pager;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED)
	public News findById(Long id) {
		News news = newsMapper.getNews(id);
		return news;
	}
}
