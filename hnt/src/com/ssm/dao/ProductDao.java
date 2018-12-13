package com.ssm.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ssm.mapper.ProductMapper;
import com.ssm.mapper.ProductDetailMapper;
import com.ssm.pojo.Product;
import com.ssm.pojo.ProductDetail;
import com.ssm.utils.Pager;
import com.ssm.utils.SqlSessionFactoryUtils;

public class ProductDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
    ProductMapper ProductMapper = sqlSession.getMapper(ProductMapper.class);
    ProductDetailMapper ProductDetailMapper = sqlSession.getMapper(ProductDetailMapper.class);
    Product Product= new Product();
    
    public Pager findProduct(Product product,Pager pager){
		Map<String,Object> params = new HashMap<>();
		params.put("product",product);
		int recordCount = ProductMapper.count(params);
		pager.setRowCount(recordCount);
		if(recordCount>0){
			params.put("pageModel", pager);
		}
		List<Product> Products = ProductMapper.selectByPage(params);
		pager.setResultList(Products);
		sqlSession.close();
		return pager;
	}
    
    public List<Product> findAllProduct(){
		
		List<Product> Products = ProductMapper.selectAllProduct();
	
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
    
    public List<Product> findByProduct(Product product){
    	Map<String,Object> params = new HashMap<>();
		params.put("product",product);
		List<Product> Products = ProductMapper.selectByParam(params);
		sqlSession.commit();
		sqlSession.close();
		return Products;
	}
    
    public Product findProductByName(String name){
		Product Product = ProductMapper.selectByProductId(name);
		sqlSession.close();
		return Product;
	}
    
    public Product findProductById(Integer id){
		Product product =null;
		try{
			product = ProductMapper.selectById(id);
			sqlSession.commit();
		}finally{
			sqlSession.close();
		}
		return product;
	}
    
    public void deleteAll(String ids){
    	
    	try {
    		String[] idArray = ids.split(",");
    		for(String id:idArray){
    			Product pt = new Product();
    			pt.setId(Integer.valueOf(id));
    			pt.setState("0");
    			ProductMapper.update(pt);
    		}
    		sqlSession.commit();
    	} finally {
    		sqlSession.close();
        }
	}
    
    public int updateProduct(Product Product){
    	int yes = 0;
    	try {
		 Integer yes1 = ProductMapper.update(Product);
		 if(yes1!=null){
			 yes = yes1;
			 sqlSession.commit();
		 }

    	} finally {
    		sqlSession.close();
        }
    	return yes;
	}
    
	public String saveProduct(Product Product){
    	String str = "";
    	try {
	    	if(ProductMapper.selectByProductId(Product.getProductId())==null){
			 ProductMapper.save(Product);
			 sqlSession.commit();
			str =Product.getProductId()+"信息保存成功。";
	    	}else{
	    		str ="该分类已经存在。";
	    	}
    	}catch(Exception e){
    		sqlSession.rollback();
    		str ="系统繁忙，请稍后再试。";
    	}finally {
    		sqlSession.close();
        }
    	return str;
	}
	public String saveCompositeProducts(Product Product,List<ProductDetail> plist){
    	String str = "";
    	try {
    		Map<String,Object> params = new HashMap<>();
    		params.put("list", plist);
	    	if(ProductMapper.selectByProductId(Product.getProductId())==null){
	    	 ProductDetailMapper.insertAll(params);
			 ProductMapper.save(Product);
			
			 sqlSession.commit();
			str =Product.getProductId()+"信息保存成功。";
	    	}else{
	    		str ="该分类已经存在。";
	    	}
    	}catch(Exception e){
    		sqlSession.rollback();
    		str ="系统繁忙，请稍后再试。";
    	}finally {
    		sqlSession.close();
        }
    	return str;
	}
}
