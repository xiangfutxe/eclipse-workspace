package com.ssm.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ssm.mapper.AddressMapper;
import com.ssm.mapper.SettleMapper;
import com.ssm.mapper.WRewardMapper;
import com.ssm.mapper.WSettleMapper;
import com.ssm.pojo.Address;
import com.ssm.pojo.Settle;
import com.ssm.utils.Pager;
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
    
    public int save(Settle st){
    	int result =0;
    	try {
    		result = stMapper.save(st);
    		String table_name_r = "wreward_"+st.getWeekTag();
    		String table_name_s = "wsettle_"+st.getWeekTag();
    		
    		if(result>0){
    			wrdMapper.createWReward(table_name_r);
        		wstMapper.createWSettle(table_name_s);
        		sqlSession.commit();
    		}
    		
    	}catch(Exception e){ 
    		e.printStackTrace();
    	}finally {
    		sqlSession.close();
        }
    	return result;
	}
    
    public int del(Integer weekTag){
    	int result =0;
    	try {
    		String table_name_r = "wreward_"+weekTag;
    		String table_name_s = "wsettle_"+weekTag;
    		result = stMapper.deleteByWeekTag(weekTag);
    		if(result>0){
    			wrdMapper.dropWReward(table_name_r);
    			wstMapper.dropWSettle(table_name_s);
    			sqlSession.commit();
    		}
    		
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
    		Settle st1 = stMapper.selectByWeekTag(st.getWeekTag());
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
    		st = stMapper.selectByWeekTag(max);
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
    
    public Settle findByWeekTag(Integer weekTag){
    	Settle st = null;
    	try {
    	
    		st = stMapper.selectByWeekTag(weekTag);
    		
    			sqlSession.commit();
    	}catch(Exception e){ 
    		e.printStackTrace();
    	}finally {
    		sqlSession.close();
        }
    	return st;
    }
    
}
