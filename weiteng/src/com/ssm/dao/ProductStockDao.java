package com.ssm.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ssm.mapper.ProductMapper;
import com.ssm.mapper.ProductPriceMapper;
import com.ssm.mapper.ProductStockMapper;
import com.ssm.mapper.ProductDetailMapper;
import com.ssm.pojo.Product;
import com.ssm.pojo.ProductPrice;
import com.ssm.pojo.ProductStock;
import com.ssm.utils.Pager;
import com.ssm.utils.SqlSessionFactoryUtils;

public class ProductStockDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
    ProductMapper ProductMapper = sqlSession.getMapper(ProductMapper.class);
    ProductPriceMapper productPriceMapper = sqlSession.getMapper(ProductPriceMapper.class);
    ProductStockMapper stockMapper = sqlSession.getMapper(ProductStockMapper.class);
    ProductDetailMapper ProductDetailMapper = sqlSession.getMapper(ProductDetailMapper.class);
    Product Product= new Product();
    
    public Pager findByPage(ProductStock product,Pager pager){
		Map<String,Object> params = new HashMap<>();
		params.put("product",product);
		int recordCount = stockMapper.count(params);
		pager.setRowCount(recordCount);
		if(recordCount>0){
			params.put("pageModel", pager);
		}
		List<ProductStock> list = stockMapper.selectByPage(params);
		pager.setResultList(list);
		sqlSession.close();
		return pager;
	}
    
    public List<ProductStock> findAllProduct(String userId){
		
		List<ProductStock> Products = stockMapper.selectAllProduct(userId);
	
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
    
    public List<ProductStock> findByProduct(ProductStock product){
    	Map<String,Object> params = new HashMap<>();
		params.put("product",product);
		List<ProductStock> list = stockMapper.selectByParam(params);
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
    
	public int saveProduct(Product product){
    	int result = 0;
    	try {
	    	if(ProductMapper.selectByProductId(product.getProductId())==null){
			 ProductMapper.save(product);
			 ProductPrice pp = new ProductPrice();
			 pp.setPid(product.getId());
			 pp.setProductId(product.getProductId());
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
