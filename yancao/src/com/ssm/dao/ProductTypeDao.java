package com.ssm.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ssm.mapper.ProductTypeMapper;
import com.ssm.pojo.ProductType;
import com.ssm.utils.Pager;
import com.ssm.utils.SqlSessionFactoryUtils;

public class ProductTypeDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
    ProductTypeMapper ProductTypeMapper = sqlSession.getMapper(ProductTypeMapper.class);
    ProductType ProductType= new ProductType();
    
    public Pager findProductType(ProductType ProductType,Pager pager){
		Map<String,Object> params = new HashMap<>();
		params.put("ProductType",ProductType);
		int recordCount = ProductTypeMapper.count(params);
		pager.setRowCount(recordCount);
		if(recordCount>0){
			params.put("pageModel", pager);
		}
		List<ProductType> ProductTypes = ProductTypeMapper.selectByPage(params);
		pager.setResultList(ProductTypes);
		sqlSession.close();
		return pager;
	}
    
    public List<ProductType> findAllProductType(){
		
		List<ProductType> ProductTypes = ProductTypeMapper.selectAllProductType();
	
		sqlSession.close();
		return ProductTypes;
	}
    
    public ProductType findProductTypeByName(String name){
		ProductType ProductType = ProductTypeMapper.selectByName(name);
		sqlSession.close();
		return ProductType;
	}
    
    public ProductType findProductTypeById(Integer id){
		ProductType ProductType = ProductTypeMapper.selectById(id);
		sqlSession.close();
		return ProductType;
	}
    
    public void deleteAll(String[] ids){
    	
    	try {
    		for(String id:ids){
    			ProductTypeMapper.deleteById(Integer.valueOf(id));
    		}
    		sqlSession.commit();
    	} finally {
    		sqlSession.close();
        }
	}
    
    public void updateProductType(ProductType ProductType){
    	try {
		 ProductTypeMapper.update(ProductType);
		 sqlSession.commit();

    	} finally {
    		sqlSession.close();
        }
	}
    
	public String saveProductType(ProductType ProductType){
    	String str = "";
    	try {
	    	if(ProductTypeMapper.selectByName(ProductType.getTypeName())==null){
			 ProductTypeMapper.save(ProductType);
			 sqlSession.commit();
			str =ProductType.getTypeName()+"信息保存成功。";
	    	}else{
	    		str ="该分类已经存在。";
	    	}
    	}finally {
    		sqlSession.close();
        }
    	return str;
	}
}
