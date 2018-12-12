package com.ssm.dao;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ssm.mapper.AddressMapper;
import com.ssm.mapper.DRewardMapper;
import com.ssm.mapper.DSettleMapper;
import com.ssm.mapper.SettleDayMapper;
import com.ssm.mapper.SettleMapper;
import com.ssm.mapper.WRewardMapper;
import com.ssm.mapper.WSettleMapper;
import com.ssm.pojo.Address;
import com.ssm.pojo.SettleDay;
import com.ssm.pojo.Settle;
import com.ssm.utils.Pager;
import com.ssm.utils.SqlSessionFactoryUtils;

public class SettleDayDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
    SettleDayMapper stMapper = sqlSession.getMapper(SettleDayMapper.class);
    DRewardMapper wrdMapper = sqlSession.getMapper(DRewardMapper.class);
    DSettleMapper wstMapper = sqlSession.getMapper(DSettleMapper.class);


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
    
    public int save(SettleDay st){
    	int result =0;
    	try {
    		if(stMapper.selectByDayTime(st.getStartTime())==null){
	    		result = stMapper.save(st);
	    		String table_name_r = "reward_day_"+st.getWeekTag();
	    		String table_name_s = "settle_day_"+st.getWeekTag();
	    		if(result>0){
	    				wrdMapper.createDReward(table_name_r);
	    				wstMapper.createDSettle(table_name_s);
	    			
	        		sqlSession.commit();
	    		}
    		}
    	}catch(Exception e){ 
    		e.printStackTrace();
    	}finally {
    		sqlSession.close();
        }
    	return result;
	}
    
    public int del(Timestamp date,int weekTag){
    	Integer result =0;
    	try {
    		String table_name_r = "reward_day_"+weekTag;
    		String table_name_s = "settle_day_"+weekTag;
    		result = stMapper.deleteByDayTime(date);
    		if(result!=null&&result>0){
    			 wrdMapper.delete("delete from "+table_name_r+" where dayTime='"+date+"'");
    			wstMapper.delete("delete from "+table_name_s+" where dayTime='"+date+"'");
    			sqlSession.commit();
    		}
    		
    	}catch(Exception e){ 
    		e.printStackTrace();
    	}finally {
    		sqlSession.close();
        }
    	return result;
	}
    
    public int update(SettleDay st){
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
    
    public int init_settle(SettleDay st){
    	int result =0;
    	try {
    		SettleDay st1 = stMapper.selectByDayTime(st.getDayTime());
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
    
    public SettleDay getEndSettle(){
    	SettleDay st = new SettleDay(); 
    	try {
    		st = stMapper.selectMaxDay();
    		
    	}catch(Exception e){ 
    		st = null;
    		e.printStackTrace();
    	}finally {
    		sqlSession.close();
        }
    	return st;
	}
    
}
