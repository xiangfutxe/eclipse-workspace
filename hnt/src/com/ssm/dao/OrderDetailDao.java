package com.ssm.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ssm.mapper.OrderDetailMapper;
import com.ssm.pojo.OrderDetail;
import com.ssm.utils.SqlSessionFactoryUtils;

public class OrderDetailDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
    OrderDetailMapper OrderDetailMapper = sqlSession.getMapper(OrderDetailMapper.class);
    OrderDetail OrderDetail= new OrderDetail();
    
    public List<OrderDetail> findByOrderId(String orderId){
    	List<OrderDetail> olist = null;
		try{
		 olist = OrderDetailMapper.selectByOrderId(orderId);
		sqlSession.commit();
		}finally{
		sqlSession.close();
		}
		return olist;
	}
    
  
    
  
    
    public OrderDetail findById(Integer id){
    	
    OrderDetail od = null;
    	try{
		od = OrderDetailMapper.selectById(id);
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
    			OrderDetailMapper.deleteById(Integer.valueOf(id));
    		}
    		sqlSession.commit();
    	} finally {
    		sqlSession.close();
        }
	}
    
    public void deleteByOrderId(String orderId){
    	try {
    			OrderDetailMapper.deleteByOrderId(orderId);
    		
    		sqlSession.commit();
    	} finally {
    		sqlSession.close();
        }
	}
    
    public int Update(OrderDetail OrderDetail){
    	int num=0;
    	try {
    		Integer num1=	OrderDetailMapper.Update(OrderDetail);
    		if(num1!=null){
    			num =num1;
    			sqlSession.commit();
    		}
    		
    	} finally {
    		sqlSession.close();
        }
    	return num;
	}
    
    
	public String saveAll(List<OrderDetail> plist){
    	String str = "";
    	try {
    		Map<String,Object> params = new HashMap<>();
    		params.put("list", plist);
			 OrderDetailMapper.insertAll(params);;
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
