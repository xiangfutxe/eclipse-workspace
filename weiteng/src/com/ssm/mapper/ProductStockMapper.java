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

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import com.ssm.dao.provider.ProductDynaSqlProvider;
import com.ssm.dao.provider.ProductStockDynaSqlProvider;
import com.ssm.pojo.Product;
import com.ssm.pojo.ProductStock;
import com.ssm.utils.Constants;

public interface ProductStockMapper {
		
	@SelectProvider(type=ProductStockDynaSqlProvider.class,method="selectWhitParam")
	@Results(id="productMap",value={
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="pid", property="pid"),
		@Result(column="product_id", property="productId"),
		@Result(column="product_name", property="productName"),
		@Result(column="specification", property="specification"),
		@Result(column="uid", property="uid"),
		@Result(column="user_id", property="userId"),
		@Result(column="user_name", property="userName"),
		@Result(column="price", property="price"),
		@Result(column="num", property="num"),
		@Result(column="total_num", property="totalNum"),
		@Result(column="image_url", property="imageUrl"),
		@Result(column="entry_time", property="entryTime"),
		@Result(column="end_time", property="endTime"),
	})
	List<ProductStock> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=ProductStockDynaSqlProvider.class,method="selectListWhitParam")
	@ResultMap("productMap")
	List<ProductStock> selectByParam(Map<String,Object> params);
	
	@SelectProvider(type=ProductStockDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@Select("select * from "+Constants.PRODUCTSTOCKTABLE+" where user_id=#{userId}")
	@ResultMap("productMap")
	List<ProductStock> selectAllProduct(String userId);
	
	@Select("select max(id) from "+Constants.PRODUCTSTOCKTABLE+"")
	Integer maxId();
	
	
	@Select("select * from "+Constants.PRODUCTSTOCKTABLE+" where id=#{id}")
	@ResultMap("productMap")
	ProductStock selectById(Integer id);
	
	@Select("select * from "+Constants.PRODUCTSTOCKTABLE+" where id=#{id} for update")
	@ResultMap("productMap")
	ProductStock selectByIdForUpdate(Integer id);
	
	@Select("select * from "+Constants.PRODUCTSTOCKTABLE+" where product_id=#{productId} and user_id=#{userId}")
	@ResultMap("productMap")
	ProductStock selectByProductId(@Param("productId") String productId,@Param("userId")String userId);
	
	@Delete("delete from "+Constants.PRODUCTSTOCKTABLE+" where id=#{id}")
	int deleteById(Integer id);
	
	@UpdateProvider(type=ProductStockDynaSqlProvider.class,method="update")
	int update(ProductStock product);
	
	@UpdateProvider(type=ProductStockDynaSqlProvider.class,method="updateNum")
	int updateNum(String productId,String userId,Integer num);
	
	@UpdateProvider(type=ProductStockDynaSqlProvider.class,method="updateSubNum")
	Integer updateSubNum(String productId,String userId,Integer num);
	
	
	@InsertProvider(type=ProductStockDynaSqlProvider.class,method="insert")
	@Options(useGeneratedKeys=true, keyProperty="id")
	int save(ProductStock product);
	
	@Select("selelct max(id) from "+Constants.PRODUCTSTOCKTABLE+"")
	int max();

}
