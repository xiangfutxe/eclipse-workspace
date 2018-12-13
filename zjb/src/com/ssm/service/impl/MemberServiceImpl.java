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

import com.ssm.mapper.MemberInfoMapper;
import com.ssm.mapper.MemberMapper;
import com.ssm.pojo.Member;
import com.ssm.pojo.MemberInfo;
import com.ssm.service.MemberService;
import com.utils.Constants;
import com.utils.MD5;
import com.utils.Pager;

@Service
public class MemberServiceImpl implements MemberService {
	
	Logger log = Logger.getLogger(MemberServiceImpl.class);
	
	@Autowired
	private MemberMapper memberMapper = null;
	
	@Autowired
	private MemberInfoMapper memberInfoMapper = null;
	
	@Override
	public int insert(Member member,MemberInfo info) {
		// TODO 自动生成的方法存根
		WebApplicationContext contextLoader = ContextLoader.getCurrentWebApplicationContext();
	      DataSourceTransactionManager tr = contextLoader.getBean("transactionManager", DataSourceTransactionManager.class);
	      //2.获取事务定义
	      DefaultTransactionDefinition def = new DefaultTransactionDefinition();
	      //3.设置事务隔离级别，开启新事务
	      def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
	      //4.获得事务状态
	      TransactionStatus status = tr.getTransaction(def);
	      int count =  0;
			int result = 0;
		try{
			count = memberMapper.insert(member);
			if(count>0){
				info.setMemberId(member.getId());
				result = memberInfoMapper.insert(info);
				if(result>0) tr.commit(status);
				else tr.rollback(status);
			}else tr.rollback(status);
	 }catch(Exception e){
   	  e.printStackTrace();
   	  tr.rollback(status);
     }
		return count;
	}
	
	@Override
	public int update(MemberInfo info) {
		// TODO 自动生成的方法存根
		WebApplicationContext contextLoader = ContextLoader.getCurrentWebApplicationContext();
	      DataSourceTransactionManager tr = contextLoader.getBean("transactionManager", DataSourceTransactionManager.class);
	      //2.获取事务定义
	      DefaultTransactionDefinition def = new DefaultTransactionDefinition();
	      //3.设置事务隔离级别，开启新事务
	      def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
	      //4.获得事务状态
	      TransactionStatus status = tr.getTransaction(def);
	      int count =  0;
		try{
				count = memberInfoMapper.updateForMid(info);
				if(count>0) tr.commit(status);
				else tr.rollback(status);
		 }catch(Exception e){
		 	  e.printStackTrace();
		 	  tr.rollback(status);
		 }
			return count;
		}
	

	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED)
	public Pager findByPager(Member member, Pager pager) {
		Map<String,Object> params = new HashMap<>();
		params.put("member",member);
		int recordCount = memberMapper.count(params);
		pager.setRowCount(recordCount);
		if(recordCount>0){
			params.put("pageModel", pager);
		}
		List<Member> list = memberMapper.findByPager(params);
		pager.setResultList(list);
		return pager;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED)
	public Member findById(Long id) {
		Member Member = memberMapper.getById(id);
		return Member;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED)
	public Member findAllById(Long id) {
		Member member = memberMapper.findAllById(id);
		return member;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED)
	public Member findAllByIdForInfo(Long id) {
		Member member = memberMapper.findAllByIdForInfo(id);
		return member;
	}

	
	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED)
	public Member findByNickName(String name) {
		Member member = memberMapper.getMemberByName(name);
		return member;
	}

	@Override
	public int update(Member member) {
		// TODO 自动生成的方法存根
				WebApplicationContext contextLoader = ContextLoader.getCurrentWebApplicationContext();
			      DataSourceTransactionManager tr = contextLoader.getBean("transactionManager", DataSourceTransactionManager.class);
			      //2.获取事务定义
			      DefaultTransactionDefinition def = new DefaultTransactionDefinition();
			      //3.设置事务隔离级别，开启新事务
			      def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
			      //4.获得事务状态
			      TransactionStatus status = tr.getTransaction(def);
			      int count =  0;
				try{
						count = memberMapper.update(member);
						if(count>0) tr.commit(status);
						else tr.rollback(status);
				 }catch(Exception e){
				 	  e.printStackTrace();
				 	  tr.rollback(status);
				 }
					return count;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED)
	public int update(String sql) {
		// TODO 自动生成的方法存根
		int result = memberMapper.updateBySql(sql);
		return result;
	}
	
	@Override
	public Member login(Member member){
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
	      Member login_member = null;
	      try{
					login_member= memberMapper.getMemberByName(member.getNickName());
					if(login_member!=null){
						if(login_member.getPassword().equals(MD5.GetMD5Code(member.getPassword()))){
							String sql = "update "+Constants.MEMBERTABLE+" set version=version+1,view_num=view_num+1,end_time='"+member.getEndTime()+"' where id='"+login_member.getId()+"' and version='"+login_member.getVersion()+"'";
							if(memberMapper.updateBySql(sql)==0){
								tr.rollback(status);
							}
						}else{
							login_member = null;
						}
					}
	      }catch(Exception e){
	    	  e.printStackTrace();
	    	  tr.rollback(status);
	      }finally{
	    	  tr.commit(status);   
	      }
        return login_member;
	}
	
}
