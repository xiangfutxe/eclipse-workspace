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

import com.ssm.mapper.UnitMapper;
import com.ssm.pojo.Unit;
import com.ssm.service.UnitService;
import com.utils.Constants;
import com.utils.Pager;

@Service
public class UnitServiceImpl implements UnitService {
	
	Logger log = Logger.getLogger(UnitServiceImpl.class);
	
	@Autowired
	private UnitMapper unitMapper = null;
	
	@Override
	public int insert(Unit unit) {
		// TODO 自动生成的方法存根
		int count = 0;
		WebApplicationContext contextLoader = ContextLoader.getCurrentWebApplicationContext();
	      DataSourceTransactionManager tr = contextLoader.getBean(
	    	"transactionManager", DataSourceTransactionManager.class);
	      DefaultTransactionDefinition def = new DefaultTransactionDefinition();
	      def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
	      TransactionStatus status = tr.getTransaction(def);
	      try{
	    	 count = unitMapper.insert(unit);
	    	 tr.commit(status);   
	      }catch(Exception e){
	    	  e.printStackTrace();
	    	  tr.rollback(status);
	      }
		return count;
	}
	
	@Override
	public int update(Unit unit) {
		// TODO 自动生成的方法存根
		int count = 0;
		WebApplicationContext contextLoader = ContextLoader.getCurrentWebApplicationContext();
	      DataSourceTransactionManager tr = contextLoader.getBean(
	    	"transactionManager", DataSourceTransactionManager.class);
	      DefaultTransactionDefinition def = new DefaultTransactionDefinition();
	      def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
	      TransactionStatus status = tr.getTransaction(def);
	      try{
	    	  count = unitMapper.update(unit);
	    	  tr.commit(status);   
	      }catch(Exception e){
	    	  e.printStackTrace();
	    	  tr.rollback(status);
	      }
		return count;
	}
	
	@Override
	public int delete(String str) {
		// TODO 自动生成的方法存根
		int count = 0;
		WebApplicationContext contextLoader = ContextLoader.getCurrentWebApplicationContext();
	      DataSourceTransactionManager tr = contextLoader.getBean(
	    	"transactionManager", DataSourceTransactionManager.class);
	      DefaultTransactionDefinition def = new DefaultTransactionDefinition();
	      def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
	      TransactionStatus status = tr.getTransaction(def);
	      try{
	    	  String sql = "delete from "+Constants.UNITTABLE+" where id IN "+str;
	    	  count = unitMapper.deleteBySql(sql);
	    	  tr.commit(status);   
	      }catch(Exception e){
	    	  e.printStackTrace();
	    	  tr.rollback(status);
	      }
		return count;
	}
	

	
	@Override
	public int remove(String str) {
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
	    	  String sql = "update "+Constants.UNITTABLE+" set state='0',end_time='"+date+"' where id IN "+str;
	    	  count = unitMapper.updateBySql(sql);
	    	  tr.commit(status);   
	      }catch(Exception e){
	    	  e.printStackTrace();
	    	  tr.rollback(status);
	      }
		return count;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED)
	public Pager findByPager(Unit unit, Pager pager) {
		Map<String,Object> params = new HashMap<>();
		params.put("unit",unit);
		int recordCount = unitMapper.count(params);
		pager.setRowCount(recordCount);
		if(recordCount>0){
			params.put("pageModel", pager);
		}
		List<Unit> nlist = unitMapper.findByList(params);
		pager.setResultList(nlist);
		return pager;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED)
	public Unit findById(Long id) {
		Unit unit = unitMapper.getUnit(id);
		return unit;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED)
	public Unit findByName(String name) {
		Unit unit = unitMapper.getUnitByName(name);
		return unit;
	}
}
