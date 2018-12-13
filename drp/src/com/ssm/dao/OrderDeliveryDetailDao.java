package com.ssm.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ssm.mapper.OrderDeliveryDetailMapper;
import com.ssm.pojo.OrderDeliveryDetail;
import com.ssm.pojo.OrderDetail;
import com.ssm.utils.SqlSessionFactoryUtils;

public class OrderDeliveryDetailDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
	OrderDeliveryDetailMapper OrderDeliveryDetailMapper = sqlSession.getMapper(OrderDeliveryDetailMapper.class);
    
    public List<OrderDeliveryDetail> findByOrderId(String orderId){
    	List<OrderDeliveryDetail> olist = null;
		try{
		 olist = OrderDeliveryDetailMapper.selectByOrderId(orderId);
		sqlSession.commit();
		}finally{
		sqlSession.close();
		}
		return olist;
	}
    
    
    public OrderDeliveryDetail findById(Integer id){
    	
    	OrderDeliveryDetail od = null;
    	try{
		od = OrderDeliveryDetailMapper.selectById(id);
		sqlSession.commit();
		}finally{
		sqlSession.close();
		}
		return od;
	}
    
    public void deleteAll(String ids){
    	try {
    		String[] idArray = ids.split(",");
    		for(String id:idArray){
    			OrderDeliveryDetailMapper.deleteById(Integer.valueOf(id));
    		}
    		sqlSession.commit();
    	} finally {
    		sqlSession.close();
        }
	}
    
    public void delete(Integer id){
    	try {
    		
    			OrderDeliveryDetailMapper.deleteById(Integer.valueOf(id));
    	
    		sqlSession.commit();
    	} finally {
    		sqlSession.close();
        }
	}
    
    public void deleteByOrderId(String orderId){
    	try {
    		OrderDeliveryDetailMapper.deleteByOrderId(orderId);
    		
    		sqlSession.commit();
    	} finally {
    		sqlSession.close();
        }
	}
    
    public int Update(OrderDeliveryDetail OrderDeliveryDetail){
    	int num=0;
    	try {
    		Integer num1=	OrderDeliveryDetailMapper.update(OrderDeliveryDetail);
    		if(num1!=null){
    			num =num1;
    			sqlSession.commit();
    		}
    		
    	} finally {
    		sqlSession.close();
        }
    	return num;
	}
    
	public String saveAll(List<OrderDeliveryDetail> plist){
    	String str = "";
    	try {
    		Map<String,Object> params = new HashMap<>();
    		params.put("list", plist);
    		OrderDeliveryDetailMapper.insertAll(params);;
			 sqlSession.commit();
			str ="订单详情信息批量保存成功。";
    	}catch(Exception e){
    		sqlSession.rollback();
    	}finally {
    		sqlSession.close();
        }
    	return str;
	}
}
