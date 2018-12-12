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

import com.ssm.dao.provider.ProductDynaSqlProvider;
import com.ssm.pojo.Product;
import com.ssm.utils.Constants;

public interface ProductMapper {
		
	@SelectProvider(type=ProductDynaSqlProvider.class,method="selectWhitParam")
	@Results(id="productMap",value={
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="product_id", property="productId"),
		@Result(column="product_name", property="productName"),
		@Result(column="product_type", property="productType"),
		@Result(column="product_sort", property="productSort"),
		@Result(column="specification", property="specification"),
		@Result(column="features", property="features"),
		@Result(column="state", property="state"),
		@Result(column="type", property="type"),
		@Result(column="price", property="price"),
		@Result(column="price1", property="price1"),
		@Result(column="cash_num", property="cashNum"),
		@Result(column="cash", property="cash"),
		@Result(column="integral", property="integral"),
		@Result(column="num", property="num"),
		@Result(column="total_num", property="totalNum"),
		@Result(column="is_hide", property="isHide"),
		@Result(column="entry_time", property="entryTime"),
		@Result(column="end_time", property="endTime"),
		@Result(column="image_url", property="imageUrl"),
		@Result(column="image_url_1", property="imageUrl1"),
		@Result(column="image_url_2", property="imageUrl2"),
		@Result(column="image_url_3", property="imageUrl3"),
		@Result(column="image_url_4", property="imageUrl4")
	})
	List<Product> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=ProductDynaSqlProvider.class,method="selectListWhitParam")
	@ResultMap("productMap")
	List<Product> selectByParam(Map<String,Object> params);
	
	@SelectProvider(type=ProductDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@Select("select * from "+Constants.PRODUCTTABLE+" where sate=1")
	@ResultMap("productMap")
	List<Product> selectAllProduct();
	
	
	
	@Select("select max(id) from "+Constants.PRODUCTTABLE+"")
	Integer maxId();
	
	
	@Select("select * from "+Constants.PRODUCTTABLE+" where id=#{id}")
	@ResultMap("productMap")
	Product selectById(Integer id);
	
	@Select("select * from "+Constants.PRODUCTTABLE+" where product_id=#{name}")
	@ResultMap("productMap")
	Product selectByProductId(String name);
	
	@Delete("delete from "+Constants.PRODUCTTABLE+" where id=#{id}")
	int deleteById(Integer id);
	
	@UpdateProvider(type=ProductDynaSqlProvider.class,method="updateProduct")
	int update(Product product);
	
	@UpdateProvider(type=ProductDynaSqlProvider.class,method="updateNum")
	int updateNum(String productId,Integer num);
	
	@UpdateProvider(type=ProductDynaSqlProvider.class,method="updateSubNum")
	Integer updateSubNum(String productId,Integer num);
	
	
	@InsertProvider(type=ProductDynaSqlProvider.class,method="insertProduct")
	@Options(useGeneratedKeys=true, keyProperty="id")
	Integer save(Product product);
	
	@Select("selelct max(id) from "+Constants.PRODUCTTABLE+"")
	int max();

}
