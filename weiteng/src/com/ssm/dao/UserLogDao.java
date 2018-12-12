package com.ssm.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ssm.mapper.UserLogMapper;
import com.ssm.pojo.Admin;
import com.ssm.pojo.UserLog;
import com.ssm.utils.Pager;
import com.ssm.utils.SqlSessionFactoryUtils;

public class UserLogDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
    UserLogMapper userLogMapper = sqlSession.getMapper(UserLogMapper.class);
    Admin admin= new Admin();
    
   
    
    public Pager findByPage(UserLog userLog,Pager pager){
		Map<String,Object> params = new HashMap<>();
		params.put("userLog",userLog);
		int recordCount = userLogMapper.count(params);
		pager.setRowCount(recordCount);
		if(recordCount>0){
			params.put("pageModel", pager);
		}
		List<UserLog> admins = userLogMapper.selectByPage(params);
		pager.setResultList(admins);
		sqlSession.close();
		return pager;
	}
    
    public List<UserLog> findByList(UserLog userLog){
		Map<String,Object> params = new HashMap<>();
		params.put("userLog",userLog);
		List<UserLog> admins = userLogMapper.selectByList(params);
		sqlSession.close();
		return admins;
	}
    
    public int saveUserLog(UserLog userLog){
    	int num = 0;
    	try {
	    	Integer update = userLogMapper.save(userLog);
	    	if(update!=null&&update>0){
	    		num = update;
	    		sqlSession.commit();
	    	}
    	}catch(Exception e){
    		sqlSession.rollback();
    		e.printStackTrace();
    	}finally {
    		sqlSession.close();
        }
    	return num;
	}
    
   
}
