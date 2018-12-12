package com.ssm.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ssm.mapper.ProductSortMapper;
import com.ssm.pojo.ProductSort;
import com.ssm.utils.Pager;
import com.ssm.utils.SqlSessionFactoryUtils;

public class ProductSortDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
    ProductSortMapper ProductSortMapper = sqlSession.getMapper(ProductSortMapper.class);
    ProductSort ProductSort= new ProductSort();
    
    public Pager findProductSort(ProductSort ProductSort,Pager pager){
		Map<String,Object> params = new HashMap<>();
		params.put("productSort",ProductSort);
		int recordCount = ProductSortMapper.count(params);
		pager.setRowCount(recordCount);
		if(recordCount>0){
			params.put("pageModel", pager);
		}
		List<ProductSort> ProductSorts = ProductSortMapper.selectByPage(params);
		pager.setResultList(ProductSorts);
		sqlSession.close();
		return pager;
	}
    
    public List<ProductSort> findAllProductSort(){
		
		List<ProductSort> ProductSorts = ProductSortMapper.selectAllProductSort();
	
		sqlSession.close();
		return ProductSorts;
	}
    
    public ProductSort findProductSortByName(String name){
		ProductSort ProductSort = ProductSortMapper.selectByName(name);
		sqlSession.close();
		return ProductSort;
	}
    
    public ProductSort findProductSortById(Integer id){
		ProductSort ProductSort = ProductSortMapper.selectById(id);
		sqlSession.close();
		return ProductSort;
	}
    
    public void deleteAll(String[] idArray){
    	
    	try {
    		for(String id:idArray){
    			ProductSortMapper.deleteById(Integer.valueOf(id));
    		}
    		sqlSession.commit();
    	} finally {
    		sqlSession.close();
        }
	}
    
    public void updateProductSort(ProductSort ProductSort){
    	try {
		 ProductSortMapper.update(ProductSort);
		 sqlSession.commit();

    	} finally {
    		sqlSession.close();
        }
	}
    
	public String saveProductSort(ProductSort ProductSort){
    	String str = "";
    	try {
	    	if(ProductSortMapper.selectByName(ProductSort.getName())==null){
			 ProductSortMapper.save(ProductSort);
			 sqlSession.commit();
			str =ProductSort.getName()+"信息保存成功。";
	    	}else{
	    		str ="该分类已经存在。";
	    	}
    	}finally {
    		sqlSession.close();
        }
    	return str;
	}
}
