package com.ssm.dao;


import org.apache.ibatis.session.SqlSession;

import com.ssm.mapper.SettleMapper;
import com.ssm.mapper.WRewardMapper;
import com.ssm.mapper.WSettleMapper;
import com.ssm.pojo.Settle;
import com.ssm.utils.SqlSessionFactoryUtils;

public class SettleDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
    SettleMapper stMapper = sqlSession.getMapper(SettleMapper.class);
    WRewardMapper wrdMapper = sqlSession.getMapper(WRewardMapper.class);
    WSettleMapper wstMapper = sqlSession.getMapper(WSettleMapper.class);


    public int maxWeekTag(){
    	int result =0;
    	try {
    		result = stMapper.selectMaxWeek();
			 sqlSession.commit();
    		
    	}catch(Exception e){ 
    		e.printStackTrace();
    	}finally {
    		sqlSession.close();
        }
    	return result;
	}
    
    public String save(Settle st){
    	String msg="";
    	try {
    		Integer result = stMapper.save(st);
    		if(result!=null&&result>0) {
    			msg="yes";
    			sqlSession.commit();
    		}else {
    			msg="结算信息保存失败。";
    		}
    		
    	}catch(Exception e){ 
    		e.printStackTrace();
    		msg="数据保存异常。";
    	}finally {
    		sqlSession.close();
        }
    	return msg;
	}
    
    public int del(Integer weekTag){
    	int result =0;
    	try {
    	
    		result = stMapper.deleteByMonthTag(weekTag);
    		
    	}catch(Exception e){ 
    		e.printStackTrace();
    	}finally {
    		sqlSession.close();
        }
    	return result;
	}
    
    public int update(Settle st){
    	int result =0;
    	try {
    		result = stMapper.update(st);
    		if(result>0)
			 sqlSession.commit();
    		
    	}catch(Exception e){ 
    		e.printStackTrace();
    	}finally {
    		sqlSession.close();
        }
    	return result;
	}
    
    public int init_settle(Settle st){
    	int result =0;
    	try {
    		Settle st1 = stMapper.selectByMonthTag(st.getMonthTag());
    		if(st1==null){
    			result = stMapper.save(st);
    		}
    		if(result>0)
    			sqlSession.commit();
    	}catch(Exception e){ 
    		e.printStackTrace();
    	}finally {
    		sqlSession.close();
        }
    	return result;
	}
    
    public Settle getNextSettle(){
    	Settle st = null;
    	try {
    		int max = stMapper.selectMaxWeek();
    		st = stMapper.selectByMonthTag(max);
    		if(st==null){
    			sqlSession.commit();
    		}
    	}catch(Exception e){ 
    		e.printStackTrace();
    	}finally {
    		sqlSession.close();
        }
    	return st;
	}
    
    public Settle findByMonthTag(Integer monthTag){
    	Settle st = null;
    	try {
    	
    		st = stMapper.selectByMonthTag(monthTag);
    			sqlSession.commit();
    	}catch(Exception e){ 
    		e.printStackTrace();
    	}finally {
    		sqlSession.close();
        }
    	return st;
    }
    
}
