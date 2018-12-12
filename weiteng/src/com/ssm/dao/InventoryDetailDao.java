package com.ssm.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ssm.mapper.InventoryDetailMapper;
import com.ssm.pojo.InventoryDetail;
import com.ssm.utils.Pager;
import com.ssm.utils.SqlSessionFactoryUtils;

public class InventoryDetailDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
	InventoryDetailMapper inventoryDetailMapper = sqlSession.getMapper(InventoryDetailMapper.class);

    public Pager findByPage(InventoryDetail InventoryDetail,Pager pager){
    	
    	try{
		Map<String,Object> params = new HashMap<>();
		params.put("inventoryDetail",InventoryDetail);
		int recordCount =inventoryDetailMapper.count(params);
		pager.setRowCount(recordCount);
		if(recordCount>0){
			params.put("pageModel", pager);
		}
		List<InventoryDetail> invs =inventoryDetailMapper.selectByPage(params);
		pager.setResultList(invs);
    	}finally{
    		sqlSession.close();
    	}
		return pager;
	}
    
public List<InventoryDetail> findByApplyId(String applyId){
	List<InventoryDetail> invs = null;
    	try{
		invs =inventoryDetailMapper.selectByApplyId(applyId);
    	}finally{
    		sqlSession.close();
    	}
		return invs;
	}
    
  
    
  
	
}
