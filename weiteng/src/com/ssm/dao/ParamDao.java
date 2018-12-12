package com.ssm.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ssm.mapper.ParamMapper;
import com.ssm.pojo.Param;
import com.ssm.utils.Pager;
import com.ssm.utils.SqlSessionFactoryUtils;

public class ParamDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
    ParamMapper ParamMapper = sqlSession.getMapper(ParamMapper.class);
    Param Param= new Param();
    
    public Pager findParam(Param Param,Pager pager){
		Map<String,Object> params = new HashMap<>();
		params.put("param",Param);
		int recordCount = ParamMapper.count(params);
		pager.setRowCount(recordCount);
		if(recordCount>0){
			params.put("pageModel", pager);
		}
		List<Param> Params = ParamMapper.selectByPage(params);
		pager.setResultList(Params);
		sqlSession.close();
		return pager;
	}
    
    public List<Param> findAllParam(){
		
		List<Param> Params = ParamMapper.selectAllParam();
	
		sqlSession.close();
		return Params;
	}
    
    public Param findParamByName(String name){
		Param Param = ParamMapper.selectByName(name);
		sqlSession.close();
		return Param;
	}
    
    public Param findParamById(Integer id){
		Param Param = ParamMapper.selectById(id);
		sqlSession.close();
		return Param;
	}
    
    public void deleteAll(String[] ids){
    	
    	try {
    		for(String id:ids){
    			ParamMapper.deleteById(Integer.valueOf(id));
    		}
    		sqlSession.commit();
    	} finally {
    		sqlSession.close();
        }
	}
    
    public void updateParam(Param Param){
    	try {
		 ParamMapper.update(Param);
		 sqlSession.commit();

    	} finally {
    		sqlSession.close();
        }
	}
    
	public String saveParam(Param Param){
    	String str = "";
    	try {
	    	if(ParamMapper.selectByName(Param.getParamName())==null){
			 ParamMapper.save(Param);
			 sqlSession.commit();
			str =Param.getParamName()+"信息保存成功。";
	    	}else{
	    		str ="该部门已经存在。";
	    	}
    	}finally {
    		sqlSession.close();
        }
    	return str;
	}
}
