package com.ssm.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ssm.mapper.ModuleMapper;
import com.ssm.pojo.Module;
import com.ssm.pojo.News;
import com.ssm.utils.Pager;
import com.ssm.utils.SqlSessionFactoryUtils;

public class ModuleDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
    ModuleMapper moduleMapper = sqlSession.getMapper(ModuleMapper.class);
   
    public Pager findByPage(Module module,Pager pager){
    	try{
			Map<String,Object> params = new HashMap<>();
			params.put("module",module);
			int recordCount = moduleMapper.count(params);
			pager.setRowCount(recordCount);
			if(recordCount>0){
				params.put("pageModel", pager);
			}
			List<Module> results = moduleMapper.selectByPage(params);
			sqlSession.commit();
			pager.setResultList(results);
    	} finally {
			sqlSession.close();
    	}
    	return pager;
	}
    
    public int save(Module module){
    	int result =0;
    	try {
    		result = moduleMapper.save(module);
			 sqlSession.commit();
    	}catch(Exception e){
    		result = 2;
    		e.printStackTrace();
    	}finally {
    		sqlSession.close();
        }
    	return result;
	}
    
    public int update(Module module){
    	int result =0;
    	try {
    		result = moduleMapper.update(module);
			 sqlSession.commit();
    	}catch(Exception e){ 
    		result = 2;
    		e.printStackTrace();
    	}finally {
    		sqlSession.close();
        }
    	return result;
	}
    
 public int delete(Integer id){
	 int result =0;
    	try {
    		
    		result = moduleMapper.deleteById(id);
    		
    		sqlSession.commit();
    	}catch(Exception e){ 
    		result = 2;
    		e.printStackTrace();
    	} finally {
    		sqlSession.close();
        }
    	return result;
	}
 
 	
 
 	public Module findById(Integer id){
 		Module module = null;
 		try {
 			module = moduleMapper.selectById(id);
		 sqlSession.commit();
 		}catch(Exception e){ 
    		e.printStackTrace();
    	} finally {
    		sqlSession.close();
        }
 		return module;
		
	}
 	
 	public Module findByCode(Integer code){
 		Module module = null;
 		try {
 			module = moduleMapper.selectByCode(code);
		 sqlSession.commit();
 		}catch(Exception e){ 
    		e.printStackTrace();
    	} finally {
    		sqlSession.close();
        }
 		return module;
		
	}
 	
 	
}
