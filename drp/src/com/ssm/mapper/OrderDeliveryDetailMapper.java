package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.ssm.dao.provider.OrderDeliveryDetailDynaSqlProvider;
import com.ssm.pojo.OrderDeliveryDetail;
import com.ssm.pojo.OrderDetail;
import com.ssm.utils.Constants;

public interface OrderDeliveryDetailMapper {

	
	@Select("select * from "+Constants.ORDERDELIVERYDETAILTABLE+" where orderId=#{orderId}")
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
		@Result(column="num", property="num")
	})
	List<OrderDeliveryDetail> selectByOrderId(String orderId);
	
	
	
	@Select("select * from "+Constants.ORDERDELIVERYDETAILTABLE+" where orderId=#{orderId} for update")
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
		@Result(column="num", property="num")
	})
	List<OrderDeliveryDetail> selectByOrderIdForUpdate(String orderId);
	
	@Select("select * from "+Constants.ORDERDELIVERYDETAILTABLE+" where id=#{id}")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="orderId", property="orderId"),
		@Result(column="to_order_id", property="toOrderId"),
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
		@Result(column="num", property="num")
	})
	OrderDeliveryDetail selectById(Integer id);
	
	@Delete("delete from "+Constants.ORDERDELIVERYDETAILTABLE+" where id=#{id}")
	Integer deleteById(Integer id);
	
	@Delete("delete from "+Constants.ORDERDELIVERYDETAILTABLE+" where orderId=#{orderId}")
	Integer deleteByOrderId(String orderId);
	
	@InsertProvider(type = OrderDeliveryDetailDynaSqlProvider.class, method = "insertAll")  
	Integer insertAll(Map<String,Object> params);
	
	@UpdateProvider(type = OrderDeliveryDetailDynaSqlProvider.class, method = "update")  
	Integer update(OrderDeliveryDetail orderDetail); 

}
