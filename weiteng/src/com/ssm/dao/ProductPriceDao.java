package com.ssm.dao;


import org.apache.ibatis.session.SqlSession;

import com.ssm.mapper.ProductMapper;
import com.ssm.mapper.ProductPriceMapper;
import com.ssm.mapper.ProductDetailMapper;
import com.ssm.pojo.Product;
import com.ssm.pojo.ProductPrice;
import com.ssm.utils.SqlSessionFactoryUtils;

public class ProductPriceDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
    ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
    ProductPriceMapper productPriceMapper = sqlSession.getMapper(ProductPriceMapper.class);

    ProductDetailMapper ProductDetailMapper = sqlSession.getMapper(ProductDetailMapper.class);
    Product Product= new Product();
   
  
    
    public ProductPrice findAllById(Integer id){
    	ProductPrice product =null;
		try{
			product = productPriceMapper.selectAllById(id);
			sqlSession.commit();
		}finally{
			sqlSession.close();
		}
		return product;
	}
    
    public ProductPrice findByPid(Integer id){
    	ProductPrice product =null;
		try{
			product = productPriceMapper.selectByPid(id);
			sqlSession.commit();
		}finally{
			sqlSession.close();
		}
		return product;
	}
    
    public ProductPrice findAllByPid(Integer id){
    	ProductPrice product =null;
		try{
			product = productPriceMapper.selectAllByPid(id);
			sqlSession.commit();
		}finally{
			sqlSession.close();
		}
		return product;
	}
    
    public ProductPrice findAllByProductId(String productId){
    	ProductPrice product =null;
		try{
			product = productPriceMapper.selectAllByProductId(productId);
			sqlSession.commit();
		}finally{
			sqlSession.close();
		}
		return product;
	}
    
    public ProductPrice findProductId(String productId){
    	ProductPrice product =null;
		try{
			product = productPriceMapper.selectByProductId(productId);
			sqlSession.commit();
		}finally{
			sqlSession.close();
		}
		return product;
	}
    
	public int save(ProductPrice product){
    	int result = 0;
    	try {
	    	if(productPriceMapper.selectByProductId(product.getProductId())==null){
			 productPriceMapper.save(product);
			 sqlSession.commit();
	    	}else{
	    		result=1;
	    	}
    	}catch(Exception e){
    		sqlSession.rollback();
    		result=2;
    	}finally {
    		sqlSession.close();
        }
    	return result;
	}
	
	public int update(ProductPrice product){
    	int result = 0;
    	try {
			productPriceMapper.update(product);
			 sqlSession.commit();
    	}catch(Exception e){
    		e.printStackTrace();
    		sqlSession.rollback();
    		result=2;
    	}finally {
    		sqlSession.close();
        }
    	return result;
	}
	
	public int save(Integer pid){
    	int result = 0;
    	try {
	    	if(productPriceMapper.selectByPid(pid)==null){
	    		Product pro = productMapper.selectById(pid);
	    		ProductPrice pp = new ProductPrice();
	    		pp.setPid(pid);
	    		pp.setProductId(pro.getProductId());
				 productPriceMapper.save(pp);
				 sqlSession.commit();
	    	}else{
	    		result=1;
	    	}
    	}catch(Exception e){
    		sqlSession.rollback();
    		result=2;
    	}finally {
    		sqlSession.close();
        }
    	return result;
	}
	
}
