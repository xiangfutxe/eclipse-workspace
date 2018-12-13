package com.ssm.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ssm.mapper.AdminLogMapper;
import com.ssm.pojo.Admin;
import com.ssm.pojo.AdminLog;
import com.ssm.utils.Pager;
import com.ssm.utils.SqlSessionFactoryUtils;

public class AdminLogDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
    AdminLogMapper adminLogMapper = sqlSession.getMapper(AdminLogMapper.class);
    Admin admin= new Admin();
    
   
    
    public Pager findByPage(AdminLog adminLog,Pager pager){
		Map<String,Object> params = new HashMap<>();
		params.put("adminLog",adminLog);
		int recordCount = adminLogMapper.count(params);
		pager.setRowCount(recordCount);
		if(recordCount>0){
			params.put("pageModel", pager);
		}
		List<AdminLog> admins = adminLogMapper.selectByPage(params);
		pager.setResultList(admins);
		sqlSession.close();
		return pager;
	}
    
    public int saveAdminLog(AdminLog adminLog){
    	int num = 0;
    	try {
	    	num = adminLogMapper.save(adminLog);
			 sqlSession.commit();
    	}finally {
    		sqlSession.close();
        }
    	return num;
	}
    
    public List<AdminLog> findByList(AdminLog adminLog){
    	List<AdminLog> result = new ArrayList<AdminLog>();
    	try {
			Map<String,Object> params = new HashMap<>();
			params.put("adminLog",adminLog);
			result = adminLogMapper.selectByPage(params);
    	}finally {
    		sqlSession.close();
        }
		return result;
	}
    
    public String del(AdminLog adminLog){
    	String msg ="";
    	String str = "";
    	try {
			if(adminLog!=null){
				if(adminLog.getAdminName()!=null||!adminLog.getAdminName().equals("")){
					if(str.equals("")){
						str = "where adminName like '%"+adminLog.getAdminName()+"%'";
					}else{
						str = " and adminName like '%"+adminLog.getAdminName()+"%'";

					}
				}
				
				if(adminLog.getType()!=null||!adminLog.getType().equals("")){
					if(str.equals("")){
						str = "where type like '%"+adminLog.getType()+"%'";
					}else{
						str = " and type like '%"+adminLog.getType()+"%'";

					}
				}
				
				if(adminLog.getStartTime()!=null){
					if(str.equals("")){
						str = "where entryTime >= '"+adminLog.getStartTime()+"'";
					}else{
						str = " and entryTime  >=  '"+adminLog.getStartTime()+"'";

					}
				}
				
				if(adminLog.getEndTime()!=null){
					if(str.equals("")){
						str = "where entryTime >= '"+adminLog.getEndTime()+"'";
					}else{
						str = " and entryTime  >=  '"+adminLog.getEndTime()+"'";

					}
				}
			}
			String sql = "delete from admin_log";
			Integer up = adminLogMapper.del(sql);
			if(up!=null){
				msg="数据批量删除操作成功.";
				sqlSession.commit();
			}else{
				msg="数据批量删除操作失败.";
			}
    	}catch(Exception e){
    		e.printStackTrace();
    		msg="数据操作异常，请重试"; 
    	}finally {
    		sqlSession.close();
        }
		return msg;
	}
    
   
}
