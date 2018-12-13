package com.ssm.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ssm.mapper.ProductDetailMapper;
import com.ssm.pojo.ProductDetail;
import com.ssm.utils.Pager;
import com.ssm.utils.SqlSessionFactoryUtils;

public class ProductDetailDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
    ProductDetailMapper ProductDetailMapper = sqlSession.getMapper(ProductDetailMapper.class);
    ProductDetail ProductDetail= new ProductDetail();
    
    public Pager findProductDetail(ProductDetail productDetail,Pager pager){
		Map<String,Object> params = new HashMap<>();
		params.put("productDetail",productDetail);
		int recordCount = ProductDetailMapper.count(params);
		pager.setRowCount(recordCount);
		if(recordCount>0){
			params.put("pageModel", pager);
		}
		List<ProductDetail> Products = ProductDetailMapper.selectByPage(params);
		pager.setResultList(Products);
		sqlSession.close();
		return pager;
	}
    
    public List<ProductDetail> findAllProductDetail(){
		
		List<ProductDetail> Products = ProductDetailMapper.selectAllProductDetail();
	
		sqlSession.close();
		return Products;
	}
    
    public List<ProductDetail> findByProductDetail(ProductDetail productDetail){
    	List<ProductDetail> Products = null;
    	try{
    	Map<String,Object> params = new HashMap<>();
		params.put("productDetail",productDetail);
		Products = ProductDetailMapper.selectByParam(params);
		sqlSession.commit();
    	}finally{
		sqlSession.close();
    	}
		return Products;
	}
    
    
    public ProductDetail findProductById(Integer id){
		ProductDetail Product = ProductDetailMapper.selectById(id);
		sqlSession.close();
		return Product;
	}
    
    public void deleteAll(String ids){
    	
    	try {
    		String[] idArray = ids.split(",");
    		for(String id:idArray){
    			
    			ProductDetailMapper.deleteById(Integer.valueOf(id));
    		}
    		sqlSession.commit();
    	} finally {
    		sqlSession.close();
        }
	}
    
    public void updateProductDetail(ProductDetail ProductDetail){
    	try {
		 ProductDetailMapper.update(ProductDetail);
		 sqlSession.commit();

    	} finally {
    		sqlSession.close();
        }
	}
    
	public String saveProductDetail(ProductDetail ProductDetail){
    	String str = "";
    	try {
	    	if(ProductDetailMapper.selectByProductId(ProductDetail.getProductId())==null){
			 ProductDetailMapper.save(ProductDetail);
			 sqlSession.commit();
			str =ProductDetail.getProductId()+"信息保存成功。";
	    	}else{
	    		str ="该分类已经存在。";
	    	}
    	}finally {
    		sqlSession.close();
        }
    	return str;
	}
	
	public String saveAll(List<ProductDetail> plist){
    	String str = "";
    	try {
    		Map<String,Object> params = new HashMap<>();
    		params.put("list", plist);
			 ProductDetailMapper.insertAll(params);;
			 sqlSession.commit();
			str ="信息批量保存成功。";
    	}catch(Exception e){
    		sqlSession.rollback();
    	}finally {
    		sqlSession.close();
        }
    	return str;
	}
}
