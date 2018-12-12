package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.ssm.dao.provider.ProductSortDynaSqlProvider;
import com.ssm.pojo.ProductSort;
import com.ssm.utils.Constants;

public interface ProductSortMapper {
		
	@SelectProvider(type=ProductSortDynaSqlProvider.class,method="selectWhitParam")
	@Results(id="sortMap",value={
			@Result(id=true,column="id",property="id",javaType=Integer.class),
			@Result(column="name", property="name"),
			@Result(column="state", property="state"),
			@Result(column="entry_time", property="entryTime"),
			@Result(column="end_time", property="endTime")
		})
	List<ProductSort> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=ProductSortDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@Select("select * from "+Constants.PRODUCTSORTTABLE+" order by id asc ")
	@ResultMap("sortMap")
	List<ProductSort> selectAllProductSort();
	
	@Select("select * from "+Constants.PRODUCTSORTTABLE+" where id=#{id}")
	@ResultMap("sortMap")
	ProductSort selectById(Integer id);
	
	@Select("select * from "+Constants.PRODUCTSORTTABLE+" where name=#{name}")
	@ResultMap("sortMap")
	ProductSort selectByName(String name);
	
	@Delete("delete from "+Constants.PRODUCTSORTTABLE+" where id=#{id}")
	int deleteById(Integer id);
	
	@UpdateProvider(type=ProductSortDynaSqlProvider.class,method="updateProductSort")
	int update(ProductSort ProductSort);
	
	@UpdateProvider(type=ProductSortDynaSqlProvider.class,method="insertProductSort")
	int save(ProductSort ProductSort);

}
