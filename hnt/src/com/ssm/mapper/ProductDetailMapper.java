package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.InsertProvider;


import com.ssm.dao.provider.ProductDetailDynaSqlProvider;
import com.ssm.pojo.ProductDetail;
import com.ssm.utils.Constants;

public interface ProductDetailMapper {
		
	@SelectProvider(type=ProductDetailDynaSqlProvider.class,method="selectWhitParam")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="productId", property="productId"),
		@Result(column="productName", property="productName"),
		@Result(column="productType", property="productType"),
		@Result(column="specification", property="specification"),
		@Result(column="pId", property="pId"),
		@Result(column="p_id", property="p_id"),
		@Result(column="productPrice", property="productPrice"),
		@Result(column="productPv", property="productPv"),
		@Result(column="price", property="price"),
		@Result(column="pv", property="pv"),
		@Result(column="price_cy", property="priceCy"),
		@Result(column="pv_cy", property="pvCy"),
		@Result(column="num", property="num")
	})
	List<ProductDetail> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=ProductDetailDynaSqlProvider.class,method="selectListWhitParam")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="productId", property="productId"),
		@Result(column="productName", property="productName"),
		@Result(column="productType", property="productType"),
		@Result(column="specification", property="specification"),
		@Result(column="pId", property="pId"),
		@Result(column="p_id", property="p_id"),
		@Result(column="productPrice", property="productPrice"),
		@Result(column="productPv", property="productPv"),
		@Result(column="price", property="price"),
		@Result(column="pv", property="pv"),
		@Result(column="price_cy", property="priceCy"),
		@Result(column="pv_cy", property="pvCy"),
		@Result(column="num", property="num"),
		@Result(column="imageUrl", property="imageUrl")
	})
	List<ProductDetail> selectByParam(Map<String,Object> params);
	
	@SelectProvider(type=ProductDetailDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@Select("select * from "+Constants.PRODUCTDETAILTABLE+" ")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="productId", property="productId"),
		@Result(column="productName", property="productName"),
		@Result(column="productType", property="productType"),
		@Result(column="specification", property="specification"),
		@Result(column="pId", property="pId"),
		@Result(column="p_id", property="p_id"),
		@Result(column="productPrice", property="productPrice"),
		@Result(column="productPv", property="productPv"),
		@Result(column="price", property="price"),
		@Result(column="pv", property="pv"),
		@Result(column="price_cy", property="priceCy"),
		@Result(column="pv_cy", property="pvCy"),
		@Result(column="num", property="num"),
		@Result(column="imageUrl", property="imageUrl")
	})
	List<ProductDetail> selectAllProductDetail();
	
	
	@Select("select * from "+Constants.PRODUCTDETAILTABLE+" where id=#{id}")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="productId", property="productId"),
		@Result(column="productName", property="productName"),
		@Result(column="productType", property="productType"),
		@Result(column="specification", property="specification"),
		@Result(column="pId", property="pId"),
		@Result(column="p_id", property="p_id"),
		@Result(column="productPrice", property="productPrice"),
		@Result(column="productPv", property="productPv"),
		@Result(column="price", property="price"),
		@Result(column="pv", property="pv"),
		@Result(column="price_cy", property="priceCy"),
		@Result(column="pv_cy", property="pvCy"),
		@Result(column="num", property="num"),
		@Result(column="imageUrl", property="imageUrl")
	})
	ProductDetail selectById(Integer id);
	
	@Select("select * from "+Constants.PRODUCTDETAILTABLE+" where pId=#{name}")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="productId", property="productId"),
		@Result(column="productName", property="productName"),
		@Result(column="productType", property="productType"),
		@Result(column="specification", property="specification"),
		@Result(column="pId", property="pId"),
		@Result(column="p_id", property="p_id"),
		@Result(column="productPrice", property="productPrice"),
		@Result(column="productPv", property="productPv"),
		@Result(column="price", property="price"),
		@Result(column="pv", property="pv"),
		@Result(column="num", property="num"),
		@Result(column="imageUrl", property="imageUrl")
	})
	List<ProductDetail> selectByProductId(String name);
	
	@Delete("delete from "+Constants.PRODUCTDETAILTABLE+" where id=#{id}")
	int deleteById(Integer id);
	
	@SelectProvider(type=ProductDetailDynaSqlProvider.class,method="updateProductDetail")
	void update(ProductDetail productDetail);
	
	@SelectProvider(type=ProductDetailDynaSqlProvider.class,method="insertProductDetail")
	void save(ProductDetail productDetail);
	
	@InsertProvider(type = ProductDetailDynaSqlProvider.class, method = "insertAll")  
	void insertAll(Map<String,Object> params); 

}
