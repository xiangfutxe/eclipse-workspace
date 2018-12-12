package com.ssm.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ssm.mapper.InventoryMapper;
import com.ssm.pojo.Inventory;
import com.ssm.utils.Pager;
import com.ssm.utils.SqlSessionFactoryUtils;

public class InventoryDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
	InventoryMapper inventoryMapper = sqlSession.getMapper(InventoryMapper.class);
    
    public Pager findInventoryByPage(Inventory inventory,Pager pager){
		Map<String,Object> params = new HashMap<>();
		params.put("inventory",inventory);
		int recordCount =inventoryMapper.count(params);
		pager.setRowCount(recordCount);
		if(recordCount>0){
			params.put("pageModel", pager);
		}
		List<Inventory> invs =inventoryMapper.selectByPage(params);
		pager.setResultList(invs);
		sqlSession.close();
		return pager;
	}
    
    public List<Inventory> findAll(){
    	List<Inventory> invs = null;
		try{
		  invs =inventoryMapper.selectAllInventory();
		}finally {
    		sqlSession.close();
        }
		sqlSession.close();
		return invs;
	}
    
    public Inventory findById(Integer id){
    	Inventory inventory = inventoryMapper.selectById(id);
  		sqlSession.close();
  		return inventory;
  	}
    
    public Inventory findByName(String name){
    	Inventory inventory = inventoryMapper.selectByName(name);
  		sqlSession.close();
  		return inventory;
  	}
    
 public void deleteAll(String[] ids){
    	
    	try {
    		for(String id:ids){
    			inventoryMapper.deleteById(Integer.valueOf(id));
    		}
    		sqlSession.commit();
    	} finally {
    		sqlSession.close();
        }
	}
    
    public int saveInventory(Inventory inventory){
    	int result= 0;
    	try {
	    	if(inventoryMapper.selectByName(inventory.getName())==null){
	    		result = inventoryMapper.save(inventory);
			 sqlSession.commit();
	    	}else{
	    		result=2;
	    	}
    	}catch(Exception e){
    		result=3;
    		sqlSession.rollback();
    	}finally {
    		sqlSession.close();
        }
    	return result;
	}
    public int  updateInventory(Inventory inventory){
    	int result=0;
    	try {
    		result = inventoryMapper.update(inventory);
    		sqlSession.commit();
    	}catch(Exception e){
    		result = 2;
    		sqlSession.rollback();
    		e.printStackTrace();
    	} finally {
    		sqlSession.close();
        }
    	return result;
	}
	
}
