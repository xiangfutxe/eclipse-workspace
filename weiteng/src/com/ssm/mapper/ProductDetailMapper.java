package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.InsertProvider;


import com.ssm.dao.provider.ProductDetailDynaSqlProvider;
import com.ssm.pojo.ProductDetail;
import com.ssm.utils.Constants;

public interface ProductDetailMapper {
		
	@SelectProvider(type=ProductDetailDynaSqlProvider.class,method="selectWhitParam")
	@Results(id="productMap",value={
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="pid", property="pid"),
		@Result(column="product_id", property="productId"),
		@Result(column="product_name", property="productName"),
		@Result(column="specification", property="specification"),
		@Result(column="productPrice", property="productPrice"),
		@Result(column="price", property="price"),
		@Result(column="num", property="num")
	})
	List<ProductDetail> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=ProductDetailDynaSqlProvider.class,method="selectListWhitParam")
	@ResultMap("productMap")
	List<ProductDetail> selectByParam(Map<String,Object> params);
	
	@SelectProvider(type=ProductDetailDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@Select("select * from "+Constants.PRODUCTDETAILTABLE+" ")
	@ResultMap("productMap")
	List<ProductDetail> selectAllProductDetail();
	
	
	@Select("select * from "+Constants.PRODUCTDETAILTABLE+" where id=#{id}")
	@ResultMap("productMap")
	ProductDetail selectById(Integer id);
	
	@Select("select * from "+Constants.PRODUCTDETAILTABLE+" where pid=#{name}")
	@ResultMap("productMap")
	List<ProductDetail> selectByProductId(String name);
	
	@Delete("delete from "+Constants.PRODUCTDETAILTABLE+" where id=#{id}")
	int deleteById(Integer id);
	
	@SelectProvider(type=ProductDetailDynaSqlProvider.class,method="updateProductDetail")
	void update(ProductDetail productDetail);
	
	@InsertProvider(type=ProductDetailDynaSqlProvider.class,method="insertProductDetail")
	Integer save(ProductDetail productDetail);
	
	@InsertProvider(type = ProductDetailDynaSqlProvider.class, method = "insertAll")  
	Integer insertAll(Map<String,Object> params); 

}
