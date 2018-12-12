package com.ssm.mapper;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;

import com.ssm.dao.provider.ProductDynaSqlProvider;
import com.ssm.dao.provider.ProductPriceDynaSqlProvider;
import com.ssm.pojo.ProductPrice;
import com.ssm.utils.Constants;

public interface ProductPriceMapper {
		
	@Select("select * from "+Constants.PRODUCTTABLE+" where id=#{id}")
	@Results(id="priceMap",value={
			@Result(id=true,column="id",property="id",javaType=Integer.class),
			@Result(column="p_id", property="pid"),
			@Result(column="product_id", property="productId"),
			@Result(column="price1", property="price1"),
			@Result(column="price2", property="price2"),
			@Result(column="price3", property="price3"),
			@Result(column="price4", property="price4"),
			@Result(column="price5", property="price5"),
			@Result(column="price6", property="price6"),
			@Result(column="price7", property="price7"),
			@Result(column="cash1", property="cash1"),
			@Result(column="cash2", property="cash2"),
			@Result(column="cash3", property="cash3"),
			@Result(column="cash4", property="cash4"),
			@Result(column="cash5", property="cash5"),
			@Result(column="cash6", property="cash6"),
			@Result(column="cash7", property="cash7"),
			@Result(column="cash_num_1", property="cashNum1"),
			@Result(column="cash_num_2", property="cashNum2"),
			@Result(column="cash_num_3", property="cashNum3"),
			@Result(column="cash_num_4", property="cashNum4"),
			@Result(column="cash_num_5", property="cashNum5"),
			@Result(column="cash_num_6", property="cashNum6"),
			@Result(column="cash_num_7", property="cashNum7"),
			@Result(column="integral1", property="integral1"),
			@Result(column="integral2", property="integral2"),
			@Result(column="integral3", property="integral3"),
			@Result(column="integral4", property="integral4"),
			@Result(column="integral5", property="integral5"),
			@Result(column="integral6", property="integral6"),
			@Result(column="integral7", property="integral7"),
			@Result(column="entry_time", property="entryTime"),
			@Result(column="end_time", property="endTime")
		})
	ProductPrice selectById(Integer id);
	
	@Select("select * from "+Constants.PRODUCTPRICETABLE+" where product_id=#{name}")
	@ResultMap("priceMap")
	ProductPrice selectByProductId(String name);
	
	@Select("select * from "+Constants.PRODUCTPRICETABLE+" where p_id=#{pid}")
	@ResultMap("priceMap")
	ProductPrice selectByPid(Integer pid);
	
	@SelectProvider(type=ProductPriceDynaSqlProvider.class,method="selectAllByProductId")
	ProductPrice selectAllByProductId(String productId);
	
	@SelectProvider(type=ProductPriceDynaSqlProvider.class,method="selectAllByPid")
	ProductPrice selectAllByPid(Integer id);
	
	@SelectProvider(type=ProductPriceDynaSqlProvider.class,method="selectAllById")
	ProductPrice selectAllById(Integer id);
	
	@Delete("delete from "+Constants.PRODUCTPRICETABLE+" where id=#{id}")
	int deleteById(Integer id);
	
	@UpdateProvider(type=ProductPriceDynaSqlProvider.class,method="update")
	int update(ProductPrice product);
	
	
	
	@InsertProvider(type=ProductPriceDynaSqlProvider.class,method="insert")
	@Options(useGeneratedKeys=true, keyProperty="id")
	int save(ProductPrice product);
	
}
