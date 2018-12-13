package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.ssm.dao.provider.ProductTypeDynaSqlProvider;
import com.ssm.pojo.ProductType;
import com.ssm.utils.Constants;

public interface ProductTypeMapper {
		
	@SelectProvider(type=ProductTypeDynaSqlProvider.class,method="selectWhitParam")
	List<ProductType> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=ProductTypeDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@Select("select * from "+Constants.PRODUCTTYPETABLE+" order by id asc ")
	List<ProductType> selectAllProductType();
	
	@Select("select * from "+Constants.PRODUCTTYPETABLE+" where id=#{id}")
	ProductType selectById(Integer id);
	
	@Select("select * from "+Constants.PRODUCTTYPETABLE+" where typeName=#{name}")
	ProductType selectByName(String name);
	
	@Delete("delete from "+Constants.PRODUCTTYPETABLE+" where id=#{id}")
	int deleteById(Integer id);
	
	@SelectProvider(type=ProductTypeDynaSqlProvider.class,method="updateProductType")
	void update(ProductType productType);
	
	@SelectProvider(type=ProductTypeDynaSqlProvider.class,method="insertProductType")
	void save(ProductType productType);

}
