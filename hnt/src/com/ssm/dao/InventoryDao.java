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
    
 public void deleteAll(String ids){
    	
    	try {
    		String[] idArray = ids.split(",");
    		for(String id:idArray){
    			inventoryMapper.deleteById(Integer.valueOf(id));
    		}
    		sqlSession.commit();
    	} finally {
    		sqlSession.close();
        }
	}
    
    public String saveInventory(Inventory inventory){
    	String str = "";
    	try {
	    	if(inventoryMapper.selectByName(inventory.getInventoryName())==null){
	    		inventoryMapper.save(inventory);
			 sqlSession.commit();
			str =inventory.getInventoryName()+"信息保存成功。";
	    	}else{
	    		str ="该仓库已经存在。";
	    	}
    	}catch(Exception e){
    		str ="系统繁忙中，请稍后访问。";
    		sqlSession.rollback();
    	}finally {
    		sqlSession.close();
        }
    	return str;
	}
    public String updateInventory(Inventory inventory){
    	String str = "";
    	try {
    		inventoryMapper.update(inventory);
    		sqlSession.commit();
    		str ="仓库修改成功。";
    	}catch(Exception e){
    		str ="系统繁忙中，请稍后访问。";
    		sqlSession.rollback();
    	} finally {
    		sqlSession.close();
        }
    	return str;
	}
	
}
