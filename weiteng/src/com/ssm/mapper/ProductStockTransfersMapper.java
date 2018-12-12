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
import com.ssm.dao.provider.ProductStockTransfersDynaSqlProvider;
import com.ssm.pojo.Product;
import com.ssm.pojo.ProductStock;
import com.ssm.pojo.ProductStockTransfers;
import com.ssm.utils.Constants;

public interface ProductStockTransfersMapper {
		
	@SelectProvider(type=ProductStockTransfersDynaSqlProvider.class,method="selectWhitParam")
	@Results(id="productMap",value={
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="pid", property="pid"),
		@Result(column="product_id", property="productId"),
		@Result(column="product_name", property="productName"),
		@Result(column="specification", property="specification"),
		@Result(column="uid", property="uid"),
		@Result(column="user_id", property="userId"),
		@Result(column="user_name", property="userName"),
		@Result(column="uid1", property="uid1"),
		@Result(column="user_id_1", property="userId1"),
		@Result(column="user_name_1", property="userName1"),
		@Result(column="price", property="price"),
		@Result(column="num", property="num"),
		@Result(column="entry_time", property="entryTime"),
		@Result(column="end_time", property="endTime"),
	})
	List<ProductStockTransfers> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=ProductStockTransfersDynaSqlProvider.class,method="selectListWhitParam")
	@ResultMap("productMap")
	List<ProductStockTransfers> selectByParam(Map<String,Object> params);
	
	@SelectProvider(type=ProductStockTransfersDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@Select("select * from "+Constants.PRODUCTSTOCKTRANSFERSTABLE+" where user_id=#{userId}")
	@ResultMap("productMap")
	List<ProductStockTransfers> selectAllProduct(String userId);
	
	@Select("select max(id) from "+Constants.PRODUCTSTOCKTRANSFERSTABLE+"")
	Integer maxId();
	
	
	@Select("select * from "+Constants.PRODUCTSTOCKTRANSFERSTABLE+" where id=#{id}")
	@ResultMap("productMap")
	ProductStockTransfers selectById(Integer id);
	
	@Select("select * from "+Constants.PRODUCTSTOCKTRANSFERSTABLE+" where product_id=#{productId} and user_id=#{userId}")
	@ResultMap("productMap")
	ProductStockTransfers selectByProductId(@Param("productId") String productId,@Param("userId")String userId);
	
	@Delete("delete from "+Constants.PRODUCTSTOCKTRANSFERSTABLE+" where id=#{id}")
	int deleteById(Integer id);
	
	@UpdateProvider(type=ProductStockTransfersDynaSqlProvider.class,method="update")
	int update(ProductStockTransfers product);
	
	
	@InsertProvider(type=ProductStockTransfersDynaSqlProvider.class,method="insert")
	@Options(useGeneratedKeys=true, keyProperty="id")
	int save(ProductStockTransfers product);
	
	@Select("selelct max(id) from "+Constants.PRODUCTSTOCKTABLE+"")
	int max();

}
