package com.ssm.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ssm.mapper.OrderDetailMapper;
import com.ssm.mapper.OrderDetailProductMapper;
import com.ssm.mapper.OrderMapper;
import com.ssm.mapper.ProductMapper;
import com.ssm.mapper.ProductDetailMapper;
import com.ssm.pojo.Order;
import com.ssm.pojo.OrderDetail;
import com.ssm.pojo.OrderDetailProduct;
import com.ssm.pojo.ProductDetail;
import com.ssm.utils.ArithUtil;
import com.ssm.utils.SqlSessionFactoryUtils;

public class OrderDetailProductDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
    OrderDetailProductMapper OrderDetailProductMapper = sqlSession.getMapper(OrderDetailProductMapper.class);
    OrderDetailMapper OrderDetailMapper = sqlSession.getMapper(OrderDetailMapper.class);
    OrderMapper OrderMapper = sqlSession.getMapper(OrderMapper.class);
    ProductMapper ProductMapper = sqlSession.getMapper(ProductMapper.class);
    ProductDetailMapper ProductDetailMapper = sqlSession.getMapper(ProductDetailMapper.class);
    
    OrderDetail OrderDetail= new OrderDetail();
    
    public String checkOrderDetailProuct(){
    	String msg ="yes";
    	int error =0;
		try{
		 List<Order> olist = OrderMapper.selectByTotal();
		 if(olist!=null){
			 if(olist.size()>0){
				 for(int i=0;i<olist.size();i++){
					 int total_num = 0;
					 List<OrderDetailProduct> oplist = new ArrayList<OrderDetailProduct>();
					 List<OrderDetail> odlist = OrderDetailMapper.selectByOrderId(olist.get(i).getOrderId());
				 	for(int j=0;j<odlist.size();j++){
				 		if(odlist.get(j).getType()==1){
				 			OrderDetailProduct od = new OrderDetailProduct();
			 				od.setOrderId(olist.get(i).getOrderId());
			 				od.setOdId(odlist.get(j).getId());
			 				od.setPid(odlist.get(j).getPid());
			 				od.setProductId(odlist.get(j).getProductId());
			 				od.setProductName(odlist.get(j).getProductName());
			 				od.setSpecification(odlist.get(j).getSpecification());
			 				od.setProductType(odlist.get(j).getProductType());
			 				od.setProductPrice(odlist.get(j).getProductPrice());
			 				od.setProductPv(odlist.get(j).getProductPv());
			 				od.setPrice(odlist.get(j).getPrice());
			 				od.setPv(odlist.get(j).getPv());
			 				od.setNum(odlist.get(j).getNum());
			 				od.setType(1);
			 				od.setDeliveryNum(0);
				 			oplist.add(od);
				 			total_num = total_num+od.getNum();
				 		}
				 	}
				 		for(int j=0;j<odlist.size();j++){
				 			if(odlist.get(j).getType()==2){
				 			int num1 = odlist.get(j).getNum();
				 			 List<ProductDetail> pdlist = ProductDetailMapper.selectByProductId(odlist.get(j).getProductId());
				 			for(int t=0;t<pdlist.size();t++){
				 				OrderDetailProduct od = new OrderDetailProduct();
				 				od.setOrderId(olist.get(i).getOrderId());
				 				od.setOdId(odlist.get(j).getId());
				 				od.setPid(pdlist.get(t).getP_id());
				 				od.setProductId(pdlist.get(t).getProductId());
				 				od.setProductName(pdlist.get(t).getProductName());
				 				od.setSpecification(pdlist.get(t).getSpecification());
				 				od.setProductType(pdlist.get(t).getProductType());
				 				od.setProductPrice(pdlist.get(t).getProductPrice());
				 				od.setProductPv(pdlist.get(t).getProductPv());
				 				od.setPrice(ArithUtil.mul(pdlist.get(t).getPrice(),num1));
				 				od.setPv(ArithUtil.mul(pdlist.get(t).getPv(),num1));
				 				od.setType(1);
				 				od.setDeliveryNum(0);
				 				od.setNum(pdlist.get(t).getNum()*num1);
				 				total_num = total_num+od.getNum();
				 				boolean b = true;
				 				for(int p=0;p<oplist.size();p++){
				 					if(oplist.get(p).getProductId().equals(od.getProductId())){
				 						oplist.get(p).setNum(oplist.get(p).getNum()+od.getNum());
				 						oplist.get(p).setPrice(ArithUtil.add(oplist.get(p).getPrice(),od.getPrice()));
				 						oplist.get(p).setPv(ArithUtil.add(oplist.get(p).getPv(),od.getPv()));
				 						b=false;
				 					}
				 					if(!b) break;
				 				}
				 				if(b){
				 					oplist.add(od);
				 				}
				 			}
				 		}//end type=2;
				 		
				 	}
				 	if(OrderMapper.updateForTotalNum(total_num, olist.get(i).getId())>0){
				 	Map<String,Object> params = new HashMap<>();
				 	params.put("list",oplist);
				 		if(OrderDetailProductMapper.insertAll(params)>0){
				 			msg="yes";
				 		}else{
				 			msg="订单详情插入有误。";
				 			error++;
				 		}
				 	}else{
				 		msg="订单总数保存失败。";
				 		error++;
			 		}
				 }
			 }
		 }
		 if(error==0)
			 sqlSession.commit();
		 else sqlSession.rollback();
		}catch(Exception e){
			msg ="数据操作有误。";
			sqlSession.rollback();
		}finally{
			sqlSession.close();
		}
		return msg;
	}
    
  
    public List<OrderDetailProduct> findByOrderId(String orderId){
    	
    	List<OrderDetailProduct> od = null;
        	try{
    		od = OrderDetailProductMapper.selectByOrderId(orderId);
    		sqlSession.commit();
    		}finally{
    		sqlSession.close();
    		}
    		return od;
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
    
    public int Update(OrderDetailProduct OrderDetailProduct){
    	int num=0;
    	try {
    		Integer num1=	OrderDetailProductMapper.update(OrderDetailProduct);
    		if(num1!=null){
    			num =num1;
    			sqlSession.commit();
    		}
    		
    	} finally {
    		sqlSession.close();
        }
    	return num;
	}
    
    public void deleteByOrderId(String orderId){
    	try {
    			OrderDetailMapper.deleteByOrderId(orderId);
    		
    		sqlSession.commit();
    	} finally {
    		sqlSession.close();
        }
	}
    
	public String saveAll(List<OrderDetailProduct> plist){
    	String str = "";
    	try {
    		Map<String,Object> params = new HashMap<>();
    		params.put("list", plist);
			 OrderDetailProductMapper.insertAll(params);;
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
