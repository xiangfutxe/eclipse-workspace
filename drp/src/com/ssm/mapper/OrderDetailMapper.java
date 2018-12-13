package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.ssm.dao.provider.OrderDetailDynaSqlProvider;
import com.ssm.pojo.OrderDetail;
import com.ssm.utils.Constants;

public interface OrderDetailMapper {

	
	@Select("select * from "+Constants.ORDERDETAILTABLE+" where orderId=#{orderId}")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="orderId", property="orderId"),
		@Result(column="pid", property="pid"),
		@Result(column="productId", property="productId"),
		@Result(column="productName", property="productName"),
		@Result(column="productType", property="productType"),
		@Result(column="specification", property="specification"),
		@Result(column="type", property="type"),
		@Result(column="productPrice", property="productPrice"),
		@Result(column="productPv", property="productPv"),
		@Result(column="price", property="price"),
		@Result(column="pv", property="pv"),
		@Result(column="price_cy", property="priceCy"),
		@Result(column="pv_cy", property="pvCy"),
		@Result(column="total_price_cy", property="TotalPriceCy"),
		@Result(column="total_pv_cy", property="TotalPvCy"),
		@Result(column="num", property="num")
	})
	List<OrderDetail> selectByOrderId(String orderId);
	
	@Select("select * from "+Constants.ORDERDETAILTABLE+" where id=#{id}")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="orderId", property="orderId"),
		@Result(column="pid", property="pid"),
		@Result(column="productId", property="productId"),
		@Result(column="productName", property="productName"),
		@Result(column="productType", property="productType"),
		@Result(column="specification", property="specification"),
		@Result(column="type", property="type"),
		@Result(column="productPrice", property="productPrice"),
		@Result(column="productPv", property="productPv"),
		@Result(column="price", property="price"),
		@Result(column="pv", property="pv"),
		@Result(column="price_cy", property="priceCy"),
		@Result(column="pv_cy", property="pvCy"),
		@Result(column="total_price_cy", property="TotalPriceCy"),
		@Result(column="total_pv_cy", property="TotalPvCy"),
		@Result(column="num", property="num")
	})
	OrderDetail selectById(Integer id);
	
	@Delete("delete from "+Constants.ORDERDETAILTABLE+" where id=#{id}")
	int deleteById(Integer id);
	
	@Delete("delete from "+Constants.ORDERDETAILTABLE+" where orderId=#{orderId}")
	int deleteByOrderId(String orderId);
	
	@InsertProvider(type=OrderDetailDynaSqlProvider.class,method="insertOrderDetail")
	void save(OrderDetail orderDetail);
	
	@UpdateProvider(type=OrderDetailDynaSqlProvider.class,method="UpdateOrderDetail")
	Integer Update(OrderDetail orderDetail);
	
	@InsertProvider(type = OrderDetailDynaSqlProvider.class, method = "insertAll")  
	Integer insertAll(Map<String,Object> params); 

}
