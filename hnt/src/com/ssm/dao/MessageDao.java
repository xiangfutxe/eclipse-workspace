package com.ssm.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ssm.mapper.MessageMapper;
import com.ssm.pojo.Message;
import com.ssm.utils.Pager;
import com.ssm.utils.SqlSessionFactoryUtils;

public class MessageDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
    MessageMapper MessageMapper = sqlSession.getMapper(MessageMapper.class);
   
    public Pager findByPage(Message msg,Pager pager){
    	try{
			Map<String,Object> params = new HashMap<>();
			params.put("msg",msg);
			int recordCount = MessageMapper.count(params);
			pager.setRowCount(recordCount);
			if(recordCount>0){
				params.put("pageModel", pager);
			}
			List<Message> Messagelist = MessageMapper.selectByPage(params);
			sqlSession.commit();
			pager.setResultList(Messagelist);
    	} finally {
			sqlSession.close();
    	}
    	return pager;
	}
    
    public String saveMessage(Message msg){
    	String str = "";
    	try {
	    	MessageMapper.save(msg);
			 sqlSession.commit();
			str ="留言保存成功。";
    	}finally {
    		sqlSession.close();
        }
    	return str;
	}
    
    public String updateMessage(Message msg){
    	String str = "";
    	try {
	    	Integer up1 =MessageMapper.update(msg);
	    	if(up1!=null&&up1>0){
				 sqlSession.commit();
				 str ="留言修改成功。";
	    	}else{
	    		str ="留言回复更新失败。";
	    	}
    	}catch(Exception e){ 
    		str ="数据异常，请重试。";
    		e.printStackTrace();
    	}finally {
    		sqlSession.close();
        }
    	return str;
	}
    
 public void deleteAll(String ids){
    	
    	try {
    		String[] idArray = ids.split(",");
    		for(String id:idArray){
    			MessageMapper.deleteById(Integer.valueOf(id));
    		}
    		sqlSession.commit();
    	}catch(Exception e){ 
    		e.printStackTrace();
    	} finally {
    		sqlSession.close();
        }
	}
 
 	
 
 	public Message findById(Integer id){
 		Message Message = null;
 		try {
 			Message = MessageMapper.selectById(id);
 			sqlSession.commit();
 		}catch(Exception e){ 
    		e.printStackTrace();
    	} finally {
    		sqlSession.close();
        }
 		return Message;
		
	}
 	
 	
}
