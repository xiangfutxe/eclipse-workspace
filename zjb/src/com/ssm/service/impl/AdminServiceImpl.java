package com.ssm.service.impl;

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

import com.ssm.mapper.AdminMapper;
import com.ssm.pojo.Admin;
import com.ssm.service.AdminService;
import com.utils.Constants;
import com.utils.MD5;
import com.utils.Pager;

@Service
public class AdminServiceImpl implements AdminService {
	
	Logger log = Logger.getLogger(AdminServiceImpl.class);
	
	@Autowired
	private AdminMapper adminMapper = null;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED)
	public int insert(Admin admin) {
		// TODO 自动生成的方法存根
		int count = 0;
		count = adminMapper.insert(admin);
		return count;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED)
	public int insertList(List<Admin> adminList) {
		int count = 0;
		Map<String,Object> params = new HashMap<>();
		params.put("tableName", Constants.ADMINTABLE);
		params.put("list",adminList);
		count = adminMapper.insertAll(params);
		return count;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED)
	public Pager findByPager(Admin Admin, Pager pager) {
		Map<String,Object> params = new HashMap<>();
		params.put("admin",Admin);
		int recordCount = adminMapper.count(params);
		pager.setRowCount(recordCount);
		if(recordCount>0){
			params.put("pageModel", pager);
		}
		List<Admin> list = adminMapper.findByPager(params);
		pager.setResultList(list);
		return pager;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED)
	public Admin findById(Integer id) {
		Admin Admin = adminMapper.getById(id);
		return Admin;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED)
	public Admin findByAdminName(String adminName) {
		Admin admin = adminMapper.getAdminByName(adminName);
		return admin;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED)
	public int update(Admin admin) {
		// TODO 自动生成的方法存根
		return 0;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED)
	public int update(String sql) {
		// TODO 自动生成的方法存根
		int result = adminMapper.updateBySql(sql);
		return result;
	}
	
	@Override
	public Admin login(Admin admin){
		 //1.获取事务控制管理器
		WebApplicationContext contextLoader = ContextLoader.getCurrentWebApplicationContext();
	      DataSourceTransactionManager tr = contextLoader.getBean(
	    		  "transactionManager", DataSourceTransactionManager.class);
	      //2.获取事务定义
	      DefaultTransactionDefinition def = new DefaultTransactionDefinition();
	      //3.设置事务隔离级别，开启新事务
	      def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
	      //4.获得事务状态
	      TransactionStatus status = tr.getTransaction(def);
	      Admin login_admin = null;
	      try{
					login_admin= adminMapper.getAdminByName(admin.getAdminName());
					if(login_admin!=null){
						if(login_admin.getPassword().equals(MD5.GetMD5Code(admin.getPassword()))){
							String sql = "update admin set version=version+1,view_num=view_num+1,end_time='"+admin.getEndTime()+"' where id='"+login_admin.getId()+"' and version='"+login_admin.getVersion()+"'";
							if(adminMapper.updateBySql(sql)==0){
								tr.rollback(status);
							}
						}else{
							login_admin = null;
						}
					}
	      }catch(Exception e){
	    	  e.printStackTrace();
	    	  tr.rollback(status);
	      }finally{
	    	  tr.commit(status);   
	      }
        return login_admin;
	}
	
}
