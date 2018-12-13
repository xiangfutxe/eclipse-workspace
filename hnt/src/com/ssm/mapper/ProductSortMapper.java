package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.ssm.dao.provider.ProductSortDynaSqlProvider;
import com.ssm.pojo.ProductSort;
import com.ssm.utils.Constants;

public interface ProductSortMapper {
		
	@SelectProvider(type=ProductSortDynaSqlProvider.class,method="selectWhitParam")
	List<ProductSort> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=ProductSortDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@Select("select * from "+Constants.PRODUCTSORTTABLE+" order by id asc ")
	List<ProductSort> selectAllProductSort();
	
	@Select("select * from "+Constants.PRODUCTSORTTABLE+" where id=#{id}")
	ProductSort selectById(Integer id);
	
	@Select("select * from "+Constants.PRODUCTSORTTABLE+" where name=#{name}")
	ProductSort selectByName(String name);
	
	@Delete("delete from "+Constants.PRODUCTSORTTABLE+" where id=#{id}")
	int deleteById(Integer id);
	
	@SelectProvider(type=ProductSortDynaSqlProvider.class,method="updateProductSort")
	void update(ProductSort ProductSort);
	
	@SelectProvider(type=ProductSortDynaSqlProvider.class,method="insertProductSort")
	void save(ProductSort ProductSort);

}
