package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import org.apache.ibatis.annotations.InsertProvider;


import com.ssm.dao.provider.ProductDynaSqlProvider;
import com.ssm.pojo.Product;
import com.ssm.utils.Constants;

public interface ProductMapper {
		
	@SelectProvider(type=ProductDynaSqlProvider.class,method="selectWhitParam")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="productId", property="productId"),
		@Result(column="productName", property="productName"),
		@Result(column="productType", property="productType"),
		@Result(column="specification", property="specification"),
		@Result(column="features", property="features"),
		@Result(column="state", property="state"),
		@Result(column="price", property="price"),
		@Result(column="pv", property="pv"),
		@Result(column="price_cy", property="priceCy"),
		@Result(column="pv_cy", property="pvCy"),
		@Result(column="num", property="num"),
		@Result(column="totalNum", property="totalNum"),
		@Result(column="is_hide", property="isHide"),
		@Result(column="give_num", property="giveNum"),
		@Result(column="give_type", property="giveType"),
		@Result(column="give_id", property="giveId"),
		@Result(column="give_product_id", property="giveProductId"),
		@Result(column="give_product_name", property="giveProductName"),
		@Result(column="max_num", property="maxNum"),
		@Result(column="type", property="type"),
		@Result(column="entryTime", property="entryTime"),
		@Result(column="endTime", property="endTime"),
		@Result(column="imageUrl", property="imageUrl")
	})
	List<Product> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=ProductDynaSqlProvider.class,method="selectListWhitParam")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="productId", property="productId"),
		@Result(column="productName", property="productName"),
		@Result(column="productType", property="productType"),
		@Result(column="specification", property="specification"),
		@Result(column="features", property="features"),
		@Result(column="state", property="state"),
		@Result(column="price", property="price"),
		@Result(column="pv", property="pv"),
		@Result(column="price_cy", property="priceCy"),
		@Result(column="pv_cy", property="pvCy"),
		@Result(column="num", property="num"),
		@Result(column="totalNum", property="totalNum"),
		@Result(column="is_hide", property="isHide"),
		@Result(column="give_num", property="giveNum"),
		@Result(column="give_type", property="giveType"),
		@Result(column="give_id", property="giveId"),
		@Result(column="give_product_id", property="giveProductId"),
		@Result(column="give_product_name", property="giveProductName"),
		@Result(column="max_num", property="maxNum"),
		@Result(column="type", property="type"),
		@Result(column="entryTime", property="entryTime"),
		@Result(column="endTime", property="endTime"),
		@Result(column="imageUrl", property="imageUrl")
	})
	List<Product> selectByParam(Map<String,Object> params);
	
	@SelectProvider(type=ProductDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@Select("select * from "+Constants.PRODUCTTABLE+"")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="productId", property="productId"),
		@Result(column="productName", property="productName"),
		@Result(column="productType", property="productType"),
		@Result(column="specification", property="specification"),
		@Result(column="features", property="features"),
		@Result(column="state", property="state"),
		@Result(column="price", property="price"),
		@Result(column="pv", property="pv"),
		@Result(column="price_cy", property="priceCy"),
		@Result(column="pv_cy", property="pvCy"),
		@Result(column="num", property="num"),
		@Result(column="totalNum", property="totalNum"),
		@Result(column="is_hide", property="isHide"),
		@Result(column="give_num", property="giveNum"),
		@Result(column="max_num", property="maxNum"),
		@Result(column="give_type", property="giveType"),
		@Result(column="give_id", property="giveId"),
		@Result(column="give_product_id", property="giveProductId"),
		@Result(column="give_product_name", property="giveProductName"),
		@Result(column="type", property="type"),
		@Result(column="entryTime", property="entryTime"),
		@Result(column="endTime", property="endTime"),
		@Result(column="imageUrl", property="imageUrl")
	})
	List<Product> selectAllProduct();
	
	@Select("select * from "+Constants.PRODUCTTABLE+" where type=#{type}")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="productId", property="productId"),
		@Result(column="productName", property="productName"),
		@Result(column="productType", property="productType"),
		@Result(column="specification", property="specification"),
		@Result(column="features", property="features"),
		@Result(column="state", property="state"),
		@Result(column="price", property="price"),
		@Result(column="pv", property="pv"),
		@Result(column="price_cy", property="priceCy"),
		@Result(column="pv_cy", property="pvCy"),
		@Result(column="num", property="num"),
		@Result(column="totalNum", property="totalNum"),
		@Result(column="is_hide", property="isHide"),
		@Result(column="give_num", property="giveNum"),
		@Result(column="max_num", property="maxNum"),
		@Result(column="give_type", property="giveType"),
		@Result(column="give_id", property="giveId"),
		@Result(column="give_product_id", property="giveProductId"),
		@Result(column="give_product_name", property="giveProductName"),
		@Result(column="type", property="type"),
		@Result(column="entryTime", property="entryTime"),
		@Result(column="endTime", property="endTime"),
		@Result(column="imageUrl", property="imageUrl")
	})
	List<Product> selectByType(Integer type);
	
	@Select("select max(id) from "+Constants.PRODUCTTABLE+"")
	Integer maxId();
	
	
	@Select("select * from "+Constants.PRODUCTTABLE+" where id=#{id}")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="productId", property="productId"),
		@Result(column="productName", property="productName"),
		@Result(column="productType", property="productType"),
		@Result(column="specification", property="specification"),
		@Result(column="features", property="features"),
		@Result(column="state", property="state"),
		@Result(column="price", property="price"),
		@Result(column="pv", property="pv"),
		@Result(column="price_cy", property="priceCy"),
		@Result(column="pv_cy", property="pvCy"),
		@Result(column="num", property="num"),
		@Result(column="totalNum", property="totalNum"),
		@Result(column="is_hide", property="isHide"),
		@Result(column="give_num", property="giveNum"),
		@Result(column="max_num", property="maxNum"),
		@Result(column="give_type", property="giveType"),
		@Result(column="give_id", property="giveId"),
		@Result(column="give_product_id", property="giveProductId"),
		@Result(column="give_product_name", property="giveProductName"),
		@Result(column="type", property="type"),
		@Result(column="entryTime", property="entryTime"),
		@Result(column="endTime", property="endTime"),
		@Result(column="imageUrl", property="imageUrl")
	})
	Product selectById(Integer id);
	
	@Select("select * from "+Constants.PRODUCTTABLE+" where productId=#{name}")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="productId", property="productId"),
		@Result(column="productName", property="productName"),
		@Result(column="productType", property="productType"),
		@Result(column="specification", property="specification"),
		@Result(column="features", property="features"),
		@Result(column="state", property="state"),
		@Result(column="price", property="price"),
		@Result(column="pv", property="pv"),
		@Result(column="price_cy", property="priceCy"),
		@Result(column="pv_cy", property="pvCy"),
		@Result(column="num", property="num"),
		@Result(column="totalNum", property="totalNum"),
		@Result(column="is_hide", property="isHide"),
		@Result(column="give_num", property="giveNum"),
		@Result(column="max_num", property="maxNum"),
		@Result(column="give_type", property="giveType"),
		@Result(column="give_id", property="giveId"),
		@Result(column="give_product_id", property="giveProductId"),
		@Result(column="give_product_name", property="giveProductName"),
		@Result(column="type", property="type"),
		@Result(column="entryTime", property="entryTime"),
		@Result(column="endTime", property="endTime"),
		@Result(column="imageUrl", property="imageUrl")
	})
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
	int save(Product product);
	
	@Select("selelct max(id) from "+Constants.PRODUCTTABLE+"")
	int max();

}
