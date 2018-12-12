package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.ssm.dao.provider.ProductTypeDynaSqlProvider;
import com.ssm.pojo.ProductType;
import com.ssm.utils.Constants;

public interface ProductTypeMapper {
		
	@SelectProvider(type=ProductTypeDynaSqlProvider.class,method="selectWhitParam")
	@Results(id="typeMap",value={
			@Result(id=true,column="id",property="id",javaType=Integer.class),
			@Result(column="type_name", property="typeName"),
			@Result(column="state", property="state"),
			@Result(column="entry_time", property="entryTime"),
			@Result(column="end_time", property="endTime")
		})
	List<ProductType> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=ProductTypeDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@Select("select * from "+Constants.PRODUCTTYPETABLE+" order by id asc ")
	@ResultMap("typeMap")
	List<ProductType> selectAllProductType();
	
	@Select("select * from "+Constants.PRODUCTTYPETABLE+" where id=#{id}")
	@ResultMap("typeMap")
	ProductType selectById(Integer id);
	
	@Select("select * from "+Constants.PRODUCTTYPETABLE+" where type_name=#{name}")
	@ResultMap("typeMap")
	ProductType selectByName(String name);
	
	@Delete("delete from "+Constants.PRODUCTTYPETABLE+" where id=#{id}")
	int deleteById(Integer id);
	
	@SelectProvider(type=ProductTypeDynaSqlProvider.class,method="updateProductType")
	void update(ProductType productType);
	
	@SelectProvider(type=ProductTypeDynaSqlProvider.class,method="insertProductType")
	void save(ProductType productType);

}
