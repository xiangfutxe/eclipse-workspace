package com.ssm.dao;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ssm.mapper.ProductMapper;
import com.ssm.mapper.ProductPriceMapper;
import com.ssm.mapper.ProductStockMapper;
import com.ssm.mapper.ProductStockTransfersMapper;
import com.ssm.mapper.UserMapper;
import com.ssm.mapper.ProductDetailMapper;
import com.ssm.pojo.Product;
import com.ssm.pojo.ProductPrice;
import com.ssm.pojo.ProductStock;
import com.ssm.pojo.ProductStockTransfers;
import com.ssm.pojo.User;
import com.ssm.utils.Pager;
import com.ssm.utils.SqlSessionFactoryUtils;

public class ProductStockTransfersDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
    ProductMapper ProductMapper = sqlSession.getMapper(ProductMapper.class);
    ProductPriceMapper productPriceMapper = sqlSession.getMapper(ProductPriceMapper.class);
    ProductStockMapper stockMapper = sqlSession.getMapper(ProductStockMapper.class);
    ProductStockTransfersMapper transfersMapper = sqlSession.getMapper(ProductStockTransfersMapper.class);
    ProductDetailMapper ProductDetailMapper = sqlSession.getMapper(ProductDetailMapper.class);
    Product Product= new Product();
    
    public Pager findByPage(ProductStockTransfers transfers,Pager pager){
		Map<String,Object> params = new HashMap<>();
		params.put("transfers",transfers);
		int recordCount = transfersMapper.count(params);
		pager.setRowCount(recordCount);
		if(recordCount>0){
			params.put("pageModel", pager);
		}
		List<ProductStockTransfers> list = transfersMapper.selectByPage(params);
		pager.setResultList(list);
		sqlSession.close();
		return pager;
	}
    
    public List<ProductStockTransfers> findAllProduct(String userId){
		
		List<ProductStockTransfers> Products = transfersMapper.selectAllProduct(userId);
	
		sqlSession.close();
		return Products;
	}
    
 public int getMaxId(){
		int id = 0;
		Integer max = ProductMapper.maxId();
		if(max!=null) id=max;
		sqlSession.close();
		return id;
	}
    
    public List<ProductStockTransfers> findByProduct(ProductStockTransfers transfers){
    	Map<String,Object> params = new HashMap<>();
		params.put("transfers",transfers);
		List<ProductStockTransfers> list = transfersMapper.selectByParam(params);
		sqlSession.commit();
		sqlSession.close();
		return list;
	}
    
    public ProductStock findProductByName(String name,String userId){
    	ProductStock product = stockMapper.selectByProductId(name,userId);
		sqlSession.close();
		return product;
	}
    
    public ProductStock findProductById(Integer id){
    	ProductStock product =null;
		try{
			product = stockMapper.selectById(id);
			sqlSession.commit();
		}finally{
			sqlSession.close();
		}
		return product;
	}
    
    public void deleteAll(String[] ids){
    	
    	try {
    		for(String id:ids){
    			stockMapper.deleteById(Integer.valueOf(id));
    		}
    		sqlSession.commit();
    	} finally {
    		sqlSession.close();
        }
	}
    
    public int updateProduct(ProductStock Product){
    	int yes = 0;
    	try {
		 Integer yes1 = stockMapper.update(Product);
		 if(yes1!=null){
			 yes = yes1;
			 sqlSession.commit();
		 }

    	} finally {
    		sqlSession.close();
        }
    	return yes;
	}
    
	public String save(int id, ProductStockTransfers transfers,Timestamp date){
    	String msg = "";
    	try {
    		ProductStock stock = stockMapper.selectByIdForUpdate(id);
	    	if(stock!=null){
	    		User user = userMapper.selectUsersByUserId(transfers.getUserId1());
	    		if(user!=null) {
		    		transfers.setUid(stock.getUid());
		    		transfers.setUserId(stock.getUserId());
		    		transfers.setUserName(stock.getUserName());
		    		transfers.setUid1(user.getId());
		    		transfers.setUserId1(user.getUserId());
		    		transfers.setUserName1(user.getUserName());
		    		transfers.setPid(stock.getPid());
		    		transfers.setProductId(stock.getProductId());
		    		transfers.setProductName(stock.getProductName());
		    		transfers.setSpecification(stock.getSpecification());
		    		if(stock.getNum()-transfers.getNum()>=0) {
		    			Integer up = transfersMapper.save(transfers);
		    			if(up!=null&&up>0) {
		    			Integer up1 = stockMapper.updateSubNum(stock.getProductId(), stock.getUserId(), transfers.getNum());
		    			if(up1!=null&&up1>0) {
				    		ProductStock stock1 = stockMapper.selectByProductId(stock.getProductId(), transfers.getUserId1());
		    				if(stock1!=null) {
				    			Integer up2 = stockMapper.updateNum(stock1.getProductId(), stock1.getUserId(), transfers.getNum());
				    			if(up2!=null&&up2>0) {
				    				msg = "yes";
				    			}else {
				    				msg ="转入会员云仓信息更新失败。";
				    			}
				    		}else {
				    			stock1 = new ProductStock();
				    			stock1.setPid(stock.getPid());
				    			stock1.setProductId(stock.getProductId());
				    			stock1.setProductName(stock.getProductName());
				    			stock1.setSpecification(stock.getSpecification());
				    			stock1.setUid(user.getId());
				    			stock1.setUserId(user.getUserId());
				    			stock1.setUserName(user.getUserName());
				    			stock1.setNum(transfers.getNum());
				    			stock1.setTotalNum(transfers.getNum());
				    			stock1.setEntryTime(date);
				    			stock1.setEndTime(date);
				    			Integer up2 = stockMapper.save(stock1);
				    			if(up2!=null&&up2>0) {
				    				msg = "yes";
				    			}else {
				    				msg="转入会员云仓信息保存失败。";
				    			}
				    		}
		    				
		    			}else {
		    				msg="转出会员云仓信息更新失败。";
		    			}
		    			}else {
		    				msg="调拨记录保存失败。";
		    			}
		    		}else {
	    				msg="转出会员云仓库存不足。";
	    			}
		    		
	    		}else {
    				msg="转入会员信息获取失败。";
    			}
	    	}else {
				msg="云仓信息获取失败。";
			}
				if(msg.equals("yes"))
		    	sqlSession.commit();
				else sqlSession.rollback();
    	}catch(Exception e){
    		e.printStackTrace();
    		sqlSession.rollback();
    		msg="系统繁忙，请稍后再试！";
    	}finally {
    		sqlSession.close();
        }
    	return msg;
	}
	
}
