package com.ssm.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.ssm.mapper.PromotionMapper;
import com.ssm.pojo.Promotion;
import com.ssm.utils.SqlSessionFactoryUtils;

public class PromotionDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
    PromotionMapper proMapper = sqlSession.getMapper(PromotionMapper.class);
    public String savePro(Promotion adr){
    	String str = "";
    	try {
    		proMapper.save(adr);
			 sqlSession.commit();
			str ="促销信息保存成功。";
	    	
    	}finally {
    		sqlSession.close();
        }
    	return str;
	}
    
    public String updatePro(Promotion adr){
    	String str = "";
    	try {
    		proMapper.update(adr);
			 sqlSession.commit();
			 str ="促销信息修改成功。";
    	}catch(Exception e){ 
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
    			proMapper.deleteById(Integer.valueOf(id));
    		}
    		sqlSession.commit();
    	}catch(Exception e){ 
    		e.printStackTrace();
    	} finally {
    		sqlSession.close();
        }
	}
 
 public int delete(Integer id){
 	int num=0;
 	try {
 		Integer num1 = proMapper.deleteById(Integer.valueOf(id));
 		if(num1!=null&&num1>0){
 			num=num1;
	 		sqlSession.commit();
 		}else{
 			sqlSession.rollback();
 		}
 	}catch(Exception e){ 
 		e.printStackTrace();
 	} finally {
 		sqlSession.close();
     }
 	return num;
}
 
 	
 
 	public Promotion findById(Integer id){
 		Promotion adr = null;
 		try {
		 adr = proMapper.selectById(id);
		 sqlSession.commit();
 		}catch(Exception e){ 
    		e.printStackTrace();
    	} finally {
    		sqlSession.close();
        }
 		return adr;
		
	}
 	
	public Promotion findByName(String name){
 		Promotion adr = null;
 		try {
		 adr = proMapper.selectByName(name);
		 sqlSession.commit();
 		}catch(Exception e){ 
    		e.printStackTrace();
    	} finally {
    		sqlSession.close();
        }
 		return adr;
		
	}
 	
	public List<Promotion> findByAll(){
 		List<Promotion> adr = null;
 		try {
		 adr = proMapper.selectAllPromotion();
		 sqlSession.commit();
 		}catch(Exception e){ 
    		e.printStackTrace();
    	} finally {
    		sqlSession.close();
        }
 		return adr;
		
	}
}
